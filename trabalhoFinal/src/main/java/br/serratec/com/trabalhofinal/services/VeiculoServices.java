package br.serratec.com.trabalhofinal.services;

import br.serratec.com.trabalhofinal.dto.ClienteResponseDTO;
import br.serratec.com.trabalhofinal.dto.VeiculoRequestDTO;
import br.serratec.com.trabalhofinal.dto.VeiculoResponseDTO;
import br.serratec.com.trabalhofinal.model.Cliente;
import br.serratec.com.trabalhofinal.model.Veiculo;
import br.serratec.com.trabalhofinal.repository.ClienteRepository;
import br.serratec.com.trabalhofinal.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoServices {

    @Autowired
    private VeiculoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

 public VeiculoResponseDTO inserir(VeiculoRequestDTO dto) {

    Cliente cliente = clienteRepository.findById(dto.clienteId())
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

    Veiculo veiculo = new Veiculo();

    veiculo.setPlaca(dto.placa());
    veiculo.setMarca(dto.marca());
    veiculo.setModelo(dto.modelo());
    veiculo.setAno(dto.ano());
    veiculo.setCor(dto.cor());
    veiculo.setCliente(cliente);

    Veiculo veiculoSalvo = repository.save(veiculo);

    ClienteResponseDTO clienteDTO = new ClienteResponseDTO(
            cliente.getId(),
            cliente.getNome(),
            cliente.getTelefone(),
            cliente.getEmail(),
            mascaraCpf(cliente.getCpf()),
            cliente.getCep(),
            cliente.getLogradouro(),
            cliente.getBairro(),
            cliente.getCidade(),
            cliente.getEstado()
    );

    return new VeiculoResponseDTO(
            veiculoSalvo.getId(),
            veiculoSalvo.getPlaca(),
            veiculoSalvo.getMarca(),
            veiculoSalvo.getModelo(),
            veiculoSalvo.getAno(),
            veiculoSalvo.getCor(),
            clienteDTO
    );
}

    private String mascaraCpf(String cpf) {
        if (cpf == null || cpf.length() < 11) return cpf;
        return "***." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-**";
    }

    public List<VeiculoResponseDTO> listar() {
    return repository.findAll()
            .stream()
            .map(veiculo -> new VeiculoResponseDTO(
                    veiculo.getId(),
                    veiculo.getPlaca(),
                    veiculo.getMarca(),
                    veiculo.getModelo(),
                    veiculo.getAno(),
                    veiculo.getCor(),
                    new ClienteResponseDTO(
                            veiculo.getCliente().getId(),
                            veiculo.getCliente().getNome(),
                            veiculo.getCliente().getTelefone(),
                            veiculo.getCliente().getEmail(),
                            mascaraCpf(veiculo.getCliente().getCpf()),
                            veiculo.getCliente().getCep(),
                            veiculo.getCliente().getLogradouro(),
                            veiculo.getCliente().getBairro(),
                            veiculo.getCliente().getCidade(),
                            veiculo.getCliente().getEstado()
                    )
            ))
            .toList();
}

    public VeiculoResponseDTO buscarPorId(Long id) {
    Veiculo veiculo = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

    Cliente cliente = veiculo.getCliente();

    ClienteResponseDTO clienteDTO = new ClienteResponseDTO(
            cliente.getId(),
            cliente.getNome(),
            cliente.getTelefone(),
            cliente.getEmail(),
            mascaraCpf(cliente.getCpf()),
            cliente.getCep(),
            cliente.getLogradouro(),
            cliente.getBairro(),
            cliente.getCidade(),
            cliente.getEstado()
    );

    return new VeiculoResponseDTO(
            veiculo.getId(),
            veiculo.getPlaca(),
            veiculo.getMarca(),
            veiculo.getModelo(),
            veiculo.getAno(),
            veiculo.getCor(),
            clienteDTO
    );
}

   public VeiculoResponseDTO update(Long id, VeiculoRequestDTO dto) {

    Veiculo veiculo = repository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Veículo não encontrado!"));

    Cliente cliente = clienteRepository.findById(dto.clienteId())
            .orElseThrow(() ->
                    new RuntimeException("Cliente não encontrado!"));

    veiculo.setPlaca(dto.placa());
    veiculo.setMarca(dto.marca());
    veiculo.setModelo(dto.modelo());
    veiculo.setAno(dto.ano());
    veiculo.setCor(dto.cor());

    veiculo.setCliente(cliente);

    Veiculo veiculoUpdate = repository.save(veiculo);

    ClienteResponseDTO clienteDTO = new ClienteResponseDTO(
            cliente.getId(),
            cliente.getNome(),
            cliente.getTelefone(),
            cliente.getEmail(),
            mascaraCpf(cliente.getCpf()),
            cliente.getCep(),
            cliente.getLogradouro(),
            cliente.getBairro(),
            cliente.getCidade(),
            cliente.getEstado()
    );

    return new VeiculoResponseDTO(
            veiculoUpdate.getId(),
            veiculoUpdate.getPlaca(),
            veiculoUpdate.getMarca(),
            veiculoUpdate.getModelo(),
            veiculoUpdate.getAno(),
            veiculoUpdate.getCor(),
            clienteDTO
    );
}

    public void deletar(Long id){
        repository.deleteById(id);
    }
}

