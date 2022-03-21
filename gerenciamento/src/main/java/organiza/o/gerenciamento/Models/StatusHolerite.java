package organiza.o.gerenciamento.Models;

public enum StatusHolerite {

	//Definição de status
	PENDENTE("Pendente"),
	PAGO("Pago"),
	CANCELADO("Cancelado");
	
	//Atributo
	private String descricao;

	//Constructor
	StatusHolerite(String string) {
	}

	
	//Getters e Setters
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
