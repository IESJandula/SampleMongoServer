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
@Document(collection = "clientes")
public class Cliente 
{
    @Id
    private String id;

    @Field(name = "nombre_cliente")
    @JsonProperty("nombre_cliente")
    private String nombre;

    @Field(name = "email_cliente")
    @JsonProperty("email_cliente")
    private String email;

    @Field(name = "telefono_cliente")
    @JsonProperty("telefono_cliente")
    private String telefono;
}
