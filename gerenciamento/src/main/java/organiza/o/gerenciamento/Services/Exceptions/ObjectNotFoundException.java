package organiza.o.gerenciamento.Services.Exceptions;

//Para criar uma exception é necessario extends de RuntimeException e indicar uma versão serial
public class ObjectNotFoundException extends RuntimeException{

	//O tratamento de versão se incrementa de forma automatica
	private static final long serialVersionUID = 1L;

	
	//Contrutores de superclass
	public ObjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	
	public ObjectNotFoundException(String message) {
		super(message);
	}
	
	
}
