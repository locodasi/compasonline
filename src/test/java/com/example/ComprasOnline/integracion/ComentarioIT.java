package com.example.ComprasOnline.integracion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ComprasOnline.converters.UserConverter;
import com.example.ComprasOnline.entities.Comentario;
import com.example.ComprasOnline.entities.User;
import com.example.ComprasOnline.models.ComentarioModelo;
import com.example.ComprasOnline.models.ImagenModelo;
import com.example.ComprasOnline.models.ProductoModelo;
import com.example.ComprasOnline.models.RubroModelo;
import com.example.ComprasOnline.models.UsuarioModelo;
import com.example.ComprasOnline.services.IComentarioService;
import com.example.ComprasOnline.services.IImagenService;
import com.example.ComprasOnline.services.IRubroService;
import com.example.ComprasOnline.services.IUserServices;

@SpringBootTest
public class ComentarioIT {

	@Autowired
	@Qualifier("comentarioService")
	private IComentarioService cometarioService;
	
	@Autowired
	@Qualifier("userService")
	private IUserServices userService;
	
	@Autowired
	@Qualifier("userConverter")
	private UserConverter userConverter;
	
	@Autowired
	@Qualifier("rubroService")
	private IRubroService rubroService;
	
	@Test
	void InsertComen() {
		
		UsuarioModelo user = userConverter.entityToModel(userService.buscarUsuario("admin"));
		Set<RubroModelo> l = new HashSet<>();
		l.addAll(rubroService.getAllModel());
		
		ProductoModelo p = new ProductoModelo(2, null, 0, null, l, new ImagenModelo());
		ComentarioModelo c= new ComentarioModelo(0,user,p,"hola");
		ComentarioModelo c2 = new ComentarioModelo(); 
		c2= cometarioService.insertOrUpdate(c);
		
		Assertions.assertTrue(c.getComentario()== c2.getComentario());	
		//fail();
		
	}
	
//	@Test
//	void BorrarComen() {
//		cometarioService.remove(1);
//	}
//	
//	@Test
//	void TraerComen() {
//		List<Comentario> listaC = new ArrayList<>();
//		
//		listaC.addAll(cometarioService.getAll());
//		
//		for (Comentario comentario : listaC) {
//			System.out.println(comentario.getUsuario().getUsuario() + ": " + comentario.getComentario() + " Sobre: " + comentario.getProducto().getNombre());
//			
//		}
//	}
}
