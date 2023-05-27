package com.codebind.lab1remake.DB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired ClientRepository repo;

    @GetMapping()
    public List<Client> homePage()
    {
        return repo.getAllClients();
    }
    @GetMapping("/{name}")
    public Client getClient(@PathVariable("name") String login)
    {
        return repo.findById(login).get();
    }

    @PostMapping("/{name}/update")
    public void updateClient(@PathVariable("name") String login,@RequestBody Client client)
    {
        repo.setClientInfoByLogin(client.name,client.surname,client.contactInfo,client.dateOfBirth,client.login,client.password,login);
    }
    @PostMapping("/create")
    public void createClient(@RequestBody Client client)
    {
        repo.save(client);
    }
    @GetMapping("/{name}/delete")
    public void deleteClient(@PathVariable("name") String login)
    {
        repo.delete(repo.findById(login).get());
    }
}
