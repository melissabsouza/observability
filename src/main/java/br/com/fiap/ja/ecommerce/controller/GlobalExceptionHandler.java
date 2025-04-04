package br.com.fiap.ja.ecommerce.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleRecursoNaoEncontradoException(Exception ex, Model model) {
        model.addAttribute("mensagem", ex.getMessage());
        return "error"; // Nome da página de erro Thymeleaf (src/main/resources/templates/error.html)
    }
}