package com.example.ComprasOnline.models;

import javax.validation.constraints.NotBlank;

public class ComentarioModelo {

	private UsuarioModelo usuario;
	
	private ProductoModelo producto;
	
	@NotBlank
	private String comentario;
	
	public ComentarioModelo() {}

	public ComentarioModelo(UsuarioModelo usuario, ProductoModelo producto, @NotBlank String comentario) {
		super();
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

	@Override
	public String toString() {
		return "ComentarioModelo [usuario=" + usuario + ", producto=" + producto + ", comentario=" + comentario + "]";
	}
	
	
}
