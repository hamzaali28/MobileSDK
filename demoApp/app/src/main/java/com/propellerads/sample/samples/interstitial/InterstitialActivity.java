/*
 *  * *****************************************
 *  *  * Copyright (c) 2017.
 *  *  * Alexey Baskakov
 *  *  *
 *  *  * PropellerAds
 *  *  * http://propellerads.com
 *  *  *****************************************
 */

package com.propellerads.sample.samples.interstitial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.propellerads.sample.R;
import com.propellerads.sample.utiles.ToastHelper;
import com.propellerads.sdk.common.ads.FailResponse;
import com.propellerads.sdk.common.ads.interstitial.IPropellerAdsInterstitialListener;
import com.propellerads.sdk.common.ads.interstitial.PropellerAdsInterstitial;

import java.util.Locale;

public class InterstitialActivity extends AppCompatActivity {
    private static final int ZONE_ID = 1175287;
    Toolbar mToolBar;
    View mButtonLoad;
    View mButtonShow;
    EditText mEditText;
    PropellerAdsInterstitial mPropellerAdsInterstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

        mButtonLoad = findViewById(R.id.button_load);
        mButtonShow = findViewById(R.id.button_show);
        mEditText = (EditText) findViewById(R.id.edit_text);
        mEditText.setText(String.valueOf(ZONE_ID));

        initInterstitial();

        //To ensure a smooth experience, you should pre-fetch the content as soon
        // as your Activity is created, then display it if the fetch was successful.
        loadInterstitial();

        mButtonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadInterstitial();
            }
        });

        mButtonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mButtonShow.setEnabled(false);
                if (mPropellerAdsInterstitial.isLoaded()) {
                    mPropellerAdsInterstitial.show();
                }
            }
        });
    }

    private void loadInterstitial() {
        mButtonShow.setEnabled(false);

        mPropellerAdsInterstitial.setZoneId(getZoneId());
        mPropellerAdsInterstitial.load();
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

    private void initInterstitial() {
        mPropellerAdsInterstitial = new PropellerAdsInterstitial(this, getZoneId());
        mPropellerAdsInterstitial.addListener(new IPropellerAdsInterstitialListener() {
            @Override
            public void loaded(PropellerAdsInterstitial interstitial) {
                ToastHelper.showToast(InterstitialActivity.this, "loaded");
                mButtonShow.setEnabled(true);
            }

            @Override
            public void failed(PropellerAdsInterstitial interstitial, FailResponse response) {
                ToastHelper.showToast(InterstitialActivity.this, String.format(Locale.getDefault(), "failed: %d - %s", response.getStatusCode(), response.getResponseString()));
            }

            @Override
            public void shown(PropellerAdsInterstitial interstitial) {
                ToastHelper.showToast(InterstitialActivity.this, "shown");
            }

            @Override
            public void clicked(PropellerAdsInterstitial interstitial) {
                ToastHelper.showToast(InterstitialActivity.this, "clicked");
            }

            @Override
            public void closed(PropellerAdsInterstitial interstitial) {
                ToastHelper.showToast(InterstitialActivity.this, "closed");
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (mPropellerAdsInterstitial != null) {
            mPropellerAdsInterstitial.onDestroy();
            mPropellerAdsInterstitial = null;
        }
        super.onDestroy();
    }
}



