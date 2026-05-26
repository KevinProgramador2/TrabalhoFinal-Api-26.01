package br.serratec.com.trabalhofinal.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusAgendamento {
    AGENDADO("Agendado"),
    CANCELADO("Cancelado"),
    CONCLUIDO("Concluído");

    private String descricao;

    StatusAgendamento(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static StatusAgendamento fromValue(String value) {
        for (StatusAgendamento status : StatusAgendamento.values()) {
            if (status.descricao.equalsIgnoreCase(value)) {
                return status;
            }
        }
        return null;
    }
}