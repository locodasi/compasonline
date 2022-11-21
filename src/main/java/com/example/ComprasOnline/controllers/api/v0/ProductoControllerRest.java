package com.example.ComprasOnline.controllers.api.v0;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ComprasOnline.entities.Producto;
import com.example.ComprasOnline.models.ImagenModelo;
import com.example.ComprasOnline.models.ProductoModelo;
import com.example.ComprasOnline.models.RubroModelo;
import com.example.ComprasOnline.models.request.ProductoRequest;
import com.example.ComprasOnline.services.IProductoServices;
import com.example.ComprasOnline.services.IRubroService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/v0/producto")
public class ProductoControllerRest {
	
	@Autowired
	@Qualifier("rubroService")
	private IRubroService rubroService;
	
	@Autowired
	@Qualifier("productoService")
	private IProductoServices productoService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary="Agrega un producto y sus derivados", description = "Se guarda en la base de datos el producto y su correspondiente imagen, rubros, y datos.")
	@PostMapping("/agregarProducto")
	public ResponseEntity<Object> agregar(@RequestBody ProductoRequest producto){
		
		Object body = "";
		HttpStatus status = HttpStatus.CONFLICT;
		
		ProductoModelo p = new ProductoModelo();
		ImagenModelo i = new ImagenModelo();
		
		i.setFormato(producto.getFormato());
		i.setUrl(producto.getUrl());
		
		p.setCosto(producto.getCosto());
		p.setDescripcion(producto.getDescripcion());
		p.setNombre(producto.getNombre());
		p.setImagen(i);
		
		Set<RubroModelo> rubros = new HashSet<>();
		for (Integer id_rubro : producto.getRubros()) {
			rubros.add(rubroService.traerPorId(id_rubro));
		}
		
		p.setRubros(rubros);
		
		System.out.println(p);
		
		try {
			 ProductoModelo productoAgregado = productoService.insert(p); 
			body = productoAgregado;
			status = HttpStatus.CREATED;
		} catch (Exception e) {
			body = "Error de la excepcion: " + e.getMessage(); 
		}
		
		return ResponseEntity.status(status).body(body);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary="Eliminar producto por id", description = "Elimina al producto con su determinado id y todas sus relaciones de rubro e imagen")
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Object> eliminarPersona(@PathVariable("id") int id) {
		
	
	    boolean isRemoved = productoService.remove(id);
	 
	    		
	    if (!isRemoved) {
	    	Object body ="No se puedo eliminar";
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
	    }

	    return ResponseEntity.status(HttpStatus.OK).body("Eliminado id: " +id);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary="Lista de productos", description = "Devuelve la lista de productos con su imagen y rubros")
	@GetMapping("/listaDeProductos")
	public ResponseEntity<Object> todasLasPersonasBD(){
		
		
		List<ProductoModelo> listaProductos = productoService.getAllModel();
//		System.out.println("d");
		//System.out.println(listaProductos);
		//System.out.println(productoService.traerPorId(1));
		Object body = "Lista vacia";
				
		if(listaProductos != null) {
			body = listaProductos;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(body);
		
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary="Edita un producto, su imagen y rubros", description = "Se actualizan los datos de la tabla producto, producto_rubro y imagen.")
	@PutMapping("/editarProducto/{id}")
	public ResponseEntity<Object> editar(@PathVariable int id, 
			@RequestBody ProductoRequest producto){
		
	
		Object body = "";
		HttpStatus status = HttpStatus.CONFLICT;
		
		
		ProductoModelo p = productoService.traerPorId(id);
		
		p.setCosto(producto.getCosto());
		p.setDescripcion(producto.getDescripcion());
		p.setNombre(producto.getNombre());
		p.getImagen().setFormato(producto.getFormato());
		p.getImagen().setUrl(producto.getUrl());
		
		Set<RubroModelo> rubros = new HashSet<>();
		for (Integer id_rubro : producto.getRubros()) {
			rubros.add(rubroService.traerPorId(id_rubro));
		}
		
		p.setRubros(rubros);
		
		
		try {
			 ProductoModelo productoAgregado = productoService.update(p); 
			body = productoAgregado;
			status = HttpStatus.CREATED;
		} catch (Exception e) {
			body = "Error de la excepcion: " + e.getMessage(); 
		}
		
		return ResponseEntity.status(status).body(body);
	}
}
