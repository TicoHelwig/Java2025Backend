@RestController
@RequestMapping("/api/productos")
@CrossOrigin
public class ProductoController {
    @Autowired
    private ProductoService service;

    @GetMapping
    public List<Producto> listar() {
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody @Valid Producto producto) {
        return new ResponseEntity<>(service.crear(producto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Producto buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        return service.actualizar(id, producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
