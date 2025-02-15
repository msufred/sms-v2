package com.github.msufred.sms.views.icons;

import javafx.scene.shape.SVGPath;

public class StarIcon extends SVGIcon {

    public StarIcon() {
        super();
    }

    public StarIcon(double size) {
        super(size);
    }

    @Override
    protected SVGPath createShape() {
        SVGPath path = new SVGPath();
        path.setContent("M5.82,22.02c-0.207,0-0.414-0.064-0.588-0.191c-0.308-0.224-0.462-0.603-0.398-0.978l1.091-" +
                "6.361L1.302,9.986 C1.03,9.721,0.931,9.324,1.049,8.961C1.166,8.6,1.479,8.335,1.855,8.281l6.391-" +
                "0.935l2.857-5.789c0.337-0.683,1.457-0.683,1.793,0 l2.857,5.789l6.391,0.935c0.377,0.055,0.689," +
                "0.319,0.807,0.681c0.117,0.362,0.02,0.759-0.254,1.025l-4.623,4.503l1.091,6.361 c0.064,0.375-0.09," +
                "0.754-0.397,0.978c-0.309,0.226-0.717,0.255-1.054,0.076L12,18.899l-5.715,3.005 C6.139,21.981,5.979," +
                "22.02,5.82,22.02z M12,16.77c0.16,0,0.32,0.038,0.465,0.115l4.386,2.307l-0.837-4.883 c-0.056-0.324," +
                "0.052-0.655,0.288-0.886l3.549-3.457l-4.906-0.717c-0.325-0.047-0.606-0.252-0.752-0.547L12," +
                "4.259L9.807,8.702 c-0.146,0.295-0.426,0.5-0.752,0.547L4.148,9.966l3.549,3.457c0.236,0.23,0.344," +
                "0.562,0.288,0.886l-0.838,4.883l4.387-2.307 C11.68,16.808,11.84,16.77,12,16.77z");
        return path;
    }

    @Override
    protected String getIconStyleClass() {
        return "star-icon";
    }
}
