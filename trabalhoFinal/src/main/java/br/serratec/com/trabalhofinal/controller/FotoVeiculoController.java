package br.serratec.com.trabalhofinal.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import br.serratec.com.trabalhofinal.enums.TipoFoto;
import br.serratec.com.trabalhofinal.model.FotoVeiculo;
import br.serratec.com.trabalhofinal.services.FotoVeiculoServices;

@RestController
@RequestMapping("/veiculos")
public class FotoVeiculoController {

    @Autowired
    private FotoVeiculoServices service;

    @PostMapping("/{id}/fotos")
    public ResponseEntity<FotoVeiculo> upload(
            @PathVariable Long id,
            @RequestParam("arquivo") MultipartFile arquivo,
            @RequestParam("tipo") TipoFoto tipo) throws IOException {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(id, arquivo, tipo));
    }

    @GetMapping("/{id}/fotos")
    public ResponseEntity<List<FotoVeiculo>> listar(@PathVariable Long id) {
        return ResponseEntity.ok(service.listarPorVeiculo(id));
    }

    @GetMapping("/fotos/{fotoId}")
    public ResponseEntity<Resource> download(@PathVariable Long fotoId) throws IOException {
        FotoVeiculo foto = service.getFoto(fotoId);
        Resource resource = service.download(fotoId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                .contentType(MediaType.parseMediaType(foto.getTipoConteudo()))
                .body(resource);
    }

    @DeleteMapping("/fotos/{fotoId}")
    public ResponseEntity<String> deletar(@PathVariable Long fotoId) throws IOException {
        service.deletar(fotoId);
        return ResponseEntity.ok("Foto deletada com sucesso!");
    }
}