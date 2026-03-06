package br.com.challenge.forumhub;

import br.com.challenge.forumhub.model.User;
import br.com.challenge.forumhub.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ForumhubApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumhubApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (userRepository.findByUsername("admin").isEmpty()) {
				User user = new User();
				user.setUsername("admin");
				user.setPassword(passwordEncoder.encode("123456")); // senha criptografada
				userRepository.save(user);
				System.out.println("Usuário de teste criado: admin / 123456");
			}
		};
	}
}