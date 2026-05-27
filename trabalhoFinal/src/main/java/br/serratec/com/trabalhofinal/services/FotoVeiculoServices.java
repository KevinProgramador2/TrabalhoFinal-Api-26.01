package br.serratec.com.trabalhofinal.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.serratec.com.trabalhofinal.enums.TipoFoto;
import br.serratec.com.trabalhofinal.model.FotoVeiculo;
import br.serratec.com.trabalhofinal.model.Veiculo;
import br.serratec.com.trabalhofinal.repository.FotoVeiculoRepository;
import br.serratec.com.trabalhofinal.repository.VeiculoRepository;

@Service
public class FotoVeiculoServices {

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Autowired
    private FotoVeiculoRepository fotoRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    public FotoVeiculo salvar(Long veiculoId, MultipartFile arquivo, TipoFoto tipo) throws IOException {
        Veiculo veiculo = veiculoRepository.findById(veiculoId)
                .orElseThrow(() -> new RuntimeException("Veiculo nao encontrado"));

        Path diretorio = Paths.get(uploadDir);
        Files.createDirectories(diretorio);

        String nomeArquivo = UUID.randomUUID() + "_" + arquivo.getOriginalFilename();
        Path destino = diretorio.resolve(nomeArquivo);
        Files.copy(arquivo.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

        FotoVeiculo foto = new FotoVeiculo();
        foto.setNomeArquivo(nomeArquivo);
        foto.setCaminhoArquivo(destino.toString());
        foto.setTipoConteudo(arquivo.getContentType());
        foto.setTipo(tipo);
        foto.setVeiculo(veiculo);

        return fotoRepository.save(foto);
    }

    public FotoVeiculo getFoto(Long id) {
        return fotoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Foto nao encontrada"));
    }

    public List<FotoVeiculo> listarPorVeiculo(Long veiculoId) {
        return fotoRepository.findByVeiculoId(veiculoId);
    }

    public Resource download(Long id) throws IOException {
        FotoVeiculo foto = fotoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Foto nao encontrada"));

        Path caminho = Paths.get(foto.getCaminhoArquivo());
        Resource recurso = new UrlResource(caminho.toUri());

        if (!recurso.exists())
            throw new RuntimeException("Arquivo nao encontrado no disco");
        return recurso;
    }

    public void deletar(Long id) throws IOException {
        FotoVeiculo foto = fotoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Foto nao encontrada"));

        Files.deleteIfExists(Paths.get(foto.getCaminhoArquivo()));
        fotoRepository.delete(foto);
    }
}
