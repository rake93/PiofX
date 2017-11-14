package com.firstacademy.piofx.fonts.openSans;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ananth on 10/8/17.
 */

public class OpenSansSemiBold extends TextView {
    public OpenSansSemiBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public OpenSansSemiBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OpenSansSemiBold(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf1 = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/Open_Sans/OpenSans-SemiBold.ttf");
        setTypeface(tf1);
    }
}
