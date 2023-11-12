import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Caja {
    private ArrayList<Producto> carrito = new ArrayList<>();
    private Inventario inventario;

    public Caja(Inventario inventario) {
        this.inventario = inventario;
    }

    // Agregar un producto al carrito
    public void agregarProductoAlCarrito(Producto producto) {
        carrito.add(producto);
    }

    // Borrar un producto del carrito
    public void borrarProductoDelCarrito(Producto producto) {
        carrito.remove(producto);
    }

    // Ver el total del carrito
    public float verTotalDelCarrito() {
        float total = 0;
        for (Producto producto : carrito) {
            total += producto.getPrecio();
        }
        return total;
    }

    // Imprimir ticket y guardar en un archivo ticket.txt
    public void imprimirTicket() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("ticket.txt"));
            writer.write("----- Ticket de Compra -----");
            writer.newLine();
            for (Producto producto : carrito) {
                writer.write(producto.ListarInformación());
                writer.newLine();
                writer.write("Precio: " + producto.getPrecio());
                writer.newLine();
                writer.write("------------------------------");
                writer.newLine();
            }
            writer.write("Total: " + verTotalDelCarrito());
            writer.newLine();
            writer.write("Gracias por su compra!");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Listar objetos en el carrito
    public void listarObjetosEnCarrito() {
        System.out.println("----- Productos en el Carrito -----");
        for (Producto producto : carrito) {
            System.out.println(producto.ListarInformación());
            System.out.println("------------------------------");
        }
    }

    // Vaciar el carrito
    public void vaciarCarrito() {
        carrito.clear();
    }

    // Modificar la cantidad de productos en el carrito
    public void modificarCantidadProducto(Producto producto, int nuevaCantidad) {
        if (nuevaCantidad > 0) {
            if (carrito.contains(producto)) {
                int index = carrito.indexOf(producto);
                carrito.get(index).setCantidad(nuevaCantidad);
            }
        } else {
            carrito.remove(producto);
        }
    }
}
