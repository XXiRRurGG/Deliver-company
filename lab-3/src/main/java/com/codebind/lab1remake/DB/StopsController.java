package com.codebind.lab1remake.DB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/stops")
public class StopsController {

    @Autowired
    private final StopDao stopsDao;

    public StopsController(StopDao stopsDao) {
        this.stopsDao = stopsDao;
    }

    @ResponseBody
    @GetMapping("")
    public List<Stops> homePage() throws SQLException
    {
        return stopsDao.getAllStops();
    }

    @GetMapping("/{name}")
    public String getStopByName(@PathVariable("name") String stopName, Model model) throws SQLException {
        Stops stop = stopsDao.getStopByName(stopName);
        model.addAttribute("stop",stop);
        return "stopConfiguration";
    }

    @PostMapping("/{name}/delete")
    public String deleteStop(@PathVariable("name") String stopName) throws SQLException
    {
        stopsDao.deleteStop(stopName);
        return "redirect:/stops";
    }
    @GetMapping("/{name}/edit")
    public String editStopView(@PathVariable("name") String stopName)
    {
        return "stopEditnCreate";
    }
    @PostMapping("/{name}/edit")
    public String editStop(@PathVariable("name") String stopName, @RequestParam("stop") String newStopName) throws SQLException
    {
        stopsDao.updateStop(stopName,newStopName);
        return "redirect:/stops/" + newStopName;
    }
    @GetMapping("/addStop")
    public String createStopView()
    {
        return "stopEditnCreate";
    }
    @PostMapping("/addStop")
    public String addStop(@RequestParam("stop") String newStopName) throws SQLException
    {
        stopsDao.insertStop(newStopName);
        return "redirect:/stops";
    }
}
