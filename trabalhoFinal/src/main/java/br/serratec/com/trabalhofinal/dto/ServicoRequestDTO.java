package br.serratec.com.trabalhofinal.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ServicoRequestDTO(
        @NotBlank(message = "Descrição") @Size(min = 5, max = 255, message = "Descrição:") String descricao,
        @NotNull(message = "Valor") @DecimalMin(value = "0.01", message = "Valor:") BigDecimal valor,
        @NotNull(message="Tempo estimado") @Min(value=1,message="Tempo estimado:") Integer tempoEstimadoMinutos) {
}
