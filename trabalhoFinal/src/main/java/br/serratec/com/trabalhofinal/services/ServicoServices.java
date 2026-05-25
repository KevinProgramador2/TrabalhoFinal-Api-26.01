package br.serratec.com.trabalhofinal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.serratec.com.trabalhofinal.model.Servico;
import br.serratec.com.trabalhofinal.repository.ServicoRepository;

@Service
public class ServicoServices {

    @Autowired
    private ServicoRepository repository;

    public Servico inserir(Servico servico){
        return repository.save(servico);
    }

    public Servico update(Long id, Servico servicoUpdate){
        Servico servico = repository.findById(id).orElseThrow(() -> new RuntimeException("Servico nao encontrado!"));

        servico.setDescricao(servicoUpdate.getDescricao());
        servico.setValor(servicoUpdate.getValor());
        servico.setTempoEstimado(servicoUpdate.getTempoEstimado());

        return repository.save(servico);

    }
    

}
