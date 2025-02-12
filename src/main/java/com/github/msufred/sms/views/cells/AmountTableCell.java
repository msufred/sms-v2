package com.github.msufred.sms.views.cells;

import com.github.msufred.sms.views.ViewUtils;
import com.github.msufred.sms.views.icons.PesoIcon;
import javafx.scene.control.TableCell;

public class AmountTableCell<T> extends TableCell<T, Double> {
    @Override
    protected void updateItem(Double amount, boolean empty) {
        if (empty || amount == 0) {
            setText("");
            setGraphic(null);
        } else {
            setText(ViewUtils.toStringMoneyFormat(amount));
            PesoIcon pesoIcon = new PesoIcon(12);
            pesoIcon.getStyleClass().add("tag-normal");
            setGraphic(pesoIcon);
        }
    }
}
