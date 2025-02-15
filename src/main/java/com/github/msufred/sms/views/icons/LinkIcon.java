package com.github.msufred.sms.views.icons;

import javafx.scene.shape.SVGPath;

public class LinkIcon extends SVGIcon {

    public LinkIcon() {
        super();
    }

    public LinkIcon(double size) {
        super(size);
    }

    @Override
    protected SVGPath createShape() {
        SVGPath path = new SVGPath();
        path.setContent("M21.111,2.751c-2.337-2.259-6-2.258-8.347,0.01l-1.72,1.71c-0.392,0.389-0.394,1.022-0.004,1.414 " +
                "c0.389,0.392,1.022,0.393,1.414,0.004l1.709-1.7c1.559-1.505,4-1.505,5.559,0c1.586,1.532,1.63,4.069,0.11,5.644l-3,3 " +
                "c-0.135,0.135-0.278,0.26-0.433,0.375c-1.768,1.319-4.278,0.961-5.6-0.807c-0.33-0.441-0.957-0.533-1.4-0.202 " +
                "c-0.442,0.331-0.532,0.957-0.202,1.399c1.177,1.574,2.984,2.404,4.813,2.404c1.25,0,2.51-0.388,3.585-1.192 " +
                "c0.229-0.171,0.447-0.36,0.65-0.563l3.013-3.012C23.558,8.854,23.491,5.049,21.111,2.751z " +
                "M11.533,18.113l-1.698,1.697c-1.558,1.505-4,1.505-5.557,0.001c-1.586-1.533-1.631-4.07-0.111-5.645l3-3 " +
                "c0.135-0.135,0.28-0.26,0.434-0.375c0.855-0.64,1.909-0.909,2.966-0.756c1.058,0.152,1.993,0.708,2.632,1.563 " +
                "c0.33,0.441,0.957,0.533,1.399,0.202c0.442-0.331,0.533-0.958,0.202-1.4c-0.96-1.283-2.362-2.116-3.948-2.345 " +
                "C9.265,7.826,7.685,8.23,6.403,9.19c-0.23,0.171-0.447,0.36-0.65,0.563l-3.012,3.013c-2.298,2.379-2.232,6.186,0.147,8.484 " +
                "c1.167,1.127,2.664,1.691,4.164,1.691c1.503-0.001,3.009-0.568,4.185-1.704l1.71-1.71c0.391-0.391,0.391-1.023,0-1.414 " +
                "S11.924,17.723,11.533,18.113z");
        return path;
    }

    @Override
    protected String getIconStyleClass() {
        return "link-icon";
    }
}
