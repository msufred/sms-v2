package com.github.msufred.sms.views.icons;

import javafx.scene.shape.SVGPath;

public class MicOffIcon extends SVGIcon {

    public MicOffIcon() {
        super();
    }

    public MicOffIcon(double size) {
        super(size);
    }

    @Override
    protected SVGPath createShape() {
        SVGPath path = new SVGPath();
        path.setContent("M23.707,22.293l-5.9-5.9c-0.036-0.053-0.06-0.111-0.107-0.158c-0.043-0.042-" +
                "0.096-0.061-0.144-0.093L9.707,8.293 c0,0,0,0,0-0.001l-8-8c-0.391-0.391-1.023-0.391-" +
                "1.414,0s-0.391,1.023,0,1.414L8,9.414v2.587c0.001,1.068,0.418,2.072,1.174,2.827 " +
                "c0.755,0.754,1.758,1.169,2.824,1.169c0.001,0,0.003,0,0.005,0c0.723,0,1.411-0.205," +
                "2.018-0.563l1.475,1.475 c-0.99,0.713-2.144,1.092-3.314,1.127C12.12,18.025,12.064," +
                "18,12,18c-0.054,0-0.101,0.023-0.153,0.031 c-1.464-0.037-2.92-0.589-4.046-1.693C6.645," +
                "15.205,5.988,13.626,6,12v-2c0-0.552-0.448-1-1-1s-1,0.448-1,1v1.993 c-0.016,2.159," +
                "0.86,4.263,2.401,5.774c1.298,1.271,2.922,2.002,4.599,2.211V22H8c-0.552,0-1,0.447-1," +
                "1s0.448,1,1,1h8 c0.553,0,1-0.447,1-1s-0.447-1-1-1h-3v-2.022c1.403-0.175,2.764-0.721," +
                "3.929-1.634l5.364,5.364C22.488,23.902,22.744,24,23,24 s0.512-0.098,0.707-0.293C24.098," +
                "23.316,24.098,22.684,23.707,22.293z M12.001,13.997c-0.001,0-0.002,0-0.002,0 c-0.533," +
                "0-1.035-0.207-1.412-0.584C10.209,13.035,10,12.533,10,12v-0.586l2.51,2.51C12.345," +
                "13.967,12.176,13.997,12.001,13.997z M8.861,4.38C9.404,4.488,9.93,4.14,10.04," +
                "3.599C10.229,2.67,11.053,1.997,12,1.997c0,0,0.001,0,0.002,0 c0.534,0,1.036,0.209," +
                "1.414,0.587C13.793,2.962,14.001,3.465,14,4v5.34c0,0.552,0.447,1,1,1s1-0.448," +
                "1-1V4.001 c0.001-1.068-0.414-2.073-1.169-2.83c-0.754-0.756-1.759-1.173-2.827-" +
                "1.174c-0.001,0-0.002,0-0.004,0 c-1.895,0-3.543,1.347-3.92,3.204C7.97,3.742,8.32," +
                "4.27,8.861,4.38z M18.712,14.215c0.061,0.011,0.12,0.016,0.179,0.016c0.475,0,0.896-0.339," +
                "0.983-0.822C19.958,12.943,20,12.47,20,12v-2 c0-0.552-0.447-1-1-1s-1,0.448-1,1v2c0," +
                "0.351-0.032,0.705-0.095,1.053C17.808,13.597,18.169,14.116,18.712,14.215z");
        return path;
    }

    @Override
    protected String getIconStyleClass() {
        return "mic-off-icon";
    }
}
