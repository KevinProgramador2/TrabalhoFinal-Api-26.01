package br.serratec.com.trabalhofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.serratec.com.trabalhofinal.model.UsuarioPerfil;
import br.serratec.com.trabalhofinal.model.UsuarioPerfilPK;

public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, UsuarioPerfilPK> {
}
