package es.iesjandula.SampleMongoServer.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.SampleMongoServer.model.Producto;

@Repository
public interface IProductoRepository extends MongoRepository<Producto, String> 
{
    List<Producto> findByNombre(String nombre);

    List<Producto> findByPrecioGreaterThan(double precio);

    List<Producto> findByStockLessThan(int stock);
}