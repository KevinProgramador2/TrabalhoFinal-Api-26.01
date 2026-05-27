package br.serratec.com.trabalhofinal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteRequestDTO(
        @NotBlank(message = "Nome")
        @Size(min = 3, max = 100, message = "Digite o Nome")
        String nome,

        @NotBlank(message = "Telefone")
        @Size(min = 10, max = 15, message = "Digite o Telefone")
        @Pattern(regexp = "\\d{10,15}", message = "Telefone deve conter apenas números")
        String telefone,

        @NotBlank(message = "Email")
        @Email(message = "Email inválido")
        @Size(max = 50, message = "Digite o Email")
        String email,

        @NotBlank(message = "CPF")
        @Size(min = 11, max = 11, message = "Digite o CPF")
        @Pattern(regexp = "\\d{11}", message = "CPF deve conter apenas números")
        String cpf,

        @NotBlank(message = "CEP")
        @Size(min = 8, max = 8, message = "Digite o CEP")
        @Pattern(regexp = "\\d{8}", message = "CEP deve conter apenas números")
        String cep
    ){
}
