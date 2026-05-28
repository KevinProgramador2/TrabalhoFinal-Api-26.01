package br.serratec.com.trabalhofinal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.serratec.com.trabalhofinal.dto.OrdemServicoRequestDTO;
import br.serratec.com.trabalhofinal.model.OrdemServico;
import br.serratec.com.trabalhofinal.services.OrdemServicoServices;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/historico-manutencao")
public class HistoricoManutencaoController {

    private final OrdemServicoServices service;

    public HistoricoManutencaoController(OrdemServicoServices service) {
        this.service = service;
    }

    @GetMapping("/veiculos/{veiculoId}")
    public ResponseEntity<List<OrdemServico>> listarPorVeiculo(@PathVariable Long veiculoId) {
        return ResponseEntity.ok(service.listarPorVeiculo(veiculoId));
    }

    @GetMapping("{id}")
    public ResponseEntity<OrdemServico> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<OrdemServico> criar(@Valid @RequestBody OrdemServicoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.inserir(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<OrdemServico> atualizar(
        @PathVariable Long id,
        @Valid @RequestBody OrdemServicoRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
