@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository repo;

    @Override
    public Producto crear(Producto producto) {
        return repo.save(producto);
    }

    @Override
    public List<Producto> listar() {
        return repo.findAll();
    }

    @Override
    public Producto buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado"));
    }

    @Override
    public Producto actualizar(Long id, Producto nuevo) {
        Producto actual = buscarPorId(id);
        actual.setNombre(nuevo.getNombre());
        actual.setDescripcion(nuevo.getDescripcion());
        actual.setPrecio(nuevo.getPrecio());
        actual.setCategoria(nuevo.getCategoria());
        actual.setImagenUrl(nuevo.getImagenUrl());
        actual.setStock(nuevo.getStock());
        return repo.save(actual);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
