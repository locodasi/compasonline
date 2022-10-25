package com.example.ComprasOnline.controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.ComprasOnline.helpers.ViewRouteHelper;
import com.example.ComprasOnline.models.UsuarioModelo;
import com.example.ComprasOnline.services.IUserServices;
import com.example.ComprasOnline.services.implementation.UserService;

@Controller
public class UserController {

	@Autowired
	@Qualifier("userService")
	private IUserServices userServices;
	
	@GetMapping("/login")
	public String login(Model model,
						@RequestParam(name="error",required=false) String error,
						@RequestParam(name="logout", required=false) String logout) {
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return ViewRouteHelper.USER_LOGIN;
	}
	
	
	@RequestMapping(value= "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		
		if(auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return ViewRouteHelper.USER_LOGIN;
	}
	
	@GetMapping("/loginsuccess")
	public String loginCheck() {
		return "redirect:/productos/verProductos";
		
	}
	
	@GetMapping("")
	public String index() {
		return "/login";
	}
	
	@GetMapping("/crearUsuario")
	public ModelAndView crearUsuario (Model model) {
		model.addAttribute("usuario", new UsuarioModelo());
		 
		ModelAndView mv = new ModelAndView(ViewRouteHelper.USER_FORM);
		return mv;
	}
	
	@PostMapping("nuevoUsuario")
	public ModelAndView nuevoUsuario(@Valid @ModelAttribute("usuario") UsuarioModelo usuario, BindingResult b) {
		ModelAndView mv = new ModelAndView();
		if(b.hasErrors()) {
			mv.setViewName(ViewRouteHelper.USER_FORM);
		}else {
			
			BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
			usuario.setContra(pe.encode(usuario.getContra()));
			
			try {
				userServices.insertOrUpdate(usuario);
				mv.setViewName(ViewRouteHelper.USER_LOGIN);
			}catch (Exception e) {
				// TODO: handle exception
				mv.setViewName(ViewRouteHelper.USER_FORM);
				mv.addObject("error", 1); 
			}
			
			
			mv.addObject("usuario",usuario);
		}
		
		return mv;
	}
	
	@GetMapping("/user/editarUser")
	public ModelAndView editar() {

		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		ModelAndView mv = new ModelAndView(ViewRouteHelper.USER_EDIT);
		mv.addObject("User", userServices.buscarUsuario(user.getUsername()));
		mv.addObject("usuario",user.getUsername());
		return mv;
	}
	
	
	@PostMapping("user/editarUsuario")
	public ModelAndView editarUsuario(@Valid @ModelAttribute("usuario") UsuarioModelo usuario, BindingResult b) {
		ModelAndView mv = new ModelAndView();
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("s");
		if(b.hasErrors()) {
			System.out.println("d");
			mv.setViewName(ViewRouteHelper.USER_EDIT);
			mv.addObject("usuario",user.getUsername());
			mv.addObject("valido", 1);
			mv.addObject("User", userServices.buscarUsuario(user.getUsername()));
		}else {
			System.out.println("j");
			BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
			usuario.setContra(pe.encode(usuario.getContra()));
			
			try {
				userServices.insertOrUpdate(usuario);
				mv.setViewName("redirect:/user/editarUser");
			}catch (Exception e) {
				// TODO: handle exception
				mv.setViewName(ViewRouteHelper.PRODUCTOS_EDITAR);
				mv.addObject("error", 1); 
			}
			
		}
		
		return mv;
	}
	
}
