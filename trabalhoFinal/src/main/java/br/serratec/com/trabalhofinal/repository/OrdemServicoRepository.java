package br.serratec.com.trabalhofinal.repository;

import java.util.List;

import br.serratec.com.trabalhofinal.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico,Long>{

    List<OrdemServico> findByVeiculoId(Long veiculoId);
}

