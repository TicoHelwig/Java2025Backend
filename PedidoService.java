package com.techlab.retrogaming.pedidos;

import com.techlab.retrogaming.excepciones.StockInsuficienteException;
import com.techlab.retrogaming.productos.Producto;
import com.techlab.retrogaming.productos.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoService productoService;

    public Pedido crearPedido(int usuarioId, List<LineaPedido> lineas) {
        for (LineaPedido linea : lineas) {
            Producto producto = productoService.obtenerProductoPorId(linea.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            productoService.validarStock(producto, linea.getCantidad());
        }

        Pedido pedido = new Pedido();
        pedido.setUsuarioId(usuarioId);
        pedido.setFecha(LocalDateTime.now());
        pedido.setEstado("pendiente");
        pedido.setLineas(lineas);

        for (LineaPedido linea : lineas) {
            Producto producto = productoService.obtenerProductoPorId(linea.getProducto().getId()).get();
            producto.setStock(producto.getStock() - linea.getCantidad());
            productoService.agregarProducto(producto);
        }

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidosPorUsuario(int usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId);
    }

    public Pedido actualizarEstadoPedido(int id, String estado) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        pedido.setEstado(estado);
        return pedidoRepository.save(pedido);
    }
}