import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Caja {
    private ArrayList<ItemCarrito> carrito = new ArrayList<>();
    private Inventario inventario;

    public Caja(Inventario inventario) {
        this.inventario = inventario;
    }

    // Agregar un producto al carrito
    public void agregarProductoAlCarrito(Producto producto) {
        ItemCarrito item = new ItemCarrito(producto, 1);
        item.getProducto().setCantidadDisponible(item.getProducto().getCantidadDisponible() - 1);
        carrito.add(item);
    }

    // Borrar un producto del carrito
    public void borrarProductoDelCarrito(Producto producto) {
        for (ItemCarrito item : carrito) {
            if (item.getProducto().equals(producto)) {
                carrito.remove(item);
                item.getProducto().setCantidadDisponible(item.getProducto().getCantidadDisponible() + item.getCantidad());
                break;
            }
        }
    }

    // Ver el total del carrito
    public float verTotalDelCarrito() {
        float total = 0;
        for (ItemCarrito item : carrito) {
            total += item.getProducto().getPrecio() * item.getCantidad();
        }
        return total;
    }

    // Imprimir ticket y guardar en un archivo ticket.txt
    public void imprimirTicket() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("ticket.txt"));
            writer.write("----- Ticket de Compra -----");
            writer.newLine();
            for (ItemCarrito item : carrito) {
                writer.write(item.getProducto().getNombre());
                writer.newLine();
                writer.write("Precio: " + item.getProducto().getPrecio());
                writer.newLine();
                writer.write("Cantidad: " + item.getCantidad());
                writer.newLine();
                writer.write("Subtotal: " + item.getProducto().getPrecio() * item.getCantidad());
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
        for (ItemCarrito item: carrito) {
            System.out.println(item.getProducto().getNombre());
            System.out.println("------------------------------");
        }
    }

    // Vaciar el carrito
    public void vaciarCarrito() {
        for (ItemCarrito item : carrito) {
            item.getProducto().setCantidadDisponible(item.getProducto().getCantidadDisponible() + item.getCantidad());
        }
        carrito.clear();
    }

    // Modificar la cantidad de productos en el carrito
    public void modificarCantidadProducto(Producto producto, int nuevaCantidad) {
        ItemCarrito item = null;
        for(ItemCarrito items : carrito){
            if(items.getProducto().equals(producto)){
                item = items;
                break;
            }
        }
        for(ItemCarrito items : carrito){
            if(nuevaCantidad < 0){
                item = items;
                System.out.println("La cantidad no puede ser negativa");
                return;
            }
            else{
                if(item == null){
                    System.out.println("Item is null");
                    return;
                }
                if(nuevaCantidad > item.getProducto().getCantidadDisponible()){
                    System.out.println("No hay suficientes productos en el inventario");
                    return;
                }
                else if(nuevaCantidad < item.getCantidad()){
                    item.getProducto().setCantidadDisponible(item.getProducto().getCantidadDisponible() + (item.getCantidad() - nuevaCantidad));
                }
                else if(nuevaCantidad > item.getCantidad()){
                    System.out.println(nuevaCantidad - item.getCantidad());
                    
                    item.getProducto().setCantidadDisponible(item.getProducto().getCantidadDisponible() - (nuevaCantidad - item.getCantidad()));
                }
                item.setCantidadDisponible(nuevaCantidad);
            }
        }
    }
    

   public static class ItemCarrito{
        private Producto producto;
        private int cantidad;

        public ItemCarrito(Producto producto, int cantidad) {
            this.producto = producto;
            this.cantidad = cantidad;
        }

        public Producto getProducto() {
            return producto;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidadDisponible(int cantidad) {
            this.cantidad = cantidad;
        }
    }
}
