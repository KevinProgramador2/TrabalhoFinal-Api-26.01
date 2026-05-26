package br.serratec.com.trabalhofinal.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "servicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Servico {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotBlank(message = "Descrição")
@Size(min = 5, max = 255, message = "Descrição:")
@Column(nullable = false)
private String descricao;

@DecimalMin(value = "0.01", message = "Valor")
@Column(nullable = false, precision = 10, scale = 2)
private BigDecimal valor;

@Min(value = 1, message = "Tempo")
@Column(nullable = false)
private Integer tempoEstimadoMinutos;

@JsonIgnore
@OneToMany(mappedBy = "servico", cascade = CascadeType.ALL, orphanRemoval = true)
private Set<OrdemServicoItem> itens = new HashSet<>();

public Servico(String descricao, BigDecimal valor, Integer tempoEstimadoMinutos) {
this.descricao = descricao;
this.valor = valor;
this.tempoEstimadoMinutos = tempoEstimadoMinutos;
    }

}
