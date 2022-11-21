package com.example.ComprasOnline.helpers;

//swagger-ui.html

import java.util.ArrayList;
import java.util.List;

import com.example.ComprasOnline.entities.Producto;
import com.example.ComprasOnline.entities.Rubro;
import com.example.ComprasOnline.entities.User;
import com.example.ComprasOnline.models.MensajeModelo;

public class FuncionesHelper {

	public List<MensajeModelo> getMensajes(List<MensajeModelo> mensajes, User usuario){
		
		List<MensajeModelo> mensajesCorrectos = new ArrayList<>();
		
		for (MensajeModelo mensaje : mensajes) {
			
			if(mensaje.getUsuario_emisor().getId() == usuario.getId() || mensaje.getUsuario_receptor().getId() == usuario.getId()) {
				mensajesCorrectos.add(mensaje);
			}
		}
		
		return mensajesCorrectos;
	}
	
	public List <Producto> getProductosBusqueda(List<Producto> productos, BusquedaHelper busqueda){
		List<Producto> productosBusqueda = new ArrayList<>();
		List<Producto> productosBusqueda3 = new ArrayList<>();
		if(busqueda.getIdRubro()!=0) {
			System.out.println("rurbo");
			productosBusqueda = busquedaPorRubro(productos, productosBusqueda, busqueda.getIdRubro());
			
			System.out.println(productosBusqueda.size());
			if(busqueda.getPrecioMin()!=0) {
				
				for (Producto producto : productosBusqueda) {
					
					if(producto.getCosto()>= busqueda.getPrecioMin()) {
						productosBusqueda3.add(producto);
					}
				}
			}
			System.out.println(productosBusqueda.size());
			
			if(busqueda.getPrecioMax()!=0 && busqueda.getPrecioMin()!=0) {
				List<Producto> productosBusqueda2 = new ArrayList<>();
				
				for (Producto producto : productosBusqueda3) {
					
					if(producto.getCosto()<= busqueda.getPrecioMax()) {
						productosBusqueda2.add(producto);
					}
				}
				
				System.out.println(productosBusqueda2.size());
				return productosBusqueda2;
			}else if(busqueda.getPrecioMax()!=0) {
				List<Producto> productosBusqueda2 = new ArrayList<>();
				
				for (Producto producto : productosBusqueda) {
					
					if(producto.getCosto()<= busqueda.getPrecioMax()) {
						productosBusqueda2.add(producto);
					}
				}
				
				System.out.println(productosBusqueda2.size());
				return productosBusqueda2;
			}
			
			System.out.println(productosBusqueda.size());
			
			if(busqueda.getPrecioMin()!=0) {
				return productosBusqueda3;
			}else {
				return productosBusqueda;
			}
			
			
		}else if (busqueda.getPrecioMin()!=0) {
			
			if(busqueda.getPrecioMax()!=0) {
				
				for (Producto producto : productos) {
					
					if(producto.getCosto() <= busqueda.getPrecioMax() && producto.getCosto() >= busqueda.getPrecioMin()) {
						productosBusqueda.add(producto);
					}
				}
			}else {
				for (Producto producto : productos) {
					
					if(producto.getCosto() >= busqueda.getPrecioMin()) {
						productosBusqueda.add(producto);
					}
				}
			}
			
			return productosBusqueda;
			
		}else if(busqueda.getPrecioMax()!=0) {

			for (Producto producto : productos) {
				
				if(producto.getCosto() <= busqueda.getPrecioMax()) {
					productosBusqueda.add(producto);
				}
			}
			
			return productosBusqueda;
			
		}else {
			return productos;
		}
		
	}
	
	private List <Producto> busquedaPorRubro(List<Producto> productos, List<Producto> productos2,  int valor){
		for (Producto producto : productos) {
			
			for (Rubro r : producto.getRubros()) {
				
				if(r.getId() == valor) {
					productos2.add(producto);
				}
			}
		}
		
		return productos2;
	}
}
		
