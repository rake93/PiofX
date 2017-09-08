package com.firstacademy.piofx.fonts.patua;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ananth on 8/9/17.
 */

public class PatuaRegular extends TextView {
    public PatuaRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public PatuaRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PatuaRegular(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf1 = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/Patua_One/PatuaOne-Regular.ttf");
        setTypeface(tf1);
    }
}
