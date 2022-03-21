package organiza.o.gerenciamento.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import organiza.o.gerenciamento.Models.Setor;
import organiza.o.gerenciamento.Models.Supervisor;
import organiza.o.gerenciamento.Repositories.SetorRepository;
import organiza.o.gerenciamento.Services.Exceptions.ObjectNotFoundException;


@Service
public class SetorService {

	
	@Autowired
	private SetorRepository setorRepository;
	
	@Autowired
	private SupervisorService superService;
	
	
	
	//METODOS
	
	// GETTERS
	
	//Metodo para listar todos os setores
	public List<Setor> mostrarTodosOsSetores(){
		
		return setorRepository.findAll();
	}
	
	//Metoso para listar um setor
	public Setor mostrarSetor(Integer id_setor) {
		
		Optional<Setor> setor = setorRepository.findById(id_setor);
		
		return setor.orElseThrow(() -> new ObjectNotFoundException("Objeto não cadastrado! O id procurado foi: " + id_setor + " e ele não consta no banco de dados"));
		
	}
	
	
	//Metodo para listar os setores e supervisores em uma mesma lista
	@SuppressWarnings("rawtypes")
	public List<List> setorESupervisor(){
		
		return setorRepository.setorESupervisor();
	}
	
	
	
	//Metodo para listar setores sem supervisor
	public List<Setor> setorSemSupervisor(){
			
		return setorRepository.setorSemSupervisor();
	}
		
		
	
	//Metoso para listar setores que não tenham cargos vinculados
	public List<Setor> setorSemCargo(){
		
		return setorRepository.setorSemCargo();
	}
	
	// FIM DOS GETTERS
	
	// POST
	
	//Metodo para cadastrar um setor
	public Setor cadastrarSetor(Setor setor) {
		
		setor.setId_setor(null); /*Por que o id é auto incrementavel*/
		
		return setorRepository.save(setor);
	}
	
	
	// PUTS
	
	//Metodo para editar um setor
	public Setor editarSetor(Setor setor) {
		
		mostrarSetor(setor.getId_setor());
		
		return setorRepository.save(setor);
	}
	
	
	//Metodo de edição para desvincular o supervisor do setor
	public Setor deixarSetorSemSupervisor(Integer id_setor, Integer id_colaborador) {
		
		Setor setor = mostrarSetor(id_setor);
		setor.setSupervisor(null);
		
		Supervisor  supervisor = superService.mostrarSupervisor(id_colaborador);
		supervisor.setSetor(null);
		
		return setorRepository.save(setor);
	}
	
	
	//Metodo de edição para atribuir um supervisor a um setor
	public Setor atribuirSupervisor(Integer id_setor, Integer id_colaborador){
		
		Setor setor = mostrarSetor(id_setor);
		
		Supervisor supervisorAnterior = superService.mostrarSupervisorDoSetor(id_setor);
		Supervisor supervisor = superService.mostrarSupervisor(id_colaborador);
		
		if(setor.getSupervisor() != null) {
			
			setor.setSupervisor(null);
			supervisorAnterior.setSetor(null);
		}
		
		setor.setSupervisor(supervisor);
		supervisor.setSetor(setor);
		
		return setorRepository.save(setor);
	}
	
	
	// DELETE
	
	//Metodo de exclusão de um setor
	public void excluirSetor(Integer id_setor) {
		mostrarSetor(id_setor);
		
		//Tratamento de esceções do retorno de erro de deleção
		try {
			
			setorRepository.deleteById(id_setor);
		//É chamada a Data... como parametro importada do spring
		} catch (DataIntegrityViolationException e) {
			
			//É chamada a Data... como retorno de objeto importado do services.exceptions.
			throw new organiza.o.gerenciamento.Services.Exceptions.DataIntegrityViolationException("O setor não pode ser Excluido por que tem cargos vinculados a ele!");	
		}		
	}

	
}
