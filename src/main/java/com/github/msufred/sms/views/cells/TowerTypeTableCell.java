package com.github.msufred.sms.views.cells;

import com.github.msufred.sms.data.Tower;
import com.github.msufred.sms.views.icons.CircleFilledIcon;
import com.github.msufred.sms.views.icons.PentagonIcon;
import com.github.msufred.sms.views.icons.StarFilledIcon;
import com.github.msufred.sms.views.icons.TriangleFilledIcon;
import com.github.msufred.sms.views.icons.*;
import javafx.scene.control.TableCell;

import java.util.Objects;

public class TowerTypeTableCell extends TableCell<Tower, String> {
    @Override
    protected void updateItem(String type, boolean empty) {
        if (empty || type == null || type.equals("null")) {
            setText("");
            setGraphic(null);
        } else {
            setText(type);
            SVGIcon icon;
            if (Objects.equals(type, Tower.TYPE_ACCESS_POINT)) {
                icon = new PentagonIcon(14);
                icon.setStyle("-fx-background-color: #c2410c");
            } else if (Objects.equals(type, Tower.TYPE_RELAY)) {
                icon = new TriangleFilledIcon(14);
                icon.setStyle("-fx-background-color: #eab308");
            } else if (Objects.equals(type, Tower.TYPE_DEFAULT)){
                icon = new CircleFilledIcon(14);
                icon.setStyle("-fx-background-color: #15803d");
            } else {
                icon = new StarFilledIcon(14);
                icon.setStyle("-fx-background-color: #dc2626");
            }
            setGraphic(icon);
        }
    }
}
