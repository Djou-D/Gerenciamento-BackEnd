package organiza.o.gerenciamento.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Cargo {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_cargo;
	
	@Column(nullable = false, length = 150)
	private String nome_cargo;
	
	@Column(nullable = false, length = 500)
	private String atribuicao_cargo;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_setor")
	private Setor setor;

	
	@OneToMany(mappedBy = "cargo")
	private List<Funcionario> funcionario = new ArrayList<>();


	public Integer getId_cargo() {
		return id_cargo;
	}


	public void setId_cargo(Integer id_cargo) {
		this.id_cargo = id_cargo;
	}


	public String getNome_cargo() {
		return nome_cargo;
	}


	public void setNome_cargo(String nome_cargo) {
		this.nome_cargo = nome_cargo;
	}


	public String getAtribuicao_cargo() {
		return atribuicao_cargo;
	}


	public void setAtribuicao_cargo(String atribuicao_cargo) {
		this.atribuicao_cargo = atribuicao_cargo;
	}


	public Setor getSetor() {
		return setor;
	}


	public void setSetor(Setor setor) {
		this.setor = setor;
	}


	public List<Funcionario> getFuncionario() {
		return funcionario;
	}


	public void setFuncionario(List<Funcionario> funcionario) {
		this.funcionario = funcionario;
	}
	
	
	
}
