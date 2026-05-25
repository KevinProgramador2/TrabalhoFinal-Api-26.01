package br.serratec.com.trabalhofinal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioLoginRequestDTO(
@NotBlank(message = "Email")
@Email(message = "Email inválido")
String email,

@NotBlank(message = "Senha")
@Size(min = 6, message = "Senha deve ter pelo menos 6 caracteres")
String senha
) {
}
