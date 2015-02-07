package com.raghurana.singleactivityapp.utilities;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

public class DisplayUtil {

    private static final String ClassName = "DisplayUtil";

    public static void logPhoneScreenDensity(Context context) {
        switch (context.getResources().getDisplayMetrics().densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                Log.i(ClassName, "DENSITY_LOW");
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                Log.i(ClassName, "DENSITY_MEDIUM");
                break;
            case DisplayMetrics.DENSITY_HIGH:
                Log.i(ClassName, "DENSITY_HIGH");
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                Log.i(ClassName, "DENSITY_XHIGH");
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                Log.i(ClassName, "DENSITY_XXHIGH");
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                Log.i(ClassName, "DENSITY_XXXHIGH");
                break;
        }
    }


}
