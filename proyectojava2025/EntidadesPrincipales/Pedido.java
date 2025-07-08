@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String estado; // PENDIENTE, CONFIRMADO, ENVIADO, ENTREGADO, CANCELADO
    private LocalDateTime fecha;

    @OneToMany(cascade = CascadeType.ALL)
    private List<LineaPedido> lineas;

    public double getTotal() {
        return lineas.stream().mapToDouble(LineaPedido::getSubtotal).sum();
    }

    // Getters, setters, constructor
}
