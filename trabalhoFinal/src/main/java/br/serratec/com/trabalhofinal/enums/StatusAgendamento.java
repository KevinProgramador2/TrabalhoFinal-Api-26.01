package br.serratec.com.trabalhofinal.enums;

public enum StatusAgendamento {
    AGENDADO("Agendado"),
    CANCELADO("Cancelado"),
    CONCLUIDO("Concluído");

    private String descricao;

    StatusAgendamento(String descricao) {
        this.descricao = descricao;
    }

    @com.fasterxml.jackson.annotation.JsonValue // Adicione isso!
    public String getDescricao() {
        return descricao;
    }
}
