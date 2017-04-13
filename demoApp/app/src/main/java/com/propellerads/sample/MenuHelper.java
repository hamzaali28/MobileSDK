/*
 *  * *****************************************
 *  *  * Copyright (c) 2017.
 *  *  * Alexey Baskakov
 *  *  *
 *  *  * PropellerAds
 *  *  * http://propellerads.com
 *  *  *****************************************
 */

package com.propellerads.sample;

import android.content.Context;

import com.propellerads.sample.recycler.MenuElement;
import com.propellerads.sample.recycler.OpenActivityMenuElement;
import com.propellerads.sample.samples.direct.DirectActivity;
import com.propellerads.sample.samples.interstitial.InterstitialActivity;
import com.propellerads.sample.samples.interstitial.fragment.FragmentInterstitialActivity;
import com.propellerads.sample.samples.native_ad.NativeAdActivity;
import com.propellerads.sample.samples.native_ad.NativeAdRecyclerViewActivity;

import java.util.LinkedList;
import java.util.List;

public class MenuHelper {
    public static List<MenuElement> getMenu(Context context) {
        List<MenuElement> menuElements = new LinkedList<>();
        menuElements.add(new OpenActivityMenuElement(context.getString(R.string.direct_sample), DirectActivity.class));
        menuElements.add(new OpenActivityMenuElement(context.getString(R.string.interstitial_sample), InterstitialActivity.class));
        menuElements.add(new OpenActivityMenuElement(context.getString(R.string.fragment_interstitial_sample), FragmentInterstitialActivity.class));
        menuElements.add(new OpenActivityMenuElement(context.getString(R.string.native_ad_sample), NativeAdActivity.class));
        menuElements.add(new OpenActivityMenuElement(context.getString(R.string.native_ad_recycler_view_sample), NativeAdRecyclerViewActivity.class));
        return menuElements;
    }
}
