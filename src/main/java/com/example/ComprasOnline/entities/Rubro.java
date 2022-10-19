package com.example.ComprasOnline.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="rubro")
public class Rubro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@ManyToMany(mappedBy = "rubros")
	@JsonIgnoreProperties("rubros")
	private Set<Producto> Producto;
	
	public Set<Producto> getProducto() {
		return Producto;
	}

	public void setProducto(Set<Producto> producto) {
		Producto = producto;
	}

	

	public Rubro(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}
	
	public Rubro() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Rubro [id=" + id + ", descripcion=" + descripcion + ", Producto=" + Producto + "]";
	}
	
	
}
