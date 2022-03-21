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

import organiza.o.gerenciamento.Models.Cargo;
import organiza.o.gerenciamento.Models.Funcionario;
import organiza.o.gerenciamento.Services.FuncionarioService;

@CrossOrigin
@RestController
@RequestMapping("empresa")
public class FuncionarioController {

	
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	
	//Rota para listar todos os funcionario
	@GetMapping("/funcionario")
	public List<Funcionario> mostrarTodosOsFucionarios(){
		
		List<Funcionario> funcionarios = funcionarioService.mostrarTodosOsFuncionarios();
		return funcionarios;
	}
	
	//Rota para listar um funcionario
	@GetMapping("/funcionario/{id_colaborador}")
	public ResponseEntity<Funcionario> mostrarFuncionario(@PathVariable Integer id_colaborador){
		
		Funcionario funcionario = funcionarioService.mostrarFuncionario(id_colaborador);
		return ResponseEntity.ok().body(funcionario);
	}
	

	//Rota para listar funcionarios com cargos vinculados
	@SuppressWarnings("rawtypes")
	@GetMapping("/funcionario-cargo")
	public List<List> funcionarioComCargo(){
		return funcionarioService.funcionarioComCargo();
	}
	
	
	//Rota para listar funcionarios pelo cargo
	@GetMapping("/funcionario/cargo/{id_cargo}")
	public List<Funcionario> mostrarFuncionarioPorCargo(@PathVariable Integer id_cargo){
		List<Funcionario> funcionario = funcionarioService.mostrarFuncionarioPorCargo(id_cargo);
		return funcionario;
	}
	
	
	
	
	//Rota para cadastrar um funcionario com o setor e cargo setado
	@PostMapping("/funcionario")
	public ResponseEntity<Void> inserirFuncionario(@RequestParam(name = "cargo")Integer id_cargo,  @RequestBody Funcionario funcionario){
		
		String senha = funcionario.getSenha();
		String senhaEncrypt = passwordEncoder.encode(senha);
		
		funcionario.setSenha(senhaEncrypt);
		
		funcionario = funcionarioService.cadastrarFuncionario(id_cargo, funcionario);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(funcionario.getId_colaborador()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	
	//Rota para editar um funcionario
	@PutMapping("/funcionario/{id_colaborador}/{id_cargo}")
	public ResponseEntity<Void> editarFuncionario(@PathVariable Integer id_colaborador, @PathVariable Integer id_cargo,  @RequestBody Funcionario funcionario){
		
		String senha = funcionario.getSenha();
		String senhaEncrypt = passwordEncoder.encode(senha);
		
		funcionario.setSenha(senhaEncrypt);
		
		funcionario.setId_colaborador(id_colaborador);
		funcionario = funcionarioService.editarFuncionario(funcionario, id_cargo);
		
		return ResponseEntity.noContent().build();
	}
	
	
	//Rota de edição pra trocar o funcionario de cargo
	@PutMapping("/funcionario/trocar-cargo/{id_colaborador}/{id_cargo}")
	public ResponseEntity<Funcionario> trocarCargoDoFuncionario(@PathVariable Integer id_colaborador, @PathVariable Integer id_cargo){
		
		funcionarioService.trocarCargoDoFuncionario(id_colaborador, id_cargo);
		
		return ResponseEntity.noContent().build();
	}
	
	
	
	//Rota para excluir um funcionario
	@DeleteMapping("/funcionario/{id_colaborador}")
	public ResponseEntity<Void> deletarFuncionario(@PathVariable Integer id_colaborador){
		
		funcionarioService.deletarFuncionario(id_colaborador);
		return ResponseEntity.noContent().build();
	}
	
	
}
