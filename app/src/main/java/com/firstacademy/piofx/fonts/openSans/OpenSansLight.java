package com.firstacademy.piofx.fonts.openSans;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ananth on 9/8/17.
 */

public class OpenSansLight extends TextView {
    public OpenSansLight(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public OpenSansLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OpenSansLight(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf1 = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/Open_Sans/OpenSans-Light.ttf");
        setTypeface(tf1);
    }
}
