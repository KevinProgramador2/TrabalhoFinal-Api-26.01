package br.serratec.com.trabalhofinal.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import br.serratec.com.trabalhofinal.enums.StatusOrdemServico;

public record OrdemServicoResponseDTO(
Long id, String numero, ClienteResponseDTO cliente, VeiculoResponseDTO veiculo, StatusOrdemServico status,
        
List<OrdemServicoItemResponseDTO> itens,
BigDecimal valorTotal,
LocalDateTime dataCriacao,
LocalDateTime dataAtualizacao
) {
}
