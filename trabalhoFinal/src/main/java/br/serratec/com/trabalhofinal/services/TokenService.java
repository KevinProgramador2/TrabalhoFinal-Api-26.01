package br.serratec.com.trabalhofinal.services;

import br.serratec.com.trabalhofinal.model.Cliente;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.springframework.security.config.Elements.JWT;

@Service
public class TokenService {

    // Em produção, use uma chave complexa vinda de variáveis de ambiente
    private final String secret = "minha-chave-secreta-super-protegida";

    public void gerarToken(Cliente cliente) {
        JWT.create()
                .withIssuer("api-mecanica")
                .withSubject(cliente.getEmail())
                .withExpiresAt(Instant.now().plus(2, ChronoUnit.HOURS))
                .sign(Algorithm.HMAC256(secret));
    }
}
