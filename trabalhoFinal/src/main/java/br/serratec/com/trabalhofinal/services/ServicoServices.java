package br.serratec.com.trabalhofinal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.serratec.com.trabalhofinal.dto.ServicoRequestDTO;
import br.serratec.com.trabalhofinal.dto.ServicoResponseDTO;
import br.serratec.com.trabalhofinal.model.Servico;
import br.serratec.com.trabalhofinal.repository.ServicoRepository;

@Service
public class ServicoServices {

    @Autowired
    private ServicoRepository repository;

    public ServicoResponseDTO inserir(ServicoRequestDTO servicoInserir){

        Servico servico = new Servico();

        servico.setDescricao(servicoInserir.descricao());
        servico.setValor(servicoInserir.valor());
        servico.setTempoEstimadoMinutos(servicoInserir.tempoEstimadoMinutos());

        Servico servicoSalvo = repository.save(servico);

        return new ServicoResponseDTO(
            servicoSalvo.getId(),
            servicoSalvo.getDescricao(),
            servicoSalvo.getValor(),
            servicoSalvo.getTempoEstimadoMinutos()
        );

        
    }

    public ServicoResponseDTO update(Long id, ServicoRequestDTO servicoUpdate){
        Servico servico = repository.findById(id).orElseThrow(() -> new RuntimeException("Servico nao encontrado!"));

        servico.setDescricao(servicoUpdate.descricao());
        servico.setValor(servicoUpdate.valor());
        servico.setTempoEstimadoMinutos(servicoUpdate.tempoEstimadoMinutos());

        Servico servicoSalvo = repository.save(servico);

        return new ServicoResponseDTO(
            servicoSalvo.getId(),
            servicoSalvo.getDescricao(),
            servicoSalvo.getValor(),
            servicoSalvo.getTempoEstimadoMinutos()
        );

    }
    

}
