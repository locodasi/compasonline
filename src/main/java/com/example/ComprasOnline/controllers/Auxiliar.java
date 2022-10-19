package com.example.ComprasOnline.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.ComprasOnline.helpers.ViewRouteHelper;
import com.example.ComprasOnline.models.UsuarioModelo;
import com.example.ComprasOnline.services.IUserServices;

@Controller
@RequestMapping("aux")
public class Auxiliar {


	@Autowired
	@Qualifier("userService")
	private IUserServices userServices;
	
	@GetMapping("/crearUsuario")
	public String crearUsuario (Model model) {
		model.addAttribute("usuario", new UsuarioModelo());
		return "productos/jj";
	}
	
	@PostMapping("/nuevoUsuario")
	public ModelAndView nuevoUsuario(@Valid @ModelAttribute("usuario") UsuarioModelo usuario, BindingResult b) {
		ModelAndView mv = new ModelAndView();
		if(b.hasErrors()) {
			System.out.println("hhh");
			mv.setViewName("redirect:/aux/crearUsuario");
		}else {
			
			System.out.println("gggg");
			BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
			usuario.setContra(pe.encode(usuario.getContra()));
			
			userServices.insertOrUpdate(usuario);
			
			mv.setViewName(ViewRouteHelper.USER_LOGIN);
			mv.addObject("usuario",usuario);
		}
		
		return mv;
	}
}
