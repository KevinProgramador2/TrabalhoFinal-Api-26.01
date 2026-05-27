package br.serratec.com.trabalhofinal.services;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

import br.serratec.com.trabalhofinal.dto.AgendamentoResponseDTO;
import br.serratec.com.trabalhofinal.enums.StatusAgendamento;
import br.serratec.com.trabalhofinal.model.Agendamento;
import br.serratec.com.trabalhofinal.model.Cliente;
import br.serratec.com.trabalhofinal.model.Veiculo;
import br.serratec.com.trabalhofinal.repository.AgendamentoRepository;
import br.serratec.com.trabalhofinal.repository.ClienteRepository;
import br.serratec.com.trabalhofinal.repository.VeiculoRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AgendamentoServices {

    private final AgendamentoRepository agendamentoRepository;
    private final ClienteRepository clienteRepository;
    private final VeiculoRepository veiculoRepository;

    public AgendamentoServices(AgendamentoRepository agendamentoRepository, ClienteRepository clienteRepository, VeiculoRepository veiculoRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.clienteRepository = clienteRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public AgendamentoResponseDTO cancelar(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id).orElse(null);
        if (agendamento == null) {
            return null;
        }
        agendamento.setStatusAgendamento(StatusAgendamento.CANCELADO);
        Agendamento atualizado = agendamentoRepository.save(agendamento);
        return new AgendamentoResponseDTO(
                atualizado.getId(),
                atualizado.getCliente().getNome(),
                atualizado.getVeiculo().getPlaca(),
                atualizado.getData().toString(),
                atualizado.getHora().toString(),
                atualizado.getServico(),
                atualizado.getStatusAgendamento());
    }

    @Transactional
    public AgendamentoResponseDTO criar(Agendamento agendamento) {

        // 1. Verifica PRIMEIRO se o horário está ocupado por qualquer cliente
        if (agendamentoRepository.existsByDataAndHoraAndStatusAgendamentoNot(
                agendamento.getData(), agendamento.getHora(), StatusAgendamento.CANCELADO)) {
            throw new RuntimeException("Horário já está ocupado!");
        }

        if (agendamento.getStatusAgendamento() == null) {
            throw new RuntimeException("Status do agendamento inválido ou não informado.");
        }

        // 3. Reutiliza cliente existente pelo CPF, ou salva novo
        Cliente cliente = clienteRepository.findByCpf(agendamento.getCliente().getCpf())
            .orElseGet(() -> {
                if (clienteRepository.findByEmail(agendamento.getCliente().getEmail()).isPresent()) {
                    throw new RuntimeException("Email já cadastrado para outro cliente.");
                }
                return clienteRepository.save(agendamento.getCliente());
            });
        agendamento.setCliente(cliente);

        Veiculo veiculoParaSalvar = agendamento.getVeiculo();
        veiculoParaSalvar.setCliente(cliente);

        Veiculo veiculo = veiculoRepository.findByPlaca(veiculoParaSalvar.getPlaca())
                .orElseGet(() -> veiculoRepository.save(veiculoParaSalvar));
        agendamento.setVeiculo(veiculo);

        Agendamento salvo = agendamentoRepository.save(agendamento);

        return new AgendamentoResponseDTO(
                salvo.getId(),
                salvo.getCliente().getNome(),
                salvo.getVeiculo().getPlaca(),
                salvo.getData().toString(),
                salvo.getHora().toString(),
                salvo.getServico(),
                salvo.getStatusAgendamento());
    }

    public boolean verificarDisponibilidade(LocalDate data, LocalTime hora) {
        return agendamentoRepository.existsByDataAndHoraAndStatusAgendamentoNot(
            data, hora, StatusAgendamento.CANCELADO);
    }
}