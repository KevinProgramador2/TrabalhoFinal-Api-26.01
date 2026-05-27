package br.serratec.com.trabalhofinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.serratec.com.trabalhofinal.model.FotoVeiculo;

@Repository
public interface FotoVeiculoRepository extends JpaRepository<FotoVeiculo, Long> {
    List<FotoVeiculo> findByVeiculoId(Long veiculoId);
}