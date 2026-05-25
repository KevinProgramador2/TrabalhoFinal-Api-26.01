package br.serratec.com.trabalhofinal.controller;

import br.serratec.com.trabalhofinal.dto.ClienteResponseDTO;
import br.serratec.com.trabalhofinal.model.Cliente;
import br.serratec.com.trabalhofinal.services.ClienteServices;
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
    public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente) {
        return  ResponseEntity.status(HttpStatus.CREATED).body(service.inserir(cliente));

    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    
    @PutMapping("/{id}")
    public Cliente update(@PathVariable Long id, @RequestBody ClienteResponseDTO dto) {
        
        Cliente cliente = service.update(id,dto);

        return cliente;
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }

}

