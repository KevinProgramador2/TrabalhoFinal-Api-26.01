package br.serratec.com.trabalhofinal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.serratec.com.trabalhofinal.dto.VeiculoRequestDTO;
import br.serratec.com.trabalhofinal.dto.VeiculoResponseDTO;
import br.serratec.com.trabalhofinal.services.VeiculoServices;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoServices service;

    @PostMapping
    public ResponseEntity<VeiculoResponseDTO> inserir(
            @Valid @RequestBody VeiculoRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.inserir(dto));
    }

    @GetMapping
    public ResponseEntity<List<VeiculoResponseDTO>> listar() {

        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody VeiculoRequestDTO dto) {

        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {

        service.deletar(id);

        return ResponseEntity.ok("Veículo deletado com sucesso!");
    }
}