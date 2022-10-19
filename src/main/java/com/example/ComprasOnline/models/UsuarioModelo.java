package com.example.ComprasOnline.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

public class UsuarioModelo {

	private int id;
	
	@NotBlank
	private String usuario;
	
	@Length(min=4)
	private String contra;
	
	@NotNull
	@Email 
	private String email;
	
	public UsuarioModelo() {}


	public UsuarioModelo(int id, @NotBlank String usuario, @Length(min = 4) String contra,
			@NotNull @Email String email) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.contra = contra;
		this.email = email;
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


	@Override
	public String toString() {
		return "UsuarioModelo [id=" + id + ", usuario=" + usuario + ", contra=" + contra + ", email=" + email + "]";
	}
	
}
