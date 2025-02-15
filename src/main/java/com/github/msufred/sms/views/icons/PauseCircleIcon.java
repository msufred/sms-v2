package com.github.msufred.sms.views.icons;

import javafx.scene.shape.SVGPath;

public class PauseCircleIcon extends SVGIcon {

    public PauseCircleIcon() {
        super();
    }

    public PauseCircleIcon(double size) {
        super(size);
    }

    @Override
    protected SVGPath createShape() {
        SVGPath path = new SVGPath();
        path.setContent("M12,1C5.935,1,1,5.935,1,12s4.935,11,11,11s11-4.935,11-11S18.065,1,12,1z M12,21c-4.962," +
                "0-9-4.037-9-9 c0-4.962,4.038-9,9-9c4.963,0,9,4.038,9,9C21,16.963,16.963,21,12,21z M10,8C9.448," +
                "8,9,8.448,9,9v6c0,0.553,0.448,1,1,1s1-0.447,1-1V9C11,8.448,10.552,8,10,8z M14,8c-0.553,0-1," +
                "0.448-1,1v6c0,0.553,0.447,1,1,1s1-0.447,1-1V9C15,8.448,14.553,8,14,8z");
        return path;
    }

    @Override
    protected String getIconStyleClass() {
        return "pause-circle-icon";
    }
}
