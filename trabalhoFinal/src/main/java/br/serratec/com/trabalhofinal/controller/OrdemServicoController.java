package br.serratec.com.trabalhofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.serratec.com.trabalhofinal.dto.OrdemServicoDTO;
import br.serratec.com.trabalhofinal.model.OrdemServico;
import br.serratec.com.trabalhofinal.services.OrdemServicoServices;

@RestController
@RequestMapping("/ordemservico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoServices service;

    @PostMapping
    public OrdemServico inserir(@RequestBody OrdemServicoDTO dto) {
        return service.inserir(dto);
    }

    @PutMapping("/{id}")
    public OrdemServico update(@PathVariable Long id,
                               @RequestBody OrdemServicoDTO dto) {

        return service.update(id, dto);
    }
}