package com.example.ComprasOnline.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ImagenModelo {

	private int id;
	
	@NotBlank
	private String url;
	
	@NotBlank
	private String formato;
	
	
	//No se si es necesario tenerlo como atributo ni en el constructor
//	@NotNull
//	private ProductoModelo producto;

	public ImagenModelo(int id, @NotBlank String url, @NotBlank String formato) {
		super();
		this.id = id;
		this.url = url;
		this.formato = formato;
	}
	
	public ImagenModelo() {
		this.url=null;
	}

//	public ImagenModelo(int id2, String url2, String formato2, ProductoModelo entityToModel) {
//		// TODO Auto-generated constructor stub
//		super();
//		this.id = id;
//		this.url = url;
//		this.formato = formato;
//		this.producto = entityToModel;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "ImagenModelo [id=" + id + ", url=" + url + ", formato=" + formato + "]";
	}

//	public ProductoModelo getProducto() {
//		return producto;
//	}
//
//	public void setProducto(ProductoModelo producto) {
//		this.producto = producto;
//	}
	
	
	
}
