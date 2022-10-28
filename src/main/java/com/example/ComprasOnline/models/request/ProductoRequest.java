package com.example.ComprasOnline.models.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductoRequest {

	@JsonProperty("Id")
	private int id;
	
	@JsonProperty("Costo")
	private float costo;
	
	@JsonProperty("Descripcion")
	private String descripcion;
	
	@JsonProperty("Nombre")
	private String nombre;
	
	//Datos imagen
	@JsonProperty("Url")
	private String url;
	
	@JsonProperty("Formato")
	private String formato;
	
	@JsonProperty("Rubros")
	private List<Integer> rubros;

	public ProductoRequest() {
		
	}
	
	
	


	
	

	public ProductoRequest(int id, float costo, String descripcion, String nombre, String url, String formato,
			List<Integer> rubros) {
		super();
		this.id = id;
		this.costo = costo;
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.url = url;
		this.formato = formato;
		this.rubros = rubros;
	}








	public ProductoRequest(int id, float costo, String descripcion, String nombre, List<Integer> rubros) {
	super();
	this.id = id;
	this.costo = costo;
	this.descripcion = descripcion;
	this.nombre = nombre;
	this.rubros = rubros;
}



	@Override
	public String toString() {
		return "ProductoRequest [id=" + id + ", costo=" + costo + ", descripcion=" + descripcion + ", nombre=" + nombre
				+ ", url=" + url + ", formato=" + formato + ", rubros=" + rubros + "]";
	}








	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public List<Integer> getRubros() {
		return rubros;
	}

	public void setRubros(List<Integer> rubros) {
		this.rubros = rubros;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}
	
	

}
