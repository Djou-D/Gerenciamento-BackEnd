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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import organiza.o.gerenciamento.Models.Cargo;
import organiza.o.gerenciamento.Services.CargoService;


@CrossOrigin
@RestController
@RequestMapping("empresa")
public class CargoController {

	
	@Autowired
	private CargoService cargoService;
	
	
	
	//Rota para listar todos os cargos
	@GetMapping("/cargo")
	public List<Cargo> mostrarTodosOsCargos(){
		
		List<Cargo> cargo = cargoService.mostrarTodosOsCargos();
		return cargo;
	}
	
	
	//Rota para listar um cargo
	@GetMapping("/cargo/{id_cargo}")
	public ResponseEntity<Cargo> mostrarCargo(@PathVariable Integer id_cargo){
		
		Cargo cargo = cargoService.mostrarCargo(id_cargo);
		return ResponseEntity.ok().body(cargo);
	}
	
	
	//Rota para listar cargo com setor na mesma lista
	@SuppressWarnings("rawtypes")
	@GetMapping("/cargo/com-setor")
	public List<List> cargosComSetor(){
		
		List<List> cargoSetor = cargoService.cargosComSetor();
		return cargoSetor;
	}
	
	
	//Rota para listar cargos que não tenham setor viculado
	@GetMapping("/cargo/sem-setor")
	public List<Cargo> cargosSemSetor(){
		
		List<Cargo> cargos = cargoService.cargosSemSetor();
		return cargos;
	}
	
	
	//Rota para listar cargos de um determinado setor
	@GetMapping("/setor/cargos/{id_setor}")
	public List<Cargo> cargosDoSetor(@PathVariable Integer id_setor){
	
		List<Cargo> cargos = cargoService.cargosDoSetor(id_setor);
		
		return cargos;
		
	}
	
	
	
	//Rota para cadastrar um cargo
	@PostMapping("/cargo")
	public ResponseEntity<Void> cadastrarCargo(@RequestBody Cargo cargo){
		
		cargo = cargoService.cadastrarCargo(cargo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cargo.getId_cargo()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	
	//Rota para cadastrar um cargo vinculado a um setor
	@PostMapping("/cargo/setor")
	public ResponseEntity<Cargo> cadastrarCargoComSetor(@RequestParam(value="setor")Integer id_setor, @RequestBody Cargo cargo){
		
		cargo = cargoService.cadastrarCargoComSetor(id_setor, cargo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cargo.getId_cargo()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	
	
	//Rota para editar um cargo ou trocar de setor
	@PutMapping("/cargo/{id_setor}/{id_cargo}")
	public ResponseEntity<Void> editarCargo(@PathVariable Integer id_setor, @PathVariable Integer id_cargo, @RequestBody Cargo cargo){
		
		cargo.setId_cargo(id_cargo);
		cargo = cargoService.editarCargo(id_setor, cargo);
		
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	//Rota para excluir um cargo
	@DeleteMapping("/cargo/{id_cargo}")
	public ResponseEntity<Void> excluirCargo(@PathVariable Integer id_cargo){
		
		cargoService.excluirCargo(id_cargo);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
}
