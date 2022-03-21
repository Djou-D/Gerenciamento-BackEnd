package organiza.o.gerenciamento.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import organiza.o.gerenciamento.Models.Colaboradores;


public interface ColaboradorRepository extends JpaRepository<Colaboradores, Integer>{

	
	Colaboradores findByEmail(String email);
	
	
	@Query(value = "SELECT * FROM colaboradores WHERE colaboradores.nome = :nome", nativeQuery = true)
	Colaboradores colaboradorPeloNome(String nome);
	
	
	@SuppressWarnings("rawtypes")
	@Query(value = "SELECT colaboradores.dtype, colaboradores.id_colaborador, colaboradores.cpf, colaboradores.email, colaboradores.cidade, colaboradores.foto, colaboradores.nome, colaboradores.senha, colaboradores.id_cargo, role_system.id_role, role_system.nome_role FROM colaboradores right join role on colaboradores.id_colaborador = role.id_colaborador left join role_system on role.id_role = role_system.id_role order by colaboradores.nome;", nativeQuery = true)
	List<List> colaboradoresRoles();

}
