package com.github.msufred.sms.views.icons;

import javafx.scene.shape.SVGPath;

public class SendIcon extends SVGIcon {

    public SendIcon() {
        super();
    }

    public SendIcon(double size) {
        super(size);
    }

    @Override
    protected SVGPath createShape() {
        SVGPath path = new SVGPath();
        path.setContent("M22.989,1.944c-0.005-0.091-0.022-0.178-0.052-0.264c-0.011-0.032-0.021-0.062-0.035-0.093 " +
                "c-0.049-0.107-0.109-0.208-0.195-0.294c-0.086-0.086-0.188-0.147-0.295-0.196c-0.03-0.014-0.06-0.024-" +
                "0.091-0.035 c-0.085-0.029-0.173-0.046-0.263-0.051c-0.027-0.002-0.053-0.007-0.08-0.007c-0.104," +
                "0.002-0.207,0.017-0.308,0.052l-20,7 C1.283,8.191,1.018,8.549,1.001,8.959c-0.017,0.41,0.218,0.788," +
                "0.593,0.954l8.648,3.844l3.844,8.648C14.247,22.769,14.606,23,15,23 c0.014,0,0.027,0,0.041-0.001c0.409-" +
                "0.017,0.768-0.281,0.903-0.669l7-20c0.035-0.101,0.049-0.205,0.052-0.309 C22.997,1.995,22.991,1.97," +
                "22.989,1.944z M18.195,4.391l-7.416,7.416L4.711,9.111L18.195,4.391z M14.89,19.289l-2.697-6.068 " +
                "l7.416-7.416L14.89,19.289z");
        return path;
    }

    @Override
    protected String getIconStyleClass() {
        return "send-icon";
    }
}
