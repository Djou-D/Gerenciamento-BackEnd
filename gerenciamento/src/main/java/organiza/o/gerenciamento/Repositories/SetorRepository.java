package organiza.o.gerenciamento.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import organiza.o.gerenciamento.Models.Setor;

public interface SetorRepository extends JpaRepository<Setor, Integer>{

	//Metodo para listar setores sem cargos
	@Query(value="SELECT * FROM setor WHERE cargo.id_setor is null", nativeQuery = true)
	List<Setor> setorSemCargo();
	
	
	
	//Metodo para listar um setor sem supervisor
	@Query(value = "SELECT * FROM setor WHERE setor.id_colaborador is null", nativeQuery = true)
	List<Setor> setorSemSupervisor();
	
	
	//Metodo para listar o setor com o supervisor na mesma lista
	@SuppressWarnings("rawtypes")
	@Query(value = "SELECT setor.id_setor, setor.nome_setor, setor.atribuicao_setor, colaboradores.id_colaborador, colaboradores.nome, colaboradores.email FROM setor left JOIN colaboradores ON setor.id_colaborador = colaboradores.id_colaborador order by setor.nome_setor",nativeQuery = true)
	List<List> setorESupervisor();

	
}
