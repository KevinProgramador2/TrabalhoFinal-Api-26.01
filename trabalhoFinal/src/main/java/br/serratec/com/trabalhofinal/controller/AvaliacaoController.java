package br.serratec.com.trabalhofinal.controller;

import br.serratec.com.trabalhofinal.model.Avaliacao;
import br.serratec.com.trabalhofinal.services.AvaliacaoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoServices service;

    @PostMapping
    public ResponseEntity<Avaliacao> inserir(@RequestBody Avaliacao avaliacao) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.inserir(avaliacao));
    }

    @GetMapping
    public ResponseEntity<List<Avaliacao>> listar() {

        return ResponseEntity.ok(service.listar());
    }

    @PutMapping("/{id}")
    public Avaliacao update(@PathVariable Long id,
                            @RequestBody Avaliacao avaliacao) {

        return service.update(id, avaliacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {

        service.deletar(id);

        return ResponseEntity.ok("Avaliação deletada com sucesso!");
    }
}
