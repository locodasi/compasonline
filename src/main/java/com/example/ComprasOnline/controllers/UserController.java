package com.example.ComprasOnline.controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.example.ComprasOnline.helpers.ViewRouteHelper;
import com.example.ComprasOnline.models.BromaModelo;
import com.example.ComprasOnline.models.UsuarioModelo;
import com.example.ComprasOnline.services.IUserServices;
import com.example.ComprasOnline.services.implementation.UserService;
import com.google.gson.Gson;

@Controller
public class UserController {

	
	@Autowired
	@Qualifier("userService")
	private IUserServices userServices;
	
	@GetMapping("/bromaAleatoria")
	private BromaModelo bromaAleatoria() {
		
		String url = "https://official-joke-api.appspot.com/random_joke";
		
		RestTemplate restTemplate = new RestTemplate();
		
		String broma = restTemplate.getForObject(url,  String.class);
		
		Gson gson = new Gson();
		
		BromaModelo b = gson.fromJson(broma, BromaModelo.class);
		
		return b;
	}
	
	@GetMapping("/login")
	public ModelAndView login(Model model,
						@RequestParam(name="error",required=false) String error,
						@RequestParam(name="logout", required=false) String logout) {
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		
		ModelAndView mv = new ModelAndView(ViewRouteHelper.USER_LOGIN);
		mv.addObject("broma",bromaAleatoria());
		return mv;
	}
	
	//El logout ni siquiera entra aca
	@RequestMapping(value= "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {	
		System.out.println("eeeeeeeeeeee");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		
		if(auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		System.out.println("ddddddddddddddddd");
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
				userServices.insert(usuario);
				mv.setViewName("redirect:/login");
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
		mv.addObject("validar",0);
		mv.addObject("error",0);
		mv.addObject("contra","");
		return mv;
	}
	
	//No logro desencriptar la contra
	@PostMapping("user/validarUsuario")
	public ModelAndView validaUser(@ModelAttribute("contra") String contra) {
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView mv = new ModelAndView();
		System.out.println("ddd" + contra);
		com.example.ComprasOnline.entities.User usuario = userServices.buscarUsuario(user.getUsername());
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		if (pe.matches(contra, usuario.getContra())) {
			mv.addObject("User", userServices.buscarUsuario(user.getUsername()));
			mv.addObject("usuario",user.getUsername());
			mv.addObject("validar",1);
			mv.addObject("error",0);
			mv.addObject("contra","");
			mv.addObject("id",usuario.getId());
		}else{
			mv.addObject("User", userServices.buscarUsuario(user.getUsername()));
			mv.addObject("usuario",user.getUsername());
			mv.addObject("validar",0);
			mv.addObject("error",1);
			mv.addObject("contra","");
		}
		mv.setViewName(ViewRouteHelper.USER_EDIT);
		return mv;
	}
	
	
	@PostMapping("user/editarUsuario")
	public ModelAndView editarUsuario(@Valid @ModelAttribute("usuario") UsuarioModelo usuario, BindingResult b) {
		ModelAndView mv = new ModelAndView();
		User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		

		if(b.hasErrors()) {
			mv.setViewName(ViewRouteHelper.USER_EDIT);
			mv.addObject("usuario",user.getUsername());
			mv.addObject("validar", 1);
			mv.addObject("error",2);
			mv.addObject("User", userServices.buscarUsuario(user.getUsername()));
		}else {
			
			BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
			usuario.setContra(pe.encode(usuario.getContra()));
			
			
			try {
				userServices.update(usuario);
				mv.setViewName("redirect:/user/editarUser");
			}catch (Exception e) {
				// TODO: handle exception
				mv.setViewName(ViewRouteHelper.PRODUCTOS_EDITAR);
				mv.addObject("error", 1); 
			}
			
		}
		
		return mv;
	}
	

	@GetMapping("/user/borrarUser/{id}")
	public ModelAndView borrarRubro(@PathVariable("id") int id, Model model) {

		userServices.remove(id);
		
		return new ModelAndView("redirect:/logout");
	}
	
}
