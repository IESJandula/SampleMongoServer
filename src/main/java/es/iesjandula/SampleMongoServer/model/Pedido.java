package es.iesjandula.SampleMongoServer.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "pedidos")
public class Pedido 
{
    @Id
    private String id;

    @Field(name = "id_cliente")
    @JsonProperty("id_cliente")
    private String idCliente;

    @Field(name = "productos_ids")
    @JsonProperty("productos_ids")
    private List<String> productosIds;

    @Field(name = "total_pedido")
    @JsonProperty("total_pedido")
    private double total;
}
