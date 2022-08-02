package com.springproject.rentalcar.controller;


import com.springproject.rentalcar.custom.response.ResponseBody;
import com.springproject.rentalcar.entity.Client;
import com.springproject.rentalcar.service.ClientServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientServiceImp clientServiceImp;

    @GetMapping()
    public List<Client> fetchClients(){
        return clientServiceImp.fetchClients();
    }

    @PostMapping()
    public Client createClient(@RequestBody Client client){
        return clientServiceImp.createClient(client);
    }

    @GetMapping("/{clientId}")
    public Client getClient(@PathVariable("clientId") Long clientId){
        return clientServiceImp.getClient(clientId);
    }

    @GetMapping("/byEmail/{clientEmail}")
    public Client getClientByEmail(@PathVariable("clientEmail") String clientEmail){
        return clientServiceImp.getClientByEmail(clientEmail);
    }

    @PostMapping("/{clientId}")
    public ResponseBody updateClient(@RequestBody Client client,@PathVariable("clientId") Long clientId) {
        Client updatedClient = clientServiceImp.updateClient(client, clientId);
        return new ResponseBody(updatedClient, "Client Updated");
    }

    @DeleteMapping("/{clientId}")
    public ResponseBody deleteClient(@PathVariable("clientId") Long clientId){
        Client client = clientServiceImp.deleteClient(clientId);
        return new ResponseBody(client, "Client Deleted");
    }

}
