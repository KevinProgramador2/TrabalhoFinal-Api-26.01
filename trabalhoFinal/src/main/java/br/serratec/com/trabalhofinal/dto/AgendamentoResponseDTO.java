package br.serratec.com.trabalhofinal.dto;

import br.serratec.com.trabalhofinal.enums.StatusAgendamento;

public record AgendamentoResponseDTO(Long id, String nomeCliente, String placaVeiculo, String data,
            String hora, String servico, StatusAgendamento statusAgendamento) {
}