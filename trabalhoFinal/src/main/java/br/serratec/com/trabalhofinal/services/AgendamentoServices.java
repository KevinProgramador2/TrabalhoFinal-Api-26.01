package br.serratec.com.trabalhofinal.services;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

import br.serratec.com.trabalhofinal.enums.StatusAgendamento;
 import br.serratec.com.trabalhofinal.model.Agendamento;
import br.serratec.com.trabalhofinal.repository.AgendamentoRepository;

@Service
public class AgendamentoServices {


    private AgendamentoRepository repository;


    public Agendamento criar(Agendamento agendamento) {

    boolean ocupado = repository.existsByDataAndHora(
            agendamento.getData(),
            agendamento.getHora()
    );

    if (ocupado) {
        throw new RuntimeException("Horário já está ocupado!");
    }

    agendamento.setStatusAgendamento(StatusAgendamento.AGENDADO);

    return repository.save(agendamento);
}


    public boolean existsByDataAndHora(LocalDate localDate, LocalTime localTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsByDataAndHora'");
    }
}
