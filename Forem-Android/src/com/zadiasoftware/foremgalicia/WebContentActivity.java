/*
 * Copyright (C) 2011 Zadia Software for FOREM Galicia 
 *
 * Licensed under the GPL License V3
 */
package com.zadiasoftware.foremgalicia;

import greendroid.app.GDActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;

public class WebContentActivity extends GDActivity {

    public static final String EXTRA_CONTENT_URL = "com.zadiasoftware.foremgalicia.extra.CONTENT_URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String contentUrl = getIntent().getStringExtra(EXTRA_CONTENT_URL);
        if (!TextUtils.isEmpty(contentUrl)) {
            setActionBarContentView(R.layout.web_view);
            final WebView webView = (WebView) findViewById(R.id.web_view);
                webView.loadUrl(contentUrl);

        }
    }

}
