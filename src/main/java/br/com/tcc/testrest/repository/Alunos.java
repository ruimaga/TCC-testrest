package br.com.tcc.testrest.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tcc.testrest.model.Aluno;


public interface Alunos extends JpaRepository<Aluno, Long> {


	public List<Aluno> findByNomeContainingIgnoreCase (String nome);
	
	
	
	
}
