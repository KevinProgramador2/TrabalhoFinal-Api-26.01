package br.serratec.com.trabalhofinal.repository;

import br.serratec.com.trabalhofinal.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
