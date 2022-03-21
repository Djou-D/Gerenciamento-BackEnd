package organiza.o.gerenciamento.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import organiza.o.gerenciamento.Models.Colaboradores;
import organiza.o.gerenciamento.Repositories.ColaboradorRepository;
import organiza.o.gerenciamento.Services.Exceptions.ObjectNotFoundException;

@Service
public class ColaboradoresService {

	
	@Autowired
	private ColaboradorRepository repository;
	
	
	//Método para retornar um colaborador
	public Colaboradores mostrarColaborador(Integer id_colaborador) {
		
		Optional<Colaboradores> colaborador = repository.findById(id_colaborador);
		return colaborador.orElseThrow(() -> new ObjectNotFoundException("Objeto não cadastrado! O id procurado foi: " + id_colaborador + " e ele não consta no banco de dados"));
	}
	
	
	@SuppressWarnings("rawtypes")
	public List<List> colaboradorRoles(){
		return repository.colaboradoresRoles();
	}
	
	
	
	//Metodo que busca o supervisor pelo nome para o metodo de upload de arquivo
	public Colaboradores buscarColaboradorPeloNome(String nome) {
		
		Colaboradores colaborador = repository.colaboradorPeloNome(nome);
		return colaborador;
	}
	
	
	//Metodo para salvar o caminho de um arquivo no banco de dados
	public Colaboradores salvarFoto(Integer id_colaborador, String caminhoFoto) {
		
		Colaboradores colaborador = mostrarColaborador(id_colaborador);
		colaborador.setFoto(caminhoFoto);
		
		return repository.save(colaborador);
	}
	
	
}
