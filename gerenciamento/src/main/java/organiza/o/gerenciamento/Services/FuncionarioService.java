package organiza.o.gerenciamento.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import organiza.o.gerenciamento.Models.Cargo;
import organiza.o.gerenciamento.Models.Funcionario;
//import organiza.o.gerenciamento.Models.Setor;
import organiza.o.gerenciamento.Repositories.FuncionarioRepository;
import organiza.o.gerenciamento.Services.Exceptions.ObjectNotFoundException;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	
	@Lazy
	@Autowired
	private CargoService cargoService;
	
	
//	@Lazy
//	@Autowired
//	private SetorService setorService;
	
	
	
	// METODOS
	
	// GETTERS
	
	//Metodo para listar todos os funcionarios
	public List<Funcionario> mostrarTodosOsFuncionarios(){
		//atribuindo os valores da lista com o findAll que é um metodo do DataJpa.
		return funcionarioRepository.findAll();
		
	}
	
	
	//Metodo para listar um funcionario especifico
	public Funcionario mostrarFuncionario(Integer id_colaborador) {
		//optional - evita que a aplicação pare quando o retorno requisitado não existir.
		//evita os erros NullPointerException
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id_colaborador);
		//orElseThrow - se o objeto estiver no banco de dados ele é chamado se não lança uma exceção.
		return funcionario.orElseThrow(() -> new ObjectNotFoundException("Objeto não cadastrado! O id procurado foi: " + id_colaborador + " e ele não consta no banco de dados")); /*Retorna a turma ou a exceção se a turma não for encontrada*/
		
	}
	
	
	//Metodo para listar funcionarios com seus cargos
	@SuppressWarnings("rawtypes")
	public List<List> funcionarioComCargo(){
		return funcionarioRepository.funcionarioComCargo();
	}
	
	
	//Metodo para listar funcionarios atravez de um cargo especifico
	public List<Funcionario> mostrarFuncionarioPorCargo(Integer id_cargo){
		
		List<Funcionario> funcionario = funcionarioRepository.funcionarioPeloCargo(id_cargo);
		return funcionario;
	}
	
	
	// FIM DOS GETTERS
	
	// POST
	
	//Metodo para cadastrar um funcionario vinculado a um cargo e setor
	public Funcionario cadastrarFuncionario(Integer id_cargo, Funcionario funcionario) {
		
		funcionario.setId_colaborador(null);
		
		Cargo cargo = cargoService.mostrarCargo(id_cargo);
//		Setor setor = setorService.mostrarSetor(id_setor);
		
//		funcionario.setSetor(setor);
		funcionario.setCargo(cargo);
		
		return funcionarioRepository.save(funcionario);
	}
	
	
	// PUT
	
	//Metodo para editar um funcionario
	public Funcionario editarFuncionario(Funcionario funcionario, Integer id_cargo) {
		
		mostrarFuncionario(funcionario.getId_colaborador());
		
		Cargo cargo = cargoService.mostrarCargo(id_cargo);
		funcionario.setCargo(cargo);
		return funcionarioRepository.save(funcionario);
	}
	
	
	//Metodo para retirar um funcionario de seu cargo
	public Funcionario trocarCargoDoFuncionario(Integer id_colaborador, Integer id_cargo) {
		
		Funcionario funcionario = mostrarFuncionario(id_colaborador);
		
		Cargo cargo = cargoService.mostrarCargo(id_cargo);
//		Setor setor = setorService.mostrarSetor(id_setor);
		
		funcionario.setCargo(cargo);
//		funcionario.setSetor(setor);
		
		
		return funcionarioRepository.save(funcionario);
	}
	
	
	//Metodo para salvar o caminho de um arquivo no banco de dados
	public Funcionario salvarFoto(Integer id_colaborador, String caminhoFoto) {
		
		Funcionario funcionario = mostrarFuncionario(id_colaborador);
		funcionario.setFoto(caminhoFoto);
		
		return funcionarioRepository.save(funcionario);
	}
	
	
	// DELETE
	
	//Metodo para deletar um funcionario
	public void deletarFuncionario(Integer id_colaborador) {
		mostrarFuncionario(id_colaborador);
				
		try {
			
			funcionarioRepository.deleteById(id_colaborador);
		
		} catch (DataIntegrityViolationException e) {
			
			//É chamada a Data... como retorno de objeto importado do services.exceptions.
			throw new organiza.o.gerenciamento.Services.Exceptions.DataIntegrityViolationException("O funcionario não pode ser Excluido!");	
		}
		
	}
	
}
