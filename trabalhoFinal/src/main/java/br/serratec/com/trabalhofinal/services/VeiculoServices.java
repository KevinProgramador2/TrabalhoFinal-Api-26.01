package br.serratec.com.trabalhofinal.services;

import br.serratec.com.trabalhofinal.dto.VeiculoDTO;
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

    public Veiculo inserir(VeiculoDTO dto){
        Cliente cliente = clienteRepository.findById(dto.clienteId())
            .orElseThrow(() -> new RuntimeException("Cliente nao encontrado"));

        Veiculo veiculo = new Veiculo();

        veiculo.setPlaca(dto.placa());
        veiculo.setMarca(dto.marca());
        veiculo.setModelo(dto.modelo());
        veiculo.setAno(dto.ano());
        veiculo.setCor(dto.cor());

        veiculo.setCliente(cliente);

        return repository.save(veiculo);
    }

    public List<Veiculo> listar() {
        return repository.findAll();
    }

    public Veiculo update(Long id, Veiculo veiculoAtualizado){

        Veiculo veiculo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veiculo nao encontrado!"));

        veiculo.setPlaca(veiculoAtualizado.getPlaca());
        veiculo.setMarca(veiculoAtualizado.getMarca());
        veiculo.setModelo(veiculoAtualizado.getModelo());
        veiculo.setAno(veiculoAtualizado.getAno());
        veiculo.setCor(veiculoAtualizado.getCor());
        veiculo.setCliente(veiculoAtualizado.getCliente());

        return repository.save(veiculo);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }
}

