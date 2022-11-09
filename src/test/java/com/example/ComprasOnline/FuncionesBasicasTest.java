package com.example.ComprasOnline;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.ComprasOnline.entities.Comentario;
import com.example.ComprasOnline.entities.Rubro;
import com.example.ComprasOnline.models.ComentarioModelo;
import com.example.ComprasOnline.models.ImagenModelo;
import com.example.ComprasOnline.models.ProductoModelo;
import com.example.ComprasOnline.models.RubroModelo;
import com.example.ComprasOnline.models.UsuarioModelo;
import com.example.ComprasOnline.models.request.ComentarioRequest;
import com.example.ComprasOnline.models.request.ProductoRequest;

public class FuncionesBasicasTest {

	@Test
	void rubroModelo() {

		RubroModelo r= new RubroModelo(1,"hola");
		 r.getId();
		 r.getDescripcion();
		 r.setDescripcion("adios");
		 r.setId(0);
		 

		System.out.println(r);

	}
	
	@Test
	void rubroEntidad() {

		Rubro r= new Rubro(1,"hola");
		 r.getId();
		 r.getDescripcion();
		 r.getProducto();
		 r.setProducto(null);
		 r.setDescripcion("adios");
		 r.setId(0);
		 

		System.out.println(r);

	}
	
	@Test
	void request() {

		ComentarioRequest c = new ComentarioRequest();
		ComentarioRequest c2 = new ComentarioRequest(0, null, 0, null);
		c.setComentario(null);
		c.setId(0);
		c.setId_producto(0);
		c.setUsuario(null);
		c.getComentario();
		c.getId();
		c.getId_producto();
		c.getUsuario();
		System.out.println(c);
		ProductoRequest p = new ProductoRequest();
		ProductoRequest p2 = new ProductoRequest(0, 0, null, null, null);
		p.setCosto(0);
		p.setDescripcion(null);
		p.setFormato(null);
		p.setId(0);
		p.setNombre(null);
		p.setRubros(null);
		p.setUrl(null);
		p.getCosto();
		p.getDescripcion();
		p.getFormato();
		p.getId();
		p.getNombre();
		p.getRubros();
		p.getUrl();
		System.out.println(p);

	}
	
	@Test
	void comentarioModelo() {

		ComentarioModelo r= new ComentarioModelo(0,null, null, null);
		 r.getComentario();
		 r.getProducto();
		 r.getUsuario();
		 r.setComentario(null);
		 r.setProducto(null);
		 r.setUsuario(null);
		 r = new ComentarioModelo();
		 
		System.out.println(r);

	}
	
	@Test
	void comentarioEntidad() {

		Comentario r= new Comentario(0, null, null, null);
		 r.getComentario();
		 r.getProducto();
		 r.getUsuario();
		 r.setComentario(null);
		 r.setProducto(null);
		 r.setUsuario(null);
		 r = new Comentario();
		 
		System.out.println(r);

	}
	
	@Test
	void imagenModelo() {

		ImagenModelo r= new ImagenModelo(1, null, null);
		 r.getId();
		// r.getProducto();
		 r.getFormato();
		 r.getUrl();
		 r.setFormato(null);
		 r.setId(2);
		// r.setProducto(null);
		 r.setUrl(null);
		 
		 r = new ImagenModelo();
		 
		System.out.println(r);

	}
	
	@Test
	void productoModelo() {

		ProductoModelo r= new ProductoModelo(0, null, 0, null, null, null);
		 r.getId();
		 r.getCosto();
		 r.getDescripcion();
		 r.getImagen();
		 r.getNombre();
		 r.getRubros();
		 
		 r.setCosto(0);
		 r.setDescripcion(null);
		 r.setId(0);
		 r.setImagen(null);
		 r.setNombre(null);
		 r.setRubros(null);
		 
		 
		 r = new ProductoModelo();
		 
		System.out.println(r);

	}
	
	@Test
	void usuarioModelo() {

		UsuarioModelo r= new UsuarioModelo(0, null, null, null);
		 r.getId();
		 r.getContra();
		 r.getEmail();
		 r.getUsuario();

		 
		 r.setContra(null);
		 r.setEmail(null);
		 r.setId(0);
		 r.setUsuario(null);
		 
		 
		 r = new UsuarioModelo();
		 
		System.out.println(r);

	}
}
