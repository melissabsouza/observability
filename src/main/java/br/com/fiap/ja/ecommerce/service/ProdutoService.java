package br.com.fiap.ja.ecommerce.service;

import br.com.fiap.ja.ecommerce.dto.ProdutoDTO;
import br.com.fiap.ja.ecommerce.entity.Produto;
import br.com.fiap.ja.ecommerce.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoDTO salvar(ProdutoDTO produtoDTO) {
        Produto produto = toEntity(produtoDTO);
        if (produtoDTO.getUuid() == null) {
            produto = repository.save(produto);
        } else {
            ProdutoDTO byId = this.findById(produtoDTO.getUuid());
            byId.setNome(produto.getNome());
            byId.setPreco(produto.getPreco());
            produto = repository.save(toEntity(byId));
        }
        return toDto(produto);
    }

    public List<ProdutoDTO> findAll() {
        List<Produto> list = repository.findAll();
        List<ProdutoDTO> produtoDTOList = list.stream().map(ProdutoService::toDto).toList();
        return produtoDTOList;
    }

    public void deletarPorId(UUID id) {
        repository.deleteById(id);

    }

    public ProdutoDTO findById(UUID id) {
        Optional<Produto> byId = repository.findById(id);
        if (byId.isPresent()){
            return toDto(byId.get());
        }
        throw new RuntimeException("uuid not found");
    }


    private static Produto toEntity(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setUuid(produtoDTO.getUuid());
        produto.setNome(produtoDTO.getNome());
        produto.setPreco(produtoDTO.getPreco());
        produto.setCategoria(produtoDTO.getCategoria());
        return produto;
    }

    private static ProdutoDTO toDto(Produto produto) {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setUuid(produto.getUuid());
        produtoDTO.setNome(produto.getNome());
        produtoDTO.setPreco(produto.getPreco());
        produtoDTO.setCategoria(produto.getCategoria());
        produtoDTO.setDataAlteracao(produto.getDataAlteracao());
        return produtoDTO;
    }

}
