package br.serratec.com.trabalhofinal.dto;

import java.math.BigDecimal;

public record OrdemServicoItemResponseDTO(Long id, ServicoResponseDTO servico,
                Integer quantidade, BigDecimal valor, BigDecimal desconto, BigDecimal subtotal) {

}
