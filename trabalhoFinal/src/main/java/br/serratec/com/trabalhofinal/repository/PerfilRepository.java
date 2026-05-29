package br.serratec.com.trabalhofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.serratec.com.trabalhofinal.model.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
