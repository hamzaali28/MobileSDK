/*
 *  * *****************************************
 *  *  * Copyright (c) 2017.
 *  *  * Alexey Baskakov
 *  *  *
 *  *  * PropellerAds
 *  *  * http://propellerads.com
 *  *  *****************************************
 */

package com.propellerads.sample.samples.direct;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.propellerads.sample.R;
import com.propellerads.sdk.common.ads.direct.PropellerAdsDirect;

public class DirectActivity extends AppCompatActivity {
    private static final int ZONE_ID = 1157584;
    Toolbar mToolBar;
    View mButtonLoad;
    View mButtonShow;
    EditText mEditText;
    PropellerAdsDirect mPropellerAdsDirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

        mButtonLoad = findViewById(R.id.button_load);
        mButtonLoad.setVisibility(View.GONE);
        mButtonShow = findViewById(R.id.button_show);
        mEditText = (EditText) findViewById(R.id.edit_text);
        mEditText.setText(String.valueOf(ZONE_ID));

        mButtonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPropellerAdsDirect = new PropellerAdsDirect(DirectActivity.this, getZoneId());
                mPropellerAdsDirect.show();
            }
        });
    }

    private int getZoneId() {
        String digitsString = String.valueOf(mEditText.getText()).replaceAll("\\D+", "");
        try {
            int zoneId = Integer.parseInt(digitsString);
            return zoneId;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}



