package com.lama.dsa.repository.client;


import com.lama.dsa.model.client.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IClientRepository extends MongoRepository<Client, String> {

    public List<Client> findByName(String name);

    public List<Client> findById(int clientId);


}
