package com.lama.dsa.service.client;

import com.lama.dsa.model.client.Client;
import com.lama.dsa.repository.client.IClientRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("ClientService")
public class ClientService implements IClientService {

    @Autowired
    private IClientRepository clientRepository;

    public ClientService() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Client> getClientByName(String name) {
        return clientRepository.findByName(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Client insertClient(Client client){
        return clientRepository.insert(client);
    }
}
