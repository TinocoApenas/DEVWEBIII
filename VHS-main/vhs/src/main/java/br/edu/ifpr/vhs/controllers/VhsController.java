package br.edu.ifpr.vhs.controllers;

import br.edu.ifpr.vhs.entities.Vhs;
import br.edu.ifpr.vhs.services.CategoryService;
import br.edu.ifpr.vhs.services.VhsService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/vhs")
public class VhsController {

    @Autowired
    private VhsService vhsService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String findAll(Model model) {
        List<Vhs> vhsList = vhsService.findAll();
        model.addAttribute("vhsList", vhsList);
        return "vhs-list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("vhs", new Vhs());
        model.addAttribute("categories", categoryService.findAll());
        return "vhs-form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Vhs vhs, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "vhs-form";
        }
        vhs.setRegistrationDate(LocalDate.now());
        vhsService.save(vhs);
        redirectAttributes.addFlashAttribute("successMessage", "Fita salva com sucesso!");
        return "redirect:/vhs";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Vhs vhs = vhsService.findById(id);
        model.addAttribute("vhs", vhs);
        model.addAttribute("categories", categoryService.findAll());
        return "vhs-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            vhsService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Fita excluída com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("deleteError", "Erro ao excluir a fita.");
        }
        return "redirect:/vhs";
    }

}