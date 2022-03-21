package organiza.o.gerenciamento.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;


@Entity
public class Colaboradores {

	
	@Id  /*CHAVE-PRIMARIA*/
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id_colaborador;

	@Column(nullable = false, length = 150)
	protected String nome;
	
	@Column(nullable = true, length = 150)
	private String cidade;
	
	@Column(nullable = true, length = 11)
	private String cpf;
	
	@Column(nullable = true, length = 150)
	private String email;
	
	@Column(nullable = true, length = 150)
	private String senha;
	
	@Column(nullable = true, length = 450)
	private String foto;
	
	

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "role", joinColumns = @JoinColumn(name = "id_colaborador"), inverseJoinColumns = @JoinColumn(name = "id_role"))
	private Set<RoleSystem> roles;

	
	@OneToMany(mappedBy = "colaborador", cascade = CascadeType.ALL)
	private List<Holerite> holerite = new ArrayList<>();
	

	public Colaboradores() {
		
	}



	public Colaboradores(Integer id_colaborador, String nome, String cidade, String cpf, String email, String senha,
			String foto, Set<RoleSystem> roles) {

		this.id_colaborador = id_colaborador;
		this.nome = nome;
		this.cidade = cidade;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.foto = foto;
		this.roles = roles;
	}


	
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCidade() {
		return cidade;
	}


	public void setCidade(String cidade) {
		this.cidade = cidade;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getFoto() {
		return foto;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}


	public Set<RoleSystem> getRoles() {
		return roles;
	}


	public void setRoles(Set<RoleSystem> roles) {
		this.roles = roles;
	}



	public Integer getId_colaborador() {
		return id_colaborador;
	}



	public void setId_colaborador(Integer id_colaborador) {
		this.id_colaborador = id_colaborador;
	}



	public List<Holerite> getHolerite() {
		return holerite;
	}



	public void setHolerite(List<Holerite> holerite) {
		this.holerite = holerite;
	}





	
	
	
	
	
	
	

}
