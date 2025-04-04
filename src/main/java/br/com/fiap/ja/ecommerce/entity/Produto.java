package br.com.fiap.ja.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@ToString
@Entity
@Table(name = "tb_produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min=3, message = "O nome deve ter pelo menos 3 caracteres")
    private String nome;

    @NotNull(message = "O preço é obrigatório.")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero.")
    private BigDecimal preco;

    @NotBlank(message = "A categoria é obrigatória")
    private String categoria;

    @UpdateTimestamp
    @Column(name="data_alteracao")
    private ZonedDateTime dataAlteracao;

}
