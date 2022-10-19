package com.example.ComprasOnline.controllers;

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
import org.springframework.web.servlet.ModelAndView;

import com.example.ComprasOnline.entities.Mensaje;
import com.example.ComprasOnline.helpers.FuncionesHelper;
import com.example.ComprasOnline.helpers.ViewRouteHelper;
import com.example.ComprasOnline.models.MensajeModelo;
import com.example.ComprasOnline.models.UsuarioModelo;
import com.example.ComprasOnline.services.IMensajeService;
import com.example.ComprasOnline.services.IUserServices;
import com.example.ComprasOnline.services.implementation.MensajeService;

@Controller
@RequestMapping("mensajes")
public class MensajesController {

	@Autowired
	@Qualifier("mensajeService")
	private IMensajeService mensajeService;
	
	@Autowired
	@Qualifier("userService")
	private IUserServices userService;
	
	private com.example.ComprasOnline.entities.User yo;
	private com.example.ComprasOnline.entities.User otro;
	private List<MensajeModelo> mensajes = new ArrayList<>();
	
	@GetMapping("verMensajes")
	public ModelAndView verMensajes(Model model) {
		
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		ModelAndView mv = new ModelAndView(ViewRouteHelper.MENSAJES_VER);
		mv.addObject("usuario",user.getUsername());
		
		mensajes =  mensajeService.getAllModel();
		
		FuncionesHelper helper = new FuncionesHelper();
		
		if (user.getUsername() != "admin") {
			yo = userService.buscarUsuario(user.getUsername());
			otro = userService.buscarUsuario("admin");
			mv.addObject("mensajes", helper.getMensajes(mensajes, yo));
		}
		
		model.addAttribute("usuarios", userService.getAll());
		model.addAttribute("mensaje", new MensajeModelo());
		return mv;
	}
	

	@PostMapping("nuevoMensaje")
	public ModelAndView nuevoUsuario(@Valid @ModelAttribute("mensaje") MensajeModelo mensaje, BindingResult b) {
		ModelAndView mv = new ModelAndView();
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mv.addObject("usuario",user.getUsername());
		FuncionesHelper helper = new FuncionesHelper();
		if(b.hasErrors()) {
			mv.setViewName(ViewRouteHelper.MENSAJES_VER);
			mv.addObject("mensajes", helper.getMensajes(mensajes, yo));
		}else {
			
			
			mv.setViewName("redirect:/mensajes/verMensajes");
			
			
			mensaje.setUsuario_emisor(new UsuarioModelo(yo.getId(), yo.getUsuario(), yo.getContra(), yo.getEmail()));
			mensaje.setUsuario_receptor(new UsuarioModelo(otro.getId(), otro.getUsuario(), otro.getContra(), otro.getEmail()));
						
			mensajeService.insertOrUpdate(mensaje);

			System.out.println(mensaje);
			
			mv.addObject("mensajes", helper.getMensajes(mensajes, yo).add(mensaje));
			mv.addObject("mensaje", new MensajeModelo());

		}
		
		return mv;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("verMensajesId/{usuario}")
	public ModelAndView verMensajesConId(@PathVariable("usuario") String usuario, Model model) {
		
		
		ModelAndView mv = new ModelAndView(ViewRouteHelper.MENSAJES_VER_ID);
		
		yo = userService.buscarUsuario("admin");
		otro = userService.buscarUsuario(usuario);
		
		
		//mensajes =  mensajeService.getAllModel();
		
		FuncionesHelper helper = new FuncionesHelper();
		
		mv.addObject("mensajes", helper.getMensajes(mensajes, otro));
		mv.addObject("usuario", otro);
		
		model.addAttribute("mensaje", new MensajeModelo());
		return mv;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("nuevoMensajeId/{usuario}")
	public ModelAndView nuevoUsuarioId(@Valid @PathVariable("usuario") String usuario, @ModelAttribute("mensaje") MensajeModelo mensaje, BindingResult b) {
		ModelAndView mv = new ModelAndView();
		FuncionesHelper helper = new FuncionesHelper();
		if(b.hasErrors()) {
			mv.setViewName(ViewRouteHelper.MENSAJES_VER_ID);
			mv.addObject("mensajes", helper.getMensajes(mensajes, otro));
		}else {
			
			
			mv.setViewName("redirect:/mensajes/verMensajesId/" + usuario);
			
			
			mensaje.setUsuario_emisor(new UsuarioModelo(yo.getId(), yo.getUsuario(), yo.getContra(), yo.getEmail()));
			mensaje.setUsuario_receptor(new UsuarioModelo(otro.getId(), otro.getUsuario(), otro.getContra(), otro.getEmail()));
						
			mensajeService.insertOrUpdate(mensaje);

			System.out.println(mensaje);
			
			mensajes.add(mensaje);
			

		}
		
		return mv;
	}
}
