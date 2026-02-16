package es.iesjandula.SampleMongoServer.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import es.iesjandula.SampleMongoServer.model.Pedido;

@Repository
public interface IPedidoRepository extends MongoRepository<Pedido, String> 
{
    List<Pedido> findByIdCliente(String idCliente);

    List<Pedido> findByTotalGreaterThan(double total);
}
