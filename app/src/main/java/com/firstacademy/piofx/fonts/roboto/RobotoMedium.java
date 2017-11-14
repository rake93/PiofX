package com.firstacademy.piofx.fonts.roboto;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ananth on 5/9/17.
 */

public class RobotoMedium extends TextView {
    public RobotoMedium(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public RobotoMedium(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RobotoMedium(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf1 = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/Roboto/Roboto-Medium.ttf");
        setTypeface(tf1);
    }
}
