package com.springproject.rentalcar.service;

import com.springproject.rentalcar.entity.Client;

import java.util.List;

public interface ClientService {

    public List<Client> fetchClients();
    public Client createClient(Client client);
    public Client getClient(Long clientId);
    public Client updateClient(Client client, Long clientId);
    public Client getClientByEmail(String clientEmail);
    public Client deleteClient(Long clientId);
}
