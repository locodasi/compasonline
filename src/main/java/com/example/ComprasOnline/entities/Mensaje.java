package com.example.ComprasOnline.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.example.ComprasOnline.models.UsuarioModelo;

@Entity
@Table(name="mensaje")
public class Mensaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn (name="id_emisor")
	private User usuario_emisor; // aca aun tengo que agrega la nomenclatura de relacion y hacerlo en todas las que los tipos de datos sean entidades
	
	@ManyToOne
	@JoinColumn (name="id_receptor")
	private User usuario_receptor;
	
	@Column(name="mensaje")
	private String mensaje;
	
	@Column(name="enviado")
	@CreationTimestamp
	private LocalDateTime enviado;

	public Mensaje(int id, User usuario_emisor, User usuario_receptor, String mensaje) {
		super();
		this.id = id;
		this.usuario_emisor = usuario_emisor;
		this.usuario_receptor = usuario_receptor;
		this.mensaje = mensaje;
	}
	
	public Mensaje () {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUsuario_emisor() {
		return usuario_emisor;
	}

	public void setUsuario_emisor(User usuario_emisor) {
		this.usuario_emisor = usuario_emisor;
	}

	public User getUsuario_receptor() {
		return usuario_receptor;
	}

	public void setUsuario_receptor(User usuario_receptor) {
		this.usuario_receptor = usuario_receptor;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "Mensaje [id=" + id + ", usuario_emisor=" + usuario_emisor + ", usuario_receptor=" + usuario_receptor
				+ ", mensaje=" + mensaje + ", enviado=" + enviado + "]";
	}
	
	
}
