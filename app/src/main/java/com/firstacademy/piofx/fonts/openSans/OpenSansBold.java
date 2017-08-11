package com.firstacademy.piofx.fonts.openSans;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ananth on 9/8/17.
 */

public class OpenSansBold extends TextView {
    public OpenSansBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public OpenSansBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OpenSansBold(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf1 = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/Open_Sans/OpenSans-Bold.ttf");
        setTypeface(tf1);
    }
}
