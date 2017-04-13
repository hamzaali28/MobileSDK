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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.propellerads.sample.R;
import com.propellerads.sample.samples.native_ad.recycler.NewsAdapter;
import com.propellerads.sample.samples.native_ad.recycler.elements.ElementBase;
import com.propellerads.sample.samples.native_ad.recycler.elements.NativeAdElement;
import com.propellerads.sample.samples.native_ad.recycler.elements.NewsElement;
import com.propellerads.sample.utiles.ToastHelper;
import com.propellerads.sdk.common.ads.FailResponse;
import com.propellerads.sdk.common.ads.native_ad.PropellerAdsNativeAd;
import com.propellerads.sdk.common.ads.native_ad.IPropellerAdsNativeAdListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class NativeAdRecyclerViewActivity extends AppCompatActivity {
    private static final int ITEM_COUNT = 15;
    private static final int AD_POSITION = 7;
    private static final int ZONE_ID = 917214;
    private Toolbar mToolBar;
    private RecyclerView mRecyclerView;
    private NewsAdapter mNewsAdapter;
    private List<ElementBase> mNews;
    private List<PropellerAdsNativeAd> mPropellerAdsNativeAds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_ad_recycler_view);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setHasFixedSize(true);

        mNews = new LinkedList<>();
        for (int i = 0; i < ITEM_COUNT; i++) {
            mNews.add(new NewsElement(String.format("%s news item", i + 1)));
        }

        mNewsAdapter = new NewsAdapter(this, mNews);

        mRecyclerView.setAdapter(mNewsAdapter);

        initNativeAd();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    private void initNativeAd() {
        int count = ITEM_COUNT / AD_POSITION;
        for (int i = 0; i < count; i++) {
            final int position = AD_POSITION * i;

            final PropellerAdsNativeAd propellerAdsNativeAd = new PropellerAdsNativeAd(this, ZONE_ID);
            mPropellerAdsNativeAds.add(propellerAdsNativeAd);
            propellerAdsNativeAd.addListener(new IPropellerAdsNativeAdListener() {
                @Override
                public void loaded(PropellerAdsNativeAd nativeAd) {
                    ToastHelper.showToast(NativeAdRecyclerViewActivity.this, "loaded");
                    mNews.add(position, new NativeAdElement(propellerAdsNativeAd));
                    mNewsAdapter.notifyItemInserted(position);
                }

                @Override
                public void failed(PropellerAdsNativeAd nativeAd, FailResponse response) {
                    ToastHelper.showToast(NativeAdRecyclerViewActivity.this, String.format(Locale.getDefault(), "failed: %d - %s", response.getStatusCode(), response.getResponseString()));
                }

                @Override
                public void shown(PropellerAdsNativeAd nativeAd) {
                    ToastHelper.showToast(NativeAdRecyclerViewActivity.this, "shown");
                }

                @Override
                public void clickUrlOpened(PropellerAdsNativeAd nativeAd) {
                    ToastHelper.showToast(NativeAdRecyclerViewActivity.this, "clickUrlOpened");
                }

                @Override
                public void expired(PropellerAdsNativeAd nativeAd) {
                    ToastHelper.showToast(NativeAdRecyclerViewActivity.this, "expired");
                }
            });
            propellerAdsNativeAd.load();
        }
    }

    @Override
    protected void onDestroy() {
        for (PropellerAdsNativeAd propellerAdsNativeAd : mPropellerAdsNativeAds) {
            propellerAdsNativeAd.onDestroy();
        }
        super.onDestroy();
    }
}



