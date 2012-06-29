/*
 * Copyright (C) 2011 Zadia Software for FOREM Galicia 
 *
 * Licensed under the GPL License V3
 */
package com.zadiasoftware.foremgalicia;

import greendroid.app.GDListActivity;
import greendroid.graphics.drawable.ActionBarDrawable;
import greendroid.widget.ActionBarItem;
import greendroid.widget.ItemAdapter;
import greendroid.widget.NormalActionBarItem;
import greendroid.widget.item.DescriptionItem;
import greendroid.widget.item.DrawableItem;
import greendroid.widget.item.Item;
import greendroid.widget.item.LongTextItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class FOREM extends GDListActivity {

	List<String> familias = new ArrayList<String>();
	String[] original;
	Locale locale;
	@Override
	public void onCreate(Bundle savedInstanceState) {
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
        
		List<Item> items = new ArrayList<Item>();
		// items.add(new TextItem(getString(R.string.familiasrecursos)));
		items.add(new LongTextItem(getString(R.string.consultarecursos)));

		original = getResources().getStringArray(R.array.familias);

		Collections.addAll(familias, original);
		// Collections.sort(familias);
		Iterator<String> iter = familias.iterator();
		while (iter.hasNext()) {
			Item it = new DrawableItem(iter.next(), R.drawable.flechanaran);
			items.add(it);
		}
		setListAdapter(new ItemAdapter(this, items));
		setTheme(R.style.FilaListado);

		insertaActionBar();
	}

	@SuppressWarnings("deprecation")
	private void insertaActionBar() {
		// actionbar

		getActionBar().setTitle(
				getResources().getString(R.string.proxectodesire));

		addActionBarItem(
				getActionBar().newActionBarItem(NormalActionBarItem.class)
						.setDrawable(
								new ActionBarDrawable(getResources(),
										R.drawable.ic_action_bar_info)),
				R.id.action_bar_view_info);
	}

	@Override
	public boolean onHandleActionBarItemClick(ActionBarItem item, int position) {
		switch (item.getItemId()) {
		case R.id.action_bar_view_info:
			startActivity(new Intent(this, InfoTabActivity.class));
			return true;

		default:
			return super.onHandleActionBarItemClick(item, position);
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		// TODO: recoger id familia
		try {
			if (position != -1) {
				String fam = familias.get(position);

				int posOriginal = -1;
				for (int i = 0; i < original.length; i++) {
					if (original[i] == fam) {
						posOriginal = i;
						break;
					}
				}
				FOREMApplication.setFamiliaActual(posOriginal);
				Log.d("ZADIA", "Cargando familia " + position);
				startActivity(new Intent(this, ElementosFamiliaActivity.class));
			}
		} catch (Exception e) {

		}
	}
	
	
	

}
