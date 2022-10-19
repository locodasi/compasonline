package com.example.ComprasOnline.controllers;

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
import org.springframework.web.servlet.ModelAndView;

import com.example.ComprasOnline.helpers.ViewRouteHelper;
import com.example.ComprasOnline.models.ProductoModelo;
import com.example.ComprasOnline.models.RubroModelo;
import com.example.ComprasOnline.services.IRubroService;

@Controller
@RequestMapping("productos")
public class ProductosController {

	@Autowired
	@Qualifier("rubroService")
	private IRubroService rubroService;
	
	@GetMapping("verProductos")
	public ModelAndView verProductos() {
		
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		ModelAndView mv = new ModelAndView(ViewRouteHelper.PRODUCTOS_VER);
		mv.addObject("usuario",user.getUsername());
		return mv;
	}
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("crearProductos")
	public ModelAndView crearProductos(Model model) {
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView mv = new ModelAndView(ViewRouteHelper.PRODUCTOS_CREAR);
		model.addAttribute("rubro", new RubroModelo());
		model.addAttribute("producto", new ProductoModelo());
		model.addAttribute("listaRubros", rubroService.getAll());
		mv.addObject("usuario",user.getUsername());
		return mv;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("nuevoRubro")
	public ModelAndView nuevoUsuario(@Valid @ModelAttribute("rubro") RubroModelo rubro, BindingResult b) {
		ModelAndView mv = new ModelAndView();
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mv.addObject("usuario",user.getUsername());
		if(b.hasErrors()) {
			mv.setViewName(ViewRouteHelper.PRODUCTOS_CREAR);
		}else {
			
			rubroService.insertOrUpdate(rubro);
	
			mv.setViewName(ViewRouteHelper.PRODUCTOS_CREAR);
			mv.addObject("rubro", new RubroModelo());
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
		model.addAttribute("listaRubros", rubroService.getAll());
		ModelAndView mv = new ModelAndView(ViewRouteHelper.PRODUCTOS_CREAR);
		mv.addObject("usuario",user.getUsername());
		return mv;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("borrarRubro/{id}")
	public ModelAndView borrarRubro(@PathVariable("id") int id, Model model) {

		rubroService.remove(id);
		
		return editarProductos();
	}
	
	
}
