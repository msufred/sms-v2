module com.github.msufred.sms {
    requires java.sql;
    requires java.xml;
    requires java.desktop;

    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.swing;

    requires org.apache.commons.io;
    requires com.h2database;
    requires org.reactivestreams;
    requires io.reactivex.rxjava2;
    requires rxjavafx;

    requires feathericons;

    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires commons.math3;
    requires org.jxmapviewer.jxmapviewer2;
    requires jdk.compiler;

    opens com.github.msufred.sms to javafx.fxml;
    opens com.github.msufred.sms.views to javafx.fxml;
    opens com.github.msufred.sms.views.panels to javafx.fxml;
    opens com.github.msufred.sms.views.forms to javafx.fxml;

    opens com.github.msufred.sms.data to javafx.base;
    opens com.github.msufred.sms.data.controllers to javafx.base;
    opens com.github.msufred.sms.data.controllers.models to javafx.base;

    exports com.github.msufred.sms;
    exports com.github.msufred.sms.data;
}