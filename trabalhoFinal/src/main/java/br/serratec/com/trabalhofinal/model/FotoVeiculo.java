package br.serratec.com.trabalhofinal.model;

import br.serratec.com.trabalhofinal.enums.TipoFoto;
import jakarta.persistence.*;

@Entity
public class FotoVeiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nomeArquivo;
    private String caminhoArquivo;
    private String tipoConteudo;

    @Enumerated(EnumType.STRING)
    private TipoFoto tipo;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    public FotoVeiculo() {
    }

    public FotoVeiculo(Long id, String nomeArquivo, String caminhoArquivo, String tipoConteudo, TipoFoto tipo,
            Veiculo veiculo) {
        this.id = id;
        this.nomeArquivo = nomeArquivo;
        this.caminhoArquivo = caminhoArquivo;
        this.tipoConteudo = tipoConteudo;
        this.tipo = tipo;
        this.veiculo = veiculo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public String getTipoConteudo() {
        return tipoConteudo;
    }

    public void setTipoConteudo(String tipoConteudo) {
        this.tipoConteudo = tipoConteudo;
    }

    public TipoFoto getTipo() {
        return tipo;
    }

    public void setTipo(TipoFoto tipo) {
        this.tipo = tipo;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

}