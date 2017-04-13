/*
 *  * *****************************************
 *  *  * Copyright (c) 2017.
 *  *  * Alexey Baskakov
 *  *  *
 *  *  * PropellerAds
 *  *  * http://propellerads.com
 *  *  *****************************************
 */

package com.propellerads.sample.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.propellerads.sample.R;

public class MenuElementViewHolder extends RecyclerView.ViewHolder {
    TextView mTitle;
    MenuElement mMenuElement;

    public MenuElementViewHolder(View view) {
        super(view);
        mTitle = (TextView) view.findViewById(R.id.title);
    }

    public void setMenuItem(MenuElement menuElement) {
        mMenuElement = menuElement;
        mTitle.setText(menuElement.getTitle());
    }

}
