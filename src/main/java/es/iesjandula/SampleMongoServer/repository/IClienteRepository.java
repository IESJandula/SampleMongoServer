package es.iesjandula.SampleMongoServer.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.SampleMongoServer.model.Cliente;

@Repository
public interface IClienteRepository extends MongoRepository<Cliente, String> 
{
    Optional<Cliente> findByEmail(String email);

    boolean existsByEmail(String email);
}