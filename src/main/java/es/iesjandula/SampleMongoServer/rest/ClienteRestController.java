package es.iesjandula.SampleMongoServer.rest;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.iesjandula.SampleMongoServer.model.Cliente;
import es.iesjandula.SampleMongoServer.repository.IClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteRestController {

    @Autowired
    private IClienteRepository clienteRepository;

    // Importar clientes
    @GetMapping("/importarClientes")
    public String importarClientes() {

        ObjectMapper objectMapper = new ObjectMapper();

        try {

            InputStream inputStream = getClass()
                    .getClassLoader()
                    .getResourceAsStream("clientes.json");

            List<Cliente> clientes = objectMapper.readValue(
                    inputStream,
                    new TypeReference<List<Cliente>>() {}
            );

            clienteRepository.deleteAll();
            clienteRepository.saveAll(clientes);

            return "Clientes importados correctamente";

        } catch (Exception e) {
            return "Error al importar: " + e.getMessage();
        }
    }

    // Listar todos
    @GetMapping("/listaClientes")
    public List<Cliente> listaClientes() {
        return clienteRepository.findAll();
    }

    // Obtener cliente por id
    @GetMapping("/obtenerCliente/{id}")
    public Object obtenerCliente(@PathVariable String id) {

        if (!clienteRepository.existsById(id)) {
            return "El cliente no existe";
        }

        return clienteRepository.findById(id).get();
    }

    // Crear cliente
    @PostMapping("/crearCliente")
    public String crearCliente(@RequestBody Cliente cliente) {

        if (cliente.getId() != null &&
                clienteRepository.existsById(cliente.getId())) {

            return "El cliente ya existe";
        }

        clienteRepository.save(cliente);
        return "Cliente creado correctamente";
    }

    // Eliminar cliente
    @DeleteMapping("/eliminarCliente/{id}")
    public String eliminarCliente(@PathVariable String id) {

        if (!clienteRepository.existsById(id)) {
            return "El cliente no se encuentra registrado";
        }

        clienteRepository.deleteById(id);
        return "Cliente eliminado correctamente";
    }

    // Borrar todos
    @DeleteMapping("/borrarTodo")
    public String borrarTodo() {
        clienteRepository.deleteAll();
        return "Todos los clientes eliminados";
    }
}