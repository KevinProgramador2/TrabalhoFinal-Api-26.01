package br.serratec.com.trabalhofinal.model;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotBlank(message = "Nome")
@Size(min = 3, max = 100, message = "Digite o Nome")
@Column(nullable = false)
private String nome;

@NotBlank(message = "Telefone")
@Size(min = 10, max = 15, message = "Digite o Teelefone")
@Pattern(regexp = "\\d{10,15}", message = "Telefone deve conter apenas números")
@Column(nullable = false)
private String telefone;

@NotBlank(message = "Email")
@Email(message = "Email inválido")
@Size(max = 100, message = "Digite o Email")
@Column(unique = true, nullable = false)
private String email;

@NotBlank(message = "CPF")
@Size(min = 11, max = 11, message = "CPF deve ter 11 caracteres")
@Pattern(regexp = "\\d{11}", message = "CPF deve conter apenas números")
@Column(unique = true, nullable = false)
    private String cpf;

@NotBlank(message = "CEP")
@Size(min = 8, max = 8, message = "Digite o CEP")
@Pattern(regexp = "\\d{8}", message = "CEP deve conter apenas números")
@Column(nullable = false)
private String cep;

@Column(nullable = false)
private String logradouro;

@Column(nullable = false)
private String bairro;

@Column(nullable = false)
private String cidade;

@Column(nullable = false)
private String estado;

@JsonIgnore
@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
private Set<Veiculo> veiculos = new HashSet<>();

public Cliente(Long id, String nome, String telefone, String email, String cpf) {
this.id = id;
this.nome = nome;
this.telefone = telefone;
this.email = email;
this.cpf = cpf;
}

@Override
public String toString() {
return "Cliente [id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", email=" + email + ", cpf=" + cpf
+ ", cep=" + cep + ", logradouro=" + logradouro + ", bairro=" + bairro + ", cidade=" + cidade
+ ", estado=" + estado + "]";
}

}
