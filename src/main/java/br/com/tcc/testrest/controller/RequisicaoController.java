package br.com.tcc.testrest.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.tcc.testrest.filter.RequisicaoFilter;
import br.com.tcc.testrest.model.Requisicao;
import br.com.tcc.testrest.model.TipoAtributo;
import br.com.tcc.testrest.model.TipoMetodo;
import br.com.tcc.testrest.repository.Requisicoes;
import br.com.tcc.testrest.test.TesteRequisicaoRest;


@Controller
@RequestMapping("/requisicoes")
public class RequisicaoController {
	
	private static final String VIEWERCADASTRO_REQUISICAO = "requisicao/cadastro-requisicao";
	

	@Autowired
	private Requisicoes requisicoes;
	
	
	@GetMapping("/novo")
	public ModelAndView novo(Requisicao requisicao){
		
		ModelAndView mv = new ModelAndView(VIEWERCADASTRO_REQUISICAO);
		mv.addObject(requisicao);
		mv.addObject("tipoAtributos", TipoAtributo.values());
		mv.addObject("tipoMetodos",   TipoMetodo.values());
		return mv;
	}
		

	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Requisicao requisicao, BindingResult result,
			 				   RedirectAttributes attr){
		if(result.hasErrors())
			
			return novo(requisicao);
			
		requisicoes.save(requisicao);
		attr.addFlashAttribute("mensagem", "Requisicao salvo com sucesso!");
		return new ModelAndView("redirect:/requisicoes/novo");
	
	}
	
	
	@GetMapping 
	public ModelAndView pesquisar(RequisicaoFilter requisicaoFilter){
	
		ModelAndView mv = new ModelAndView("requisicao/pesquisa-requisicao");
		mv.addObject("requisicoes", 
		requisicoes.findByUrlContainingIgnoreCase(Optional.ofNullable(requisicaoFilter.getUrl()).orElse("%")));
		
		return mv;
	}
	
	
	@GetMapping("/{id}")
	public ModelAndView editar(@PathVariable Long id){
		
		Requisicao requisicao = requisicoes.findOne(id);
		return novo(requisicao);
	}
	
	@DeleteMapping("/{id}") 
	public String delete(@PathVariable Long id, RedirectAttributes attr){
		requisicoes.delete(id);
		attr.addFlashAttribute("mensagem", "Requisicao removida com sucesso!");
		return "redirect:/requisicoes";
	}
	
	@DeleteMapping("/testar/{id}") 
	public String testarRest(@PathVariable Long id, RedirectAttributes attr){
		String msgSaida = "Requisicao testada com sucesso!";
		TesteRequisicaoRest test = new TesteRequisicaoRest();
		Requisicao requisicao = requisicoes.findOne(id);
		switch(requisicao.getMetodo()) {
		  case GET:
			  requisicao = test.testarMetodoGet(requisicao);
			  break;
		  case POST:
			  requisicao = test.testarMetodoPost(requisicao);
		      break;
		  case PUT:
			  requisicao = test.testarMetodoPut(requisicao);
		      break;    
		  case DELETE:
			  requisicao = test.testarMetodoDelete(requisicao);
		      break;       
		  default:
			  msgSaida = "Método da Requisicao não informado";
		}

		requisicoes.save(requisicao);
		attr.addFlashAttribute("mensagem", msgSaida);
		return "redirect:/requisicoes";
	}
	

}
	
	
	