package com.example.ComprasOnline.models;


import javax.validation.constraints.NotBlank;



public class MensajeModelo {

	private int id;
	private UsuarioModelo usuario_emisor;
	private UsuarioModelo usuario_receptor;
	
	@NotBlank
	private String mensaje;
	
	public MensajeModelo() {}

	public MensajeModelo(UsuarioModelo usuario_emisor, UsuarioModelo usuario_receptor, @NotBlank String mensaje) {
		super();
		this.usuario_emisor = usuario_emisor;
		this.usuario_receptor = usuario_receptor;
		this.mensaje = mensaje;
	}

	public UsuarioModelo getUsuario_emisor() {
		return usuario_emisor;
	}

	public void setUsuario_emisor(UsuarioModelo usuario_emisor) {
		this.usuario_emisor = usuario_emisor;
	}

	public UsuarioModelo getUsuario_receptor() {
		return usuario_receptor;
	}

	public void setUsuario_receptor(UsuarioModelo usuario_receptor) {
		this.usuario_receptor = usuario_receptor;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	

	@Override
	public String toString() {
		return "MensajeModelo [usuario_emisor=" + usuario_emisor + ", usuario_receptor=" + usuario_receptor
				+ ", mensaje=" + mensaje + "]";
	}
	
	
}
