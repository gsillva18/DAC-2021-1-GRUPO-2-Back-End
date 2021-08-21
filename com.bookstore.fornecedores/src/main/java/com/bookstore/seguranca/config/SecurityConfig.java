package com.bookstore.seguranca.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bookstore.seguranca.filtro.AuthTokenFilter;
import com.bookstore.seguranca.util.AuthEntryPointJwt;
import com.bookstore.service.AutenticacaoService;

/**
 * @author NPG
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	/** Para facilitar a geração de senha encriptada */
//	public static void main(String[] args) {
//		System.out.println("Senha 1: " + new BCryptPasswordEncoder().encode("123456789"));
//		System.out.println("Senha 2: " + new BCryptPasswordEncoder().encode("senha234"));
//		System.out.println("Senha 3: " + new BCryptPasswordEncoder().encode("senha345"));
//		System.out.println("Senha 4: " + new BCryptPasswordEncoder().encode("senha456"));
//		System.out.println("Senha 5: " + new BCryptPasswordEncoder().encode("senha567"));
//	}
	
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests()
			.antMatchers("/resources/**", "/webjars/**").permitAll()
			.antMatchers(HttpMethod.GET, "/perfil").hasAuthority("CLIENT")
			.antMatchers(HttpMethod.GET, "/endereco_form/{id}").hasAuthority("CLIENT")
			.antMatchers(HttpMethod.GET, "/pedido_cancelar/{id}").hasAuthority("CLIENT")
			.antMatchers(HttpMethod.GET, "/carrinho").hasAuthority("CLIENT")
			.antMatchers(HttpMethod.GET, "/adicionar_item/{id}").hasAuthority("CLIENT")
			.antMatchers(HttpMethod.GET, "/remover_item/{id}").hasAuthority("CLIENT")
			.antMatchers(HttpMethod.GET, "/adicionar_endereco").hasAuthority("CLIENT")
			.antMatchers(HttpMethod.GET, "/finalizarPedido").hasAuthority("CLIENT")
			.antMatchers(HttpMethod.GET, "/voltar_inicio_carrinho").hasAuthority("CLIENT")
			.antMatchers(HttpMethod.GET, "/administracao").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.GET, "/autor").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.GET, "/autorform/{id}").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.GET, "/editora").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.GET, "/editoraform/{id}").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.GET, "/formapagamento").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.GET, "/livro").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.GET, "/livroform/{id}").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.GET, "/inicio").permitAll()
			.antMatchers(HttpMethod.GET, "/cadastre-se").permitAll()
			.antMatchers(HttpMethod.DELETE, "/fornecedor/deletar/{id}").permitAll()
			.anyRequest().permitAll()
			.and()
			
			.formLogin()
			.loginPage("/login")
			.permitAll()
			.defaultSuccessUrl("/inicio", true)
			
			.and()
			.csrf().disable()
			.logout().logoutSuccessUrl("/inicio");
		
			http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}
}




