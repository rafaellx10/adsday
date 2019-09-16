package br.com.fatec.adsday.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fatec.adsday.model.Contato;
import br.com.fatec.adsday.repository.ContatoRepository;


@RestController
@RequestMapping("/contatos")
public class ContatoResource {
	
	@Autowired
	private ContatoRepository contatoRepo;
	
	@PostMapping
	public Contato adicionar(@Valid @RequestBody Contato contato) {
		return contatoRepo.save(contato);
	}
	
	@GetMapping
	public List<Contato> listar() {
		return contatoRepo.findAll();
	}
		
	@GetMapping("/{id}")
	public ResponseEntity<Contato> buscar(@PathVariable Long id) {
		Contato contato = contatoRepo.getOne(id);
		
		if (contato == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(contato);
	}
	
	@GetMapping("/nome/{nome}")
	public List<Contato> buscarPorNome(@PathVariable String nome) {
		return contatoRepo.procurar(nome);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Contato> atualizar(@PathVariable Long id, 
			@Valid @RequestBody Contato contato) {
		Contato existente = contatoRepo.getOne(id);
		
		if (existente == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(contato, existente, "id");
		
		existente = contatoRepo.save(existente);
		
		return ResponseEntity.ok(existente);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Contato contato = contatoRepo.getOne(id);
		
		if (contato == null) {
			return ResponseEntity.notFound().build();
		}
		
		contatoRepo.delete(contato);
		
		return ResponseEntity.noContent().build();
	}
}
