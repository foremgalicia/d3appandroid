/*
 * Copyright (C) 2011 Zadia Software for FOREM Galicia 
 *
 * Licensed under the GPL License V3
 */
package com.zadiasoftware.foremgalicia.beans;


public class Recurso {

	public static String base_url =  "http://www.proxectodesire.eu/node/";
	
	public long articleId;
	public long feedId;
	public String title = "";
	public String description= "";
	public String media= "";
	public String pubDate= "";
	public String encodedContent= "";

	public String nid= "";
	public String fecha= "";
	public String nombre= "";
	public String familiaSncp= "";
	public String cualificacionSncp= "";
	public String unidadeSncp= "";
	public String url= "";
	public String accionesComplementarias= "";
	public String actividadesEsfuerzo= "";
	public String avaliacion= "";
	public String actividadesRefuerzo= "";
	public String contenidosOrganizados= "";
	public String contribuidor= "";
	public String descripcion= "";
	public String duracion= "";
	public String entidad= "";
	public String idioma= "";
	public String introduccionUOBjetivo= "";
	public String nivelAgregacion= "";
	public String pautasInstalacion= "";
	public String recurso= "";
	public String recursoDatos= "";
	public String bytes = "";
	public String requisitos= "";
	public String revisado= "";
	public String sncp= "";
	public String tipo= "";
	public String tipoInteractividad= "";
	public String tipoRecursoEducativo= "";
	public String recordatorioIdeasClave= "";

	
	/**
	 * @return the articleId
	 */
	public long getArticleId() {
		return articleId;
	}

	/**
	 * @param articleId
	 *            the articleId to set
	 */
	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	/**
	 * @return the feedId
	 */
	public long getFeedId() {
		return feedId;
	}

	/**
	 * @param feedId
	 *            the feedId to set
	 */
	public void setFeedId(long feedId) {
		this.feedId = feedId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return base_url + nid;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setEncodedContent(String encodedContent) {
		this.encodedContent = encodedContent;
	}

	public String getEncodedContent() {
		return encodedContent;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFamiliaSncp() {
		return familiaSncp;
	}

	public void setFamiliaSncp(String familiaSncp) {
		this.familiaSncp = familiaSncp;
	}

	public String getCualificacionSncp() {
		return cualificacionSncp;
	}

	public void setCualificacionSncp(String cualificacionSncp) {
		this.cualificacionSncp = cualificacionSncp;
	}

	public String getUnidadeSncp() {
		return unidadeSncp;
	}

	public void setUnidadeSncp(String unidadeSncp) {
		this.unidadeSncp = unidadeSncp;
	}

	public String getAccionesComplementarias() {
		return accionesComplementarias;
	}

	public void setAccionesComplementarias(String accionesComplementarias) {
		this.accionesComplementarias = accionesComplementarias;
	}

	public String getActividadesEsfuerzo() {
		return actividadesEsfuerzo;
	}

	public void setActividadesEsfuerzo(String actividadesEsfuerzo) {
		this.actividadesEsfuerzo = actividadesEsfuerzo;
	}

	public String getAvaliacion() {
		return avaliacion;
	}

	public void setAvaliacion(String avaliacion) {
		this.avaliacion = avaliacion;
	}

	public String getActividadesRefuerzo() {
		return actividadesRefuerzo;
	}

	public void setActividadesRefuerzo(String actividadesRefuerzo) {
		this.actividadesRefuerzo = actividadesRefuerzo;
	}

	public String getContenidosOrganizados() {
		return contenidosOrganizados;
	}

	public void setContenidosOrganizados(String contenidosOrganizados) {
		this.contenidosOrganizados = contenidosOrganizados;
	}

	public String getContribuidor() {
		return contribuidor;
	}

	public void setContribuidor(String contribuidor) {
		this.contribuidor = contribuidor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getIntroduccionUOBjetivo() {
		return introduccionUOBjetivo;
	}

	public void setIntroduccionUOBjetivo(String introduccionUOBjetivo) {
		this.introduccionUOBjetivo = introduccionUOBjetivo;
	}

	public String getNivelAgregacion() {
		return nivelAgregacion;
	}

	public void setNivelAgregacion(String nivelAgregacion) {
		this.nivelAgregacion = nivelAgregacion;
	}

	public String getPautasInstalacion() {
		return pautasInstalacion;
	}

	public void setPautasInstalacion(String pautasInstalacion) {
		this.pautasInstalacion = pautasInstalacion;
	}

	public String getRecurso() {
		return recurso;
	}

	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}

	public String getRecursoDatos() {
		return recursoDatos;
	}

	public void setRecursoDatos(String recursoDatos) {
		this.recursoDatos = recursoDatos;
	}

	public String getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}

	public String getRevisado() {
		return revisado;
	}

	public void setRevisado(String revisado) {
		this.revisado = revisado;
	}

	public String getSncp() {
		return sncp;
	}

	public void setSncp(String sncp) {
		this.sncp = sncp;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipoInteractividad() {
		return tipoInteractividad;
	}

	public void setTipoInteractividad(String tipoInteractividad) {
		this.tipoInteractividad = tipoInteractividad;
	}

	public String getTipoRecursoEducativo() {
		return tipoRecursoEducativo;
	}

	public void setTipoRecursoEducativo(String tipoRecursoEducativo) {
		this.tipoRecursoEducativo = tipoRecursoEducativo;
	}

	public String getRecordatorioIdeasClave() {
		return recordatorioIdeasClave;
	}

	public void setRecordatorioIdeasClave(String recordatorioIdeasClave) {
		this.recordatorioIdeasClave = recordatorioIdeasClave;
	}

	public String getBytes() {
		return bytes;
	}

	public void setBytes(String bytes) {
		this.bytes = bytes;
	}
	
	

}
