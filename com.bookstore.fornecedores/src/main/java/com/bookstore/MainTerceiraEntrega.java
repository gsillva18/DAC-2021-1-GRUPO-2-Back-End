package com.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bookstore.facades.FacadeUsuarios;

@SpringBootApplication
public class MainTerceiraEntrega implements CommandLineRunner {
	
	private FacadeUsuarios facadeUsuarios;
	
	public MainTerceiraEntrega(FacadeUsuarios facadeUsuarios) {
		this.facadeUsuarios = facadeUsuarios;
	}

	public static void main(String[] args) {
		SpringApplication.run(MainTerceiraEntrega.class, args);		
	}

	@Override
	public void run(String... args)  {
		
		//Por padrão, já se mantém o cadastro do administrador do sistema antes de iniciar a aplicação
		// Email: admin@admin.com
		// Senha: admin123
//		try {
//			facadeUsuarios.cadastrarUsuario("admin", "admin@admin.com", new BCryptPasswordEncoder().encode("admin123") , true);
//		} catch (Exception e) {
//			// Exceção lançada após a segunda execução pois ele tentará
//			// setar o adm novamente com o mesmo email, o que não é permitido.
//		}
		
		System.out.println("Server is running at port 8080 bookstore fornecedores");
	}
	
}
