package com.github.msufred.sms.views.icons;

import javafx.scene.shape.SVGPath;

public class WindIcon extends SVGIcon {

    public WindIcon() {
        super(DEFAULT_SIZE, DEFAULT_SIZE * 0.85);
    }

    public WindIcon(double size) {
        super(size, size * 0.85);
    }

    @Override
    protected SVGPath createShape() {
        SVGPath path = new SVGPath();
        path.setContent("M2,9h8.996C11,9,11.004,9,11.009,9c0.792,0,1.567-0.322,2.128-0.885c1.165-1.174,1.159-" +
                "3.077-0.013-4.242 C12.556,3.308,11.825,3.016,11,3c-0.801,0.002-1.554,0.316-2.119,0.885C8.491," +
                "4.276,8.493,4.91,8.885,5.299 c0.391,0.39,1.024,0.387,1.414-0.004c0.189-0.19,0.448-0.312,0.709-" +
                "0.295c0.266,0,0.517,0.103,0.705,0.291 c0.391,0.388,0.393,1.023,0.004,1.414C11.53,6.894,11.272," +
                "7.042,11,7H2C1.448,7,1,7.448,1,8S1.448,9,2,9z M21.974,7.028c-1.363-1.366-3.584-1.367-4.949-" +
                "0.006c-0.392,0.39-0.392,1.023-0.002,1.414 c0.391,0.392,1.023,0.392,1.414,0.002c0.584-0.583," +
                "1.535-0.583,2.121,0.002c0.584,0.586,0.582,1.538-0.004,2.122 C20.276,10.84,19.892,11,19.5,11H2c-" +
                "0.552,0-1,0.448-1,1c0,0.553,0.448,1,1,1h17.502c0.918-0.001,1.816-0.374,2.465-1.022 C23.333," +
                "10.615,23.336,8.395,21.974,7.028z M14.011,15c-0.004,0-0.007,0-0.011,0H2c-0.552,0-1,0.447-1," +
                "1s0.448,1,1,1h12.004c0.229,0.031,0.526,0.106,0.714,0.295 c0.188,0.189,0.291,0.44,0.291,0.708c-" +
                "0.001,0.267-0.105,0.518-0.296,0.706C14.524,18.896,14.274,19,14.008,19 c-0.001,0-0.002,0-0.003," +
                "0c-0.267-0.001-0.518-0.105-0.706-0.295c-0.391-0.392-1.023-0.395-1.415-0.004 c-0.391,0.39-0.393," +
                "1.022-0.003,1.414C12.446,20.684,13.198,20.998,14,21c0.003,0,0.006,0,0.009,0c0.798,0,1.549-0.31," +
                "2.113-0.872 c0.569-0.564,0.884-1.317,0.886-2.119c0.003-0.802-0.307-1.556-0.871-2.123C15.578," +
                "15.322,14.802,15,14.011,15z");
        return path;
    }

    @Override
    protected String getIconStyleClass() {
        return "wind-icon";
    }
}
