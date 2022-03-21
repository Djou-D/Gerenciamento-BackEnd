package organiza.o.gerenciamento.Models;

import java.util.Set;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Funcionario extends Colaboradores{

	
//	@JsonIgnore
//	@ManyToOne
//	@JoinColumn(name = "id_setor")
//	private Setor setor;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_cargo")
	private Cargo cargo;
	
	
	
	public Funcionario() {
		
	}
	
	
	public Funcionario(Integer id_colaborador, String nome, String cidade, String cpf, String email, String senha,
			String foto, Set<RoleSystem> roles) {
		super(id_colaborador, nome, cidade, cpf, email, senha, foto, roles);
		
		
	}
	


	


	public Cargo getCargo() {
		return cargo;
	}




	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}



	
	

	
	
	


}
