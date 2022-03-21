package organiza.o.gerenciamento.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import organiza.o.gerenciamento.Models.Funcionario;
import organiza.o.gerenciamento.Models.Holerite;
import organiza.o.gerenciamento.Models.StatusHolerite;
import organiza.o.gerenciamento.Models.Supervisor;
import organiza.o.gerenciamento.Repositories.HoleriteRepository;
import organiza.o.gerenciamento.Services.Exceptions.ObjectNotFoundException;

@Service
public class HoleriteService {

	@Autowired
	private HoleriteRepository holeriteRepository;
	
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private SupervisorService supervisorService;
	
	
	
	// METODOS
	
	// GETTERS
	
	//Metodo para listar todos os holerites
	public List<Holerite> buscarTodosOsHolerites(){
		
		return holeriteRepository.findAll();
	}
	
	
	//Metodo para listar um holerite especifico
	public Holerite buscarHolerite(Integer codigo) {
		
		Optional<Holerite> holerite = holeriteRepository.findById(codigo);
		return holerite.orElseThrow(() -> new ObjectNotFoundException("Objeto não cadastrado! O codigo procurado foi: " + codigo + " e ele não consta no banco de dados"));
	}
	
	
	//Metodo para listar os holerites de um funcionario
	public List<Holerite> buscarHoleriteDoFuncionario(Integer id_colaborador) {
		
		List<Holerite> holerite = holeriteRepository.listarHoleritesDoColaborador(id_colaborador);
		return holerite;
	}
	
	
	//Metodo para listar os holerites de um supervisor
	public List<Holerite> buscarHoleriteDoSupervisor(Integer id_colaborador){
		
		List<Holerite> holerite = holeriteRepository.listarHoleritesDoColaborador(id_colaborador);
		return holerite;
	}
	
	//Metodo para listar os holerites com o funcionario em uma mesma lista
	@SuppressWarnings("rawtypes")
	public List<List> listarHoleriteComFuncionario(){
		
		return holeriteRepository.listarFuncionarioComHolerite();
	}
	
	
	//Metodo para listar os holerites com os supervisores em uma mesma lista
	@SuppressWarnings("rawtypes")
	public List<List> listarHoleriteComSupervisor(){
		
		return holeriteRepository.listarSupervisorComHolerite();
	}
	
	// FIM DOS GETTERS
	
	// POST
	
	//Metodo para cadastrar um holerite a um funcionario
	public Holerite inserirHoleriteFuncionario(Holerite holerite, Integer id_colaborador) {
		
		holerite.setCodigo(null);
		Funcionario funcionario = funcionarioService.mostrarFuncionario(id_colaborador);
		
		holerite.setColaborador(funcionario);
		
		return holeriteRepository.save(holerite);
	}
	
	//Metodo para cadastrar um holerite a um supervisor
	public Holerite inserirHoleriteSupervisor(Holerite holerite, Integer id_colaborador) {
		
		holerite.setCodigo(null);
		Supervisor supervisor = supervisorService.mostrarSupervisor(id_colaborador);
		
		holerite.setColaborador(supervisor);
		
		return holeriteRepository.save(holerite);
	}
	
	
	// PUT
	
	//Metodo para editar um holerite de um funcionario
	public Holerite editarHoleriteFuncionario(Integer codigo, Integer id_colaborador, Holerite holerite) {
		
		buscarHolerite(codigo);
		
		Funcionario funcionario = funcionarioService.mostrarFuncionario(id_colaborador);
		holerite.setColaborador(funcionario);
		
		return holeriteRepository.save(holerite);
	}
	
	//Metodo para editar um holerite de um supervisor
	public Holerite editarHoleriteSupervisor(Integer codigo, Integer id_colaborador, Holerite holerite) {
		
		buscarHolerite(codigo);
		
		Supervisor supervisor = supervisorService.mostrarSupervisor(id_colaborador);
		holerite.setColaborador(supervisor);
		
		return holeriteRepository.save(holerite);
	}
	
	
	//Metodo para validar um boleto
	public Holerite holeritePago(Integer codigo) {
		
		Holerite holerite= buscarHolerite(codigo);
		
		StatusHolerite st1 = StatusHolerite.PAGO;
		
		holerite.setStatus_holerite(st1);
		
		return holeriteRepository.save(holerite);
	}
	
	
	//Metodo para cancelar um boleto
	public Holerite holeriteCancelado(Integer codigo) {
		
		Holerite holerite = buscarHolerite(codigo);
		
		StatusHolerite st1 = StatusHolerite.CANCELADO;
		
		holerite.setStatus_holerite(st1);
		
		return holeriteRepository.save(holerite);
	}
	
	
	// DELETE
	
	public void deletarHolerite(Integer codigo) {
		buscarHolerite(codigo);
		
		try {
			
			holeriteRepository.deleteById(codigo);
		
		} catch (DataIntegrityViolationException e) {
			
			//É chamada a Data... como retorno de objeto importado do services.exceptions.
			throw new organiza.o.gerenciamento.Services.Exceptions.DataIntegrityViolationException("O Holerite não pode ser Excluido!");	
		}
		
		
	}
	
	
	
}
