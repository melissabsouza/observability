package br.com.fiap.ja.ecommerce.controller;

import br.com.fiap.ja.ecommerce.dto.CategoriaDTO;
import br.com.fiap.ja.ecommerce.service.CategoriaService;
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

@Controller
@AllArgsConstructor
@Log
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;

    @GetMapping
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", service.getCategorias());
        return "categorias/lista";
    }

    @GetMapping("/nova")
    public String novaCategoria(Model model) {
        model.addAttribute("categoria", new CategoriaDTO());
        return "categorias/formulario";
    }

    @PostMapping("/salvar")
    public String salvarCategoria(@Valid @ModelAttribute("categoria") CategoriaDTO categoriaDTO, BindingResult bindingResults, Model model) {
        if (bindingResults.hasErrors()) {
            bindingResults.getAllErrors().forEach(e-> log.info(e.toString()));
            model.addAttribute("categoria", categoriaDTO);
            return "categorias/formulario";
        }
        service.save(categoriaDTO);
        return "redirect:/categorias";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        model.addAttribute("categoria", service.findById(id));
        return "categorias/formulario";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id){
        service.deletarPorId(id);
        return "redirect:/categorias";
    }


}
