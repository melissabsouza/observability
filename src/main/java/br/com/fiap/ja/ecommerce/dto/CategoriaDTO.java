package br.com.fiap.ja.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {

    private Long id;

    @NotBlank(message = "O nome da categoria é obrigatório.")
    @Size(min = 3, message = "O nome da categoria deve ter pelo menos 3 caracteres.")
    private String nome;

    private ZonedDateTime dataAlteracao;

}
