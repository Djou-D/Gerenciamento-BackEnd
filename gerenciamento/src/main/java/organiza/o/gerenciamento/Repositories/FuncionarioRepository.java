package organiza.o.gerenciamento.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import organiza.o.gerenciamento.Models.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{

	//Metodo para listar os funcionarios com seus cargos respectivos
	@SuppressWarnings("rawtypes")
	@Query(value = "SELECT colaboradores.nome, colaboradores.cidade, colaboradores.email, cargo.nome_cargo, cargo.atribuicao_cargo FROM cargo right JOIN colaboradores ON colaboradores.id_cargo = cargo.id_cargo order by colaboradores.nome", nativeQuery = true)
	List<List> funcionarioComCargo(); 
	
	
	//Metodo para listar os funcionarios atraves do cargo
	@Query(value = "SELECT * FROM colaboradores WHERE colaboradores.id_cargo = :id_cargo", nativeQuery = true)
	List<Funcionario> funcionarioPeloCargo(Integer id_cargo);
	
		
	//Metodo para buscar um funcionario pelo nome para a função de upload de arquivos
	@Query(value = "SELECT * FROM colaboradores WHERE colaboradores.nome = :nome", nativeQuery = true)
	Funcionario funcionarioPeloNome(String nome);
}
