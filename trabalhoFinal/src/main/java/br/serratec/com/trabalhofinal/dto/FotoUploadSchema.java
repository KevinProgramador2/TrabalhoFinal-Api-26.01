package br.serratec.com.trabalhofinal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;

public class FotoUploadSchema {

    @Schema(description = "Arquivo de imagem", type = "string", format = "binary", required = true)
    public MultipartFile arquivo;

    @Schema(description = "Tipo da foto: ANTES ou DEPOIS", allowableValues = {"ANTES", "DEPOIS"}, required = true)
    public String tipo;
}