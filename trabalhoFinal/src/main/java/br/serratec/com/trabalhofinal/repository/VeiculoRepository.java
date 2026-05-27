package br.serratec.com.trabalhofinal.repository;

import br.serratec.com.trabalhofinal.model.Veiculo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository  extends JpaRepository<Veiculo, Long> {
    Optional<Veiculo> findByPlaca(String placa);
}
