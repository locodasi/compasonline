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

import com.example.ComprasOnline.models.ProductoModelo;
import com.example.ComprasOnline.models.UsuarioModelo;

@Entity
@Table(name="comentario")
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne()
	@JoinColumn (name="id_usuario")
	private User usuario;
	
	@ManyToOne()
	@JoinColumn (name="id_producto")
	private Producto producto;
	
	@Column(name="comentario")
	private String comentario;
	
	@Column(name="enviado")
	@CreationTimestamp
	private LocalDateTime enviado;
	
	public Comentario () {}

	public Comentario(int id, User usuario, Producto producto, String comentario) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.producto = producto;
		this.comentario = comentario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
	
}
