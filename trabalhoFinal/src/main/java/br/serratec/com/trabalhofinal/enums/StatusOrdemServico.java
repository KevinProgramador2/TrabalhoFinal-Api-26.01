package br.serratec.com.trabalhofinal.enums;

public enum StatusOrdemServico {
ABERTA("Aberta"),
EM_ANDAMENTO("Em Andamento"),
FINALIZADA("Finalizada"),
CANCELADA("Cancelada");

private final String descricao;

StatusOrdemServico(String descricao) {
this.descricao = descricao;
}

public String getDescricao() {
return descricao;
}
}
