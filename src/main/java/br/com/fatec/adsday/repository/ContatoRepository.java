package br.com.fatec.adsday.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fatec.adsday.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>{

	Contato findByEmail(String email);
	
	//JPQL = Java Persistence Query Language
	@Query("SELECT c FROM Contato c WHERE c.nome LIKE :nome%")
	List<Contato> procurar(@Param("nome") String nome);
}
