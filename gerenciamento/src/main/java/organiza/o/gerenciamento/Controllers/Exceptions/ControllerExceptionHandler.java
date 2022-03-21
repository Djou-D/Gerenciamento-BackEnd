package organiza.o.gerenciamento.Controllers.Exceptions;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import organiza.o.gerenciamento.Services.Exceptions.DataIntegrityViolationException;
import organiza.o.gerenciamento.Services.Exceptions.ObjectNotFoundException;


//Especificando que essa classe é uma classe de controle de exceções
@ControllerAdvice
public class ControllerExceptionHandler {

	//retorna a classe StandartError que contem os atributos tempo, status e erro
	//servletRequest aqui faz as requisições ao servidor para retorno dos erros
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e, ServletRequest request){ /*ResponsEntity traz toda a class modelo setada incluindo seus cabeçalhos e valores*/
		
		//Tratamento do retorno de data e hora
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		String dataFormatada = formatador.format(data);
		
		//instanciando a class StandardError e passando os atributos como parametro
		StandardError error = new StandardError(dataFormatada, HttpStatus.NOT_FOUND.value(), e.getMessage()); /*Valores dos atributos = Hora do sistema em ms, valor do status de erro, através do objeto "e" pegamos a mensagem definida no service */
		
		//Retorna o corpo do erro que no caso são os valores passados no parametro da instanciação da class StandardErro do objeto error
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
		
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException e, ServletRequest request){ 
		
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		String dataFormatada = formatador.format(data);
		
		StandardError error = new StandardError(dataFormatada, HttpStatus.BAD_REQUEST.value(), e.getMessage()); 
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
}
