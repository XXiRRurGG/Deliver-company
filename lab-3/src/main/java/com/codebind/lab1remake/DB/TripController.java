package com.codebind.lab1remake.DB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/trip")
public class TripController {
    @Autowired TripRepository repo;
    @Autowired StopsRepository stopRepo;

    @GetMapping()
    public String  homePage(Model model)
    {
        var temp = repo.findAll();
        model.addAttribute("trips",repo.findAll());
        System.out.println(StreamSupport.stream(temp.spliterator(), false).count());
        return "tripMainPage";
    }
    @PostMapping("/{id}/delete")
    public String deleteTrip(@PathVariable("id") Long id)
    {
        repo.deleteById(id);
        return "redirect:/trip";
    }
    @GetMapping("/{id}/edit")
    public String editTrip(@PathVariable("id") Long id,Model model)
    {
        //var trip = repo.findById(id);
        model.addAttribute("selectedStops",stopRepo.findAllByTripId(id));
        model.addAttribute("availableStops",stopRepo.findAvailableStopsForTrip(id));

        return "tripEditnCreate";
    }
}
