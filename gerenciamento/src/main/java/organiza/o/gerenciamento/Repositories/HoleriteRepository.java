package organiza.o.gerenciamento.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import organiza.o.gerenciamento.Models.Holerite;

public interface HoleriteRepository extends JpaRepository<Holerite, Integer>{

	
	//Metodo para listar os hollerites de um colaborador especifico
	@Query(value = "SELECT * FROM holerite WHERE holerite.id_colaborador = :id_colaborador", nativeQuery = true)
	List<Holerite> listarHoleritesDoColaborador(Integer id_colaborador);
	
	
	
	//Metod para listar holerite e funcionario na mesma lista
	@SuppressWarnings("rawtypes")
	@Query(value = "SELECT colaboradores.dtype, colaboradores.nome, holerite.data_holerite, holerite.status_holerite, holerite.valor_holerite FROM holerite right JOIN colaboradores ON holerite.id_colaborador = colaboradores.id_colaborador order by colaboradores.nome", nativeQuery = true)
	List<List> listarFuncionarioComHolerite();
	
	
	//Metod para listar holerite e funcionario na mesma lista
	@SuppressWarnings("rawtypes")
	@Query(value = "SELECT colaboradores.nome, holerite.data_holerite, holerite.status_holerite, holerite.valor_holerite FROM holerite right JOIN colaboradores ON holerite.id_colaborador = colaboradores.id_colaborador order by colaboradores.nome", nativeQuery = true)
	List<List> listarSupervisorComHolerite();
	
	
}
