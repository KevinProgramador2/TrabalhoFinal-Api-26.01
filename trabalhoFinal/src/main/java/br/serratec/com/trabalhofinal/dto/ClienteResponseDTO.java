package br.serratec.com.trabalhofinal.dto;

public record ClienteResponseDTO(Long id, String nome, String telefone, String email, String cpf, String cep, 
        String logradouro, String bairro, String cidade, String estado) {
}
