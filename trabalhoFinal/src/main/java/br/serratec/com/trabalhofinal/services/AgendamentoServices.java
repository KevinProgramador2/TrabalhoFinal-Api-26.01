package br.serratec.com.trabalhofinal.services;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

import br.serratec.com.trabalhofinal.dto.AgendamentoResponseDTO;
import br.serratec.com.trabalhofinal.enums.StatusAgendamento;
import br.serratec.com.trabalhofinal.model.Agendamento;
import br.serratec.com.trabalhofinal.repository.AgendamentoRepository;

@Service
public class AgendamentoServices {

    private final AgendamentoRepository repository;

    public AgendamentoServices(AgendamentoRepository repository) {
        this.repository = repository;
    }

    public AgendamentoResponseDTO criar(Agendamento agendamento) {

        if (verificarDisponibilidade(agendamento.getData(), agendamento.getHora())) {
            throw new RuntimeException("Horário já está ocupado!");
        }

        if (agendamento.getStatusAgendamento() == null) {
            throw new RuntimeException("Status do agendamento inválido ou não informado. Use: Agendado, Concluído ou Cancelado.");
        }
        
        agendamento.getVeiculo().setCliente(agendamento.getCliente());

        Agendamento salvo = repository.save(agendamento);

         return new AgendamentoResponseDTO(
                salvo.getId(),
                salvo.getCliente().getNome(),
                salvo.getVeiculo().getPlaca(),
                salvo.getData().toString(),
                salvo.getHora().toString(),
                salvo.getServico(),
                salvo.getStatusAgendamento()
        );
    }

    public boolean verificarDisponibilidade(LocalDate data, LocalTime hora) {
        return repository.existsByDataAndHoraAndStatusAgendamentoNot(data, hora, StatusAgendamento.CANCELADO);
    }
}