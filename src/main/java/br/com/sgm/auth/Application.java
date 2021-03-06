package br.com.sgm.auth;

import br.com.sgm.auth.model.Role;
import br.com.sgm.auth.model.User;
import br.com.sgm.auth.service.UserService;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableDiscoveryClient
@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private UserService service;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
        User admin = new User();
        admin.setName("Administrador");
        admin.setCpf("05783219045");
        admin.setPhone("0619211223");
        admin.setUsername("admin");
        admin.setPassword("12345");
        admin.setEmail("admin@gmail.com");
        admin.setRoles(new ArrayList<>(Arrays.asList(Role.ROLE_ADMIN)));

        service.signup(admin);

        User client = new User();
        client.setUsername("client");
        client.setName("client");
        client.setCpf("80037687050");
        client.setPhone("0619211223");
        client.setPassword("12345");
        client.setEmail("client@gmail.com");
        client.setRoles(new ArrayList<>(Arrays.asList(Role.ROLE_USER)));

        service.signup(client);

        User citizen = new User();
        citizen.setName("citizen");
        citizen.setCpf("03924066167");
        citizen.setPhone("0619211223");
        citizen.setUsername("citizen");
        citizen.setPassword("12345");
        citizen.setEmail("citizen@gmail.com");
        citizen.setRoles(new ArrayList<>(Arrays.asList(Role.ROLE_CITIZEN)));

        service.signup(citizen);
    }
}
