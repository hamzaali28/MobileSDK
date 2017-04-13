/*
 *  * *****************************************
 *  *  * Copyright (c) 2017.
 *  *  * Alexey Baskakov
 *  *  *
 *  *  * PropellerAds
 *  *  * http://propellerads.com
 *  *  *****************************************
 */

package com.propellerads.sample.samples.native_ad.recycler.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.propellerads.sample.R;
import com.propellerads.sample.samples.native_ad.recycler.elements.NativeAdElement;
import com.propellerads.sdk.common.ads.native_ad.PropellerAdsNativeAd;

public class NativeAdViewHolder extends RecyclerView.ViewHolder {
    NativeAdElement mElement;

    TextView mTitle;
    TextView mText;
    ImageView mIcon;

    public NativeAdViewHolder(View view) {
        super(view);
        mTitle = (TextView) view.findViewById(R.id.title);
        mText = (TextView) view.findViewById(R.id.text);
        mIcon = (ImageView) view.findViewById(R.id.icon);
    }

    public void setItem(NativeAdElement element) {
        //If you you re-use the view to show different ads, make sure to call unregisterView
        //before registering the same view with a different instance of PropellerAdsNativeAd
        if(mElement!=null){
            PropellerAdsNativeAd nativeAd = mElement.getPropellerAdsNativeAd();
            if(nativeAd!=null){
                nativeAd.unregisterView(itemView);
            }
        }

        mElement = element;
        PropellerAdsNativeAd propelleradsNativeAd = element.getPropellerAdsNativeAd();
        mTitle.setText(propelleradsNativeAd.getTitle());
        mText.setText(propelleradsNativeAd.getText());
        propelleradsNativeAd.displayIcon(mIcon);
        //If you don't call registerView, impression and click will not work!
        propelleradsNativeAd.registerView(itemView);
    }
}
