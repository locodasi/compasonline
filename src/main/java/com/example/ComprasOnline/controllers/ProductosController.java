package com.example.ComprasOnline.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.ComprasOnline.entities.Producto;
import com.example.ComprasOnline.entities.Rubro;
import com.example.ComprasOnline.helpers.BusquedaHelper;
import com.example.ComprasOnline.helpers.FuncionesHelper;
import com.example.ComprasOnline.helpers.ViewRouteHelper;
import com.example.ComprasOnline.models.ComentarioModelo;
import com.example.ComprasOnline.models.ImagenModelo;
import com.example.ComprasOnline.models.ProductoModelo;
import com.example.ComprasOnline.models.RubroModelo;
import com.example.ComprasOnline.models.request.ComentarioRequest;
import com.example.ComprasOnline.services.IComentarioService;
import com.example.ComprasOnline.entities.Rubro;
import com.example.ComprasOnline.helpers.ViewRouteHelper;
import com.example.ComprasOnline.models.ImagenModelo;
import com.example.ComprasOnline.models.ProductoModelo;
import com.example.ComprasOnline.models.RubroModelo;
import com.example.ComprasOnline.services.IProductoServices;
import com.example.ComprasOnline.services.IRubroService;
import com.example.ComprasOnline.services.IUserServices;
import com.example.ComprasOnline.services.implementation.UserService;

@Controller
@RequestMapping("productos")
public class ProductosController {

	private List<ComentarioModelo> comens = new ArrayList<>();
	
	private ProductoModelo pro = new ProductoModelo();
	
	private FuncionesHelper funciones = new FuncionesHelper();
	@Autowired
	@Qualifier("rubroService")
	private IRubroService rubroService;
	
	@Autowired
	@Qualifier("comentarioService")
	private IComentarioService comentarioService;
	
	@Autowired
	@Qualifier("productoService")
	private IProductoServices productoService;
	
	@Autowired
	@Qualifier("userService")
	private IUserServices userService;
	
	private ModelAndView mv = new ModelAndView();
	
	private BusquedaHelper busqueda = new BusquedaHelper();
	
	private List <Producto> productos = new ArrayList<>();
	
