package br.serratec.com.trabalhofinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrabalhoFinalApplication {

    public static void main(String[] args) {

        System.out.println("ENV EMAIL -> " + System.getenv("USUARIO_EMAIL"));

        SpringApplication.run(TrabalhoFinalApplication.class, args);
    }
}