package organiza.o.gerenciamento.Models;

import java.util.Set;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Supervisor extends Colaboradores{

	
	@OneToOne			
	@JoinColumn(name = "id_setor", unique = true) /*CHAVE-EXTRANGEIRA*/
	@JsonIgnore
	private Setor setor;
	
	

	
	
	public Supervisor() {
		
	}
	
	
	public Supervisor(Integer id_colaborador, String nome, String cidade, String cpf, String email, String senha,
			String foto, Set<RoleSystem> roles) {
		
		super(id_colaborador, nome, cidade, cpf, email, senha, foto, roles);
		
	}



	public Setor getSetor() {
		return setor;
	}



	public void setSetor(Setor setor) {
		this.setor = setor;
	}



	
	


	
}
