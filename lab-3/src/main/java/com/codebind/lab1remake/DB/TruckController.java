package com.codebind.lab1remake.DB;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/truck")
public class TruckController {
    @Autowired
    TruckRepository repo;

    @Autowired
    CargoRepository cargoRepo;

    @GetMapping("")
    public List<Truck> homePage()
    {
        var trucks = (repo.getAllTrucks());
        return  trucks;
    }
    @GetMapping("/{name}")
    public Truck getTruck(@PathVariable("name") String truckNumber)
    {
        return repo.getTruck(truckNumber);
    }
    @PostMapping("/createTruck")
    public void createTruck(@RequestBody Truck truck)
    {
        if(cargoRepo.findById(truck.cargoId) != null)
        {
            repo.save(truck);
        }
    }
    @PostMapping("/changeTruck/{name}")
    public void changeTruck(@RequestBody Truck truck,@PathVariable("name") String truckNumber)
    {
        Optional<Truck> temp = repo.findById(truckNumber);
        if (temp.isPresent()) {
            if (cargoRepo.findById(truck.cargoId) != null) {

                repo.setTruckInfoByNumber(truck.truckNumber,truck.brand,truck.cargoId,truckNumber);
            }
        }
    }
    @GetMapping("/deleteTruck/{name}")
    public void deleteTruck(@PathVariable("name") String truckNumber)
    {
       repo.delete(repo.getTruck(truckNumber));
    }
}
