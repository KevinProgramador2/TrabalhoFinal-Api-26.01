package br.serratec.com.trabalhofinal.controller;

import br.serratec.com.trabalhofinal.dto.VeiculoDTO;
import br.serratec.com.trabalhofinal.model.Veiculo;
import br.serratec.com.trabalhofinal.services.VeiculoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoServices service;

    @PostMapping
    public ResponseEntity<Veiculo> inserir(@RequestBody VeiculoDTO veiculo) 

        return ResponseEntity
                .status(HttpStatus.CREATED)
            .body(service.inserir(veiculo));
}
                .body(service.inserir(veiculo));
    }

@GetMapping
public ResponseEntity<List<Veiculo>> listar() {
    @GetMapping
    public ResponseEntity<List<Veiculo>> listar() {

        return ResponseEntity.ok(service.listar());
    }

@PutMapping("/{id}")
public Veiculo update(@PathVariable Long id,
                      @RequestBody Veiculo veiculo) {

    return service.update(id, veiculo);
}

@DeleteMapping("/{id}")
public ResponseEntity<String> deletar(@PathVariable Long id) {

    service.deletar(id);

    return ResponseEntity.ok("Veiculo deletado com sucesso!");
}
}

