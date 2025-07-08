@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin
public class PedidoController {
    @Autowired
    private PedidoService service;

    @PostMapping
    public ResponseEntity<Pedido> crear(@RequestBody Pedido pedido) {
        // Validar stock disponible
        for (LineaPedido lp : pedido.getLineas()) {
            if (lp.getCantidad() > lp.getProducto().getStock()) {
                throw new StockInsuficienteException("Stock insuficiente para: " + lp.getProducto().getNombre());
            }
        }

        // Disminuir stock
        pedido.getLineas().forEach(lp -> {
            Producto prod = lp.getProducto();
            prod.setStock(prod.getStock() - lp.getCantidad());
        });

        pedido.setFecha(LocalDateTime.now());
        pedido.setEstado("PENDIENTE");

        return new ResponseEntity<>(service.crear(pedido), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Pedido> listar() {
        return service.listar();
    }
}
