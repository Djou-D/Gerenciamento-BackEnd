package organiza.o.gerenciamento.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import organiza.o.gerenciamento.Models.Colaboradores;

import organiza.o.gerenciamento.Services.ColaboradoresService;

@CrossOrigin
@RestController
@RequestMapping("/empresa")
public class ColaboradoresController {

	
	@Autowired
	private ColaboradoresService colaboradoresService;
	
	
	@Autowired
	private ColaboradoresService colaboradorService;
	
	
	@GetMapping("/colaborador/{id-colaborador}")
	public ResponseEntity<Colaboradores> mostrarColaborador(@PathVariable Integer id_colaborador){
		
		Colaboradores colaborador = colaboradorService.mostrarColaborador(id_colaborador);
		
		return ResponseEntity.ok().body(colaborador);
	}
	
	
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/colaborador-role")
	public List<List> colaboradorRole(){
		return colaboradorService.colaboradorRoles();
	}
	
	
	
	
	@GetMapping("/colaborador-nome/{nome}")
	public ResponseEntity<Colaboradores> buscarColaboradorPeloNome(@PathVariable String nome){
		
		Colaboradores colaborador = colaboradoresService.buscarColaboradorPeloNome(nome);
		
		return ResponseEntity.ok().body(colaborador);
	}
	
	
	
	
}
