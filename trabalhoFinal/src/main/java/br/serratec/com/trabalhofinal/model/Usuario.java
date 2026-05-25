package br.serratec.com.trabalhofinal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotBlank(message = "Email")
@Email(message = "Email inválido")
@Column(unique = true, nullable = false)
private String email;

@NotBlank(message = "Senha")
@Column(nullable = false)
private String senha;

@Column(nullable = false)
private boolean ativo = true;

public Usuario(String email, String senha) {
this.email = email;
this.senha = senha;
this.ativo = true;
}
}
