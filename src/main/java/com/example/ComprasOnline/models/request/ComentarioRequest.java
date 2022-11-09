package com.example.ComprasOnline.models.request;

import javax.validation.constraints.NotBlank;



public class ComentarioRequest {

private int id;
	
	private String usuario;
	
	private int id_producto;
	
	@NotBlank
	private String comentario;
	
	public ComentarioRequest() {}
	

	public ComentarioRequest(int id, String id_usuario, int id_producto, @NotBlank String comentario) {
		super();
		this.id = id;
		this.usuario = id_usuario;
		this.id_producto = id_producto;
		this.comentario = comentario;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	

	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public int getId_producto() {
		return id_producto;
	}


	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}


	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}


	@Override
	public String toString() {
		return "ComentarioRequest [id=" + id + ", usuario=" + usuario + ", id_producto=" + id_producto
				+ ", comentario=" + comentario + "]";
	}
	
	
	
}
