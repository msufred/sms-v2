package com.github.msufred.sms.views;

import com.github.msufred.sms.AppMain;
import com.github.msufred.sms.Settings;
import com.github.msufred.sms.data.Database;
import com.github.msufred.sms.data.User;
import com.github.msufred.sms.data.controllers.ScheduleController;
import com.github.msufred.sms.data.controllers.UserController;
import com.github.msufred.sms.views.icons.PesoIcon;
import com.github.msufred.sms.views.panels.*;
import io.github.msufred.feathericons.*;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.xml.transform.TransformerException;
import java.sql.SQLException;
import java.util.Objects;

public class MainWindow extends AbstractWindow {

    // Menus
    @FXML private MenuItem menuFilePreferences;
    @FXML private MenuItem menuFileClose;
    @FXML private MenuItem menuShowDeleted;
    @FXML private MenuItem menuShowHistory;
    @FXML private MenuItem menuHelpAbout;

    @FXML private MenuButton btnUser;
    @FXML private MenuItem btnEditInfo;
    @FXML private MenuItem btnLogout;

    // Sidebar
    @FXML private ToggleButton btnDashboard;
    @FXML private ToggleButton btnCustomers;
    @FXML private ToggleButton btnHotspots;
    @FXML private ToggleButton btnPayments;
    @FXML private ToggleButton btnInventory;
    @FXML private ToggleButton btnMaps;
    @FXML private ToggleButton btnTasks;
    @FXML private ToggleButton btnCashFlow;
    @FXML private StackPane contentPane;

    // Upcoming and Outdated Schedule Label
    @FXML private Label lblUpcoming;
    @FXML private Label lblOutdated;

    @FXML private ProgressBar progressBar;
    @FXML private Label progressLabel;

    private final AppMain appMain;
    private final Settings settings;
    private final Database database;
    private final UserController userController;
    private final ScheduleController scheduleController;
    private final CompositeDisposable disposables;

    private long userId = -1;
    private User user = null;
    private int pendingTasks = 0;
    private int outdatedTasks = 0;

    // panels
    private DashboardPanel dashboardPanel;
    private PaymentsPanel paymentsPanel;
    private AccountsPanel accountsPanel;
    private WiFiHotspotsPanel wiFiHotspotsPanel;
    private InventoryPanel inventoryPanel;
    private MapsPanel mapsPanel;
    private TasksPanel tasksPanel;
    private CashFlowPanel cashFlowPanel;
    private AbstractPanel mCurrPanel = null;

    // windows
    private EditUserWindow editUserWindow;
    private DeletedWindow deletedWindow;

    private boolean isLogout = false;

    public MainWindow(AppMain appMain, Settings settings, Database database, Stage stage) {
        super("SMS", MainWindow.class.getResource("main.fxml"), stage, null);
        this.appMain = appMain;
        this.settings = settings;
        this.database = database;
        this.userController = new UserController(database);
        this.scheduleController = new ScheduleController(database);
        this.disposables = new CompositeDisposable();
    }

    @Override
    protected void initWindow(Stage stage) {
        // boolean maximized = Boolean.parseBoolean(settings.get("system", "maximized"));
        stage.setMaximized(true);
        stage.getIcons().add(new Image(Objects.requireNonNull(MainWindow.class.getResourceAsStream("logo_v3.png"))));
    }

    @Override
    protected void onFxmlLoaded() {
        setupIcons();
        setupToggles(btnDashboard, btnPayments, btnCustomers, btnHotspots, btnInventory, btnMaps, btnTasks, btnCashFlow);

        menuFilePreferences.setOnAction(evt -> {
            // TODO
        });

        menuFileClose.setOnAction(evt -> close());
        menuShowDeleted.setOnAction(evt -> {
            if (deletedWindow == null) deletedWindow = new DeletedWindow(database, getStage());
            deletedWindow.show();
        });
        menuShowHistory.setOnAction(evt -> {
            // TODO
        });
        menuHelpAbout.setOnAction(evt -> {
            // TODO
        });

        btnEditInfo.setOnAction(evt -> {
            if (user != null) {
                if (editUserWindow == null) editUserWindow = new EditUserWindow(this, database);
                editUserWindow.show(user);
            }
        });

        btnLogout.setOnAction(evt -> {
            isLogout = true;
            close();
            appMain.showLoginWindow();
        });
    }

    public void setUserId(long id) {
        this.userId = id;
    }

    public void updateUser(int userId) {
        this.userId = userId;
        onShow();
    }

    @Override
    protected void onShow() {
        if (userId != -1) {
            showProgress(-1, "Retrieving user info...");
            disposables.add(Single.fromCallable(() -> userController.get((int) userId))
                    .flatMap(usr -> {
                        user = usr;
                        return Single.fromCallable(scheduleController::getOutdatedCount);
                    }).flatMap(count -> {
                        outdatedTasks = count;
                        return Single.fromCallable(scheduleController::getUpcomingCount);
                    }).subscribeOn(Schedulers.io()).observeOn(JavaFxScheduler.platform()).subscribe(count -> {
                        hideProgress();

                        // enable/disable actions depending on the user role
                        if (!user.getRole().equals("admin")) {
                            menuShowDeleted.setDisable(true);
                            menuShowHistory.setDisable(true);
                        }

                        pendingTasks = count;
                        if (user != null) {
                            btnUser.setText(user.getUsername());
                        }

                        lblOutdated.setText(outdatedTasks + "");
                        lblUpcoming.setText(pendingTasks + "");
                        setupPanels();
                    }, err -> {
                        hideProgress();
                        showErrorDialog("Database Error", "No User found with id: " + userId);
                    }));
        } else {
            setupPanels();
        }
    }

