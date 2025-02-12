package com.github.msufred.sms.views.cells;

import com.github.msufred.sms.views.ViewUtils;
import javafx.scene.control.TableCell;

public class TagTableCell<T> extends TableCell<T, String> {

    @Override
    protected void updateItem(String tag, boolean empty) {
        super.updateItem(tag, empty);
        setText("");
        if (!empty && tag != null) {
            setGraphic(ViewUtils.createTag(tag));
        } else {
            setGraphic(null);
        }
    }
}

