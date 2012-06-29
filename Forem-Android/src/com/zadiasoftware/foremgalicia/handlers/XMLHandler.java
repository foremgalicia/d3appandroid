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

import com.zadiasoftware.foremgalicia.beans.Recurso;
import com.zadiasoftware.util.Log;

public class XMLHandler extends DefaultHandler {

	private Recurso recursoActual = new Recurso();
	private List<Recurso> listaRecursos = new ArrayList<Recurso>();
	private boolean individual = false;


	// Current characters being accumulated
	StringBuffer chars = new StringBuffer();

	/**
	 * This is the entry point to the parser and creates the feed to be parsed
	 * 
	 * @param feedUrl
	 * @return
	 */
	public List<Recurso> getRecursos(String feedUrl, boolean ind) {
		URL url = null;
		this.individual = ind;
		
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

		return listaRecursos;
	}


	public void startElement(String uri, String localName, String qName,
			Attributes atts) {
		chars = new StringBuffer();		
		if (localName.equalsIgnoreCase("node")){
			recursoActual = new Recurso();
		}else if( individual &&  localName.equalsIgnoreCase("xml")) {
			recursoActual = new Recurso();
		}
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		String valor = chars.toString();
		Log.d("ZADIA", "Leyendo " + localName + ", " + valor);
		if(localName.equalsIgnoreCase("Nid")){
			recursoActual.nid = valor;
		}else if(localName.equalsIgnoreCase("Data")){
			recursoActual.fecha = valor;	
		}else if(localName.equalsIgnoreCase("NomeObxecto")){
			recursoActual.nombre = valor;	
		}else if(localName.equalsIgnoreCase("Familia")){
			recursoActual.familiaSncp = valor;	
		}else if(localName.equalsIgnoreCase("Cualificacion")){
			recursoActual.cualificacionSncp = valor;
		}else if(localName.equalsIgnoreCase("Unidade") || localName.equalsIgnoreCase("Unidad")){
			recursoActual.unidadeSncp = valor;
		}else if(localName.equalsIgnoreCase("URL")){
			recursoActual.url = valor;
		}else if(localName.equalsIgnoreCase("AccionesCcomplementarias0809")){
	        recursoActual.accionesComplementarias = valor;
		}else if(localName.equalsIgnoreCase("ActividadesDeRefuerzo")){
			recursoActual.actividadesRefuerzo = valor;
		}else if(localName.equalsIgnoreCase("Avaliacion")){
			recursoActual.avaliacion = valor;
		}else if(localName.equalsIgnoreCase("ContenidosOrganizados")){
			recursoActual.contenidosOrganizados = valor;
		}else if(localName.equalsIgnoreCase("Contribuidor")){
			recursoActual.contribuidor = valor;
		}else if(localName.equalsIgnoreCase("Descripcion")){
			recursoActual.descripcion = valor;
		}else if(localName.equalsIgnoreCase("Duracion")){
			recursoActual.duracion=valor;
		}else if(localName.equalsIgnoreCase("Entidad")){
			recursoActual.entidad=valor;
		}else if(localName.equalsIgnoreCase("Idioma")){
			recursoActual.idioma=valor;
		}else if(localName.equalsIgnoreCase("IntroducionUObjetivo")){
			recursoActual.introduccionUOBjetivo=valor;
		}else if(localName.equalsIgnoreCase("NivelAgregacion")){
			recursoActual.nivelAgregacion=valor;
		}else if(localName.equalsIgnoreCase("PautasInstalacion")){
			recursoActual.pautasInstalacion=valor;
	    }else if(localName.equalsIgnoreCase("Recurso")){
			recursoActual.recurso=valor;
	    }else if(localName.equalsIgnoreCase("RecursoDatos")){
			recursoActual.recursoDatos=valor;
	    }else if(localName.equalsIgnoreCase("Requisitos")){
			recursoActual.requisitos=valor;
	    }else if(localName.equalsIgnoreCase("Revisado")){
			recursoActual.revisado=valor;
	    }else if(localName.equalsIgnoreCase("SNCP")){
			recursoActual.sncp=valor;
	    }else if(localName.equalsIgnoreCase("Tipo")){
			recursoActual.tipo=valor;
	    }else if(localName.equalsIgnoreCase("TipoInteractividad")){
			recursoActual.tipoInteractividad=valor;
	    }else if(localName.equalsIgnoreCase("TIporecursoeducativo")){	    	
			recursoActual.tipoRecursoEducativo=valor;
	    }else if(localName.equalsIgnoreCase("RecordatorioIdeasClave")){
			recursoActual.recordatorioIdeasClave=valor;
		}else if( localName.equalsIgnoreCase("Urlfichero") || localName.equalsIgnoreCase("Ruta")){
			recursoActual.recurso = valor;
		}else if(localName.equalsIgnoreCase("Bytes")){
			recursoActual.bytes = valor;
		}
		
	    else if(localName.equalsIgnoreCase("node")){
			listaRecursos.add(recursoActual);
	    } else if(individual && localName.equalsIgnoreCase("xml")){
	    	listaRecursos.add(recursoActual);
	    }
	}


	public void characters(char ch[], int start, int length) {
		chars.append(new String(ch, start, length));
	}

}
