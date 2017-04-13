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

import android.content.Context;

public abstract class MenuElement {

    String mTitle;

    public MenuElement(String title) {
        this.mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public abstract void click(Context context);

}
