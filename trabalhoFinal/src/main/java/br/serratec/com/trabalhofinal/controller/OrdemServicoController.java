package br.serratec.com.trabalhofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PutMapping("/{id}")
    public OrdemServico update(
            @PathVariable Long id,
            @RequestBody OrdemServicoRequestDTO dto) {
            

        return service.update(id, dto);
    }
}