package com.umss.dev.CoursesManagement.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.umss.dev.CoursesManagement.model.UsuarioPrueba;



public class LoginController {
	
	@GetMapping("/auth/login")
	public String login(Model model) {
		model.addAttribute("usuario", new UsuarioPrueba());
		
		return "login";
		
	}

}