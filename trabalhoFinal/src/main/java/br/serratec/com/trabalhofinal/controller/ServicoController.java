package br.serratec.com.trabalhofinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.serratec.com.trabalhofinal.model.Servico;
import br.serratec.com.trabalhofinal.services.ServicoServices;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    private ServicoServices service;

    @PostMapping
    public ResponseEntity<Servico> inserir(@RequestBody Servico servico) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.inserir(servico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> update(
            @PathVariable Long id,
            @RequestBody Servico servicoUpdate) {

        return ResponseEntity.ok(service.update(id, servicoUpdate));
    }

}
