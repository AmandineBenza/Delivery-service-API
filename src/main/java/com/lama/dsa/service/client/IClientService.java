package com.lama.dsa.service.client;

import com.lama.dsa.model.client.Client;

import java.util.List;


public interface IClientService {
	
	 /**
     * Get all clients availables in the database.
     */
    public List<Client> getAll();
    
    /**
     * Get client given a name.
     */
    public List<Client> getClientByName(String name);
    
    /**
     * Insert a client in the database.
     */
    public Client insertClient(Client client);
}
