package com.example.ComprasOnline.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.ComprasOnline.models.ProductoModelo;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Imagen")
public class Imagen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="url")
	private String url;
	
	@Column(name="formato")
	private String formato;
	
	@OneToOne(mappedBy = "imagen")
	@JsonIgnore
	private Producto producto; 

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Imagen(int id, String url, String formato) {
		super();
		this.id = id;
		this.url = url;
		this.formato = formato;
	}
	
	public Imagen() {}

	public Imagen(int id2, String url2, String formato2, Producto modelToEntity) {
		// TODO Auto-generated constructor stub
		super();
		this.id = id;
		this.url = url;
		this.formato = formato;
		this.producto = modelToEntity;
	}

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



	
}
