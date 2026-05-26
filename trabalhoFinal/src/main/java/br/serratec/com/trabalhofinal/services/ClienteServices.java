package br.serratec.com.trabalhofinal.services;

import br.serratec.com.trabalhofinal.configuration.MailConfig;
import br.serratec.com.trabalhofinal.dto.ClienteRequestDTO;
import br.serratec.com.trabalhofinal.dto.ClienteResponseDTO;
import br.serratec.com.trabalhofinal.model.Cliente;
import br.serratec.com.trabalhofinal.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import br.serratec.com.trabalhofinal.dto.ViaCepDTO;

import java.util.List;

@Service
public class ClienteServices {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private MailConfig config;
    
    public ClienteResponseDTO inserir(ClienteRequestDTO dto) {

    RestTemplate restTemplate = new RestTemplate();

    String url = "https://viacep.com.br/ws/"
            + dto.cep()
            + "/json/";

    ViaCepDTO endereco =
            restTemplate.getForObject(url, ViaCepDTO.class);

    if (endereco.erro() != null && endereco.erro()) {
        throw new RuntimeException("CEP inválido!");
    }

    Cliente cliente = new Cliente();

    cliente.setNome(dto.nome());
    cliente.setTelefone(dto.telefone());
    cliente.setEmail(dto.email());
    cliente.setCpf(dto.cpf());
    cliente.setCep(dto.cep());
    cliente.setLogradouro(endereco.logradouro());
    cliente.setBairro(endereco.bairro());
    cliente.setCidade(endereco.localidade());
    cliente.setEstado(endereco.uf());

    Cliente clienteSalvo = repository.save(cliente);

    config.sendMail(
            clienteSalvo.getEmail(),
            "Cadastro de novo usuario",
            clienteSalvo.toString()
    );

    return new ClienteResponseDTO(
            clienteSalvo.getId(),
            clienteSalvo.getNome(),
            clienteSalvo.getTelefone(),
            clienteSalvo.getEmail(),
            clienteSalvo.getCpf(),
            clienteSalvo.getCep(),
            clienteSalvo.getLogradouro(),
            clienteSalvo.getBairro(),
            clienteSalvo.getCidade(),
            clienteSalvo.getEstado()
    );
}

    public List<ClienteResponseDTO> listar() {

    List<Cliente> clientes = repository.findAll();

    return clientes.stream()
            .map(cliente -> new ClienteResponseDTO(
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getTelefone(),
                    cliente.getEmail(),
                    cliente.getCpf(),
                    cliente.getCep(),
                    cliente.getEstado(),
                    cliente.getCidade(),
                    cliente.getBairro(),
                    cliente.getLogradouro()
            ))
            .toList();
    }

    public ClienteResponseDTO update(Long id, ClienteRequestDTO dto) {

    Cliente cliente = repository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Cliente não encontrado!"));

            cliente.setNome(dto.nome());
            cliente.setTelefone(dto.telefone());
            cliente.setEmail(dto.email());
            cliente.setCpf(dto.cpf());
            cliente.setCep(dto.cep());

    Cliente clienteAtualizado = repository.save(cliente);

    return new ClienteResponseDTO(
            clienteAtualizado.getId(),
            clienteAtualizado.getNome(),
            clienteAtualizado.getTelefone(),
            clienteAtualizado.getEmail(),
            clienteAtualizado.getCpf(),
            clienteAtualizado.getCep(),
            clienteAtualizado.getLogradouro(),
            clienteAtualizado.getBairro(),
            clienteAtualizado.getCidade(),
            clienteAtualizado.getEstado()
    );
}

    public void deletar(Long id){

    Cliente cliente = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

    repository.delete(cliente);
    }

}

