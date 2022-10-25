package com.example.ComprasOnline.models;

import java.util.List;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductoModelo {

	private int id;
	@NotBlank
	private String nombre;
	
	@Min(1)
	private float costo;
	
	
	private String descripcion;
	
	@NotEmpty
	@NotNull
	private Set<RubroModelo> rubros;
	
	@NotNull
	private String imagen;

	public ProductoModelo() {}
	public ProductoModelo(int id,@NotBlank String nombre, @Min(1) float costo,  String descripcion,
			@NotEmpty @NotNull Set<RubroModelo> rubros,  @NotNull String imagen) {
		super();
		this.id=id;
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

	public Set<RubroModelo> getRubros() {
		return rubros;
	}

	public void setRubros(Set<RubroModelo> rubros) {
		this.rubros = rubros;
	}


	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	@Override
	public String toString() {
		return "ProductoModelo [nombre=" + nombre + ", costo=" + costo + ", descripcion=" + descripcion + ", rubros="
				+ rubros + ", imagenes=" + imagen + "]";
	}
	
	
}
