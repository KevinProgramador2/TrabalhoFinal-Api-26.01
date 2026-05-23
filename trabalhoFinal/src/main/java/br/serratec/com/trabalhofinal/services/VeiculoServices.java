package br.serratec.com.trabalhofinal.services;

import br.serratec.com.trabalhofinal.model.Veiculo;
import br.serratec.com.trabalhofinal.repository.VeiculoRepository;

public class VeiculoServices {

    private VeiculoRepository repository;

    public Veiculo inserir(Veiculo veiculo){
        return repository.save(veiculo);
    }

}
