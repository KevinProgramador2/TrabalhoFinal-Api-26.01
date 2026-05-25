package br.serratec.com.trabalhofinal.model;

import java.math.BigDecimal;

import br.serratec.com.trabalhofinal.enums.TipoServico;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private TipoServico descricao;
    private BigDecimal valor;
    private String tempoEstimado;


    public Servico(TipoServico descricao, BigDecimal valor, String tempoEstimado) {
        this.descricao = descricao;
        this.valor = valor;
        this.tempoEstimado = tempoEstimado;
    }


    @Override
    public String toString() {
        return "Servico [descricao=" + descricao + ", valor=" + valor + ", tempoEstimado=" + tempoEstimado + "]";
    }


    public TipoServico getDescricao() {
        return descricao;
    }
    public void setDescricao(TipoServico descricao) {
        this.descricao = descricao;
    }
    public BigDecimal getValor() {
        return valor;
    }
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    public String getTempoEstimado() {
        return tempoEstimado;
    }
    public void setTempoEstimado(String tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }
    public Long getId() {
        return id;
    }
    

    

}
