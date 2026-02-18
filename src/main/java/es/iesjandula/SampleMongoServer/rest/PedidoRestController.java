package es.iesjandula.SampleMongoServer.rest;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.iesjandula.SampleMongoServer.model.Pedido;
import es.iesjandula.SampleMongoServer.repository.IPedidoRepository;

@RestController
@RequestMapping("/pedidos")
public class PedidoRestController {

    @Autowired
    private IPedidoRepository pedidoRepository;

    // Importar pedidos
    @GetMapping("/importarPedidos")
    public String importarPedidos() {

        ObjectMapper objectMapper = new ObjectMapper();

        try {

            InputStream inputStream = getClass()
                    .getClassLoader()
                    .getResourceAsStream("pedidos.json");

            List<Pedido> pedidos = objectMapper.readValue(
                    inputStream,
                    new TypeReference<List<Pedido>>() {}
            );

            pedidoRepository.deleteAll();
            pedidoRepository.saveAll(pedidos);

            return "Pedidos importados correctamente";

        } catch (Exception e) {
            return "Error al importar: " + e.getMessage();
        }
    }

    // Listar todos
    @GetMapping("/listaPedidos")
    public List<Pedido> listaPedidos() {
        return pedidoRepository.findAll();
    }

    // Obtener pedido por id
    @GetMapping("/obtenerPedido/{id}")
    public Object obtenerPedido(@PathVariable String id) {

        if (!pedidoRepository.existsById(id)) {
            return "El pedido no existe";
        }

        return pedidoRepository.findById(id).get();
    }

    // Crear pedido
    @PostMapping("/crearPedido")
    public String crearPedido(@RequestBody Pedido pedido) {

        if (pedido.getId() != null &&
                pedidoRepository.existsById(pedido.getId())) {

            return "El pedido ya existe";
        }

        pedidoRepository.save(pedido);
        return "Pedido creado correctamente";
    }

    // Eliminar pedido
    @DeleteMapping("/eliminarPedido/{id}")
    public String eliminarPedido(@PathVariable String id) {

        if (!pedidoRepository.existsById(id)) {
            return "El pedido no se encuentra registrado";
        }

        pedidoRepository.deleteById(id);
        return "Pedido eliminado correctamente";
    }

    // Borrar todos
    @DeleteMapping("/borrarTodo")
    public String borrarTodo() {
        pedidoRepository.deleteAll();
        return "Todos los pedidos eliminados";
    }
}