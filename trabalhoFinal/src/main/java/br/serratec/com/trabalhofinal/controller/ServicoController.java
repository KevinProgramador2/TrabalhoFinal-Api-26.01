package br.serratec.com.trabalhofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.serratec.com.trabalhofinal.dto.ServicoRequestDTO;
import br.serratec.com.trabalhofinal.dto.ServicoResponseDTO;
import br.serratec.com.trabalhofinal.services.ServicoServices;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;;


@RestController
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    private ServicoServices service;

    @PostMapping
    public ResponseEntity<ServicoResponseDTO> inserir(@Valid @RequestBody ServicoRequestDTO servico) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.inserir(servico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> update(
            @PathVariable Long id,
            @RequestBody ServicoRequestDTO servicoUpdate) {

        return ResponseEntity.ok(service.update(id, servicoUpdate));
    }

}
