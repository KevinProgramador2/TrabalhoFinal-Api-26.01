package br.serratec.com.trabalhofinal.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.serratec.com.trabalhofinal.model.Perfil;
import br.serratec.com.trabalhofinal.repository.PerfilRepository;

@Service
public class PerfilService {
    @Autowired
    private PerfilRepository repository;


    public Perfil inserir(Perfil perfil){
        return repository.save(perfil);
    }


    public Optional<Perfil> buscar(Long id){
        return repository.findById(id);
    }

}
