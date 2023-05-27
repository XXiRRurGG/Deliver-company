package com.codebind.lab1remake.DB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {
    @Autowired DriverRepository repo;
    @Autowired TruckRepository truckRepo;
    @Autowired TripRepository tripRepo;

    @GetMapping()
    public List<Driver> homePage()
    {
        return repo.getAllDrivers();
    }

    @GetMapping("/{name}")
    public Driver getDriver(@PathVariable("name") String login)
    {
        return repo.findById(login).get();
    }

    @PostMapping("/{name}/update")
    public void updateDriver(@PathVariable("name") String login,@RequestBody Driver driver)
    {
        if(truckRepo.findById(driver.truck).isPresent() && tripRepo.findById(driver.trip).isPresent())
         repo.setDriverInfoByLogin(driver.name,driver.surname,driver.contactInfo,driver.dateOfBirth,driver.login,driver.password,driver.truck,driver.trip,login);
    }

    @PostMapping("/create")
    public void saveDriver(@RequestBody Driver driver)
    {
        if(truckRepo.findById(driver.truck).isPresent() && tripRepo.findById(driver.trip).isPresent())
            repo.save(driver);
    }

    @GetMapping("/{name}/delete")
    public void deleteDriver(@PathVariable("name") String login)
    {
        repo.delete(repo.findById(login).get());
    }
}
