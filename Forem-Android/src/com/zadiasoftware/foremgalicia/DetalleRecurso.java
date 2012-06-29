/*
 * Copyright (C) 2011 Zadia Software for FOREM Galicia 
 *
 * Licensed under the GPL License V3
 */
package com.zadiasoftware.foremgalicia;

import greendroid.app.GDActivity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;

import org.apache.http.util.ByteArrayBuffer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.zadiasoftware.foremgalicia.beans.Recurso;
import com.zadiasoftware.foremgalicia.handlers.XMLReader;
import com.zadiasoftware.util.Log;

public class DetalleRecurso extends GDActivity {

	Recurso recurso = new Recurso();
	private ProgressDialog m_ProgressDialog = null;
	private Runnable cargarDetalles;
	Locale locale;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		try {

			Log.i("ZADIA", "Inicializando DetalleRecurso");

			super.onCreate(savedInstanceState);

			/*** IDIOMA ***/
			SharedPreferences settings = PreferenceManager
					.getDefaultSharedPreferences(this);
			Configuration config = getBaseContext().getResources()
					.getConfiguration();

			String lang = settings.getString("idioma", "");
			if (!"".equals(lang) && !config.locale.getLanguage().equals(lang)) {
				locale = new Locale(lang);
				Locale.setDefault(locale);
				config.locale = locale;
				getBaseContext().getResources().updateConfiguration(config,
						getBaseContext().getResources().getDisplayMetrics());
			}
			/** FIN IDIOMA */

			setActionBarContentView(R.layout.detalle_recurso);

			recurso = FOREMApplication.recursoActual;
			Log.i("ZADIA", "Recurso actual: " + recurso.getNombre());

			getActionBar().setTitle(recurso.getNombre());

			TextView titulo = (TextView) findViewById(R.id.title);
			if (titulo != null)
				titulo.setText(recurso.getNombre());

			/*TextView sncp = (TextView) findViewById(R.id.sncp);
			if (sncp != null) {
				if (recurso.getSncp().equalsIgnoreCase("")) {
					sncp.setText("");// sncp.setText(R.string.nosncp);
				} else
					sncp.setText(recurso.getSncp());

			}*/

			TextView familia = (TextView) findViewById(R.id.familia);
			if (familia != null)
				familia.setText(recurso.getFamiliaSncp());

			TextView cualificacion = (TextView) findViewById(R.id.cualificacion);
			if (cualificacion != null)
				cualificacion.setText(recurso.getCualificacionSncp());

			TextView unidad = (TextView) findViewById(R.id.unidad);
			if (unidad != null)
				unidad.setText(recurso.getUnidadeSncp());

			TextView tipoRecurso = (TextView) findViewById(R.id.tipoRecurso);
			if (tipoRecurso != null)
				tipoRecurso.setText(recurso.getTipoRecursoEducativo());

			TextView recursoTv = (TextView) findViewById(R.id.recurso);
			if (recurso != null) {
				if (recurso.getRecurso().equalsIgnoreCase("")) {
					recursoTv.setText(R.string.norecurso);
				} else {
					recursoTv.setText(recurso.getRecurso());

					if (recurso.getBytes() != null
							&& !recurso.getBytes().equals(""))
						recursoTv.setText(recursoTv.getText() + " "
								+ recurso.getBytes() + " bytes.");
				}

			}

			Button boton = (Button) findViewById(R.id.botonRecurso);
			if (boton != null) {
				boton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View vv) {
						if (!recurso.getRecurso().equalsIgnoreCase("")) {
							Toast.makeText(DetalleRecurso.this,
									R.string.descargandoRecurso,
									Toast.LENGTH_LONG).show();
							try {
								Intent browserIntent = new Intent(
										Intent.ACTION_VIEW, Uri.parse(recurso
												.getRecurso()));
								startActivity(browserIntent);
							} catch (Exception e) {
								Toast.makeText(DetalleRecurso.this,
										R.string.hahabidoerrordescargando,
										Toast.LENGTH_LONG).show();
								Log.e("ZADIA",
										"Error descargando fichero de URL "
												+ recurso.getUrl());
								e.printStackTrace();
							}
						} else {
							Toast.makeText(DetalleRecurso.this,
									R.string.nohayurl, Toast.LENGTH_LONG)
									.show();
						}
					}
				});
			}

			Button botonFicha = (Button) findViewById(R.id.botonFicha);
			if (botonFicha != null) {
				botonFicha.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View vv) {
						if (recurso.getUrl() != null && recurso.getUrl() != "") {
							Intent i = new Intent(Intent.ACTION_VIEW);
							i.setData(Uri.parse(recurso.getUrl()));
							startActivity(i);
						} else {
							Toast.makeText(DetalleRecurso.this,
									R.string.nohayficha, Toast.LENGTH_LONG)
									.show();
						}

					}
				});
			}
			
						cargarDetalles = new Runnable() {
				@Override
				public void run() {
					getDatos();
				}
			};
			Thread thread = new Thread(null, cargarDetalles,
					"ParseDetallesRecurso");
			thread.start();
			m_ProgressDialog = ProgressDialog.show(DetalleRecurso.this,
					getResources().getString(R.string.cargando), getResources()
							.getString(R.string.cargando_informacion), true);

		} catch (Exception e) {
			Log.e("ZADIA",
					"Error instanciando DetalleRecurso: " + e.getMessage());
			e.printStackTrace();
			this.finish();
		}
	}

	private Runnable returnRec = new Runnable() {
		@Override
		public void run() {
			m_ProgressDialog.dismiss();
			try {
				TextView tipoRecurso = (TextView) findViewById(R.id.tipoRecurso);
				if (tipoRecurso != null) {
					/*String[] tipos = getResources().getStringArray(
							R.array.tiposRecursos);
					int tipoRec = Integer
							.parseInt(recurso.tipoRecursoEducativo);
					tipoRecurso.setText(tipos[tipoRec-1]);*/
					tipoRecurso.setText(recurso.getTipoRecursoEducativo());
				}
			} catch (Exception e) {
				Log.e("ZADIA", "Error gestionando recurso: " + e.getMessage());
			}
		}
	};

	private void getDatos() {
		try {
			recurso = XMLReader.getDetallesElemento(recurso.getNid());
		} catch (Exception e) {
			Log.e("ZADIA", "Error obteniendo recursos: " + e.getMessage());
		}
		runOnUiThread(returnRec);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
		shareIntent.setType("text/plain");
		shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
				R.string.compartirtitulo);
		shareIntent.putExtra(
				android.content.Intent.EXTRA_TEXT,
				getResources().getString(R.string.compartirtexto) + "\""
						+ recurso.getNombre() + "\""
						+ getResources().getString(R.string.compartirtexto2)
						+ recurso.getUrl() + "\n\n"
						+ getResources().getString(R.string.firma));

		startActivity(Intent.createChooser(shareIntent, getResources()
				.getString(R.string.compartir)));
		return true;
	}
}
