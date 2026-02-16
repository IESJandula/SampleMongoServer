package es.iesjandula.SampleMongoServer.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import es.iesjandula.SampleMongoServer.model.Producto;
import es.iesjandula.SampleMongoServer.repository.IProductoRepository;

@RestController
@RequestMapping("/productos")
public class ProductoRestController {

    @Autowired
    private IProductoRepository productoRepository;

    // 🔹 Listar todos los productos
    @GetMapping
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    // 🔹 Obtener producto por id
    @GetMapping("/{id}")
    public Producto obtenerPorId(@PathVariable String id) {
        return productoRepository.findById(id).orElse(null);
    }

    // 🔹 Crear producto
    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    // 🔹 Eliminar producto
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) {
        productoRepository.deleteById(id);
    }
}