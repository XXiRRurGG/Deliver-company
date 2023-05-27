package com.codebind.lab1remake.DB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Controller
@RequestMapping("/cargo")
public class CargoController {
    @Autowired private CargoRepository repo;

    @GetMapping()
    public String getAllCargos(Model model)
    {
        model.addAttribute("cargos",repo.findAll());
        return "cargoMainPage";
    }
    @PostMapping("/delete")
    public String deleteCargo(@ModelAttribute("cargo") Long cargoId)
    {
        //Complete the function once add truck table(delete id from here, then delete cargo completely)

        repo.deleteById(cargoId);
        return "redirect:/cargo";
    }
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Cargo cargo = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid cargo ID: " + id));
        model.addAttribute("cargo", cargo);
        return "cargoEditnCreate";
    }

    @PostMapping("/{id}/edit")
    public String updateCargo(@PathVariable("id") Long id, @RequestParam("cargo") String items) {
        Cargo cargo = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid cargo ID: " + id));
        cargo.listofstrings.stringValue = items;
        // update other properties as needed
        repo.save(cargo);
        return "redirect:/cargo";
    }

    @GetMapping("/addCargo")
    public String showEditForm() {
        return "cargoEditnCreate";
    }

    @PostMapping("/addCargo")
    public String updateCargo(@RequestParam("cargo") String items) {
        Cargo cargo = new Cargo();
        cargo.listofstrings = new ListOfStringsTableElement();
        cargo.listofstrings.stringValue = items;
        repo.save(cargo);
        return "redirect:/cargo";
    }
}
