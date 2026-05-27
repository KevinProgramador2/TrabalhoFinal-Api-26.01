package br.serratec.com.trabalhofinal.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ordem_servico_itens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdemServicoItem {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@JsonIgnore
@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "ordem_servico_id", nullable = false)
private OrdemServico ordemServico;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "servico_id", nullable = false)
private Servico servico;

@NotNull(message = "Quantidade é obrigatória")
@Min(value = 1, message = "Quantidade deve ser pelo menos 1")
@Column(nullable = false)
private Integer quantidade;

@NotNull(message = "Desconto é obrigatório")
@DecimalMin(value = "0", message = "Desconto não pode ser negativo")
@Column(nullable = false, precision = 10, scale = 2)
private BigDecimal desconto;

@Column(nullable = false, precision = 10, scale = 2)
private BigDecimal subtotal;

@Column(nullable = false, precision = 10, scale = 2)
private BigDecimal valor;



public OrdemServicoItem(OrdemServico ordemServico, Servico servico, Integer quantidade,
BigDecimal valor, BigDecimal desconto) {
this.ordemServico = ordemServico;
this.servico = servico;
this.quantidade = quantidade;
this.desconto = desconto;
this.valor = valor;
}
}
