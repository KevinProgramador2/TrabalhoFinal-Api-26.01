package br.serratec.com.trabalhofinal.services;

import br.serratec.com.trabalhofinal.model.Avaliacao;
import br.serratec.com.trabalhofinal.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoServices {

    @Autowired
    private AvaliacaoRepository repository;

    public Avaliacao inserir(Avaliacao avaliacao) {
        return repository.save(avaliacao);
    }

    public List<Avaliacao> listar() {
        return repository.findAll();
    }

    public Avaliacao update(Long id, Avaliacao avaliacaoAtualizada) {

        Avaliacao avaliacao = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada!"));

        avaliacao.setNota(avaliacaoAtualizada.getNota());
        avaliacao.setComentario(avaliacaoAtualizada.getComentario());
        avaliacao.setCliente(avaliacaoAtualizada.getCliente());

        return repository.save(avaliacao);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}