package es.iesjandula.SampleMongoServer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iesjandula.SampleMongoServer.repository.IImportacionService;

@RestController
public class ImportacionRestController {

    @Autowired
    private IImportacionService importacionService;

    @GetMapping("/importar-productos")
    public String importarProductos() {

        importacionService.importarProductosDesdeJson("src/main/resources/productos.json");

        return "Importación realizada";
    }
}
