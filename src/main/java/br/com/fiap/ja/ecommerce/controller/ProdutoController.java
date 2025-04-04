package br.com.fiap.ja.ecommerce.controller;

import br.com.fiap.ja.ecommerce.dto.ProdutoDTO;
import br.com.fiap.ja.ecommerce.service.CategoriaService;
import br.com.fiap.ja.ecommerce.service.MessageProducer;
import br.com.fiap.ja.ecommerce.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/produtos")
@AllArgsConstructor
@Log
public class ProdutoController {

    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;
    private final MessageProducer messageProducer;

    @GetMapping
    public String listar(Model model){
        List<ProdutoDTO> lista = produtoService.findAll();
        model.addAttribute("produtos", lista);
        return "produtos/lista";
    }

    @GetMapping("/novo")
    public String novoProduto(Model model){

        model.addAttribute("produto", new ProdutoDTO());
        model.addAttribute("categorias", categoriaService.getCategorias());

        return "produtos/formulario";
    }

    @PostMapping
    public String salvar(@Valid @ModelAttribute("produto") ProdutoDTO produto, BindingResult bindingResults, Model model){

        if (bindingResults.hasErrors()) {
            bindingResults.getAllErrors().forEach(e-> log.info(e.toString()));
            model.addAttribute("categorias", categoriaService.getCategorias());
            model.addAttribute("produto", produto);
            return "produtos/formulario";
        }
        produtoService.salvar(produto);

        messageProducer.sendMessage(produto);

        return "redirect:/produtos";

    }

    @GetMapping("/editar/{uuid}")
    public String editar(@PathVariable UUID uuid, Model model) {
        model.addAttribute("categorias", categoriaService.getCategorias());
        model.addAttribute("produto", produtoService.findById(uuid));
        return "produtos/formulario";
    }

    @GetMapping("/deletar/{uuid}")
    public String deletar(@PathVariable UUID uuid){
        produtoService.deletarPorId(uuid);
        return "redirect:/produtos";
    }

}
