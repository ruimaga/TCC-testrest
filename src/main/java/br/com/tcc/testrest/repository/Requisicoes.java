package br.com.tcc.testrest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tcc.testrest.model.Requisicao;


public interface Requisicoes extends JpaRepository<Requisicao, Long> {


	public List<Requisicao> findByUrlContainingIgnoreCase (String url);
	
	
	
	
}
