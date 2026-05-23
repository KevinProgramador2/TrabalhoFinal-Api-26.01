package br.serratec.com.trabalhofinal.services;

import br.serratec.com.trabalhofinal.dto.ClienteResponseDTO;
import br.serratec.com.trabalhofinal.model.Cliente;
import br.serratec.com.trabalhofinal.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collector;


public class ClienteServices {

    @Autowired
    private ClienteRepository repository;
    
    public Cliente inserir(Cliente cliente){
        return repository.save(cliente);
    }

//    public List<ClienteResponseDTO> listarTodos() {
//        return repository.findAll().stream()
//                .map(cliente -> new ClienteResponseDTO(cliente.getId(), cliente.getNome(), cliente.getEmail()))
//                .collect(Collector.toList());
//    }

}

