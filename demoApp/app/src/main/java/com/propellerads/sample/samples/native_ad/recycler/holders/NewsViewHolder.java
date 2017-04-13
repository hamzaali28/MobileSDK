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
import android.widget.TextView;

import com.propellerads.sample.R;
import com.propellerads.sample.samples.native_ad.recycler.elements.NewsElement;

public class NewsViewHolder extends RecyclerView.ViewHolder {
    NewsElement mElement;
    TextView mTitle;

    public NewsViewHolder(View view) {
        super(view);
        mTitle = (TextView) view.findViewById(R.id.title);
    }

    public void setItem(NewsElement element) {
        mElement = element;
        mTitle.setText(element.getTitle());
    }

}
