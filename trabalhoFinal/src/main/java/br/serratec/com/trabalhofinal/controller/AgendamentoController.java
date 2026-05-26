package br.serratec.com.trabalhofinal.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.serratec.com.trabalhofinal.model.Agendamento;
import br.serratec.com.trabalhofinal.services.AgendamentoServices;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoServices service;

    @PostMapping
    public Agendamento criar(@RequestBody Agendamento agendamento) {
        return service.criar(agendamento);
    }

    @GetMapping("/disponibilidade")
    public String verificarDisponibilidade(
            @RequestParam String data,
            @RequestParam String hora) {

        boolean ocupado = service.existsByDataAndHora(
                LocalDate.parse(data),
                LocalTime.parse(hora)
        );

        return ocupado ? "Horário ocupado" : "Horário disponível";
    }
}
