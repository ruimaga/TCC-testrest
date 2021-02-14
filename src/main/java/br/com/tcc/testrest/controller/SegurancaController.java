package br.com.tcc.testrest.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class SegurancaController {

	//Mapeamento para a rota padrão
	@RequestMapping(value= {"/", "/login", "home","/testrest"})
	public String login(@AuthenticationPrincipal User user){
		
		
		if(user != null)
			
			return "redirect:/requisicoes";
		
		return "login";
	}
	
	
	
	
	
}
