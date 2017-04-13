/*
 *  * *****************************************
 *  *  * Copyright (c) 2017.
 *  *  * Alexey Baskakov
 *  *  *
 *  *  * PropellerAds
 *  *  * http://propellerads.com
 *  *  *****************************************
 */

package com.propellerads.sample.samples.native_ad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.propellerads.sample.R;
import com.propellerads.sample.utiles.ToastHelper;
import com.propellerads.sdk.common.ads.FailResponse;
import com.propellerads.sdk.common.ads.native_ad.PropellerAdsNativeAd;
import com.propellerads.sdk.common.ads.native_ad.IPropellerAdsNativeAdListener;

import java.util.Locale;

public class NativeAdActivity extends AppCompatActivity {
    private static final int ZONE_ID = 934105;
    Toolbar mToolBar;
    View mButtonLoad;
    View mButtonShow;
    EditText mEditText;
    CheckBox mPreloadIcon;
    CheckBox mPreloadImage;
    ViewGroup mNativeAdPlace;
    PropellerAdsNativeAd mPropellerAdsNativeAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_ad);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);


        mButtonLoad = findViewById(R.id.button_load);
        mButtonShow = findViewById(R.id.button_show);
        mEditText = (EditText) findViewById(R.id.edit_text);
        mEditText.setText(String.valueOf(ZONE_ID));
        mPreloadIcon = (CheckBox) findViewById(R.id.preload_icon);
        mPreloadImage = (CheckBox) findViewById(R.id.preload_image);
        mNativeAdPlace = (ViewGroup) findViewById(R.id.native_ad_place);

        initNativeAd();

        mPreloadIcon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPropellerAdsNativeAd.preloadIcon(mPreloadIcon.isChecked());
            }
        });

        mPreloadImage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPropellerAdsNativeAd.preloadImage(mPreloadImage.isChecked());
            }
        });

        loadNativeAd();

        mButtonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNativeAd();
            }
        });

        mButtonShow.setVisibility(View.GONE);
    }

    private void loadNativeAd() {
        mPropellerAdsNativeAd.setZoneId(getZoneId());
        mPropellerAdsNativeAd.load();
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

    private void initNativeAd() {
        mPropellerAdsNativeAd = new PropellerAdsNativeAd(this, getZoneId());

        mPropellerAdsNativeAd.addListener(new IPropellerAdsNativeAdListener() {

            @Override
            public void loaded(PropellerAdsNativeAd nativeAd) {
                ToastHelper.showToast(NativeAdActivity.this, "loaded");

                LayoutInflater layoutInflater = LayoutInflater.from(NativeAdActivity.this);
                ViewGroup nativeAdView = (ViewGroup) layoutInflater.inflate(R.layout.native_ad, null);
                TextView title = (TextView) nativeAdView.findViewById(R.id.title);
                TextView text = (TextView) nativeAdView.findViewById(R.id.text);
                TextView rating = (TextView) nativeAdView.findViewById(R.id.rating);
                Button openUrlButton = (Button) nativeAdView.findViewById(R.id.button_open_url);
                ImageView icon = (ImageView) nativeAdView.findViewById(R.id.icon);
                ImageView image = (ImageView) nativeAdView.findViewById(R.id.image);

                title.setText(mPropellerAdsNativeAd.getTitle());
                text.setText(mPropellerAdsNativeAd.getText());
                rating.setText(String.format(getString(R.string.rating), mPropellerAdsNativeAd.getRating()));
                openUrlButton.setText(mPropellerAdsNativeAd.getCta());

                mPropellerAdsNativeAd.displayIcon(icon);
                mPropellerAdsNativeAd.displayImage(image);

                //If you don't call registerView, impression and click will not work!
                mPropellerAdsNativeAd.registerView(openUrlButton);

                mNativeAdPlace.removeAllViews();
                mNativeAdPlace.addView(nativeAdView, new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));


            }

            @Override
            public void failed(PropellerAdsNativeAd nativeAd, FailResponse response) {
                ToastHelper.showToast(NativeAdActivity.this, String.format(Locale.getDefault(), "failed: %d - %s", response.getStatusCode(), response.getResponseString()));
            }

            @Override
            public void shown(PropellerAdsNativeAd nativeAd) {
                ToastHelper.showToast(NativeAdActivity.this, "shown");
            }

            @Override
            public void clickUrlOpened(PropellerAdsNativeAd nativeAd) {
                ToastHelper.showToast(NativeAdActivity.this, "clickUrlOpened");
            }

            @Override
            public void expired(PropellerAdsNativeAd nativeAd) {
                ToastHelper.showToast(NativeAdActivity.this, "expired");
                loadNativeAd();
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (mPropellerAdsNativeAd != null) {
            mPropellerAdsNativeAd.onDestroy();
            mPropellerAdsNativeAd = null;
        }
        super.onDestroy();
    }
}