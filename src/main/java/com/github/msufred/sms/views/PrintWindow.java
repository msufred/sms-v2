package com.github.msufred.sms.views;

import com.github.msufred.sms.views.forms.SalesReceiptForm;
import com.github.msufred.sms.views.icons.*;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.github.msufred.sms.data.*;
import com.github.msufred.sms.data.controllers.*;
import com.github.msufred.sms.views.forms.BillingReceiptForm;
import com.github.msufred.sms.views.forms.StatementForm;
import com.github.msufred.sms.views.forms.TermsConditionsForm;
import com.github.msufred.sms.views.panels.AbstractPanel;

/**
 * Window for viewing forms before printing.
 */
public class PrintWindow extends AbstractWindow {

    public enum Type { STATEMENT, RECEIPT, TERMS_AND_CONDITIONS }

    @FXML private Label lblPrinter;
    @FXML private Label lblCopies;
    @FXML private Label lblZoom;
    @FXML private ScrollPane scrollPane;
    @FXML private StackPane contentPane;
    @FXML private ProgressIndicator progress;
    @FXML private ComboBox<Printer> cbPrinters;
    @FXML private Slider zoomSlider;
    @FXML private TextField tfCopies;
    @FXML private Button btnPrint;

    private final Scale scale = new Scale();

    private final AccountController accountController;
    private final SubscriptionController subscriptionController;
    private final BillingController billingController;
    private final BillingStatementController billingStatementController;
    private final PaymentController paymentController;
    private final PaymentItemController paymentItemController;
    private final CompositeDisposable disposables;

    private final ObservableList<Printer> printers = FXCollections.observableArrayList();

    private StatementForm statementForm;
    private BillingReceiptForm billingReceiptForm;
    private SalesReceiptForm salesReceiptForm;
    private TermsConditionsForm termsConditionsForm;

    private Type mType;
    private String mBillingNo;

    // For Services & Purchases
    private boolean isForServiceOrPurchase;
    private String mPaymentNo;
    private ObservableList<PaymentItem> paymentItems;

    private final User user;
    private Account account;
    private Subscription subscription;
    private Billing billing;
    private BillingStatement billingStatement;
    private Payment payment;

    private AbstractPanel mForm = null;
    private Node mContent = null;

    public PrintWindow(User user, Database database, Stage owner) {
        super("Print", PrintWindow.class.getResource("print_window.fxml"), null, owner);
        this.user = user;
        this.accountController = new AccountController(database);
        this.subscriptionController = new SubscriptionController(database);
        this.billingController = new BillingController(database);
        this.billingStatementController = new BillingStatementController(database);
        this.paymentController = new PaymentController(database);
        this.paymentItemController = new PaymentItemController(database);
        this.disposables = new CompositeDisposable();
    }

    @Override
    protected void initWindow(Stage stage) {
        stage.initModality(Modality.APPLICATION_MODAL);
    }

    @Override
    protected void onFxmlLoaded() {
        lblPrinter.setGraphic(new PrinterIcon(14));
        lblCopies.setGraphic(new FilePlusIcon(14));
        lblZoom.setGraphic(new ZoomInIcon(14));
        btnPrint.setGraphic(new PrinterIcon(12));

        scale.xProperty().bind(zoomSlider.valueProperty());
        scale.yProperty().bind(zoomSlider.valueProperty());
        scale.setPivotX(0);
        scale.setPivotY(0);
        contentPane.getTransforms().add(scale);

        cbPrinters.setItems(printers);

        btnPrint.setOnAction(evt -> print());
    }

    /**
     * Use this method if printing for subscription billings, and or receipts.
     * @param type Type RECEIPT, STATEMENT, or TERMS_AND_CONDITIONS
     * @param billingNo
     */
    public void showAndWait(Type type, String billingNo) {
        if (type == null || (type != Type.TERMS_AND_CONDITIONS && billingNo == null)) return;
        mType = type;
        mBillingNo = billingNo;
        isForServiceOrPurchase = false;
        showAndWait();
    }

    /**
     * Use this method if printing for Service or Purchase payments only.
     * @param paymentNo
     */
    public void showAndWait(String paymentNo) {
        if (paymentNo == null || paymentNo.isBlank()) return;
        mPaymentNo = paymentNo;
        isForServiceOrPurchase = true;
        showAndWait();
    }

    @Override
    protected void onShow() {
        // refresh printers
        printers.clear();
        printers.addAll(Printer.getAllPrinters());
        cbPrinters.setValue(Printer.getDefaultPrinter());

        if (!isForServiceOrPurchase) {
            switch (mType) {
                case STATEMENT -> loadBillingStatementForm();
                case RECEIPT -> loadBillingReceiptForm();
                default -> loadTermsAndConditions();
            }
        } else {
            loadPaymentReceiptForm(mPaymentNo);
        }
    }

    private void loadBillingStatementForm() {
        progress.setVisible(true);
        disposables.add(Single.fromCallable(() -> billingStatementController.getByBillingNo(mBillingNo))
                .flatMap(bStatement -> {
                    billingStatement = bStatement;
                    return Single.fromCallable(() -> billingController.getByBillingNo(mBillingNo));
                }).flatMap(b -> {
                    billing = b;
                    return Single.fromCallable(() -> accountController.getByAccountNo(b.getAccountNo()));
                }).flatMap(acct -> {
                    account = acct;
                    return Single.fromCallable(() -> subscriptionController.getByAccountNo(acct.getAccountNo()));
                }).subscribeOn(Schedulers.io()).observeOn(JavaFxScheduler.platform()).subscribe(sub -> {
                    progress.setVisible(false);
                    subscription = sub;
                    if (statementForm == null) statementForm = new StatementForm();
                    mForm = statementForm;
                    fillStatementForm();
                }, err -> {
                    progress.setVisible(false);
                    showErrorDialog("Database Error", err.getMessage());
                }));
    }

