package br.serratec.com.trabalhofinal.dto;

public record AgendamentoResponseDTO(
        Long id,
        String nomeCliente,
        String placaVeiculo,
        String data,
        String hora,
        String servico
) {}