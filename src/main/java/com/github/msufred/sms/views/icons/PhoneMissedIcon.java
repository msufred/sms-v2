package com.github.msufred.sms.views.icons;

import javafx.scene.shape.SVGPath;

public class PhoneMissedIcon extends SVGIcon {

    public PhoneMissedIcon() {
        super();
    }

    public PhoneMissedIcon(double size) {
        super(size);
    }

    @Override
    protected SVGPath createShape() {
        SVGPath path = new SVGPath();
        path.setContent("M21.414,4l2.293-2.293c0.391-0.391,0.391-1.023,0-1.414s-1.023-0.391-1.414,0L20," +
                "2.586l-2.293-2.293 c-0.391-0.391-1.023-0.391-1.414,0s-0.391,1.023,0,1.414L18.586,4l-2.293," +
                "2.293c-0.391,0.391-0.391,1.023,0,1.414 C16.488,7.902,16.744,8,17,8s0.512-0.098,0.707-0.293L20," +
                "5.414l2.293,2.293C22.488,7.902,22.744,8,23,8s0.512-0.098,0.707-0.293 c0.391-0.391,0.391-1.023," +
                "0-1.414L21.414,4z M20.41,13.898c-0.881-0.116-1.753-0.333-2.588-0.644c-1.081-0.408-2.341-0.141-3.169," +
                "0.678l-0.718,0.718 c-1.844-1.176-3.41-2.742-4.585-4.586l0.721-0.721c0.821-0.83,1.086-2.072," +
                "0.676-3.163c-0.312-0.835-0.529-1.707-0.647-2.6 C9.893,2.109,8.616,1,7.11,1h-3C4.021,1,3.932," +
                "1.004,3.842,1.012c-0.798,0.072-1.52,0.451-2.034,1.066 C1.295,2.694,1.052,3.472,1.125,4.286c0.343," +
                "3.231,1.458,6.381,3.22,9.1c1.603,2.522,3.784,4.704,6.3,6.303 c2.714,1.761,5.85,2.877,9.081," +
                "3.228c0.086,0.008,0.172,0.012,0.258,0.012c0.001,0,0.025,0,0.027,0 c0.801-0.003,1.554-0.317," +
                "2.118-0.887s0.874-1.324,0.87-2.121v-2.975C23.038,15.423,21.929,14.113,20.41,13.898z " +
                "M21,19.925 c0.001,0.267-0.102,0.518-0.29,0.707c-0.188,0.19-0.438,0.295-0.718,0.296l-0.008," +
                "1v-1l-0.057-0.002 c-2.906-0.315-5.74-1.323-8.202-2.92c-2.278-1.448-4.246-3.416-5.697-5.7C4.433," +
                "9.841,3.424,6.995,3.116,4.09 c-0.024-0.266,0.057-0.525,0.228-0.73C3.515,3.154,3.756,3.028,4.11," +
                "3h3.02c0.496,0,0.921,0.37,0.989,0.852 c0.136,1.033,0.39,2.051,0.755,3.03C9.011,7.246,8.923,7.66," +
                "8.653,7.933l-1.27,1.27c-0.318,0.318-0.385,0.81-0.162,1.202 c1.512,2.659,3.716,4.863,6.375," +
                "6.375c0.388,0.222,0.882,0.156,1.201-0.162l1.267-1.267c0.273-0.271,0.681-0.364,1.058-0.223 " +
                "c0.979,0.364,1.998,0.618,3.019,0.752c0.504,0.071,0.873,0.508,0.86,1.04V19.925z");
        return path;
    }

    @Override
    protected String getIconStyleClass() {
        return "phone-missed-icon";
    }
}
