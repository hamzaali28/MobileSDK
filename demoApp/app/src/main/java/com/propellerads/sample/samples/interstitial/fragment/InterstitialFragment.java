/*
 *  * *****************************************
 *  *  * Copyright (c) 2017.
 *  *  * Alexey Baskakov
 *  *  *
 *  *  * PropellerAds
 *  *  * http://propellerads.com
 *  *  *****************************************
 */

package com.propellerads.sample.samples.interstitial.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.propellerads.sample.R;
import com.propellerads.sample.utiles.ToastHelper;
import com.propellerads.sdk.common.ads.FailResponse;
import com.propellerads.sdk.common.ads.interstitial.IPropellerAdsInterstitialListener;
import com.propellerads.sdk.common.ads.interstitial.PropellerAdsInterstitial;

import java.util.Locale;

public class InterstitialFragment extends Fragment {
    private static final int ZONE_ID = 1175287;
    View mButtonLoad;
    View mButtonShow;
    EditText mEditText;

    PropellerAdsInterstitial mPropellerAdsInterstitial;
    private View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(
                R.layout.fragment_interstitial,
                container,
                false
        );

        mButtonLoad = mRootView.findViewById(R.id.button_load);
        mButtonShow = mRootView.findViewById(R.id.button_show);
        mEditText = (EditText) mRootView.findViewById(R.id.edit_text);
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

        return mRootView;
    }

    private void loadInterstitial() {
        mButtonShow.setEnabled(false);
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
        mPropellerAdsInterstitial = new PropellerAdsInterstitial(getActivity(), getZoneId());
        mPropellerAdsInterstitial.addListener(new IPropellerAdsInterstitialListener() {
            @Override
            public void loaded(PropellerAdsInterstitial interstitial) {
                ToastHelper.showToast(getActivity(), "loaded");
                mButtonShow.setEnabled(true);
            }

            @Override
            public void failed(PropellerAdsInterstitial interstitial, FailResponse response) {
                ToastHelper.showToast(getActivity(), String.format(Locale.getDefault(), "failed: %d - %s", response.getStatusCode(), response.getResponseString()));
            }

            @Override
            public void shown(PropellerAdsInterstitial interstitial) {
                ToastHelper.showToast(getActivity(), "shown");
            }

            @Override
            public void clicked(PropellerAdsInterstitial interstitial) {
                ToastHelper.showToast(getActivity(), "clicked");
            }

            @Override
            public void closed(PropellerAdsInterstitial interstitial) {
                ToastHelper.showToast(getActivity(), "closed");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPropellerAdsInterstitial != null) {
            mPropellerAdsInterstitial.onDestroy();
            mPropellerAdsInterstitial = null;
        }
    }

}
