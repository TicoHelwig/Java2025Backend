public interface PedidoService {
    Pedido crear(Pedido pedido);
    List<Pedido> listar();
    Pedido buscarPorId(Long id);
}
