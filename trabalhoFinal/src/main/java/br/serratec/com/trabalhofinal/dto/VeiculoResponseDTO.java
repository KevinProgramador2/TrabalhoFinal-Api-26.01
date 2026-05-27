package br.serratec.com.trabalhofinal.dto;

public record VeiculoResponseDTO(Long id, String placa, String marca, String modelo, Integer ano, String cor, 
        ClienteResponseDTO cliente
) {
}
