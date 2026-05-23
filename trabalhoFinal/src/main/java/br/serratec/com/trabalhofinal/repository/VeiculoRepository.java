package br.serratec.com.trabalhofinal.repository;

import br.serratec.com.trabalhofinal.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository  extends JpaRepository<Veiculo, Long> {
}
