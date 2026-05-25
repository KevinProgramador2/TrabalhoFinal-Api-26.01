package br.serratec.com.trabalhofinal.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.serratec.com.trabalhofinal.dto.ItemOrdemServicoDTO;
import br.serratec.com.trabalhofinal.dto.OrdemServicoDTO;
import br.serratec.com.trabalhofinal.model.Cliente;
import br.serratec.com.trabalhofinal.model.ItemOrdemServico;
import br.serratec.com.trabalhofinal.model.OrdemServico;
import br.serratec.com.trabalhofinal.model.Servico;
import br.serratec.com.trabalhofinal.model.Veiculo;
import br.serratec.com.trabalhofinal.repository.ClienteRepository;
import br.serratec.com.trabalhofinal.repository.OrdemServicoRepository;
import br.serratec.com.trabalhofinal.repository.ServicoRepository;
import br.serratec.com.trabalhofinal.repository.VeiculoRepository;

@Service
public class OrdemServicoServices {

    @Autowired
    private OrdemServicoRepository repository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    public OrdemServico inserir(OrdemServicoDTO dto) {

        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Veiculo veiculo = veiculoRepository.findById(dto.veiculoId())
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        OrdemServico os = new OrdemServico();

        os.setCliente(cliente);
        os.setVeiculo(veiculo);
        os.setStatus(dto.status());

        List<ItemOrdemServico> itens = new ArrayList<>();

        for (ItemOrdemServicoDTO itemDTO : dto.itens()) {

            Servico servico = servicoRepository.findById(itemDTO.servicoId())
                    .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

            ItemOrdemServico item = new ItemOrdemServico();

            item.setOrdemServico(os);
            item.setServico(servico);

            item.setValorServico(itemDTO.valorServico());

            BigDecimal desconto = itemDTO.desconto() == null
                    ? BigDecimal.ZERO
                    : itemDTO.desconto();

            item.setDesconto(desconto);

            item.setQuantidade(itemDTO.quantidade());

            BigDecimal subtotal = itemDTO.valorServico()
                    .multiply(BigDecimal.valueOf(itemDTO.quantidade()))
                    .subtract(desconto);

            item.setSubtotal(subtotal);

            itens.add(item);
        }

        os.setItens(itens);

        return repository.save(os);
    }

    public OrdemServico update(Long id, OrdemServicoDTO dto) {

        OrdemServico ordemServico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada"));

        ordemServico.setStatus(dto.status());

        return repository.save(ordemServico);
    }
}