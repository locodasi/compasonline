package com.example.ComprasOnline.models;

import javax.validation.constraints.NotBlank;

public class ComentarioModelo {

	private int id;
	
	private UsuarioModelo usuario;
	
	private ProductoModelo producto;
	
	@NotBlank
	private String comentario;
	
	public ComentarioModelo() {}

	public ComentarioModelo(int id, UsuarioModelo usuario, ProductoModelo producto, @NotBlank String comentario) {
		super();
		this.id= id;
		this.usuario = usuario;
		this.producto = producto;
		this.comentario = comentario;
	}

	public UsuarioModelo getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioModelo usuario) {
		this.usuario = usuario;
	}

	public ProductoModelo getProducto() {
		return producto;
	}

	public void setProducto(ProductoModelo producto) {
		this.producto = producto;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ComentarioModelo [id=" + id + ", usuario=" + usuario + ", producto=" + producto + ", comentario="
				+ comentario + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj!=null && obj instanceof ComentarioModelo) {
			ComentarioModelo c = (ComentarioModelo) obj;
			if(this.id==c.id) {
				return true;
			}
		}
		return false;
	}

	
	
	
}
