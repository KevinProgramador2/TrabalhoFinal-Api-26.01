package br.serratec.com.trabalhofinal.controller;

import br.serratec.com.trabalhofinal.dto.ClienteRequestDTO;
import br.serratec.com.trabalhofinal.dto.ClienteResponseDTO;
import br.serratec.com.trabalhofinal.services.ClienteServices;
import jakarta.validation.Valid;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteServices service;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> inserir(@RequestBody ClienteRequestDTO cliente) {
        return  ResponseEntity.status(HttpStatus.CREATED).body(service.inserir(cliente));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    
    @PutMapping("/{id}")
        public ResponseEntity<ClienteResponseDTO> update(
        @PathVariable Long id,
        @Valid @RequestBody ClienteRequestDTO dto) {

    return ResponseEntity.ok(service.update(id, dto));
}

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }

}

