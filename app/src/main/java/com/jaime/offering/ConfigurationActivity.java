package com.jaime.offering;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

public class ConfigurationActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{
    public static final String CHECKS_KEY = "checks";
    private static final String PREFERENCE_NAME = "preference";
    private static final String PREFERENCE_HOME = "home";
    private static final String PREFERENCE_ELECTRONIC = "electronic";
    private static final String PREFERENCE_SPORT = "sport";
    private static final String PREFERENCE_IMPORTANCE = "importance";

    private CheckBox cbHome;
    private CheckBox cbElectronic;
    private CheckBox cbSport;
    private CheckBox cbImportance;
    private Button btnAccept;
    private ViewGroup mLayout;

    private boolean[] mChecks;
    private SharedPreferences mPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        cbHome = (CheckBox) findViewById(R.id.cb_home);
        cbHome.setOnCheckedChangeListener(this);
        cbElectronic = (CheckBox) findViewById(R.id.cb_electronic);
        cbElectronic.setOnCheckedChangeListener(this);
        cbSport = (CheckBox) findViewById(R.id.cb_sport);
        cbSport.setOnCheckedChangeListener(this);
        cbImportance = (CheckBox) findViewById(R.id.cb_importance);
        cbImportance.setOnCheckedChangeListener(this);
        btnAccept = (Button) findViewById(R.id.btn_accept);
        mLayout = (RelativeLayout) findViewById(R.id.activity_configuration);

        mChecks = new boolean[4];
        mPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);

        getChecksFromPreferences();
        updateSettingsChecks();

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOneCheck()) {
                    setChecks();
                    Intent intent = new Intent(ConfigurationActivity.this, ListOffersActivity.class);
                    intent.putExtra(CHECKS_KEY, mChecks);
                    startActivity(intent);
                } else
                    Snackbar.make(mLayout, "Debes seleccionar, al menos, una de las ofertas", Snackbar.LENGTH_SHORT).show();
            }
        });
    }


    private boolean isOneCheck() {
        boolean result = true;

        if (!cbHome.isChecked() && !cbElectronic.isChecked() && !cbSport.isChecked())
            result = false;

        return result;
    }


    private void setChecks() {
        if (cbHome.isChecked())
            mChecks[0] = true;
        else
            mChecks[0] = false;

        if (cbElectronic.isChecked())
            mChecks[1] = true;
        else
            mChecks[1] = false;

        if (cbSport.isChecked())
            mChecks[2] = true;
        else
            mChecks[2] = false;

        if (cbImportance.isChecked())
            mChecks[3] = true;
        else
            mChecks[3] = false;
    }


    private void getChecksFromPreferences() {
        mChecks[0] = mPreferences.getBoolean(PREFERENCE_HOME, false);
        mChecks[1] = mPreferences.getBoolean(PREFERENCE_ELECTRONIC, false);
        mChecks[2] = mPreferences.getBoolean(PREFERENCE_SPORT, false);
        mChecks[3] = mPreferences.getBoolean(PREFERENCE_IMPORTANCE, false);
    }


    private void updateSettingsChecks() {
        cbHome.setChecked(mChecks[0]);
        cbElectronic.setChecked(mChecks[1]);
        cbSport.setChecked(mChecks[2]);
        cbImportance.setChecked(mChecks[3]);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        SharedPreferences.Editor editor = mPreferences.edit();

        switch (compoundButton.getId()) {
            case R.id.cb_home:
                mChecks[0] = b;
                editor.putBoolean(PREFERENCE_HOME, b);
                editor.commit();
                break;

            case R.id.cb_electronic:
                mChecks[1] = b;
                editor.putBoolean(PREFERENCE_ELECTRONIC, b);
                editor.commit();
                break;

            case R.id.cb_sport:
                mChecks[2] = b;
                editor.putBoolean(PREFERENCE_SPORT, b);
                editor.commit();
                break;

            case R.id.cb_importance:
                mChecks[3] = b;
                editor.putBoolean(PREFERENCE_IMPORTANCE, b);
                editor.commit();
                break;
        }
    }
}
