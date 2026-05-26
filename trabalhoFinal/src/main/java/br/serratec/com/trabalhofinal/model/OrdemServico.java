package br.serratec.com.trabalhofinal.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import br.serratec.com.trabalhofinal.enums.StatusOS;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ordens_servico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdemServico {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "cliente_id", nullable = false)
private Cliente cliente;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "veiculo_id", nullable = false)
private Veiculo veiculo;

@NotNull(message = "Status é obrigatório")
@Enumerated(EnumType.STRING)
@Column(nullable = false)
private StatusOS status;

@OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
private Set<OrdemServicoItem> itens = new HashSet<>();



public OrdemServico(Cliente cliente, Veiculo veiculo, StatusOS status) {
this.cliente = cliente;
this.veiculo = veiculo;
this.status = status;
}
}
