/*
 * Copyright (C) 2011 Zadia Software for FOREM Galicia 
 *
 * Licensed under the GPL License V3
 */
package com.zadiasoftware.foremgalicia.handlers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import com.zadiasoftware.foremgalicia.beans.RSS;
import com.zadiasoftware.util.Log;

public class RSSHandler extends DefaultHandler {

	private RSS  actual = new RSS();
	private List<RSS> lista = new ArrayList<RSS>();


	// Current characters being accumulated
	StringBuffer chars = new StringBuffer();

	/**
	 * This is the entry point to the parser and creates the feed to be parsed
	 * 
	 * @param feedUrl
	 * @return
	 */
	public List<RSS> getElementosRSS(String feedUrl) {
		URL url = null;

		try {

			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();

			url = new URL(feedUrl);

			xr.setContentHandler(this);
			xr.parse(new InputSource(url.openStream()));

		} catch (IOException e) {
			Log.e("RSS Handler IO", e.getMessage() + " >> " + e.toString());
		} catch (SAXException e) {
			Log.e("RSS Handler SAX", e.toString());
		} catch (ParserConfigurationException e) {
			Log.e("RSS Handler Parser Config", e.toString());
		}

		return lista;
	}


	public void startElement(String uri, String localName, String qName,
			Attributes atts) {
		chars = new StringBuffer();		
		if (localName.equalsIgnoreCase("item")) {
			actual = new RSS();
		} 		
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		String valor = chars.toString();
		Log.d("ZADIA", "Leyendo " + localName + ", " + valor);
		if(localName.equalsIgnoreCase("title")){
			actual.title = valor;
		}else if(localName.equalsIgnoreCase("description")){
			actual.description = valor;
		}else if(localName.equalsIgnoreCase("pubDate")){
			actual.pubDate = valor;
		}else if(localName.equalsIgnoreCase("guid")){
			actual.guid = valor;
		}else if(localName.equalsIgnoreCase("link")){
			actual.link = valor;
		}
		
	    else if(localName.equalsIgnoreCase("item")){
			lista.add(actual);
	    }
	}

	/*
	 * This method is called when characters are found in between XML markers,
	 * however, there is no guarante that this will be called at the end of the
	 * node, or that it will be called only once , so we just accumulate these
	 * and then deal with them in endElement() to be sure we have all the text
	 * 
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	public void characters(char ch[], int start, int length) {
		chars.append(new String(ch, start, length));
	}

}
