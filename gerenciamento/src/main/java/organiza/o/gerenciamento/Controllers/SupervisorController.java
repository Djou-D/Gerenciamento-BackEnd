package organiza.o.gerenciamento.Controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import organiza.o.gerenciamento.Models.Supervisor;
import organiza.o.gerenciamento.Services.SupervisorService;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/empresa")
public class SupervisorController {

	
	@Autowired
	private SupervisorService superService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	//Rota para listar todos os supervisores
	@GetMapping("/supervisor")
	public List<Supervisor> mostrarTodosOsSupervisor(){
		List<Supervisor> supervisor = superService.mostrarTodosOsSupervisores();
		
		return supervisor;
	}
	
	//Rota para listar um supervisor
	@GetMapping("/supervisor/{id_colaborador}")
	public ResponseEntity<Supervisor> mostrarSupervisor(@PathVariable Integer id_colaborador) {
		
		Supervisor supervisor = superService.mostrarSupervisor(id_colaborador);
		return ResponseEntity.ok().body(supervisor);
	}
	
	
	//Roata para listar os supervisores sem setor
	@GetMapping("/supervisor/sem-setor")
	public List<Supervisor> supervisorSemSetor(){
		
		List<Supervisor> supervisor = superService.supervisorSemSetor();
		
		return supervisor;
	}
	
	
	//Rota para listar o supervisor do cargo
	@GetMapping("/supervisor/setor/{id_setor}")
	public Supervisor mostrarSupervisorDoSetor(@PathVariable Integer id_setor) {
		
		Supervisor supervisor = superService.mostrarSupervisorDoSetor(id_setor);
		return supervisor;
	}
	
	
	
	//Rota para cadastrar um supervisor
	@PostMapping("/supervisor")
	public ResponseEntity<Supervisor> cadastrarSupervisor(@RequestBody Supervisor supervisor){
		
		String senha = supervisor.getSenha();
		String senhaEncrypt = passwordEncoder.encode(senha);
		
		supervisor.setSenha(senhaEncrypt);
		
		supervisor = superService.cadastrarSupervisor(supervisor);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/supervisor/{id}").buildAndExpand(supervisor.getId_colaborador()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	
	//Rota para cadastrar um supervisor com um setor existente
//	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/supervisor/add")
	public ResponseEntity<Supervisor> inserirSupervisor(@RequestParam(value="setor")Integer id_setor, @RequestBody Supervisor supervisor){
		
		String senha = supervisor.getSenha();
		String senhaEncrypt = passwordEncoder.encode(senha);
		
		supervisor.setSenha(senhaEncrypt);
		
		supervisor = superService.inserirSupervisor(id_setor, supervisor);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/supervisor/{id}").buildAndExpand(supervisor.getId_colaborador()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	
	//Rota para editar um supervisor
	@PutMapping("/supervisor/{id_setor}/{id_colaborador}")
	public ResponseEntity<Void> editarSupervisor(@PathVariable Integer id_setor, @PathVariable Integer id_colaborador, @RequestBody Supervisor supervisor){
		
		String senha = supervisor.getSenha();
		String senhaEncrypt = passwordEncoder.encode(senha);
		
		supervisor.setSenha(senhaEncrypt);
		
		supervisor.setId_colaborador(id_colaborador);
		
		supervisor = superService.editarSupervisor(id_setor, supervisor);
		
		return ResponseEntity.noContent().build();
	}
	
	
	//Rota para editar um supervisor
	@PutMapping("/supervisor/{id_colaborador}")
	public ResponseEntity<Void> editarUmSupervisor(@PathVariable Integer id_colaborador, @RequestBody Supervisor supervisor){
		
		String senha = supervisor.getSenha();
		String senhaEncrypt = passwordEncoder.encode(senha);
		
		supervisor.setSenha(senhaEncrypt);
		
		supervisor.setId_colaborador(id_colaborador);
		
		supervisor = superService.editarUmSupervisor(supervisor);
		
		return ResponseEntity.noContent().build();
	}
	
	
	
	//Rota para excluir um supervisor
	@DeleteMapping("/supervisor/{id_colaborador}")
	public ResponseEntity<Void> excluirSupervisor(@PathVariable Integer id_colaborador){
		
		superService.excluirSupervisor(id_colaborador);
		return ResponseEntity.noContent().build();
	}
	
}