	@GetMapping("verProductos")
	public ModelAndView verProductos() {
		
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		ModelAndView mv = new ModelAndView(ViewRouteHelper.PRODUCTOS_VER);
		mv.addObject("usuario",user.getUsername());
		productos.clear();
		productos.addAll(productoService.getAll());
		mv.addObject("listaProductos", productos);
		List<Rubro> rubros = new ArrayList<>();
		rubros.add(new Rubro(0, "Todos"));
		rubros.addAll(rubroService.getAll());
		mv.addObject("rubrosLista", rubros);
		mv.addObject("busqueda", new BusquedaHelper());
		return mv;
	}
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("crearProductos")
	public ModelAndView crearProductos(ModelAndView model) {
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.setViewName(ViewRouteHelper.PRODUCTOS_CREAR);
		model.addObject("rubro", new RubroModelo());
		model.addObject("producto", new ProductoModelo());
		model.addObject("listaRubros", rubroService.getAll());
		model.addObject("usuario",user.getUsername());
		mv.addObject("error", 0); 
		mv = model;
		return mv;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("nuevoRubro")
	public ModelAndView nuevoUsuario(@Valid @ModelAttribute("rubro") RubroModelo rubro, BindingResult b) {
//		ModelAndView mv = new ModelAndView();
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mv.addObject("usuario",user.getUsername());
		if(b.hasErrors()) {
			mv.addObject("rubro",rubro);
			mv.addObject("producto", new ProductoModelo());

			System.out.println(b);
			mv.setViewName(ViewRouteHelper.PRODUCTOS_CREAR);
		}else {
			
			rubroService.insertOrUpdate(rubro);
	
			mv.setViewName(ViewRouteHelper.PRODUCTOS_CREAR);

			mv.addObject("producto", new ProductoModelo());
			mv.addObject("listaRubros", rubroService.getAll());
		}
		
		return mv;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("editarProductos")
	public ModelAndView editarProductos() {
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView mv = new ModelAndView(ViewRouteHelper.PRODUCTOS_EDITAR);
		mv.addObject("listaRubros", rubroService.getAll());
		mv.addObject("listaProductos", productoService.getAll());
		mv.addObject("usuario",user.getUsername());
		return mv;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("editarRubro/{id}")
	public ModelAndView editarRubro(@PathVariable("id") int id, Model model) {
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		RubroModelo rubro = rubroService.traerPorId(id);
		//System.out.println(rubro);
		model.addAttribute("rubro", rubro);
		model.addAttribute("producto", new ProductoModelo());
		model.addAttribute("listaRubros", rubroService.getAllModel());
		ModelAndView mv = new ModelAndView(ViewRouteHelper.PRODUCTOS_CREAR);
		mv.addObject("usuario",user.getUsername());
		return mv;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("borrarRubro/{id}")
	public ModelAndView borrarRubro(@PathVariable("id") int id, Model model) {

		
		if(rubroService.remove(id)) {
			return editarProductos();
		}else {
			return new ModelAndView("error/noBorrarRubro");
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("nuevoProducto")
	public ModelAndView nuevoProducto(@Valid @ModelAttribute("producto") ProductoModelo producto, BindingResult b, @RequestParam("file") MultipartFile imagen) {
//		ModelAndView mv = new ModelAndView();

		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		for (int id : producto.getRubrosid()) {

			producto.getRubros().add(rubroService.traerPorId(id));
		}
		
		//System.out.println("dddd " + producto);
		System.out.println("dddd " + producto);
		if(!imagen.isEmpty()) {
			Path directorioImagenes = Paths.get("src//main//resources//static/images");
			
			String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			
			try {
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//"+imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
				
				
				//System.out.println("gggg " + producto);
				System.out.println("gggg " + producto);
				if(producto.getImagen().getId()== 0  || producto.getImagen()== null) {
					ImagenModelo im = new ImagenModelo();
					im.setUrl(imagen.getOriginalFilename());
					
					
					producto.setImagen(im);
				}else {
					producto.getImagen().setUrl(imagen.getOriginalFilename());
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println(producto);
		mv.addObject("usuario",user.getUsername());
		if(b.hasErrors() ) {
			System.out.println("h");

			mv.addObject("producto", producto);
			//mv.addObject("listaRubros", rubroService.getAll());
			mv.setViewName(ViewRouteHelper.PRODUCTOS_CREAR);

		}else if(imagen.isEmpty() && producto.getImagen().getUrl().length()==0){
			mv.addObject("producto", producto);
			mv.addObject("error", 1); 
		
		}else {
			
			
			if(producto.getId()!= 0) {
				productoService.update(producto);
			}else {
				productoService.insert(producto);
			}
			
	
			mv.setViewName(ViewRouteHelper.PRODUCTOS_CREAR);
			mv.addObject("rubro", new RubroModelo());
			mv.addObject("error", 0); 
			mv.addObject("producto", new ProductoModelo());
			mv.addObject("listaRubros", rubroService.getAll());
		}
		
		return mv;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("borrarProducto/{id}")
	public ModelAndView borrarProducto(@PathVariable("id") int id, Model model) {

		productoService.remove(id);
		
		return editarProductos();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("editarProducto/{id}")
	public ModelAndView editarProducto(@PathVariable("id") int id, Model model) {
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ProductoModelo p = productoService.traerPorId(id);
		//System.out.println(rubro);
		model.addAttribute("rubro", new Rubro());
		model.addAttribute("producto", p);
		model.addAttribute("listaRubros", rubroService.getAllModel());
		ModelAndView mv = new ModelAndView(ViewRouteHelper.PRODUCTOS_CREAR);
		mv.addObject("usuario",user.getUsername());
		return mv;
	}
	@GetMapping("busqueda2")
	public ModelAndView b2() {
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView mv = new ModelAndView();
		mv.setViewName(ViewRouteHelper.PRODUCTOS_VER);
		mv.addObject("usuario",user.getUsername());
		mv.addObject("listaProductos", funciones.getProductosBusqueda(productos, busqueda));
		List<Rubro> rubros = new ArrayList<>();
		rubros.add(new Rubro(0, "Todos"));
		rubros.addAll(rubroService.getAll());
		mv.addObject("rubrosLista", rubros);
		mv.addObject("busqueda", this.busqueda);
		return mv;
	}
	@PostMapping("busqueda")
	public ModelAndView busqueda(@Valid @ModelAttribute("busqueda") BusquedaHelper busqueda, BindingResult b) {
		this.busqueda= busqueda;
		System.out.println("dsdsad");
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView mv = new ModelAndView();
		if(b.hasErrors()) {
			
			mv.setViewName(ViewRouteHelper.PRODUCTOS_VER);
			mv.addObject("usuario",user.getUsername());
			mv.addObject("listaProductos", productos);
			List<Rubro> rubros = new ArrayList<>();
			rubros.add(new Rubro(0, "Todos"));
			rubros.addAll(rubroService.getAll());
			mv.addObject("rubrosLista", rubros);
			
			this.busqueda = new BusquedaHelper();
			mv.addObject("busqueda", this.busqueda);
		}else {
			mv.setViewName(ViewRouteHelper.PRODUCTOS_VER);
			mv.addObject("usuario",user.getUsername());
			mv.addObject("listaProductos", funciones.getProductosBusqueda(productos, busqueda));
			List<Rubro> rubros = new ArrayList<>();
			rubros.add(new Rubro(0, "Todos"));
			rubros.addAll(rubroService.getAll());
			mv.addObject("rubrosLista", rubros);
			mv.addObject("busqueda", this.busqueda);
		}
		
		
		return mv;
	}
	
	@GetMapping("detalleProducto/{id}")
	public ModelAndView vistaDEtallada(@PathVariable("id") int id, Model model) {
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 pro = productoService.traerPorId(id);
		//System.out.println(rubro);
		
		model.addAttribute("producto", pro);
		
		comens.clear();
		for (ComentarioModelo comentario : comentarioService.getAllModel()) {
			
			if(comentario.getProducto().getId()==id) {
				comens.add(comentario);
			}
		}
		
		model.addAttribute("comentarios", comens);
		model.addAttribute("comen", new ComentarioModelo());
		ModelAndView mv = new ModelAndView(ViewRouteHelper.PRODUCTOS_DETALLE);
		mv.addObject("usuario",user.getUsername());
		return mv;
	}
	
	@PostMapping("nuevoComen")
	public ModelAndView nuevoComen( @ModelAttribute("comen") ComentarioModelo c) {
		ModelAndView mv = new ModelAndView();
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mv.addObject("usuario",user.getUsername());
		System.out.println(c);
		if(c.getComentario()==null || c.getComentario()=="") {
			System.out.println("MAl");
			mv.addObject("producto", pro);
			mv.addObject("comen",c);
			
			
			mv.addObject("comentarios", comens);

			
			mv.setViewName(ViewRouteHelper.PRODUCTOS_DETALLE);
		}else {
			System.out.println("BIEN");
			c.setProducto(pro);
			c.setUsuario(userService.buscarUsuarioModelo(user.getUsername()));
			comens.add(comentarioService.insertOrUpdate(c));
			mv.addObject("producto", pro);
			mv.addObject("comentarios", comens);
			mv.addObject("comen",new ComentarioModelo());
			mv.setViewName(ViewRouteHelper.PRODUCTOS_DETALLE);

		}
		
		return mv;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("borrarCom/{id}")
	public ModelAndView borrarCom(@PathVariable("id") int id, Model model) {
		ModelAndView mv = new ModelAndView();
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mv.addObject("usuario",user.getUsername());
		comens.remove(comentarioService.traerPorId(id));
		comentarioService.remove(id);
		mv.addObject("producto", pro);
		mv.addObject("comentarios", comens);
		mv.addObject("comen",new ComentarioModelo());
		mv.setViewName(ViewRouteHelper.PRODUCTOS_DETALLE);
		return mv;
	}
}
