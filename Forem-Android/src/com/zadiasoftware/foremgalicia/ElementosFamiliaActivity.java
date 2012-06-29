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

import java.util.ArrayList;
import java.util.Locale;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cyrilmottier.android.greendroid.R;
import com.zadiasoftware.foremgalicia.beans.Recurso;
import com.zadiasoftware.foremgalicia.handlers.XMLReader;

public class ElementosFamiliaActivity extends GDListActivity {

	private ListView listado;

	private ProgressDialog m_ProgressDialog = null;
	private ArrayList<Recurso> recursos = null;
	private ItemAdapter adaptador;
	private Runnable viewRecursos;
	private RecursosAdapter rAdaptador;
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
		
		int familia = FOREMApplication.familiaActual;
		
		listado = getListView();

		recursos = new ArrayList<Recurso>();
		adaptador = new ItemAdapter(this);
		rAdaptador = new RecursosAdapter(this, R.layout.recursos_elemento_lista, recursos);
		listado.setAdapter(rAdaptador);
		//listado.setAdapter(adaptador);

		viewRecursos = new Runnable() {
			@Override
			public void run() {
				getRecursos();
			}
		};
		Thread thread = new Thread(null, viewRecursos, "ParseRecursos");
		thread.start();
		m_ProgressDialog = ProgressDialog.show(ElementosFamiliaActivity.this, getResources().getString(R.string.cargando),
				getResources().getString(R.string.cargando_informacion), true);

		setTheme(R.style.listaRecursos);
		getListView().setBackgroundColor(Color.parseColor("#edf1f4"));
		insertaActionBar();
	}

	@SuppressWarnings("deprecation")
	private void insertaActionBar() {
		// actionbar

		getActionBar().setTitle(getResources().getString(R.string.catalogorecursos));

		addActionBarItem(
				getActionBar().newActionBarItem(NormalActionBarItem.class)
						.setDrawable(
								new ActionBarDrawable(getResources(),
										R.drawable.ic_action_bar_info)),
				R.id.action_bar_view_info);
	}
	
	private Runnable returnRec = new Runnable() {
		@Override
		public void run() {
			if (recursos != null && recursos.size() > 0) {
				for (int i = 0; i < recursos.size(); i++){
					rAdaptador.add(recursos.get(i));
				}
				rAdaptador.notifyDataSetChanged();
			}else{
				AlertDialog.Builder builder = new AlertDialog.Builder(ElementosFamiliaActivity.this);
				builder.setMessage(R.string.nohayrecursos)
				       .setCancelable(true)
				       .setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
				           @Override
						public void onClick(DialogInterface dialog, int id) {
				                ElementosFamiliaActivity.this.finish();
				           }
				       });
				AlertDialog alert = builder.create();
				alert.show();
			}
			m_ProgressDialog.dismiss();
			adaptador.notifyDataSetChanged();
		}
	};
	
	private void getRecursos() {
		try {
			SharedPreferences sp = PreferenceManager
					.getDefaultSharedPreferences(this);
			recursos = (ArrayList<Recurso>) XMLReader.getElementosFamilia(FOREMApplication.familiaActual);
			Log.i("ZADIA", "Tamanho del array de recursos: " +  recursos.size());
		} catch (Exception e) {
			Log.e("ZADIA", "Error obteniendo recursos: " + e.getMessage());
		}
		runOnUiThread(returnRec);
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
		Log.i("ZADIA", "Click en fila " + position + ", abriendo DetalleRecurso");
		FOREMApplication.setRecursoActual(recursos.get(position));
		startActivity(new Intent(this, DetalleRecurso.class));			
	}

	private class RecursosAdapter extends ArrayAdapter<Recurso> {

		private ArrayList<Recurso> items;

		public RecursosAdapter(Context context, int textViewResourceId,
				ArrayList<Recurso> items) {
			super(context, textViewResourceId, items);
			this.items = items;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.recursos_elemento_lista, null);
			}
			
			Recurso o = items.get(position);
			if (o != null) {
				TextView tt = (TextView) v.findViewById(R.id.texto);
				if (tt != null)
					tt.setText(o.getNombre() );
			}
			
			

			return v;
		}
	}	
	
}
