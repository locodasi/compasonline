package com.example.ComprasOnline.models;

import javax.validation.constraints.NotBlank;

public class RubroModelo {

	private int id;
	@NotBlank
	private String descripcion;
	
	public RubroModelo () {}

	public RubroModelo(int id,@NotBlank String descripcion) {
		super();
		this.id=id;
		this.descripcion = descripcion;
	}

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
		return "RubroModelo [id=" + id + ", descripcion=" + descripcion + "]";
	}


	
	
}
