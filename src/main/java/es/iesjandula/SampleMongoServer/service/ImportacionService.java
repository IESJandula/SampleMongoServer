package es.iesjandula.SampleMongoServer.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.iesjandula.SampleMongoServer.model.Producto;
import es.iesjandula.SampleMongoServer.repository.IImportacionService;
import es.iesjandula.SampleMongoServer.repository.IProductoRepository;

@Service
public class ImportacionService implements IImportacionService {

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    public void importarProductosDesdeJson(String rutaArchivo) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<Producto> productos = objectMapper.readValue(
                    new File(rutaArchivo),
                    new TypeReference<List<Producto>>() {}
            );

            productoRepository.saveAll(productos);

            System.out.println("Productos importados correctamente.");

        } catch (IOException e) {
            System.err.println("Error al importar el JSON: " + e.getMessage());
        }
    }
}