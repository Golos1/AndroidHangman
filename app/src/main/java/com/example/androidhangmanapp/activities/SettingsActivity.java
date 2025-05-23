package com.example.androidhangmanapp.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.androidhangmanapp.R;

import lib.Utils;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
     @Override
     public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         if (item.getItemId() == android.R.id.home) {
             finish();
             return true;
         } else {
             return super.onOptionsItemSelected(item);
         }
     }

     public static class SettingsFragment extends PreferenceFragmentCompat {
         @Override
         public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
             setPreferencesFromResource(R.xml.root_preferences, rootKey);

             setNightModePreferenceListener();
         }

         private void setNightModePreferenceListener() {
             Preference nightModePreference = findPreference(getString(R.string.night_mode));
             if (nightModePreference != null) {
                 nightModePreference.setOnPreferenceChangeListener((preference, newValue) -> {
                     Boolean newBooleanValue = (Boolean) newValue;
                     Utils.setNightModeOnOrOff(newBooleanValue);
                     return true;
                 });
             }
         }

     }
}