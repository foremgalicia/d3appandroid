/*
 * Copyright (C) 2011 Zadia Software for FOREM Galicia 
 *
 * Licensed under the GPL License V3
 */
package com.zadiasoftware.foremgalicia;

import java.util.Locale;

import greendroid.app.GDActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.zadiasoftware.util.Log;

public class Desire3 extends GDActivity {

	private Locale locale;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			
			Log.i("ZADIA", "Inicializando Desire3(inicio)");
			
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
	        
			setActionBarContentView(R.layout.inicio);

			Button verCatalogo = (Button) this.findViewById(R.id.catalogo);
			verCatalogo.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent myIntent = new Intent(Desire3.this, FOREM.class);
					startActivityForResult(myIntent, 0);
				}
			});

			Button web = (Button) this.findViewById(R.id.visitarweb);
			web.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
				/*	String url = getResources().getString(R.string.app_url);
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(url));
					startActivity(i);
					*/
					Intent myIntent = new Intent(Desire3.this, Posts.class);
					startActivityForResult(myIntent, 0);
				}
			});
			
			Button contacto = (Button) this.findViewById(R.id.contacto);
			contacto.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					/*String url = "http://www.proxectodesire.eu/contact";
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(url));
					startActivity(i);*/
					//formato de email nativo
					final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);				
					emailIntent .setType("plain/text");				
					emailIntent .putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"proxectodesire@gmail.com"});					 
					emailIntent .putExtra(android.content.Intent.EXTRA_SUBJECT, getResources().getString(R.string.email_asunto));				 
					//emailIntent .putExtra(android.content.Intent.EXTRA_TEXT, R.string.email_texto);				 
					startActivity(Intent.createChooser(emailIntent, getResources().getString(R.string.email_titulo)));
				}
			});		
			
			ImageButton twitter = (ImageButton) this.findViewById(R.id.twitter);
			twitter.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent myIntent = new Intent(Desire3.this, Twitter.class);
					startActivityForResult(myIntent, 0);					
				}
			});				

			ImageButton slideshare = (ImageButton) this.findViewById(R.id.slideshare);
			slideshare.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					String url = "http://www.slideshare.net/foremgalicia";
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(url));
					startActivity(i);
				}
			});	

			ImageButton vimeo = (ImageButton) this.findViewById(R.id.vimeo);
			vimeo.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					String url = "http://vimeo.com/foremgalicia";
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(url));
					startActivity(i);
				}
			});				
			

		} catch (Exception e) {
			Log.e("ZADIA",
					"Error instanciando Desire3(inicio): " + e.getMessage());
			e.printStackTrace();
			this.finish();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.settings:
			Intent i = new Intent(Desire3.this, Preferencias.class);
			startActivity(i);
			break;
			
		case R.id.shareTitle:
			Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
			
			shareIntent.setType("text/plain");
			shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,getResources().getString(R.string.menu_share_subject));
			shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, 
					getResources().getString(R.string.menu_share_generic) + "\n\n" + getResources().getString(R.string.firma));
			
			startActivity(Intent.createChooser(shareIntent, getResources().getString(R.string.compartir)));

		}
		return true;
	}	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}
	
	@Override
	public void onResume() {
		try {			
			Log.i("ZADIA", "Restaurando Desire3(inicio)");
			super.onResume();
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
		}catch(Exception e){
			Log.e("ZADIA",
					"Error restaurando Desire3(inicio): " + e.getMessage());
			
		}
	}
	
	
}