    private void fillStatementForm() {
        mContent = statementForm.getView();
        contentPane.getChildren().clear();
        contentPane.getChildren().add(mContent);
        statementForm.setData(user, account, subscription, billing, billingStatement);
        statementForm.onResume();
    }

    private void loadBillingReceiptForm() {
        progress.setVisible(true);
        disposables.add(Single.fromCallable(() -> billingStatementController.getByBillingNo(mBillingNo))
                .flatMap(bStatement -> {
                    billingStatement = bStatement;
                    return Single.fromCallable(() -> billingController.getByBillingNo(mBillingNo));
                }).flatMap(b -> {
                    billing = b;
                    return Single.fromCallable(() -> accountController.getByAccountNo(b.getAccountNo()));
                }).flatMap(acct -> {
                    account = acct;
                    return Single.fromCallable(() -> paymentController.getByExtraInfo(mBillingNo));
                }).subscribeOn(Schedulers.io()).observeOn(JavaFxScheduler.platform()).subscribe(p -> {
                    progress.setVisible(false);
                    payment = p;
                    if (billingReceiptForm == null) billingReceiptForm = new BillingReceiptForm();
                    mForm = billingReceiptForm;
                    fillBillingReceiptForm();
                }));
    }

    private void fillBillingReceiptForm() {
        mContent = billingReceiptForm.getView();
        contentPane.getChildren().clear();
        contentPane.getChildren().add(mContent);
        billingReceiptForm.setData(user, account, payment);
        billingReceiptForm.onResume();
    }

    /**
     * Used only for Service or Purchase payments.
     * @param paymentNo
     */
    private void loadPaymentReceiptForm(String paymentNo) {
        progress.setVisible(true);
        disposables.add(Single.fromCallable(() -> paymentController.getByPaymentNo(paymentNo))
                .flatMap(p -> Single.fromCallable(() -> {
                    payment = p;
                    return paymentItemController.getByPayment(paymentNo);
                })).observeOn(JavaFxScheduler.platform()).subscribeOn(Schedulers.io()).subscribe(items -> {
                    progress.setVisible(false);
                    paymentItems = items;
                    if (salesReceiptForm == null) salesReceiptForm = new SalesReceiptForm();
                    mForm = salesReceiptForm;
                    fillPaymentReceiptForm();
                }, err -> {
                    progress.setVisible(false);
                    showErrorDialog("Database Error", "Error while ");
                }));
    }

    private void fillPaymentReceiptForm() {
        mContent = billingReceiptForm.getView();
        contentPane.getChildren().clear();
        contentPane.getChildren().add(mContent);
        billingReceiptForm.setData(user, payment, paymentItems);
        billingReceiptForm.onResume();
    }

    private void loadTermsAndConditions() {
        if (termsConditionsForm == null) termsConditionsForm = new TermsConditionsForm();
        mContent = termsConditionsForm.getView();
        contentPane.getChildren().clear();
        contentPane.getChildren().add(mContent);
    }

    private void print() {
        progress.setVisible(true);
        mContent.getTransforms().clear();
        contentPane.getTransforms().clear();

        if (mForm != null && mForm == statementForm) ((StatementForm) mForm).showTempBg(false);
        if (mForm != null && mForm == billingReceiptForm) ((BillingReceiptForm) mForm).showTempBg(false);

        Printer printer = cbPrinters.getValue() == null ? Printer.getDefaultPrinter() : cbPrinters.getValue();
        PrinterJob printerJob = PrinterJob.createPrinterJob(printer);

        PageLayout pageLayout = printer.createPageLayout(Paper.A4,
                PageOrientation.PORTRAIT, 0, 0, 0, 0);

        // set settings
        printerJob.getJobSettings().setPrintQuality(PrintQuality.NORMAL);
        printerJob.getJobSettings().setPageLayout(pageLayout);

        PrinterAttributes attr = printer.getPrinterAttributes();

        double scaleX = pageLayout.getPrintableWidth() / mContent.getBoundsInParent().getWidth();
        Scale printScale = new Scale(scaleX, scaleX);
        mContent.getTransforms().add(printScale);

        boolean success = printerJob.printPage(mContent);
        if (success) {
            printerJob.endJob();
        } else {
            showWarningDialog("Print Failed", "");
        }
        mContent.getTransforms().clear();
        contentPane.getTransforms().add(scale);
        progress.setVisible(false);
        close();
    }

    @Override
    protected void onClose() {
        mType = null;
        mBillingNo = null;
        account = null;
        subscription = null;
        billingStatement = null;
        billing = null;
        payment = null;
        paymentItems = null;
        contentPane.getChildren().clear();
        mContent = null;
    }

    public void dispose() {
        if (statementForm != null) statementForm.onDispose();
        if (billingReceiptForm != null) billingReceiptForm.onDispose();
        disposables.dispose();
    }
}
