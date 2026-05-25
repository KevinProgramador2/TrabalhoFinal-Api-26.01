package br.serratec.com.trabalhofinal.dto;

import java.util.List;

import br.serratec.com.trabalhofinal.enums.StatusOS;

public record OrdemServicoDTO(Long clienteId, Long veiculoId, StatusOS status, List<ItemOrdemServicoDTO> itens){

}
