package ForoHub.Challenge.Controlador;

import ForoHub.Challenge.Repositorio.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoControlador {

    @Autowired
    private TopicoRepository repository;

    // C - CREATE: Registrar tópico
    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos) {
        var existeTopico = repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());
        if (existeTopico) {
            return ResponseEntity.badRequest().body("Error: Ya existe un tópico con el mismo título y mensaje.");
        }
        var topico = new Topico(datos);
        repository.save(topico);
        return ResponseEntity.ok("Tópico creado exitosamente.");
    }

    // R - READ: Listado con paginación
    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listar(
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion) {
        var pagina = repository.findAll(paginacion).map(DatosListadoTopico::new);
        return ResponseEntity.ok(pagina);
    }

    // R - READ: Detalle de un tópico (URI: /topicos/{id})
    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id) {
        var topicoOptional = repository.findById(id);
        if (topicoOptional.isPresent()) {
            var topico = topicoOptional.get();
            return ResponseEntity.ok(new DatosListadoTopico(topico));
        }
        return ResponseEntity.status(404).body("Error: El tópico con ID " + id + " no existe.");
    }

    // U - UPDATE: Actualizar tópico (URI: /topicos/{id})
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datos) {
        // 1. Verificar existencia con isPresent()
        var topicoOptional = repository.findById(id);

        if (topicoOptional.isPresent()) {
            var topico = topicoOptional.get();

            // 2. Regla de negocio: No permitir duplicados (si se cambian título y mensaje)
            var existeDuplicado = repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());
            if (existeDuplicado) {
                return ResponseEntity.badRequest().body("Error: No se puede actualizar. Ya existe un tópico con ese título y mensaje.");
            }

            // 3. Actualizar datos en la entidad
            topico.actualizarDatos(datos);
            return ResponseEntity.ok(new DatosListadoTopico(topico));
        }

        // Si el ID no existe en la base de datos devuelve 404
        return ResponseEntity.status(404).body("Error: No se encontró el tópico con ID " + id + " para actualizar.");
    }

    // D - DELETE: Eliminar tópico (URI: /topicos/{id})
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id) {
        var topicoOptional = repository.findById(id);
        if (topicoOptional.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok("El tópico con ID " + id + " fue eliminado definitivamente.");
        }
        return ResponseEntity.status(404).body("No se pudo eliminar: El tópico con ID " + id + " no existe.");
    }
}