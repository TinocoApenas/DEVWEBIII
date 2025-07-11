package br.edu.ifpr.vhs.controllers;

import br.edu.ifpr.vhs.entities.Category;
import br.edu.ifpr.vhs.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categoryList", categoryService.findAll());
        return "category-list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("category", new Category());
        return "category-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Category category, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "category-form";
        }
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("successMessage", "Categoria salva com sucesso!");
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Category category = categoryService.findById(id);
        if (category == null) {
            return "redirect:/categories";
        }
        model.addAttribute("category", category);
        return "category-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            categoryService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Categoria excluída com sucesso!");
            return "redirect:/categories";
        } catch (RuntimeException e) {
            model.addAttribute("categoryList", categoryService.findAll());
            model.addAttribute("deleteError", e.getMessage());
            return "category-list";
        }
    }
}