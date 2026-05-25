package br.serratec.com.trabalhofinal.repository;

import br.serratec.com.trabalhofinal.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository  extends JpaRepository<Servico, Long> {
}
