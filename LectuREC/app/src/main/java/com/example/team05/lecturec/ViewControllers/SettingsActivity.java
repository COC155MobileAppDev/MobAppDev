package com.example.team05.lecturec.ViewControllers;


import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.widget.Toast;

import com.example.team05.lecturec.R;
public class SettingsActivity extends PreferenceActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

        final CheckBoxPreference checkboxPref = (CheckBoxPreference) getPreferenceManager().findPreference("notificationboxPref");


        checkboxPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object newValue) {

                if (newValue.toString().equals("true")) {
                Toast.makeText(getApplicationContext(), "Notifications ON",
                        Toast.LENGTH_SHORT).show();



                } else {
                    Toast.makeText(getApplicationContext(), "Notifications OFF",
                            Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

    }




}