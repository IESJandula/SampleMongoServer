package es.iesjandula.SampleMongoServer.model;

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
@Document(collection = "productos")
public class Producto 
{
    @Id
    private String id;

    @Field(name = "nombre_producto")
    @JsonProperty("nombre_producto")
    private String nombre;

    @Field(name = "descripcion_producto")
    @JsonProperty("descripcion_producto")
    private String descripcion;

    @Field(name = "precio_producto")
    @JsonProperty("precio_producto")
    private double precio;

    @Field(name = "stock_producto")
    @JsonProperty("stock_producto")
    private int stock;
}