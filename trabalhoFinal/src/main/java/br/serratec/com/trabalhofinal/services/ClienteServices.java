package br.serratec.com.trabalhofinal.services;

import br.serratec.com.trabalhofinal.dto.ClienteResponseDTO;
import br.serratec.com.trabalhofinal.model.Cliente;
import br.serratec.com.trabalhofinal.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServices {

    @Autowired
    private ClienteRepository repository;
    
    public Cliente inserir(Cliente cliente){
        return repository.save(cliente);
    }

    public List<ClienteResponseDTO> listar() {

    List<Cliente> clientes = repository.findAll();

    return clientes.stream()
            .map(cliente -> new ClienteResponseDTO(
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getTelefone(),
                    cliente.getEmail(),
                    cliente.getCpf()
            ))
            .toList();
    }

    public Cliente update(Long id, ClienteResponseDTO dto){

        Cliente cliente = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente nao encontrado!"));

        cliente.setNome(dto.nome());
        cliente.setTelefone(dto.telefone());
        cliente.setEmail(dto.email());
        cliente.setCpf(dto.cpf());


        return repository.save(cliente);
    }


}

