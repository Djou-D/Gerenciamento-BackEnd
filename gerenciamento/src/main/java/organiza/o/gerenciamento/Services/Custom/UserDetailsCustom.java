package organiza.o.gerenciamento.Services.Custom;

import java.util.Collection;
import java.util.stream.Collectors;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import organiza.o.gerenciamento.Models.Colaboradores;

@SuppressWarnings("serial")
public class UserDetailsCustom implements UserDetails{
	
	
	private Colaboradores colaborador;

	
	
	//Retorna as autoridades concedidas ao colaborador. especificadas pelo role de cada colaborador
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return colaborador.getRoles().stream().map(nome_role -> new SimpleGrantedAuthority("ROLE_" + nome_role)).collect(Collectors.toList());
	}

	
	
	//Retorna a senha usada para autenticar o colaborador.
	@Override
	public String getPassword() {
		return colaborador.getSenha();
	}

	
	//Retorna o email/username do colaborador usado para autenticar o colaborador.
	@Override
	public String getUsername() {
		return colaborador.getEmail();
	}

	
	//Indica se a conta do colaborador expirou. Uma conta expirada não pode ser autenticada.
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	
	//Indica se o colaborador está bloqueado ou desbloqueado. Um colaborador bloqueado não pode ser autenticado.
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	
	//Indica se as credenciais do colaborador (senha) expiraram. As credenciais expiradas impedem a autenticação.
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	
	//Indica se o colaborador está habilitado ou desabilitado. Um colaborador desabilitado não pode ser autenticado.
	@Override
	public boolean isEnabled() {
		return true;
	}




	public Colaboradores getColaborador() {
		return colaborador;
	}




	public void setColaborador(Colaboradores colaborador) {
		this.colaborador = colaborador;
	}

	
	
	
	
}
