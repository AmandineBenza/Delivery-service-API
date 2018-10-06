package com.lama.dsa.service.client;

import com.lama.dsa.model.client.Client;

import java.util.List;


public interface IClientService {

    public List<Client> getAll();

    public List<Client> getClientByName(String name);

    public Client insertClient(Client client);
}
