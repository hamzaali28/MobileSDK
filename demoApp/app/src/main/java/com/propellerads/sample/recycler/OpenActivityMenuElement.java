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
import android.content.Intent;

public class OpenActivityMenuElement extends MenuElement {
    Class mActivityClass;

    public OpenActivityMenuElement(String title, Class activityClass) {
        super(title);
        this.mActivityClass = activityClass;
    }

    @Override
    public void click(Context context) {
        Intent intent = new Intent(context, mActivityClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