    private void setupToggles(ToggleButton...toggleButtons) {
        for (ToggleButton toggle : toggleButtons) {
            toggle.addEventFilter(MouseDragEvent.MOUSE_PRESSED, event -> {
                if (toggle.isSelected()) event.consume();
                else {
                    switch (toggle.getText()) {
                        case "Payments" -> changePanel(paymentsPanel);
                        case "Accounts" ->changePanel(accountsPanel);
                        case "WiFi Hotspots" -> changePanel(wiFiHotspotsPanel);
                        case "Services & Products" -> changePanel(inventoryPanel);
                        case "Maps" -> changePanel(mapsPanel);
                        case "Tasks & Schedules" -> changePanel(tasksPanel);
                        case "Cash Flow" -> changePanel(cashFlowPanel);
                        default -> changePanel(dashboardPanel);
                    }
                }
            });
        }
    }

    private void setupPanels() {
        if (dashboardPanel == null) dashboardPanel = new DashboardPanel(this, database);
        if (paymentsPanel == null) paymentsPanel = new PaymentsPanel(this, settings, database);
        if (accountsPanel == null) accountsPanel = new AccountsPanel(this, database);
        if (wiFiHotspotsPanel == null) wiFiHotspotsPanel = new WiFiHotspotsPanel(this, database);
        if (inventoryPanel == null) inventoryPanel = new InventoryPanel(this, database);
        if (mapsPanel == null) mapsPanel = new MapsPanel(this, database);
        if (tasksPanel == null) tasksPanel = new TasksPanel(this, database);
        if (cashFlowPanel == null) cashFlowPanel = new CashFlowPanel(this, database);
        changePanel(dashboardPanel);
    }

    private void changePanel(AbstractPanel panel) {
        if (panel == null) return;
        if (mCurrPanel != null && mCurrPanel != panel) {
            mCurrPanel.onPause();
        }
        contentPane.getChildren().clear();
        contentPane.getChildren().add(panel.getView());
        panel.onResume();
        mCurrPanel = panel;
    }

    private void setupIcons() {
        menuFilePreferences.setGraphic(new SettingsIcon(12));
        menuShowHistory.setGraphic(new TrashIcon(12));
        menuShowDeleted.setGraphic(new ClockIcon(12));
        menuHelpAbout.setGraphic(new HelpCircleIcon(12));

        btnUser.setGraphic(new UserIcon(14));
        btnEditInfo.setGraphic(new Edit2Icon(12));
        btnLogout.setGraphic(new LogOutIcon(12));

        btnDashboard.setGraphic(new GridIcon(14));
        btnPayments.setGraphic(new PesoIcon(14));
        btnCustomers.setGraphic(new UsersIcon(14));
        btnHotspots.setGraphic(new WifiIcon(14));
        btnInventory.setGraphic(new BoxIcon(14));
        btnMaps.setGraphic(new MapIcon(14));
        btnTasks.setGraphic(new TagIcon(14));
        btnCashFlow.setGraphic(new PesoIcon(14));
    }

    public void showProgress(double progress, String text) {
        progressBar.setProgress(progress);
        progressBar.setVisible(true);
        progressLabel.setText(text);
        progressLabel.setVisible(true);
    }

    public void hideProgress() {
        progressBar.setVisible(false);
        progressBar.setProgress(0);
        progressLabel.setVisible(false);
        progressLabel.setText("");
    }

    public void setOutdateCount(int count) {
        outdatedTasks = count;
        lblOutdated.setText(outdatedTasks + "");
    }

    public void setUpcomingCount(int count) {
        pendingTasks = count;
        lblUpcoming.setText(pendingTasks + "");
    }

    @Override
    protected void onClose() {
        if (mCurrPanel != null) mCurrPanel.onPause();

        if (!isLogout) {
            try {
                settings.save();
            } catch (TransformerException e) {
                System.err.println("failed to save settings.xml");
            }

            try {
                database.close();
            } catch (SQLException e) {
                System.err.println("failed to close database");
            }

            if (dashboardPanel != null) dashboardPanel.onDispose();
            if (paymentsPanel != null) paymentsPanel.onDispose();
            if (accountsPanel != null) accountsPanel.onDispose();
            if (wiFiHotspotsPanel != null) wiFiHotspotsPanel.onDispose();
            if (inventoryPanel != null) inventoryPanel.onDispose();
            if (mapsPanel != null) mapsPanel.onDispose();
            if (tasksPanel != null) tasksPanel.onDispose();
            if (cashFlowPanel != null) cashFlowPanel.onDispose();

            if (deletedWindow != null) deletedWindow.dispose();
            disposables.dispose();
        }

        userId = -1;
        user = null;
        isLogout = false;
    }

    public User getUser() {
        return user;
    }
}
