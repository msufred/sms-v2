package com.github.msufred.sms.views.icons;

import javafx.scene.shape.SVGPath;

public class LayersIcon extends SVGIcon {

    public LayersIcon() {
        super();
    }

    public LayersIcon(double size) {
        super(size);
    }

    @Override
    protected SVGPath createShape() {
        SVGPath path = new SVGPath();
        path.setContent("M1.553,7.895l10,5C11.693,12.965,11.847,13,12,13s0.307-0.035,0.447-0.105l10-5C22.786,7.725,23,7.379,23,7 " +
                "s-0.214-0.725-0.553-0.895l-10-5c-0.281-0.141-0.613-0.141-0.895,0l-10,5C1.214,6.275,1,6.621,1,7S1.214,7.725,1.553,7.895z " +
                "M12,3.118L19.764,7L12,10.882L4.236,7L12,3.118z M21.553,16.105L12,20.882l-9.553-4.776c-0.495-0.245-" +
                "1.095-0.046-1.342,0.447c-0.247,0.494-0.047,1.095,0.447,1.342l10,5 C11.693,22.965,11.847,23,12," +
                "23s0.307-0.035,0.447-0.105l10-5c0.494-0.247,0.694-0.848,0.447-1.342S22.047,15.861,21.553,16.105z " +
                "M21.553,11.105L12,15.882l-9.553-4.776c-0.495-0.248-1.095-0.047-1.342,0.447s-0.047,1.095,0.447," +
                "1.342l10,5 C11.693,17.965,11.847,18,12,18s0.307-0.035,0.447-0.105l10-5c0.494-0.247,0.694-0.848," +
                "0.447-1.342 C22.647,11.058,22.047,10.858,21.553,11.105z");
        return path;
    }

    @Override
    protected String getIconStyleClass() {
        return "layers-icon";
    }
}
