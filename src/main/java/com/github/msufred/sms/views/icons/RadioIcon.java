package com.github.msufred.sms.views.icons;

import javafx.scene.shape.SVGPath;

public class RadioIcon extends SVGIcon {

    public RadioIcon() {
        super(DEFAULT_SIZE, DEFAULT_SIZE * 0.75);
    }

    public RadioIcon(double size) {
        super(size, size * 0.75);
    }

    @Override
    protected SVGPath createShape() {
        SVGPath path = new SVGPath();
        path.setContent("M12,9c-1.654,0-3,1.346-3,3s1.346,3,3,3s3-1.346,3-3S13.654,9,12,9z " +
                "M12,13c-0.551,0-1-0.448-1-1c0-0.551,0.449-1,1-1 c0.552,0,1,0.449,1,1C13," +
                "12.552,12.552,13,12,13z M5.637,5.637c0.391-0.391,0.391-1.024,0-1.414c-" +
                "0.391-0.391-1.023-0.391-1.414,0c-4.287,4.288-4.287,11.265,0,15.554 " +
                "c0.195,0.195,0.451,0.293,0.707,0.293s0.512-0.098,0.707-0.293c0.391-" +
                "0.391,0.391-1.023,0-1.414 C2.13,14.854,2.13,9.146,5.637,5.637z " +
                "M8.463,8.461C8.476,8.449,8.488,8.436,8.5,8.423c0.357-0.393,0.347-" +
                "1-0.033-1.38c-0.391-0.391-1.023-0.391-1.414,0 C7.046,7.049,7.04," +
                "7.056,7.034,7.063C5.721,8.383,5,10.136,5,11.999c0.001,1.87,0.73," +
                "3.627,2.053,4.948 c0.195,0.195,0.451,0.293,0.707,0.293s0.512-0.098," +
                "0.708-0.293c0.39-0.391,0.39-1.024-0.001-1.414 C6.516,13.584,6.514," +
                "10.412,8.463,8.461z M19.777,4.223c-0.391-0.391-1.023-0.391-1.414," +
                "0c-0.391,0.39-0.391,1.023,0,1.414c3.507,3.508,3.507,9.217,0,12.726 " +
                "c-0.391,0.391-0.391,1.023,0,1.414c0.195,0.195,0.451,0.293,0.707," +
                "0.293s0.512-0.098,0.707-0.293 C24.064,15.488,24.063,8.511,19.777,4.223z " +
                "M16.947,7.052c-0.391-0.39-1.023-0.39-1.414,0s-0.391,1.024,0,1.415c1.95," +
                "1.949,1.952,5.121,0.074,7.007 c-0.107,0.089-0.199,0.203-0.262,0.328c-0.247," +
                "0.494-0.047,1.095,0.447,1.342c0.144,0.071,0.296,0.105,0.446,0.105 c0.271,0," +
                "0.535-0.11,0.725-0.31C19.68,14.209,19.673,9.777,16.947,7.052z");
        return path;
    }

    @Override
    protected String getIconStyleClass() {
        return "radio-icon";
    }
}
