package br.serratec.com.trabalhofinal.dto;

public record VeiculoDTO(

    String placa,
    String marca,
    String modelo,
    Integer ano,
    String cor,
    Long clienteId

) {
}