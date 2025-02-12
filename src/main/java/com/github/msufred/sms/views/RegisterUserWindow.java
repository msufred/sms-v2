package com.github.msufred.sms.views;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import com.github.msufred.sms.data.Database;
import com.github.msufred.sms.data.User;
import com.github.msufred.sms.data.controllers.UserController;

import java.util.Objects;

/**
 *
 * @author Gem
 */
public class RegisterUserWindow extends AbstractWindow {

    @FXML private TextField tfUsername;
    @FXML private TextField tfFullname;
    @FXML private TextField tfDesignation;
    @FXML private TextField tfPassword;
    @FXML private TextField tfRePassword;
    @FXML private Label lblError;
    @FXML private Button btnRegister;

    private final MainWindow mainWindow;
    private final UserController userController;
    private final CompositeDisposable disposables;

    public RegisterUserWindow(MainWindow mainWindow, Database database) {
        super("Register User", RegisterUserWindow.class.getResource("register_user.fxml"), null, null);
        this.mainWindow = mainWindow;
        this.userController = new UserController(database);
        this.disposables = new CompositeDisposable();
    }

    @Override
    protected void initWindow(Stage stage) {
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(MainWindow.class.getResourceAsStream("logo_v3.png"))));
    }

    @Override
    protected void onFxmlLoaded() {
        btnRegister.setOnAction(evt -> {
            if (validated()) registerAndClose();
        });
    }

    private boolean validated() {
        lblError.setText("");
        boolean valid = false;

        if (tfUsername.getText().isBlank() || tfFullname.getText().isBlank() || tfDesignation.getText().isBlank() ||
                tfPassword.getText().isBlank() || tfRePassword.getText().isBlank()) {
            lblError.setText("Empty Username, Full Name, Designation, and/or Password field.");
        } else if (!tfPassword.getText().equals(tfRePassword.getText())) {
            lblError.setText("Password doesn't match.");
        } else {
            valid = true;
        }
        return valid;
    }

    private void registerAndClose() {
        disposables.add(Single.fromCallable(() -> {
            User user = new User();
            user.setUsername(ViewUtils.normalize(tfUsername.getText()));
            user.setFullname(ViewUtils.normalize(tfFullname.getText()));
            user.setDesignation(ViewUtils.normalize(tfDesignation.getText()));
            user.setPassword(ViewUtils.normalize(tfPassword.getText()));
            if (!userController.hasUsers()) {
                user.setRole("admin");
            }
            return userController.insertWithId(user);
        }).subscribeOn(Schedulers.io()).observeOn(JavaFxScheduler.platform()).subscribe(id -> {
            if (id > 0) {
                showInfoDialog("Success", "User registered successfully.");
            }
            mainWindow.setUserId(id);
            close();
            mainWindow.show();
        }, err -> {
            showErrorDialog("Database Error", "Error while registering new User.\n" + err);
        }));
    }

    @Override
    protected void onShow() {
        clearFields();
    }

    @Override
    protected void onClose() {
        clearFields();
    }

    private void clearFields() {
        tfUsername.clear();
        tfFullname.clear();
        tfDesignation.clear();
        tfPassword.clear();
        tfRePassword.clear();
        lblError.setText("");
    }

    public void dispose() {
        disposables.dispose();
    }
}
