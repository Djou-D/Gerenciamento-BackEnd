package organiza.o.gerenciamento.Controllers.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import organiza.o.gerenciamento.Services.ColaboradoresService;
import organiza.o.gerenciamento.Services.Utils.UploadFileUtil;


@CrossOrigin
@RestController
@RequestMapping("empresa")
public class UploadFileController {

	@Autowired
	private ColaboradoresService colaboradorService;
	
	
	
	
	
	//Upload da foto do supervisor
	@PostMapping("/enviar/imagem/{id_colaborador}")
	public ResponseEntity<String> enviarDados(@PathVariable Integer id_colaborador, MultipartFile foto, @RequestParam(value = "nome")String nome_imagem){
		
		String fileName = nome_imagem;
		String uploadDir = "/home/djou/Documentos/SoulCode/Java-web-com-spring/Front-Angular/empresa-front/src/assets/img";

		String nomeMaisCaminho = "/assets/img" + "/" + nome_imagem;
		
		colaboradorService.salvarFoto(id_colaborador, nomeMaisCaminho);

		try {
			
			UploadFileUtil.salvarArquivo(uploadDir, fileName, foto);
		}
		catch(Exception e) {
			System.out.println("O arquivo não foi enviado" + e);
		}
		
		System.out.println("Arquivo: " + nomeMaisCaminho + " Ok");
		return ResponseEntity.ok("Arquivo enviado!");
	}
	
	
	
	
	
	
	
}
