package br.serratec.com.trabalhofinal.dto;

import java.util.List;

import br.serratec.com.trabalhofinal.enums.StatusOrdemServico;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record OrdemServicoRequestDTO(
@NotNull(message = "Cliente ID")
Long clienteId,

@NotNull(message = "Veículo ID")
Long veiculoId,

@NotNull(message = "Status")
StatusOrdemServico status,

@NotEmpty(message = "Deve conter pelo menos um serviço")
@Valid
List<OrdemServicoItemRequestDTO> itens
) {
}
