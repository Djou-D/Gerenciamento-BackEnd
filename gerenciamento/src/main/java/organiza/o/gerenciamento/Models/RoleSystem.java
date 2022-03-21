package organiza.o.gerenciamento.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RoleSystem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_role;
	
	private String nome_role;
	
	
	
	public Integer getId_role() {
		return id_role;
	}

	public void setId_role(Integer id_role) {
		this.id_role = id_role;
	}

	public String getNome_role() {
		return nome_role;
	}

	public void setNome_role(String nome_role) {
		this.nome_role = nome_role;
	}
	
	
}
