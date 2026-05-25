package br.serratec.com.trabalhofinal.dto;

public record UsuarioLoginResponseDTO(String token, String tipo, Long expiresIn, UsuarioResponseDTO usuario
) {
}
