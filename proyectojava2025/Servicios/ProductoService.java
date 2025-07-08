public interface ProductoService {
    Producto crear(Producto producto);
    List<Producto> listar();
    Producto buscarPorId(Long id);
    Producto actualizar(Long id, Producto producto);
    void eliminar(Long id);
}
