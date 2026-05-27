package br.serratec.com.trabalhofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.serratec.com.trabalhofinal.dto.OrdemServicoRequestDTO;
import br.serratec.com.trabalhofinal.model.OrdemServico;
import br.serratec.com.trabalhofinal.services.OrdemServicoServices;

@RestController
@RequestMapping("/ordemservico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoServices service;

    @PostMapping
    public OrdemServico inserir(@RequestBody OrdemServicoRequestDTO dto) {
        return service.inserir(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemServico> buscar(@PathVariable Long id) {

    return ResponseEntity.ok(service.buscarPorIdOrdemServico(id));
}

    @PutMapping("/{id}")
    public OrdemServico update(@PathVariable Long id,
                               @RequestBody OrdemServicoRequestDTO dto) {

        return service.update(id, dto);
    }
}