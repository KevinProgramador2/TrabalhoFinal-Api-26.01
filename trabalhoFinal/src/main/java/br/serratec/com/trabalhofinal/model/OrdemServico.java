package br.serratec.com.trabalhofinal.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import br.serratec.com.trabalhofinal.enums.StatusOrdemServico;
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
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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

@Column(unique = true, nullable = false)
private String numero;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "cliente_id", nullable = false)
private Cliente cliente;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "veiculo_id", nullable = false)
private Veiculo veiculo;

@NotNull(message = "Status é obrigatório")
@Enumerated(EnumType.STRING)
@Column(nullable = false)
private StatusOrdemServico status;

@OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
private Set<OrdemServicoItem> itens = new HashSet<>();

@Column(nullable = false, precision = 12, scale = 2)
private BigDecimal valorTotal = BigDecimal.ZERO;

@Column(nullable = false)
private LocalDateTime dataCriacao;

@Column(nullable = false)
private LocalDateTime dataAtualizacao;

@PrePersist
public void prePersist() {
this.numero = gerarNumeroOS();
this.dataCriacao = LocalDateTime.now();
this.dataAtualizacao = LocalDateTime.now();
}

@PreUpdate
public void preUpdate() {
this.dataAtualizacao = LocalDateTime.now();
}

public void adicionarItem(OrdemServicoItem item) {
item.setOrdemServico(this);
this.itens.add(item);
atualizarValorTotal();
}

public void removerItem(OrdemServicoItem item) {
this.itens.remove(item);
atualizarValorTotal();
}

public void atualizarValorTotal() {
this.valorTotal = this.itens.stream()
.map(OrdemServicoItem::getSubtotal)
.reduce(BigDecimal.ZERO, BigDecimal::add);
}

private String gerarNumeroOS() {
return "OS-" + System.currentTimeMillis() + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
}

public OrdemServico(Cliente cliente, Veiculo veiculo, StatusOrdemServico status) {
this.cliente = cliente;
this.veiculo = veiculo;
this.status = status;
}
}
