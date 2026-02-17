package es.iesjandula.SampleMongoServer.rest;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.iesjandula.SampleMongoServer.model.Producto;
import es.iesjandula.SampleMongoServer.repository.IProductoRepository;

@RestController
@RequestMapping("/productos")
public class ProductoRestController {

    @Autowired
    private IProductoRepository productoRepository;
    
    @GetMapping("/importarProductos")
    public String importarProductos() 
    {

        ObjectMapper objectMapper = new ObjectMapper();

        try 
        {

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("productos.json");

            List<Producto> productos = objectMapper.readValue(inputStream,new TypeReference<List<Producto>>() {});

            // Limpia antes de importar (opcional)
            productoRepository.deleteAll();

            productoRepository.saveAll(productos);

            return "Productos importados correctamente";

        } 
        catch (Exception e) 
        {
            return "Error al importar: " + e.getMessage();
        }
    }


    // 🔹 Listar todos los productos
    @GetMapping("listaProductos")
    public List<Producto> obtenerProductos() 
    {
        return productoRepository.findAll();
    }

    // 🔹 Obtener producto por id
    @GetMapping("/obtenerProducto/{id}")
    public Object obtenerProductoPorId(@PathVariable String id) {

        if (!productoRepository.existsById(id)) {
            return "El producto no existe";
        }

        return productoRepository.findById(id).get();
    }

    // 🔹 Crear producto
    @PostMapping("/crearProducto")
    public String crearProducto(@RequestBody Producto producto) 
    {
    	if (producto.getId() != null &&
    	        productoRepository.existsById(producto.getId())) {

    	        return "El producto ya existe";
    	    }

    	    productoRepository.save(producto);
    	    return "Producto creado correctamente";
    }

    // 🔹 Eliminar producto
    @DeleteMapping("/eliminarProducto/{id}")
    public String eliminar(@PathVariable String id) 
    {
        if (!productoRepository.existsById(id)) {
            return "El producto no se encuentra registrado";
        }

        productoRepository.deleteById(id);
        return "Producto eliminado correctamente";
    }

    
    // Eliminar productos
    @DeleteMapping("/borrarTodo")
    public String borrarTodos()
    {
        productoRepository.deleteAll();
        return "Todos los productos eliminados";
    }

}