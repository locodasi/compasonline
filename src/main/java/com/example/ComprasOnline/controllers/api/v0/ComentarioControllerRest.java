package com.example.ComprasOnline.controllers.api.v0;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ComprasOnline.models.ComentarioModelo;
import com.example.ComprasOnline.models.ImagenModelo;
import com.example.ComprasOnline.models.ProductoModelo;
import com.example.ComprasOnline.models.RubroModelo;
import com.example.ComprasOnline.models.UsuarioModelo;
import com.example.ComprasOnline.models.request.ComentarioRequest;
import com.example.ComprasOnline.models.request.ProductoRequest;
import com.example.ComprasOnline.services.IComentarioService;
import com.example.ComprasOnline.services.IProductoServices;
import com.example.ComprasOnline.services.IRubroService;
import com.example.ComprasOnline.services.IUserServices;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/productos/detalleProducto/api/v0/comentario")
public class ComentarioControllerRest {

	@Autowired
	@Qualifier("userService")
	private IUserServices userService;
	
	@Autowired
	@Qualifier("productoService")
	private IProductoServices productoService;
	
	@Autowired
	@Qualifier("comentarioService")
	private IComentarioService comentarioService;

	@Operation(summary="Agrega un producto y sus derivados", description = "Se guarda en la base de datos el producto y su correspondiente imagen, rubros, y datos.")
	@PostMapping("/agregarCom")
	public ResponseEntity<Object> agregar(@RequestBody ComentarioRequest com){
		
		Object body = "";
		HttpStatus status = HttpStatus.CONFLICT;
		
		ComentarioModelo c = new ComentarioModelo();
		ProductoModelo p = productoService.traerPorId(com.getId_producto());
		UsuarioModelo u = userService.buscarUsuarioModelo(com.getUsuario());

		if(com.getComentario()!="") {
			c.setComentario(com.getComentario());
			c.setProducto(p);
			c.setUsuario(u);
			
			try {
				comentarioService.insertOrUpdate(c);
				body = c;
				status = HttpStatus.CREATED;
			} catch (Exception e) {
				body = "Error de la excepcion: " + e.getMessage(); 
			}
			
		}
		
		
		return ResponseEntity.status(status).body(body);
	}
}
