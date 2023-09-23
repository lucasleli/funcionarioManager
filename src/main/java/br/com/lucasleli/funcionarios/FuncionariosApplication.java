package br.com.lucasleli.funcionarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FuncionariosApplication {

    public static void main(String[] args) {
        SpringApplication.run(FuncionariosApplication.class, args);
    }

}
