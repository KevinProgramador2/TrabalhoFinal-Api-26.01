package br.serratec.com.trabalhofinal.repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;

import br.serratec.com.trabalhofinal.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    boolean existsByDataAndHora(LocalDate data, LocalTime hora);

}