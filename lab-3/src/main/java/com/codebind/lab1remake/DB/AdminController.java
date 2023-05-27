package com.codebind.lab1remake.DB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminRepository repo;

    @GetMapping()
    public List<Admin> homePage()
    {
        return repo.getAllAdmins();
    }
    @GetMapping("/{name}")
    public Admin getAdmin(@PathVariable("name") String login)
    {
        return repo.findById(login).get();
    }

    @PostMapping("/{name}/update")
    public void updateAdmin(@PathVariable("name") String login,@RequestBody Admin admin)
    {
        repo.setAdminInfoByLogin(admin.name,admin.surname,admin.contactInfo,admin.dateOfBirth,admin.login,admin.password,login);
    }
    @PostMapping("/create")
    public void createAdmin(@RequestBody Admin admin)
    {
        repo.save(admin);
    }
    @GetMapping("/{name}/delete")
    public void deleteAdmin(@PathVariable("name") String login)
    {
        repo.delete(repo.findById(login).get());
    }
}
