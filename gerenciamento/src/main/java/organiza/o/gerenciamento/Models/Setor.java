package organiza.o.gerenciamento.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Setor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_setor;
	
	@Column(nullable = false, length = 150)
	private String nome_setor;
	
	@Column(nullable = false, length = 500)
	private String atribuicao_setor;
	
	
	//Um para muitos, o setor pode ter muitos cargos.
	@OneToMany(mappedBy = "setor")
	private List<Cargo> cargos = new ArrayList<>();

	
	@OneToOne
	@JoinColumn(name = "id_colaborador", unique = true)
	private Supervisor supervisor;

	
	
	
	public Integer getId_setor() {
		return id_setor;
	}

	public void setId_setor(Integer id_setor) {
		this.id_setor = id_setor;
	}

	public String getNome_setor() {
		return nome_setor;
	}

	public void setNome_setor(String nome_setor) {
		this.nome_setor = nome_setor;
	}

	public String getAtribuicao_setor() {
		return atribuicao_setor;
	}

	public void setAtribuicao_setor(String atribuicao_setor) {
		this.atribuicao_setor = atribuicao_setor;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	public Supervisor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}
	
	
	
}
