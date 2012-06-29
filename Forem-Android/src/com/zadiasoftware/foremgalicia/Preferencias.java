/*
 * Copyright (C) 2011 Zadia Software for FOREM Galicia 
 *
 * Licensed under the GPL License V3
 */

package com.zadiasoftware.foremgalicia;

import java.util.Locale;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class Preferencias extends PreferenceActivity{
	Locale locale;
   @Override
  protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          addPreferencesFromResource(R.layout.preferencias);
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
   }
   
   public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
	   String languageToLoad  = "gl";
	   Locale locale = new Locale(languageToLoad);
	   Locale.setDefault(locale);
	   Configuration config = new Configuration();
	   config.locale = locale;
	   getBaseContext().getResources().updateConfiguration(config, null);

   }
}