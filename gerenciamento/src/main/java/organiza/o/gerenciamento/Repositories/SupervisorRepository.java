package organiza.o.gerenciamento.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import organiza.o.gerenciamento.Models.Supervisor;

public interface SupervisorRepository extends JpaRepository<Supervisor, Integer>{

	
	@Query(value = "SELECT * FROM colaboradores WHERE colaboradores.id_setor is null", nativeQuery = true)
	List<Supervisor> supervisorSemSetor();
	
	
	@SuppressWarnings("rawtypes")
	@Query(value = "SELECT  colaboradores.nome, colaboradores.email, setor.nome_setor, setor.atribuicao_setor FROM setor right JOIN colaboradores ON colaboradores.id_setor = setor.id_setor order by colaboradores.nome;",nativeQuery = true)
	List<List> supervisorESetor();
	
	
	
	
	//Metodo para listar o supervisor de um setor especifico
	@Query(value = "SELECT * FROM colaboradores WHERE colaboradores.id_setor = :id_setor", nativeQuery = true)
	Supervisor mostrarSupervisorDoSetor(Integer id_setor);
}
