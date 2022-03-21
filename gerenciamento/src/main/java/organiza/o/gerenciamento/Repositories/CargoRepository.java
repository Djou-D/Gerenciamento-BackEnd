package organiza.o.gerenciamento.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import organiza.o.gerenciamento.Models.Cargo;
import organiza.o.gerenciamento.Models.Funcionario;

public interface CargoRepository extends JpaRepository<Cargo, Integer>{

	
	//Metodo para listar todos os cargos de determinado setor
	@Query(value = "SELECT * FROM cargo WHERE cargo.id_setor = :id_setor", nativeQuery = true)
	List<Cargo> cargosDoSetor(Integer id_setor);
	
	
	//Metodo para listar os cargos que não estão vinculados a nenhum setor
	@Query(value="SELECT * FROM cargo WHERE cargo.id_setor is null", nativeQuery = true)
	List<Cargo> cargosSemSetor();
	
	
	//Metodo para listar cargos com setores
	@SuppressWarnings("rawtypes")
	@Query(value = "SELECT setor.id_setor, setor.nome_setor, setor.atribuicao_setor, cargo.id_cargo, cargo.nome_cargo, cargo.atribuicao_cargo FROM cargo right JOIN setor ON cargo.id_setor = setor.id_setor order by setor.nome_setor;",nativeQuery = true)
	List<List> cargosComSetor();
	
	
	
	//Metodo para listar os funcionarios de um cargo especifico
	@Query(value = "SELECT * FROM colaboradores WHERE colaboradores.id_cargo = :id_cargo", nativeQuery = true)
	List<Funcionario> funcionariosDoCargo(Integer id_cargo);
}
