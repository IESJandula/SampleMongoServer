package es.iesjandula.SampleMongoServer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.SampleMongoServer.model.Producto;

@Repository
public interface IProductoRepository extends MongoRepository<Producto, String>
{
	
}