
package br.serratec.com.trabalhofinal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer nota;

    private String comentario;

    @ManyToOne
    @JsonIgnoreProperties("avaliacoes")
    private Cliente cliente;

    public Avaliacao() {
    }

    public Avaliacao(Long id, Integer nota, String comentario, Cliente cliente) {
        this.id = id;
        this.nota = nota;
        this.comentario = comentario;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public Integer getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
