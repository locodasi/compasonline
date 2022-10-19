package com.example.ComprasOnline.entities;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.example.ComprasOnline.models.RubroModelo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="producto")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="costo")
	private float costo;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Comentario> comentarios = new HashSet<>(); 
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name="producto_rubro", joinColumns = @JoinColumn(name="producto_id"),inverseJoinColumns =@JoinColumn(name="rubro_id"))
	@JsonIgnoreProperties("producto")
	private Set<Rubro> rubros = new HashSet<>(); 
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_imagen",referencedColumnName = "id")
	@JsonIgnore
	private Imagen imagen; 
	

	public Set<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Set<Rubro> getRubros() {
		return rubros;
	}

	public void setRubros(Set<Rubro> rubros) {
		this.rubros = rubros;
	}
	
	public Imagen getImagen() {
		return imagen;
	}

	public void setImagen(Imagen imagen) {
		this.imagen = imagen;
	}

	public Producto() {}
	
	
	

	public Producto(int id, String nombre, float costo, String descripcion, Set<Rubro> rubros, Imagen imagen) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.costo = costo;
		this.descripcion = descripcion;
		this.rubros = rubros;
		this.imagen = imagen;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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



	public Imagen getImagenes() {
		return imagen;
	}

	public Producto(int id, String nombre, float costo, String descripcion, Imagen imagen) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.costo = costo;
		this.descripcion = descripcion;
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", costo=" + costo + ", descripcion=" + descripcion
				+ ", rubros=" + rubros + ", imagen=" + imagen + "]";
	}
	
	
	

}
