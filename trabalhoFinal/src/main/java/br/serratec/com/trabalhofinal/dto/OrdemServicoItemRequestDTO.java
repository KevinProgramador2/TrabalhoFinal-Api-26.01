package br.serratec.com.trabalhofinal.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrdemServicoItemRequestDTO(
        @NotNull(message = "ID serviço") Long servicoId,

        @NotNull(message = "Quantidade") @Min(value = 1, message = "AQuantidade deve ser pelo menos 1") Integer quantidade,

        @NotNull(message = "Valor") @DecimalMin(value = "0", message = "O Valor não pode ser negativo") BigDecimal valor,

        @NotNull(message = "Desconto") @DecimalMin(value = "0", message = "O Desconto não pode ser negativo") BigDecimal desconto) {
}
