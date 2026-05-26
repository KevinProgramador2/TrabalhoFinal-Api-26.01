package br.serratec.com.trabalhofinal.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record VeiculoRequestDTO(
@NotBlank(message = "Placa obrigatória")
@Size(min = 7, max = 8, message = "Digite a placa")
@Pattern(regexp = "[A-Z]{3}-?\\d{4}", message = "Número de placa inválido")
String placa,

@NotBlank(message = "Marca obrigatória")
@Size(min = 2, max = 50, message = "Digite a marca do veiculo")
String marca,

@NotBlank(message = "Modelo obrigatório")
@Size(min = 2, max = 50, message = "Digite o modelo do veiculo")
String modelo,

@NotNull(message = "Ano obrigatório")
@Min(value = 2000, message = "Digite o ano do veiculo")
Integer ano,

@NotBlank(message = "Cor obrigatória")
@Size(min = 3, max = 30, message = "Digite a cor do Veiculo")
String cor,

@NotNull(message = "Cliente ID")
Long clienteId
) {
}
