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

import organiza.o.gerenciamento.Models.Holerite;
import organiza.o.gerenciamento.Services.HoleriteService;



@CrossOrigin
@RestController
@RequestMapping("empresa")
public class HoleriteController {

	
	@Autowired
	private HoleriteService holeriteService;
	
	
	//Rota para listar todos os holerites
	@GetMapping("/holerite")
	public List<Holerite> buscarTodosOsHolerites(){
		
		List<Holerite> holerite = holeriteService.buscarTodosOsHolerites();
		return holerite;
	}
	
	//Rota para listar um holerite
	@GetMapping("/holerite/{codigo}")
	public ResponseEntity<Holerite> buscarHolerite(@PathVariable Integer codigo){
		
		Holerite holerite = holeriteService.buscarHolerite(codigo);
		
		return ResponseEntity.ok().body(holerite);
				
	}
	
	
	//Rota para listar os holetites de um funcionario
	@GetMapping("/funcionario/holerite/{id_colaborador}")
	public List<Holerite> buscarHoleriteDoFuncionario(@PathVariable Integer id_colaborador){
		
		List<Holerite> holerite = holeriteService.buscarHoleriteDoFuncionario(id_colaborador);
		return holerite;
	}
	
	
	//Rota para listar os holerites de um supervisor
	@GetMapping("/supervisor/holerite/{id_colaborador}")
	public List<Holerite> buscarHoleriteDoSupervisor(@PathVariable Integer id_colaborador){
		
		List<Holerite> holerite = holeriteService.buscarHoleriteDoSupervisor(id_colaborador);
		return holerite;
	}
	
	
	//Rota para listar o holerite e o funcionario na esma lista
	@SuppressWarnings("rawtypes")
	@GetMapping("/holerite-funcionario")
	public List<List> listarHoleriteComFuncionario(){
		
		return holeriteService.listarHoleriteComFuncionario();
	}
	
	
	//Rota para listar o holerite e o supervisor na mesma lista
	@SuppressWarnings("rawtypes")
	@GetMapping("/holerite-supervisor")
	public List<List> listarHoleriteComSupervisor(){
		
		return holeriteService.listarHoleriteComSupervisor();
	}
	
	
	//Rota para cadastrar o holerite de um funcionario
	@PostMapping("/holerite/funcionario/{id_colaborador}")
	public ResponseEntity<Holerite> cadastrarHoleriteFuncionario(@RequestBody Holerite holerite, @PathVariable Integer id_colaborador){
		
		holerite = holeriteService.inserirHoleriteFuncionario(holerite, id_colaborador);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id_colaborador}")
				.buildAndExpand(holerite.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	
	//Rota para cadastrar o holerite de um supervisor
	@PostMapping("/holerite/supervisor/{id_colaborador}")
	public ResponseEntity<Holerite> cadastrarHoleriteSupervisor(@RequestBody Holerite holerite, @PathVariable Integer id_colaborador){
		
		holerite = holeriteService.inserirHoleriteSupervisor(holerite, id_colaborador);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id_colaborador}")
				.buildAndExpand(holerite.getCodigo()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	
	//Rota para editar o holerite de um funcionario
	@PutMapping("/holerite/funcionario/{codigo}/{id_colaborador}")
	public ResponseEntity<Holerite> editarHolerite(@PathVariable Integer codigo, @PathVariable Integer id_colaborador, @RequestBody Holerite holerite){
		
		holerite.setCodigo(codigo);
		holerite = holeriteService.editarHoleriteFuncionario(codigo, id_colaborador, holerite);
		
		return ResponseEntity.noContent().build();
	}
	
	
	//Rota para editar o holerite de um supervisor
	@PutMapping("/holerite/supervisor/{codigo}/{id_colaborador}")
	public ResponseEntity<Holerite> editarHoleriteSupervisor(@PathVariable Integer codigo, @PathVariable Integer id_colaborador, @RequestBody Holerite holerite){
		
		holerite.setCodigo(codigo);
		holerite = holeriteService.editarHoleriteFuncionario(codigo, id_colaborador, holerite);
		
		return ResponseEntity.noContent().build();
	}
	
	
	//Rota de edição para alterar o status do holerite para pago
	@PutMapping("/holerite-pago/{codigo}")
	public ResponseEntity<Holerite> holeritePago(@PathVariable Integer codigo){
	
		holeriteService.holeritePago(codigo);
		
		return ResponseEntity.noContent().build();
	}
	
	
	
	//Rota de edição para alterar o status do holerite para cancelado
	@PutMapping("/holerite-cancelado/{codigo}")
	public ResponseEntity<Holerite> holeriteCancelado(@PathVariable Integer codigo){
		
		holeriteService.holeriteCancelado(codigo);
		
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	//Rota para deletar um holerite
	@DeleteMapping("/holerite/{codigo}")
	public ResponseEntity<Void> deletarHolerite(@PathVariable Integer codigo){
		
		holeriteService.deletarHolerite(codigo);
		
		return ResponseEntity.noContent().build();
	}
	
	
	

}
