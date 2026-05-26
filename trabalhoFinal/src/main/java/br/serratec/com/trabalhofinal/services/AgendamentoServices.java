package br.serratec.com.trabalhofinal.services;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired; // Faltava este import
import org.springframework.stereotype.Service;

import br.serratec.com.trabalhofinal.dto.AgendamentoResponseDTO; // Faltava este import
import br.serratec.com.trabalhofinal.enums.StatusAgendamento;
import br.serratec.com.trabalhofinal.model.Agendamento;
import br.serratec.com.trabalhofinal.model.Cliente;
import br.serratec.com.trabalhofinal.model.Veiculo;
import br.serratec.com.trabalhofinal.repository.AgendamentoRepository;

@Service
public class AgendamentoServices {

    @Autowired // Garante que o Spring gerencie o repositório
    private AgendamentoRepository repository;

    public AgendamentoResponseDTO criar(Agendamento agendamento) {
        
        // 1. Lógica de negócio: Verifica se o horário está ocupado
        if (verificarDisponibilidade(agendamento.getData(), agendamento.getHora())) {
            throw new RuntimeException("Horário já está ocupado!");
        }

        // 2. Define o status inicial
        agendamento.setStatusAgendamento(StatusAgendamento.AGENDADO);
        
        // 3. Salva no banco (a variável 'salvo' contém o ID gerado)
        Agendamento salvo = repository.save(agendamento);
        Cliente cliente = salvo.getCliente(); // Supondo que Agendamento tenha um relacionamento com Cliente
        Veiculo veiculo = salvo.getVeiculo(); // Supondo que Agendamento tenha um relacionamento com Veículo
        // 4. Mapeamento Manual: Transforma a Entidade em DTO
        // Certifique-se de que os nomes dos métodos (getPlaca, getNome, etc) 
        // batem com o que você escreveu na sua classe Model
        return new AgendamentoResponseDTO(
            salvo.getId(),
            cliente.getNome(),
            veiculo.getPlaca(),
            salvo.getData().toString(),
            salvo.getHora().toString(),
            salvo.getServico()
        );
    }

    public boolean verificarDisponibilidade(LocalDate data, LocalTime hora) {
        // Retorna TRUE se houver agendamento que NÃO esteja cancelado
        return repository.existsByDataAndHoraAndStatusAgendamentoNot(data, hora, StatusAgendamento.CANCELADO);
    }
}