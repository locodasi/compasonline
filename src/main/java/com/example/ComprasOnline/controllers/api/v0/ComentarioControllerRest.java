package com.example.ComprasOnline.controllers.api.v0;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ComprasOnline.entities.Comentario;
import com.example.ComprasOnline.models.ComentarioModelo;
import com.example.ComprasOnline.models.ProductoModelo;
import com.example.ComprasOnline.models.UsuarioModelo;
import com.example.ComprasOnline.models.request.ComentarioRequest;
import com.example.ComprasOnline.services.IComentarioService;
import com.example.ComprasOnline.services.IProductoServices;
import com.example.ComprasOnline.services.IUserServices;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v0/comentario")
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

	@Operation(summary="Agrega un comentario", description = "Se guarda en la base de datos el comentario")
	@PostMapping("/agregarCom")
	public ResponseEntity<Object> agregar(@RequestBody ComentarioRequest com){
		
		Object body = "";
		HttpStatus status = HttpStatus.CONFLICT;
		
		ProductoModelo p = new ProductoModelo();
		UsuarioModelo u = new UsuarioModelo();
		
		ComentarioModelo c = new ComentarioModelo();
		try {
			 p = productoService.traerPorId(com.getId_producto());
			 u = userService.buscarUsuarioModelo(com.getUsuario());
		}catch (Exception e) {
			body = "Algun valor es invalido: " + e.getMessage(); 
			return ResponseEntity.status(status).body(body);
		}
		

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
	
	@Operation(summary="Eliminar comentario por id", description = "Elimina el comentario")
	@DeleteMapping("/eliminarCom/{id}")
	public ResponseEntity<Object> eliminarComen(@PathVariable("id") int id) {
		
	
	    boolean isRemoved = comentarioService.remove(id);
	 
	    		
	    if (!isRemoved) {
	    	Object body ="No se pudo eliminar";
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	    }

	    return ResponseEntity.status(HttpStatus.OK).body("Eliminado id: " +id);
	}
	
	
	@Operation(summary="Lista de comentarios", description = "Devuelve la lista de comentarios")
	@GetMapping("/listaDeComentarios")
	public ResponseEntity<Object> todosLosComentarios(){
		
		List<ComentarioModelo> listaComen = comentarioService.getAllModel();
		//System.out.println(listaProductos);
		//System.out.println(productoService.traerPorId(1));
		Object body = "Lista vacia";
				System.out.println(listaComen);
		if(listaComen != null) {
			body = listaComen;
		}
//		
		return ResponseEntity.status(HttpStatus.OK).body(body);
		
		
	}
	
	@Operation(summary="Edita un comentario", description = "Se actualizan los datos de la tabla comentario.")
	@PutMapping("/editarComen/{id}")
	public ResponseEntity<Object> editar(@PathVariable int id, 
			@RequestBody ComentarioRequest comentario){
		
	
		Object body = "";
		HttpStatus status = HttpStatus.CONFLICT;
		
		
		ComentarioModelo c = new ComentarioModelo();
		
		try {
			c = comentarioService.traerPorId(id);
			c.setComentario(comentario.getComentario());
			c.setProducto(productoService.traerPorId(comentario.getId_producto()));
			c.setUsuario(userService.buscarUsuarioModelo(comentario.getUsuario()));	
		}catch (Exception e) {
			body = "Alguno de los argumentos es invalido: " + e.getMessage();
			return ResponseEntity.status(status).body(body);
		}
		
			
		
		
		try {
			 ComentarioModelo comAgregado = comentarioService.insertOrUpdate(c); 
			body = comAgregado;
			status = HttpStatus.CREATED;
		} catch (Exception e) {
			body = "Error de la excepcion: " + e.getMessage(); 
		}
		
		return ResponseEntity.status(status).body(body);
	}
}
