package br.serratec.com.trabalhofinal.repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;

import br.serratec.com.trabalhofinal.enums.StatusAgendamento;
import br.serratec.com.trabalhofinal.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    boolean existsByDataAndHoraAndStatusAgendamentoNot(
        LocalDate data,
        LocalTime hora,
        StatusAgendamento status
    );

    // Check por cliente: mesmo CPF no mesmo horário
    boolean existsByDataAndHoraAndClienteCpfAndStatusAgendamentoNot(
        LocalDate data,
        LocalTime hora,
        String cpf,
        StatusAgendamento status
    );
}