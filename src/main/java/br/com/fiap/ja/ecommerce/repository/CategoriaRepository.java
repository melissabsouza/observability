package br.com.fiap.ja.ecommerce.repository;

import br.com.fiap.ja.ecommerce.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}