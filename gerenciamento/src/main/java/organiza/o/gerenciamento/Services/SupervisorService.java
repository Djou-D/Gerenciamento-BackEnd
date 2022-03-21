package organiza.o.gerenciamento.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import organiza.o.gerenciamento.Models.Setor;
import organiza.o.gerenciamento.Models.Supervisor;
import organiza.o.gerenciamento.Repositories.SupervisorRepository;
import organiza.o.gerenciamento.Services.Exceptions.ObjectNotFoundException;

@Service
public class SupervisorService {

	
	@Autowired
	private SupervisorRepository superRepository;
	
	@Lazy
	@Autowired
	private SetorService setorService;
	
	
	// METODOS
	
	// GETTERS
	
	//Método para retornar todos os supervisores
	public List<Supervisor> mostrarTodosOsSupervisores(){
		
		return superRepository.findAll();	
	}
	
	
	//Método para retornar um supervisor
	public Supervisor mostrarSupervisor(Integer id_colaborador) {
		
		Optional<Supervisor> supervisor = superRepository.findById(id_colaborador);
		return supervisor.orElseThrow(() -> new ObjectNotFoundException("Objeto não cadastrado! O id procurado foi: " + id_colaborador + " e ele não consta no banco de dados"));
	}
	
	
	//Metordo para retornar todos os supervisores sem setor
	public List<Supervisor> supervisorSemSetor(){
		
		return superRepository.supervisorSemSetor();
	}
	

	//Método para retornar um objeto vinculado a outro
	public Supervisor mostrarSupervisorDoSetor(Integer id_setor) {
		
		return superRepository.mostrarSupervisorDoSetor(id_setor);
	}
	
	// FIM DOS GETTERS
	
	// POST
	
	//Metodo de cadastro para cadastro do supervisor
	public Supervisor cadastrarSupervisor(Supervisor supervisor) {
		
		supervisor.setId_colaborador(null);
		
		return superRepository.save(supervisor);
	}
	
	
	//Método de cadastro do supervisor com o setor especifico
	public Supervisor inserirSupervisor(Integer id_setor, Supervisor supervisor) {
		
		supervisor.setId_colaborador(null);
		
		if(id_setor != null) {
			
		Setor setor = setorService.mostrarSetor(id_setor);
		supervisor.setSetor(setor);
		setor.setSupervisor(supervisor);
		
		}
		return superRepository.save(supervisor);
		
	}
	
	
	// PUT
	
	//Método de edição do supervisor
	public Supervisor editarSupervisor(Integer id_setor, Supervisor supervisor) {
		
		mostrarSupervisor(supervisor.getId_colaborador());
		
		if(id_setor != null) {
			
			Setor setorAtual = setorService.mostrarSetor(id_setor);
		
			if(setorAtual != null) {
				setorAtual.setSupervisor(null);
			}
			
			Setor setor = setorService.mostrarSetor(id_setor);	
			supervisor.setSetor(setor);
			setor.setSupervisor(supervisor);
		
		}
		
		return superRepository.save(supervisor);
	}
	
	
	//Método de edição do supervisor
	public Supervisor editarUmSupervisor(Supervisor supervisor) {
		
		mostrarSupervisor(supervisor.getId_colaborador());
		
		return superRepository.save(supervisor);
	}
	
	
	
	// DELETE
	
	//Método de exclusão
	public void excluirSupervisor(Integer id_colaborador) {
		mostrarSupervisor(id_colaborador);
		
		try {
			
			superRepository.deleteById(id_colaborador);
		
		} catch (DataIntegrityViolationException e) {
			
			//É chamada a Data... como retorno de objeto importado do services.exceptions.
			throw new organiza.o.gerenciamento.Services.Exceptions.DataIntegrityViolationException("O supervisor não pode ser Excluido!");	
		}
		
		
	}
	
}
