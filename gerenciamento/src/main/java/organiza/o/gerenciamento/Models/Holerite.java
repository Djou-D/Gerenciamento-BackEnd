package organiza.o.gerenciamento.Models;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Holerite {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	
		
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(columnDefinition = "date", nullable = false)
	private Date data_holerite;
	
	
	@NumberFormat(pattern = "#.##0,00")
	@Column(nullable = false)
	private Double valor_holerite;
	
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusHolerite status_holerite;
	
	
	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "id_colaborador")
	private Colaboradores colaborador;


	
	
	public Integer getCodigo() {
		return codigo;
	}


	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}


	public Date getData_holerite() {
		return data_holerite;
	}


	public void setData_holerite(Date data_holerite) {
		this.data_holerite = data_holerite;
	}


	public Double getValor_holerite() {
		return valor_holerite;
	}


	public void setValor_holerite(Double valor_holerite) {
		this.valor_holerite = valor_holerite;
	}


	public StatusHolerite getStatus_holerite() {
		return status_holerite;
	}


	public void setStatus_holerite(StatusHolerite status_holerite) {
		this.status_holerite = status_holerite;
	}


	public Colaboradores getColaborador() {
		return colaborador;
	}


	public void setColaborador(Colaboradores colaborador) {
		this.colaborador = colaborador;
	}


	
	
	
	
}
