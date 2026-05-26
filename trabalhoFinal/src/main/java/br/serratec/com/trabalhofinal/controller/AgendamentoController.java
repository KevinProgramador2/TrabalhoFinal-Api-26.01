package br.serratec.com.trabalhofinal.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.serratec.com.trabalhofinal.dto.AgendamentoResponseDTO;
import br.serratec.com.trabalhofinal.model.Agendamento;
import br.serratec.com.trabalhofinal.services.AgendamentoServices;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoServices service;

    @PostMapping
    public AgendamentoResponseDTO criar(@RequestBody Agendamento agendamento) {
        return service.criar(agendamento);
    }

    @GetMapping("/disponibilidade")
    public Map<String, Boolean> verificarDisponibilidade(
            @RequestParam String data,
            @RequestParam String hora) {

        boolean ocupado = service.verificarDisponibilidade(
                LocalDate.parse(data),
                LocalTime.parse(hora)
        );

        return Map.of("disponivel", !ocupado);
    }
}