package com.springproject.rentalcar.service;

import com.springproject.rentalcar.entity.Client;
import com.springproject.rentalcar.exception.BusinessException;
import com.springproject.rentalcar.exception.EmptyTableException;
import com.springproject.rentalcar.exception.NoValueException;
import com.springproject.rentalcar.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImp implements ClientService{

    @Autowired
    private ClientRepository clientRepository;


    @Override
    public List<Client> fetchClients() {
        List<Client> clients =  clientRepository.findAll();
        if(clients.size() == 0){
            throw new EmptyTableException("There are no clients");
        }else {
            return clients;
        }
    }

    @Override
    public Client createClient(Client client) {

        if(client.getEmail() == "" || client.getFirstName() == "" || client.getLastName() =="" || client.getAge() == null || client.getDrivingLicenseNo()==""|| client.getAddress()==""){
            throw new BusinessException(400, "Enter valid client properties");
        }else {
            return clientRepository.save(client);
        }
    }

    @Override
    public Client getClient(Long clientId) {
        return clientRepository.findById(clientId).get();
    }

    @Override
    public Client updateClient(Client client, Long clientId) {

        if(client.getFirstName() == "" || client.getLastName() == "" || client.getAge() == null|| client.getDrivingLicenseNo()==""|| client.getAddress()==""){
            throw new BusinessException(400, "Enter valid client fields");
        }else {
            Client existingClient = clientRepository.findById(clientId).get();
            existingClient.setEmail(client.getEmail());
            existingClient.setFirstName(client.getFirstName());
            existingClient.setLastName(client.getLastName());
            existingClient.setAge(client.getAge());
            return clientRepository.save(existingClient);
        }
    }

    @Override
    public Client getClientByEmail(String clientEmail) {
        Client client = clientRepository.getClientByEmail(clientEmail);
        if (client == null){
            throw new NoValueException( "No client with that email");
        }else {
            return client;
        }
    }

    @Override
    public Client deleteClient(Long clientId) {
        Client client = clientRepository.findById(clientId).get();
        clientRepository.delete(client);
        return client;
    }
}
