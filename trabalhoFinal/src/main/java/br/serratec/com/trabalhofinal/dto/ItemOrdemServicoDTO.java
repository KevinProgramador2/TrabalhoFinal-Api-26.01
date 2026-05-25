package br.serratec.com.trabalhofinal.dto;

import java.math.BigDecimal;

public record ItemOrdemServicoDTO(Long servicoId, BigDecimal valorServico, BigDecimal desconto, Integer quantidade){

}
