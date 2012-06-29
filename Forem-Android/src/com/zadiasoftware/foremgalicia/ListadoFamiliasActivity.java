/*
 * Copyright (C) 2011 Zadia Software for FOREM Galicia 
 *
 * Licensed under the GPL License V3
 */
package com.zadiasoftware.foremgalicia;

import greendroid.app.GDListActivity;

import java.util.ArrayList;
import java.util.Locale;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListadoFamiliasActivity extends GDListActivity{

	Locale locale;
	
	ArrayList<String> FAMILIAS;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// creando interfaz...
		try {
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
			super.onCreate(savedInstanceState);
			setContentView(R.layout.principal);

			setListAdapter(new ArrayAdapter<String>(this,
					R.layout.listado_categorias_element, FAMILIAS));
			
			ListView lv = getListView();
			lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			    @Override
			    public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
			        onListItemClick(v,pos,id);
			    }
			});

			getListView().setBackgroundColor(Color.parseColor("#edf1f4"));


		} catch (Exception e) {
			e.getLocalizedMessage();
			this.finish();
		}
	}

	protected void onListItemClick(View v, int pos, long id) {
		Intent myIntent = new Intent(this, DetalleRecurso.class);
		myIntent.putExtra("tab", 0);
		startActivityForResult(myIntent, 0);
	}
	
	
	@Override
	public void onResume() {
		try {
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
	        
			super.onResume();
		} catch (Exception e) {
			// Log.e("PistaOculta",
			// "Error Reanudando actividade PistaOcultaMain: "
			// + e.getMessage());
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	// Called when menu item is selected //
	// @Override
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
			Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
			shareIntent.setType("text/plain");
			// asunto del compartido
			shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
					getText(R.string.menu_share_subject));
			// cuerpo del compartido
			shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,
					getText(R.string.menu_share_generic) + "\n\n"
							+ getText(R.string.menu_share_body));

			startActivity(Intent.createChooser(shareIntent,
					getText(R.string.menu_share_intent)));
			
			return true;
	}

}
