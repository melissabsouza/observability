package br.com.fiap.ja.ecommerce.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProdutoDTO {
    private UUID uuid;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min=3, message = "O nome deve ter pelo menos 3 caracteres")
    private String nome;

    @NotNull(message = "O preço é obrigatório.")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero.")
    private BigDecimal preco;

    @NotBlank(message = "A categoria é obrigatória")
    private String categoria;

    private ZonedDateTime dataAlteracao;

}

