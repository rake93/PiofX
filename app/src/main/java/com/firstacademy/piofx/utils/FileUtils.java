package com.firstacademy.piofx.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Rakesh Muppa on 15-08-2017.
 */

public class FileUtils {

    private FileUtils() {

    }

    public static String loadJSONFromAsset(Context context, String jsonFileName) throws IOException {

        AssetManager assetManager = context.getAssets();
        InputStream is = assetManager.open(jsonFileName);

        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        return new String(buffer, "UTF-8");
    }
}
