package organiza.o.gerenciamento.Services.Custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import organiza.o.gerenciamento.Models.Colaboradores;
import organiza.o.gerenciamento.Repositories.ColaboradorRepository;

@Service
public class UserDetailsServiceCustom implements UserDetailsService{

	
	@Autowired
	private ColaboradorRepository repository;

	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Colaboradores colaborador = repository.findByEmail(email);
		
		UserDetailsCustom userDetails = null;
		
		if(colaborador != null) {
			
			userDetails = new UserDetailsCustom();
			
			userDetails.setColaborador(colaborador);
		
		} else {
			
			throw new UsernameNotFoundException("O Colaborador: " + email + "não existe!");
		}
		
		return userDetails;
	}
	
	
	
	
}
