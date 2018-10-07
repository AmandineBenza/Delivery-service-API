package com.lama.dsa.repository.client;


import com.lama.dsa.model.client.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Database client storing. 
 */
public interface IClientRepository extends MongoRepository<Client, String> {

	/**
	 * Find client given a name.
	 * 
	 * @param name the client name
	 * @return a list of clients
	 */
    public List<Client> findByName(String name);

	/**
	 * Find client given an identifier.
	 * 
	 * @param clientId the client identifier
	 * @return a list of clients
	 */
    public List<Client> findById(int clientId);

}
