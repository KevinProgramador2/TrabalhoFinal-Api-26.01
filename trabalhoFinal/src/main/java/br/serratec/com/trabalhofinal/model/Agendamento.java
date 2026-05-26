package br.serratec.com.trabalhofinal.model;

import br.serratec.com.trabalhofinal.enums.StatusAgendamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @NotNull(message = "Cliente é obrigatório")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    @NotNull(message = "Veículo é obrigatório")
    private Veiculo veiculo;

    @NotNull(message = "Data é obrigatória")
    private LocalDate data;

    @NotNull(message = "Hora é obrigatória")
    private LocalTime hora;

    @NotBlank(message = "Serviço é obrigatório")
    private String servico;

    @Enumerated(EnumType.STRING)
    private StatusAgendamento statusAgendamento;
}