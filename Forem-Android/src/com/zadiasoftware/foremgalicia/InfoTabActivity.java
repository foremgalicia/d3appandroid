/*
 * Copyright (C) 2011 Zadia Software for FOREM Galicia 
 *
 * Licensed under the GPL License V3
 */

package com.zadiasoftware.foremgalicia;

import java.util.Locale;

import greendroid.app.ActionBarActivity;
import greendroid.app.GDTabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

public class InfoTabActivity extends GDTabActivity {
	
	Locale locale;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
		/***IDIOMA***/
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        Configuration config = getBaseContext().getResources().getConfiguration();

        String lang = settings.getString("idioma", "");
        if (! "".equals(lang) && ! config.locale.getLanguage().equals(lang))
        {
            locale = new Locale(lang);
            Locale.setDefault(locale);
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }			
		/**FIN IDIOMA*/
        
        setContentView(R.layout.info);
        
        final String aboutText =  getString(R.string.about);
        final Intent aboutIntent = new Intent(this, AcercaDe.class);
        aboutIntent.putExtra(ActionBarActivity.GD_ACTION_BAR_VISIBILITY, View.GONE);
        addTab(aboutText, aboutText, aboutIntent);

        final String licenseText =  getString(R.string.license);
        final Intent licenseIntent = new Intent(this, WebContentActivity.class);
        licenseIntent.putExtra(ActionBarActivity.GD_ACTION_BAR_VISIBILITY, View.GONE);
        licenseIntent.putExtra(WebContentActivity.EXTRA_CONTENT_URL, "file:///android_asset/LICENSE.txt");
        addTab(licenseText, licenseText, licenseIntent);
    }

    public void onAppUrlClicked(View v) {
        final Uri appUri = Uri.parse(getString(R.string.app_url));
        startActivity(new Intent(Intent.ACTION_VIEW, appUri));
    }
}
