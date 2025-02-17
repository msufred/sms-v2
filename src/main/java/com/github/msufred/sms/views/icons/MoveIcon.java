package com.github.msufred.sms.views.icons;

import javafx.scene.shape.SVGPath;

public class MoveIcon extends SVGIcon {

    public MoveIcon() {
        super();
    }

    public MoveIcon(double size) {
        super(size);
    }

    @Override
    protected SVGPath createShape() {
        SVGPath path = new SVGPath();
        path.setContent("M22.923,12.382c0.101-0.244,0.101-0.519,0-0.764c-0.051-0.123-0.124-0.233-0.217-0.326l-2.999-2.999 " +
                "c-0.391-0.391-1.023-0.391-1.414,0s-0.391,1.023,0,1.414L19.586,11H13V4.414l1.293,1.293C14.488,5.902,14.744,6,15,6 " +
                "s0.512-0.098,0.707-0.293c0.391-0.391,0.391-1.023,0-1.414l-2.999-2.999c-0.093-0.093-0.203-0.166-0.326-0.217 " +
                "c-0.244-0.101-0.52-0.101-0.764,0c-0.122,0.051-0.233,0.124-0.325,0.216l-3,3c-0.391,0.391-0.391,1.023,0," +
                "1.414s1.023,0.391,1.414,0 L11,4.414V11H4.414l1.293-1.293c0.391-0.391,0.391-1.023,0-1.414s-1.023-0.391-1.414," +
                "0l-3,3c-0.092,0.092-0.165,0.203-0.216,0.325 c-0.101,0.244-0.101,0.52,0,0.764c0.051,0.123,0.124,0.233,0.217," +
                "0.326l2.999,2.999C4.488,15.902,4.744,16,5,16 s0.512-0.098,0.707-0.293c0.391-0.391,0.391-1.023,0-1.414L4.414," +
                "13H11v6.586l-1.293-1.293c-0.391-0.391-1.023-0.391-1.414,0 s-0.391,1.023,0,1.414l2.999,2.999c0.093,0.093," +
                "0.203,0.166,0.326,0.217C11.74,22.973,11.87,23,12,23s0.26-0.026,0.382-0.077 c0.123-0.051,0.234-0.124," +
                "0.326-0.217l2.999-2.999c0.391-0.391,0.391-1.023,0-1.414s-1.023-0.391-1.414,0L13,19.586V13h6.586 l-1.293," +
                "1.293c-0.391,0.391-0.391,1.023,0,1.414C18.488,15.902,18.744,16,19,16s0.512-0.098,0.707-0.293l2.999-2.999 " +
                "C22.799,12.616,22.872,12.505,22.923,12.382z");
        return path;
    }

    @Override
    protected String getIconStyleClass() {
        return "move-icon";
    }
}
