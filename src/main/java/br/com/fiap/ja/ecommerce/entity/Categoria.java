package br.com.fiap.ja.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@ToString
@Entity
@Table(name = "tb_category")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da categoria é obrigatório.")
    @Size(min = 3, message = "O nome da categoria deve ter pelo menos 3 caracteres.")
    private String nome;

    @UpdateTimestamp
    @Column(name="data_alteracao")
    private ZonedDateTime dataAlteracao;

}

