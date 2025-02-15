package com.github.msufred.sms.views.icons;

import javafx.scene.shape.SVGPath;

public class CornerUpRightIcon extends SVGIcon {

    public CornerUpRightIcon() {
        super(DEFAULT_SIZE * 0.8);
    }

    public CornerUpRightIcon(double size) {
        super(size * 0.8);
    }

    @Override
    protected SVGPath createShape() {
        SVGPath path = new SVGPath();
        path.setContent("m17.92,6.38c.1-.24.1-.52,0-.76-.05-.12-.12-.23-.22-.33L12.71.29c-.39-.39-1.02-.39-1.41,0s-" +
                ".39,1.02,0,1.41l3.29,3.29H5C2.24,5,0,7.24,0,10v7c0,.55.45,1,1,1s1-.45,1-1v-7c0-1.65,1.35-3,3-3h9." +
                "59l-3.29,3.29c-.39.39-.39,1.02,0,1.41.2.2.45.29.71.29s.51-.1.71-.29l5-5c.09-.09.17-.2.22-.33Z");
        return path;
    }

    @Override
    protected String getIconStyleClass() {
        return "corner-up-right-icon";
    }
}
