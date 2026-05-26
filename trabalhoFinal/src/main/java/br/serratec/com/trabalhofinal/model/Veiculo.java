package br.serratec.com.trabalhofinal.model;

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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "veiculos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotBlank(message = "Placa é obrigatória")
@Size(min = 7, max = 8, message = "Digite a placa:")
@Pattern(regexp = "[A-Z]{3}-?\\d{4}", message = "Formato de placa inválido")
@Column(unique = true, nullable = false)
private String placa;

@NotBlank(message = "Marca é obrigatória")
@Size(min = 2, max = 50, message = "Digite a marca do veiculo")
@Column(nullable = false)
private String marca;

@NotBlank(message = "Modelo é obrigatório")
@Size(min = 2, max = 50, message = "Digite o modelo do veiculo")
@Column(nullable = false)
private String modelo;

@Min(value = 1900, message = "Digite o ano do veiculo")
@Column(nullable = false)
private Integer ano;

@NotBlank(message = "Cor é obrigatória")
@Size(min = 3, max = 30, message = "Digite a cor do Veiculo")
@Column(nullable = false)
private String cor;

@JsonIgnore
@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "cliente_id", nullable = false)
private Cliente cliente;

public Veiculo(String placa, String marca, String modelo, Integer ano, String cor, Cliente cliente) {
this.placa = placa;
this.marca = marca;
this.modelo = modelo;
this.ano = ano;
this.cor = cor;
this.cliente = cliente;
}
}
