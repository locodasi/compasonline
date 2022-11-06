package com.example.ComprasOnline.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="usuario")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@Column(name="usuario", unique=true,nullable=false)
	private String usuario;
	
	@Column(name="contra",nullable=false)
	private String contra;
	
	@Column(name="email")
	private String email;
	
	@Column(name="enabled")
	private boolean enabled;
	
	@Column(name="creado")
	@CreationTimestamp
	private LocalDateTime creado;
	
	@Column(name="actualizado")
	@UpdateTimestamp
	private LocalDateTime actualizado;
	
	
	@OneToMany(mappedBy = "usuario_emisor", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Mensaje> mensajes_enviados = new HashSet<>();
	
	@OneToMany(mappedBy = "usuario_receptor", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Mensaje> mensajes_recibidos = new HashSet<>();
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Comentario> comentarios = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<UserRole> userRoles = new HashSet<>();
	
	public User() {}

	public User(int id, String usuario, String contra, String email, boolean enabled) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.contra = contra;
		this.email = email;
		this.enabled = enabled;
	}
	
	public User(int id, String usuario, String contra, String email, Set<UserRole> roles) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.contra = contra;
		this.email = email;
		this.userRoles = roles;
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

	public String getContra() {
		return contra;
	}

	public void setContra(String contra) {
		this.contra = contra;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public LocalDateTime getCreado() {
		return creado;
	}

	public void setCreado(LocalDateTime creado) {
		this.creado = creado;
	}

	public LocalDateTime getActualizado() {
		return actualizado;
	}

	public void setActualizado(LocalDateTime actualizado) {
		this.actualizado = actualizado;
	}

	public Set<Mensaje> getMensajes_enviados() {
		return mensajes_enviados;
	}

	public void setMensajes_enviados(Set<Mensaje> mensajes_enviados) {
		this.mensajes_enviados = mensajes_enviados;
	}

	public Set<Mensaje> getMensajes_recibidos() {
		return mensajes_recibidos;
	}

	public void setMensajes_recibidos(Set<Mensaje> mensajes_recibidos) {
		this.mensajes_recibidos = mensajes_recibidos;
	}

	public Set<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", usuario=" + usuario + "]";
	}

	

	
}
