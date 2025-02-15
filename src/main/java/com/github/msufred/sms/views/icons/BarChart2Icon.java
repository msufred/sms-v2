package com.github.msufred.sms.views.icons;

import javafx.scene.shape.SVGPath;

public class BarChart2Icon extends SVGIcon {

    public BarChart2Icon() {
        super(DEFAULT_SIZE * 0.85, DEFAULT_SIZE);
    }

    public BarChart2Icon(double size) {
        super(size * 0.85, size);
    }

    @Override
    protected SVGPath createShape() {
        SVGPath path = new SVGPath();
        path.setContent("m13,6c-.55,0-1,.45-1,1v10c0,.55.45,1,1,1s1-.45,1-1V7c0-.55-.45-1-1-1Z " +
                "M7,0c-.55,0-1,.45-1,1v16c0,.55.45,1,1,1s1-.45,1-1V1c0-.55-.45-1-1-1Z " +
                "M1,10c-.55,0-1,.45-1,1v6c0,.55.45,1,1,1s1-.45,1-1v-6c0-.55-.45-1-1-1Z");
        return path;
    }

    @Override
    protected String getIconStyleClass() {
        return "bar-chart2-icon";
    }
}
