package com.techlab.retrogaming.productos;

import com.techlab.retrogaming.excepciones.StockInsuficienteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerProductoPorId(int id) {
        return productoRepository.findById(id);
    }

    public Producto agregarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(int id, Producto productoActualizado) {
        Optional<Producto> productoExistente = productoRepository.findById(id);
        if (productoExistente.isPresent()) {
            Producto producto = productoExistente.get();
            producto.setNombre(productoActualizado.getNombre());
            producto.setDescripcion(productoActualizado.getDescripcion());
            producto.setPrecio(productoActualizado.getPrecio());
            producto.setCategoria(productoActualizado.getCategoria());
            producto.setImagenUrl(productoActualizado.getImagenUrl());
            producto.setStock(productoActualizado.getStock());
            return productoRepository.save(producto);
        } else {
            throw new RuntimeException("Producto no encontrado");
        }
    }

    public void eliminarProducto(int id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado");
        }
        productoRepository.deleteById(id);
    }

    public void validarStock(Producto producto, int cantidad) {
        if (producto.getStock() < cantidad) {
            throw new StockInsuficienteException("Stock insuficiente para el producto: " + producto.getNombre());
        }
    }
}