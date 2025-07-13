package com.techlab.retrogaming.pedidos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        try {
            Pedido nuevoPedido = pedidoService.crearPedido(pedido.getUsuarioId(), pedido.getLineas());
            return ResponseEntity.ok(nuevoPedido);
        } catch (StockInsuficienteException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/usuarios/{usuarioId}")
    public List<Pedido> listarPedidosPorUsuario(@PathVariable int usuarioId) {
        return pedidoService.listarPedidosPorUsuario(usuarioId);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Pedido> actualizarEstadoPedido(@PathVariable int id, @RequestBody String estado) {
        try {
            Pedido pedidoActualizado = pedidoService.actualizarEstadoPedido(id, estado);
            return ResponseEntity.ok(pedidoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}