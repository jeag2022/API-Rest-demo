package com.example.SpringMVC_Project;

import com.example.SpringMVC_Project.model.Category;
import com.example.SpringMVC_Project.model.Product;
import com.example.SpringMVC_Project.model.security.Authority;
import com.example.SpringMVC_Project.model.security.User;
import com.example.SpringMVC_Project.repository.AuthorityRepository;
import com.example.SpringMVC_Project.repository.CategoryRepository;
import com.example.SpringMVC_Project.repository.ProductRepository;
import com.example.SpringMVC_Project.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringMvcProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringMvcProjectApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository,
										AuthorityRepository authorityRepository,
										PasswordEncoder passwordEncoder,
										CategoryRepository categoryRepository,
										ProductRepository productRepository) {
		return args -> {
			Authority userAuthority = authorityRepository.save(new Authority(null, "USER"));
			Authority adminAuthority = authorityRepository.save(new Authority(null, "ADMIN"));

			Set<Authority> authorities4User = new HashSet<>();
			authorities4User.add(userAuthority);
			Set<Authority> authorities4Admin = new HashSet<>();
			authorities4Admin.add(adminAuthority);

			User user = new User(null, "user", passwordEncoder.encode("password"), authorities4User, true, true, true, true);
			User admin = new User(null, "admin", passwordEncoder.encode("password"), authorities4Admin, true, true, true, true);

			userRepository.save(user);
			userRepository.save(admin);

			Category tecCategory = categoryRepository.save(new Category(null, "Tecnology"));
			Category shCategory = categoryRepository.save(new Category(null, "Shoes"));

			Product shoes = productRepository.save(new Product(null, "AND 1 Marauder", shCategory, 135, 0));
		};
	}
}
