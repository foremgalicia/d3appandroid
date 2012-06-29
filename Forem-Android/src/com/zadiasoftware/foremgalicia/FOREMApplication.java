/*
 * Copyright (C) 2011 Zadia Software for FOREM Galicia 
 *
 * Licensed under the GPL License V3
 */

package com.zadiasoftware.foremgalicia;

import greendroid.app.GDApplication;
import android.content.Intent;
import android.net.Uri;

import com.zadiasoftware.foremgalicia.beans.Recurso;


public class FOREMApplication extends GDApplication {

    @Override
    public Class<?> getHomeActivityClass() {
        return Desire3.class;
    }
    
    @Override
    public Intent getMainApplicationIntent() {
        return new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.app_url)));
    }
    
    public static int familiaActual;
	public int getFamiliaActual() {
		return familiaActual;
	}
	public static void setFamiliaActual(int fam) {
		familiaActual = fam;
	}
	
    public static Recurso recursoActual;
	public Recurso getRecursoActual() {
		return recursoActual;
	}
	public static void setRecursoActual(Recurso rec) {
		recursoActual = rec;
	}	
    

}
