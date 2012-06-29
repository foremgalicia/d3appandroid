/*
 * Copyright (C) 2011 Zadia Software for FOREM Galicia 
 *
 * Licensed under the GPL License V3
 */
package com.zadiasoftware.foremgalicia.handlers;


import java.util.List;

import android.util.Log;

import com.zadiasoftware.foremgalicia.beans.Recurso;
import com.zadiasoftware.foremgalicia.beans.RSS;

public class XMLReader {

	
	public static List<Recurso> getElementosFamilia(int familia) {
		String feed = "http://www.proxectodesire.eu/xmlall/familia/" + familia;
		Log.d("ZADIA", "Cargando feed RSS: " + feed);
		
		XMLHandler rh = new XMLHandler();
		List<Recurso> articles = rh.getRecursos(feed, false);

		return articles;
	}

	public static Recurso getDetallesElemento(String el) {
		String feed = "http://www.proxectodesire.eu/xml/" + el;
		Log.d("ZADIA", "Cargando XML: " + feed);		
		XMLHandler rh = new XMLHandler();
		Recurso article = rh.getRecursos(feed, true).get(0);

		return article;
	}		
	
	public static List<RSS> getTweets() {
		//alertnativa: http://twitter.com/statuses/user_timeline/proxectodesire.xml
		String feed = "http://api.twitter.com/1/statuses/user_timeline.rss?screen_name=proxectodesire";
		Log.d("ZADIA", "Cargando feed Twitter: " + feed);		
		RSSHandler th = new RSSHandler();
		List<RSS> list = th.getElementosRSS(feed);

		return list;
	}	
	
	public static List<RSS> getPosts(){
		String feed = "http://www.proxectodesire.eu/feed";
		Log.d("ZADIA", "Cargando feed blog: " + feed);		
		RSSHandler th = new RSSHandler();
		List<RSS> list = th.getElementosRSS(feed);
		return list;		
	}


}
