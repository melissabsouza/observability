package br.com.fiap.ja.ecommerce.service;

import br.com.fiap.ja.ecommerce.dto.CategoriaDTO;
import br.com.fiap.ja.ecommerce.dto.ProdutoDTO;
import br.com.fiap.ja.ecommerce.entity.Categoria;
import br.com.fiap.ja.ecommerce.entity.Produto;
import br.com.fiap.ja.ecommerce.repository.CategoriaRepository;
import io.micrometer.core.annotation.Timed;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    private CategoriaDTO convertToDTO(Categoria categoria) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(categoria.getId());
        dto.setNome(categoria.getNome());
        dto.setDataAlteracao(categoria.getDataAlteracao());
        return dto;
    }

    private Categoria convertToEntity(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setId(dto.getId());
        categoria.setNome(dto.getNome());
        return categoria;
    }

    public CategoriaDTO save(CategoriaDTO categoriaDTO) {
        Categoria categoria = convertToEntity(categoriaDTO);
        categoria = repository.save(categoria);
        return convertToDTO(categoria);
    }

    public List<CategoriaDTO> getCategorias() {
        List<CategoriaDTO> categorias = repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return categorias;
    }

    public void deletarPorId(Long id) {
        repository.deleteById(id);

    }

    public CategoriaDTO findById(Long id) {
        Optional<Categoria> byId = repository.findById(id);
        if (byId.isPresent()){
            return convertToDTO(byId.get());
        }
        throw new RuntimeException("id not found");
    }

}
