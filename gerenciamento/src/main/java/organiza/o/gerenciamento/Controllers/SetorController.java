package organiza.o.gerenciamento.Controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import organiza.o.gerenciamento.Models.Setor;
import organiza.o.gerenciamento.Models.Supervisor;
import organiza.o.gerenciamento.Services.SetorService;



@CrossOrigin
@RestController
@RequestMapping("/empresa")
public class SetorController {

	
	@Autowired
	private SetorService setorService;
	
	
	
	//Rota para listar todos os setores
	@GetMapping("/setor")
	public List<Setor> mostrarTodosOsSetores(){
		
		List<Setor> setor = setorService.mostrarTodosOsSetores();
		return setor;
	}
	
	//Rota para listar um setor
	@GetMapping("/setor/{id_setor}")
	public ResponseEntity<Setor> mostrarSetor(@PathVariable Integer id_setor){
		
		Setor setor = setorService.mostrarSetor(id_setor);
		return ResponseEntity.ok().body(setor);
	}
	
	
	//Rota para listar setores e supervisores em uma mesma lista
	@SuppressWarnings("rawtypes")
	@GetMapping("/setor/supervisor")
	public List<List> setorESupervisor(){
		List<List> setorSupervisor = setorService.setorESupervisor();
		return setorSupervisor;
	}
	
	
	//Rota para listar os setores sem supervisor
	@GetMapping("/setor/sem-supervisor")
	public List<Setor> setorSemSupervisor(){
		List<Setor> setor = setorService.setorSemSupervisor();
		return setor;
	}
		
	
	//Rota para listar os setores sem cargos vinculados
	@GetMapping("/setor/sem-cargo")
	public List<Setor> setorSemCargo(){
		
		List<Setor> setor = setorService.setorSemCargo();
		return setor;
	}
	
	
	//Rota para cadastrar um setor
	@PostMapping("/setor")
	public ResponseEntity<Void> inserirSetor(@RequestBody Setor setor){
		
		setor = setorService.cadastrarSetor(setor);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(setor.getId_setor()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	
	//Rota para editar um setor
	@PutMapping("/setor/{id_setor}")
	public ResponseEntity<Void> editarSetor(@PathVariable Integer id_setor, @RequestBody Setor setor){
		
		setor.setId_setor(id_setor);
		setor = setorService.editarSetor(setor);
		return ResponseEntity.noContent().build();
	}
	
	
	//Rota para editar um setor retirando o supervisor dele
	@PutMapping("/setor/{id_setor}/retirar-supervisor/{id_colaborador}")
	public ResponseEntity<Supervisor> deixarSetorSemSupervisor(@PathVariable Integer id_setor, @PathVariable Integer id_colaborador){
		
		setorService.deixarSetorSemSupervisor(id_setor, id_colaborador);
		
		return ResponseEntity.noContent().build();
	}
	
	
	//Rota para editar o setor inserindo um supervisor a ele
	@PutMapping("/setor/{id_setor}/definir-supervisor/{id_colaborador}")
	public ResponseEntity<Supervisor> atribuirSupervisor(@PathVariable Integer id_setor, @PathVariable Integer id_colaborador){
		
		setorService.atribuirSupervisor(id_setor, id_colaborador);
		
		return ResponseEntity.noContent().build();
	}
	
	
	//Rota para excluir um setor
	@DeleteMapping("/setor/{id_setor}")
	public ResponseEntity<Void> excluirSetor(@PathVariable Integer id_setor){
		
		setorService.excluirSetor(id_setor);
		return ResponseEntity.noContent().build();
	}
	
	
}
