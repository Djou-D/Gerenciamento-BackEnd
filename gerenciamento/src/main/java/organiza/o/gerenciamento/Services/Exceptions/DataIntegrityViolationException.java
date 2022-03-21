package organiza.o.gerenciamento.Services.Exceptions;

public class DataIntegrityViolationException extends RuntimeException{

	//Controle de versão
	private static final long serialVersionUID = 1L;

	
	//Constructors superClass
	public DataIntegrityViolationException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public DataIntegrityViolationException(String message) {
		super(message);
		
	}	
	
}
