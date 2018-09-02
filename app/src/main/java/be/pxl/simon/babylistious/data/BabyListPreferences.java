package be.pxl.simon.babylistious.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import be.pxl.simon.babylistious.R;

public class BabyListPreferences {
    public static boolean showCheckedItems(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
//        boolean foo = prefs.getBoolean("show_checked", R.bool.pref_show_checked_items);
        return true;
    }

    public static String getPreferredGender(Context context) {
        return "Boy";
    }
}
