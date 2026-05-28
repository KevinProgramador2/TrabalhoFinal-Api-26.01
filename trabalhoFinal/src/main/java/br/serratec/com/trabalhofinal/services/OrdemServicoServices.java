package br.serratec.com.trabalhofinal.services;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.serratec.com.trabalhofinal.configuration.MailConfig;
import br.serratec.com.trabalhofinal.dto.OrdemServicoItemRequestDTO;
import br.serratec.com.trabalhofinal.dto.OrdemServicoRequestDTO;
import br.serratec.com.trabalhofinal.model.Cliente;
import br.serratec.com.trabalhofinal.model.OrdemServico;
import br.serratec.com.trabalhofinal.model.OrdemServicoItem;
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
        private ClienteRepository clienteRepository;

        @Autowired
        private VeiculoRepository veiculoRepository;

        @Autowired
        private ServicoRepository servicoRepository;

        @Autowired
        private MailConfig config;

        public OrdemServico inserir(OrdemServicoRequestDTO dto) {

                Cliente cliente = clienteRepository.findById(dto.clienteId())
                                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

                Veiculo veiculo = veiculoRepository.findById(dto.veiculoId())
                                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

                OrdemServico os = new OrdemServico();

                os.setCliente(cliente);
                os.setVeiculo(veiculo);
                os.setStatus(dto.status());

                Set<OrdemServicoItem> itens = new HashSet<>();

                for (OrdemServicoItemRequestDTO itemDTO : dto.itens()) {

                        Servico servico = servicoRepository.findById(itemDTO.servicoId())
                                        .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

                        OrdemServicoItem item = new OrdemServicoItem();

                        item.setOrdemServico(os);
                        item.setServico(servico);
                        item.setQuantidade(itemDTO.quantidade());

                        BigDecimal desconto = itemDTO.desconto() != null ? itemDTO.desconto() : BigDecimal.ZERO;

                        item.setDesconto(desconto);

                        BigDecimal subtotal = servico.getValor()
                                        .multiply(BigDecimal.valueOf(itemDTO.quantidade()))
                                        .subtract(desconto);

                        item.setValor(servico.getValor());
                        item.setSubtotal(subtotal);

                        itens.add(item);
                }

                os.setItens(itens);

                return repository.save(os);
        }

        @Transactional
        public OrdemServico update(Long id, OrdemServicoRequestDTO dto) {

                OrdemServico os = repository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada"));

                Cliente cliente = clienteRepository.findById(dto.clienteId())
                                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

                Veiculo veiculo = veiculoRepository.findById(dto.veiculoId())
                                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

                os.setCliente(cliente);
                os.setVeiculo(veiculo);
                os.setStatus(dto.status());

                Set<OrdemServicoItem> itens = new HashSet<>();

                for (OrdemServicoItemRequestDTO itemDTO : dto.itens()) {

                        Servico servico = servicoRepository.findById(itemDTO.servicoId())
                                        .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

                        BigDecimal desconto = itemDTO.desconto() != null ? itemDTO.desconto() : BigDecimal.ZERO;
                        BigDecimal valorUnitario = servico.getValor();
                        BigDecimal subtotal = valorUnitario.multiply(BigDecimal.valueOf(itemDTO.quantidade()))
                                        .subtract(desconto);

                        OrdemServicoItem item = new OrdemServicoItem();
                        item.setOrdemServico(os);
                        item.setServico(servico);
                        item.setQuantidade(itemDTO.quantidade());
                        item.setDesconto(desconto);
                        item.setValor(valorUnitario);
                        item.setSubtotal(subtotal);
                        itens.add(item);
                }

                os.setItens(itens);

                config.sendMail(
                                os.getCliente().getEmail(),
                                "Atualização da Ordem de Serviço",
                                "A ordem de serviço com ID " + os.getId() + " foi atualizada para o status: "
                                                + os.getStatus());

                return repository.save(os);
        }

        public OrdemServico buscarPorIdOrdemServico(Long id) {

                return repository.findById(id)
                                .orElseThrow(() -> new RuntimeException("OS não encontrada"));
        }

        public Set<OrdemServico> listar() {

                return new HashSet<>(repository.findAll());
        }

        public OrdemServico buscarPorId(Long id) {

                return repository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada"));
        }

        public List<OrdemServico> listarPorVeiculo(Long veiculoId) {
                return repository.findByVeiculoId(veiculoId);
        }

        @Transactional
        public void deletar(Long id) {

                OrdemServico os = repository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada"));

                repository.delete(os);
        }
}