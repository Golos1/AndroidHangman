package lib;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO;
import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;

import com.example.androidhangmanapp.R;

public class Utils
{
    /*
    Night Mode-related helper methods
     */
    public static void setNightModeOnOffFromPreferenceValue(Context context, String keyNightMode) {
        setNightModeOnOrOff(isNightModePrefOn(context, keyNightMode));
    }

    public static void setNightModeOnOrOff(boolean setToOn) {
        int onMode  =  Build.VERSION.SDK_INT < 28 ? MODE_NIGHT_YES : MODE_NIGHT_FOLLOW_SYSTEM;
        AppCompatDelegate.setDefaultNightMode(setToOn ? onMode : MODE_NIGHT_NO);
    }

    private static boolean isNightModePrefOn(Context context, String keyNightMode) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return defaultSharedPreferences.getBoolean(keyNightMode, true);
    }

}
