package organiza.o.gerenciamento.Services.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class UploadFileUtil {

	//Metodo para salvar um arquivo
	public static void  salvarArquivo(String uploadDir, String fileName, MultipartFile file) throws IOException {
		
		Path uploadPath = Paths.get(uploadDir);
		
		if(!Files.exists(uploadPath)) {
			
			Files.createDirectories(uploadPath);
		}
		
		try(InputStream inputStream = file.getInputStream()){
			
			//caminho do arquivo e nome do arquivo selecionado
			Path filePath = uploadPath.resolve(fileName);
			//o ultimo parametro  do copy, subistitui um arquivo de mesmo nome no diretorio
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			
		}
		catch (IOException e){
			
			throw new IOException("Não foi possivel enviar o arquivo" + fileName, e);
		}
	}
}
