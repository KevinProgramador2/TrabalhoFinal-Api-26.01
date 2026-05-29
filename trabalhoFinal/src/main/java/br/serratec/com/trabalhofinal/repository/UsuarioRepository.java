package br.serratec.com.trabalhofinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.serratec.com.trabalhofinal.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
