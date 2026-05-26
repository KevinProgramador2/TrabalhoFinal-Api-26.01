package br.serratec.com.trabalhofinal.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AgendamentoResponseDTO> salvar(@RequestBody Agendamento agendamento) {
        // CHAME O MÉTODO 'criar', NÃO O 'verificarDisponibilidade'
        AgendamentoResponseDTO response = service.criar(agendamento);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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