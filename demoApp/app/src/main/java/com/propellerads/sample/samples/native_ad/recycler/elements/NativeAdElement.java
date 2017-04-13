/*
 *  * *****************************************
 *  *  * Copyright (c) 2017.
 *  *  * Alexey Baskakov
 *  *  *
 *  *  * PropellerAds
 *  *  * http://propellerads.com
 *  *  *****************************************
 */

package com.propellerads.sample.samples.native_ad.recycler.elements;

import com.propellerads.sdk.common.ads.native_ad.PropellerAdsNativeAd;

public class NativeAdElement extends ElementBase {
    PropellerAdsNativeAd mPropellerAdsNativeAd;

    public NativeAdElement(PropellerAdsNativeAd propelleradsNativeAd) {
        this.mPropellerAdsNativeAd = propelleradsNativeAd;
    }

    public PropellerAdsNativeAd getPropellerAdsNativeAd() {
        return mPropellerAdsNativeAd;
    }
}
