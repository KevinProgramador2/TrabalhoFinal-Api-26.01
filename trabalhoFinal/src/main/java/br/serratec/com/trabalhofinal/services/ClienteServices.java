package br.serratec.com.trabalhofinal.services;

import br.serratec.com.trabalhofinal.configuration.MailConfig;
import br.serratec.com.trabalhofinal.dto.ClienteRequestDTO;
import br.serratec.com.trabalhofinal.dto.ClienteResponseDTO;
import br.serratec.com.trabalhofinal.model.Cliente;
import br.serratec.com.trabalhofinal.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Cliente inserir(@org.checkerframework.checker.nullness.qual.MonotonicNonNull ClienteRequestDTO cliente) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://viacep.com.br/ws/" + cliente.getCep() + "/json/";
        ViaCepDTO endereco = restTemplate.getForObject(url, ViaCepDTO.class);

        if (endereco == null || (endereco.erro() != null && endereco.erro())) {
            throw new RuntimeException("CEP inválido!");
        }

        cliente.setLogradouro(endereco.logradouro());
        cliente.setBairro(endereco.bairro());
        cliente.setCidade(endereco.localidade());
        cliente.setEstado(endereco.uf());

        // campo senha, codigo abaixo
        // cliente.setSenha(encoder.encode(cliente.getSenha()));


        Cliente clienteSalvo = repository.save(cliente);

        config.sendMail(cliente.getEmail(), "Cadastro de novo usuário",
                "Olá " + clienteSalvo.getNome() + ", seu cadastro foi realizado com sucesso!");

        return clienteSalvo;
    }

    public List<ClienteResponseDTO> listar() {
        return repository.findAll().stream()
                .map(c -> new ClienteResponseDTO(
                        c.getId(), c.getNome(), c.getTelefone(), c.getEmail(),
                        mascararCpf(c.getCpf()), // Mascarando aqui
                        c.getCep(), c.getLogradouro(), c.getBairro(), c.getCidade(), c.getEstado()
                ))
                .toList();
    }

    private String mascararCpf(String cpf) {
        if (cpf == null || cpf.length() < 11) return cpf;
        return "***." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-**";
    }

    public Cliente update(Long id, @Valid @org.checkerframework.checker.nullness.qual.MonotonicNonNull ClienteRequestDTO dto) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        cliente.setNome(dto.nome());
        cliente.setTelefone(dto.telefone());
        cliente.setEmail(dto.email());
        // O CPF original está no banco, não sobrescreva com o mascarado do DTO!

        return repository.save(cliente);
    }

    public void deletar(Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        repository.delete(cliente);
    }
}
