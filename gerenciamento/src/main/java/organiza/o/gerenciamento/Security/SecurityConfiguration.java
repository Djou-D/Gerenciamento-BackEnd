package organiza.o.gerenciamento.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity /*É usado para ativar a segurança personalizada*/
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {



	@Autowired
	private UserDetailsService userDetailsService;


	@Bean
	public BCryptPasswordEncoder senhaCodificada() {
		
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.userDetailsService(userDetailsService).passwordEncoder(senhaCodificada());
	}
	
	
	
	
	//Instanciando o metodo HttpSecurity  como paramentro do metodo configure
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.cors().disable();
		
		//authorizeRequests faz as autorizações das requisições
		//antMatchers dá permição a acessos externos. Para dar permições ao bootstrap, para que o security não bloquei o bootstarp
		//AnyRequest diz que as configurações definidas devem ser seguidas por todas as requisições
		//authenticated diz que o usuario deve estar autenticado para acessar as requisições
		//loginPage direciona para a rota/endpoint especificado
		//Requisições basicas de http
//		http.httpBasic();
		//Para impedir erros de cors
		//Autorizando os usuarios de cada tipo de role
		
//		http.csrf().disable().authorizeRequests()
//		.antMatchers(HttpMethod.GET, "/").permitAll()
//		.antMatchers(HttpMethod.GET, "/empresa/**").hasRole("ADMIN")
//		.antMatchers(HttpMethod.POST, "/empresa/**").hasRole("ADMIN")
//		.antMatchers(HttpMethod.PUT, "/empresa/**").hasRole("ADMIN")
//		.antMatchers(HttpMethod.DELETE, "/empresa/**").hasRole("ADMIN")
//		.antMatchers(HttpMethod.GET, "/empresa/funcionario/holerite**/**").hasRole("USER")
//		.antMatchers(HttpMethod.GET, "/empresa/supervisor/holerite**/**").hasRole("USER")
//		.anyRequest().authenticated()
//		.and().formLogin()
//		.loginPage("/login").permitAll()
//		.and().logout().permitAll();
		
	}
	
}
