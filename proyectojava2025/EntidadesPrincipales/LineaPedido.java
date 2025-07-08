@Entity
public class LineaPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Producto producto;

    private int cantidad;

    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    // Getters, setters, constructor
}
