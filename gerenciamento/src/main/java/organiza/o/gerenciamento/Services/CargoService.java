package organiza.o.gerenciamento.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import organiza.o.gerenciamento.Models.Cargo;
import organiza.o.gerenciamento.Models.Funcionario;
import organiza.o.gerenciamento.Models.Setor;
import organiza.o.gerenciamento.Repositories.CargoRepository;

import organiza.o.gerenciamento.Services.Exceptions.ObjectNotFoundException;

@Service
public class CargoService {

	
	@Autowired
	private CargoRepository cargoRepository;
	
	@Autowired
	private SetorService setorService;
	
	
	
	// METODOS
	
	// GETTERS
	
	//Metodo para listar todos os cargos
	public List<Cargo> mostrarTodosOsCargos(){
		
		return cargoRepository.findAll();
	}
	
	
	//Metodo para listar um cargo especifico
	public Cargo mostrarCargo(Integer id_cargo) {
		
		Optional<Cargo> cargo = cargoRepository.findById(id_cargo);
		return cargo.orElseThrow(() -> new ObjectNotFoundException("Objeto não cadastrado! O id procurado foi: " + id_cargo + " e ele não consta no banco de dados"));
		
	}
	
	
	//Metodo para buscar os funcionarios do cargo especifico
	public List<Funcionario> listarFuncionariosDoCargo(Integer id_cargo){
		
		List<Funcionario> funcionarios = cargoRepository.funcionariosDoCargo(id_cargo);
		
		return funcionarios;
	}
	
	
	//Metodo para listar cargos sem vinculo com setor
	public List<Cargo> cargosSemSetor(){
		
		return cargoRepository.cargosSemSetor();
	}
	
	
	//Metodo para listar cargo com setor na mesma lista
	@SuppressWarnings("rawtypes")
	public List<List> cargosComSetor(){
		
		return cargoRepository.cargosComSetor();
	}
	
	
	//Metodo para listar os cargos de determinado setor
	public List<Cargo> cargosDoSetor(Integer id_setor){
	
		return cargoRepository.cargosDoSetor(id_setor);
	}
	
	
	// FIM DOS GETTERS	
	
	
	// POSTS
	
	//Metodo para cadastrar um cargo
	public Cargo cadastrarCargo(Cargo cargo) {
		
		cargo.setId_cargo(null);
		return cargoRepository.save(cargo);
		
	}
	
	
	
	//Metodo para cadastrar um cargo com um setor
	public Cargo cadastrarCargoComSetor(Integer id_setor, Cargo cargo) {
		
		cargo.setId_cargo(null);
		
		if(id_setor != null) {
			
			Setor setor = setorService.mostrarSetor(id_setor);
			
			cargo.setSetor(setor);

		}
		
		return cargoRepository.save(cargo);
	}
	
	// FIM DOS METODOS DE POST
	
	// PUTS
	
	//Metodo para editar um cargo
	public Cargo editarCargo(Integer id_setor, Cargo cargo) {
		
		mostrarCargo(cargo.getId_cargo());
		
		Setor setor = setorService.mostrarSetor(id_setor);
		cargo.setSetor(setor);
		
		return cargoRepository.save(cargo);
	}
	
	
	// DELETE
	
	//Metodo para deletar um cargo
	public void excluirCargo(Integer id_cargo) {
		mostrarCargo(id_cargo);
		
		//Tratamento de esceções do retorno de erro de deleção
		try {
			cargoRepository.deleteById(id_cargo);
		//É chamada a Data... como parametro importada do spring
		} catch (DataIntegrityViolationException e) {
			
			//É chamada a Data... como retorno de objeto importado do services.exceptions.
			throw new organiza.o.gerenciamento.Services.Exceptions.DataIntegrityViolationException("O cargo não pode ser Excluido por que tem funcionarios vinculados a ele!");	
		}		
	}
	
	
}
