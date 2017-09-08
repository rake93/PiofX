package com.firstacademy.piofx.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by ananth on 9/8/17.
 */

public class Fonts {
    public static void setFont(View element, String type, String viewName) {
        Context context = element.getContext();
        if (viewName.equalsIgnoreCase("TextView")) {
            ((TextView) element).setTypeface(getFontFromAsset(context, type));
        } else if (viewName.equalsIgnoreCase("EditText")) {
            ((EditText) element).setTypeface(getFontFromAsset(context, type));
        }  else if (viewName.equalsIgnoreCase("Button")) {
            ((Button) element).setTypeface(getFontFromAsset(context, type));
        } else if (viewName.equalsIgnoreCase("AutoCompleteTextView")) {
            ((AutoCompleteTextView) element).setTypeface(getFontFromAsset(context, type));
        } else if (viewName.equalsIgnoreCase("Menu")) {
            //((MenuItem) element).setTypeface(getFontFromAsset(context, type));
        }
        if (viewName.equalsIgnoreCase("Button") && type.equals(Fonts.opensansRegular)) {
            ((TextView) element).setTypeface(getFontFromAsset(context, type), getFontFromAsset(context, type).BOLD);
        } else if (viewName.equalsIgnoreCase("Button") && type.equals(Fonts.robotoMedium)) {
            ((TextView) element).setTypeface(getFontFromAsset(context, type), getFontFromAsset(context, type).BOLD);
        }


    }


    public static String opensansRegular = "opensans_regular";
    public static String robotoMedium = "roboto_medium";

    public static Typeface getFontFromAsset(Context context, String type) {
        Typeface typeface = null;
        if (type.equals(Fonts.opensansRegular)) {
            typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Open_Sans/OpenSans-Regular.ttf");
        }  else if (type.equals(Fonts.robotoMedium)) {
            typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto/Roboto-Medium.ttf");
        }
        return typeface;
    }
}
