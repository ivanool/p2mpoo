import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class Inventario {
    private ArrayList<Producto> productos = new ArrayList<Producto>();

    public Inventario() {
    }

public void cargarInventario(String csvFileName) {
    try {
        BufferedReader reader = new BufferedReader(new FileReader(csvFileName));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 6) {
                String tipo = parts[0];
                System.out.println(tipo);
                String nombre = parts[1];
                System.out.println(nombre);
                double precio = Double.parseDouble(parts[2]);
                int cantidadDisponible = Integer.parseInt(parts[3]);
                String codigoUnico = parts[4];
                int restriccion_edad = Integer.parseInt(parts[5]);
                Producto producto;
                producto = asignar_subclase(tipo, nombre, precio, cantidadDisponible, codigoUnico, restriccion_edad);
                productos.add(producto);
            }
        }
        reader.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    public void modificarProducto(String nombre, Producto nuevoProducto) {
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            if (producto.getNombre().equals(nombre)) {
                productos.set(i, nuevoProducto);
                break;
            }
        }
    }

    public Producto buscarProducto(String nombre) {
        for (Producto producto : productos) {
            if (producto.getNombre().equals(nombre)) {
                return producto;
            }
        }
        return null; // Producto no encontrado
    }

    public void eliminarProducto(String nombre) {
        productos.removeIf(producto -> producto.getNombre().equals(nombre));
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void mostrarInventario() {
        for (Producto producto : productos) {
            System.out.println(producto.ListarInformacion());
        }
    }

    public void modificarCSV(String csvFileName) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFileName))) {
        for (Producto producto : productos) {
            String line = producto.toCSV();
            writer.write(line);
            writer.newLine(); // Agrega una nueva línea después de cada producto
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    public void modificarCantidadProducto(String nombre, int nuevaCantidad) {
        for (Producto producto : productos) {
            if (producto.getNombre().equals(nombre)) {
                break;
            }
        }
    }

    public int getCantidadProducto(Producto producto) {
        return producto.getCantidadDisponible();
    }
    

    private Producto asignar_subclase(String tipo, String nombre, double precio, int cantidadDisponible, String codigoUnico, int restriccion_edad) {
        Producto p;
        switch(tipo){
            
            case "Bebida":
                p = new Bebida(nombre, precio, cantidadDisponible, codigoUnico, restriccion_edad);
                return p;
            case "Comida":
                p = new Comida(nombre, precio, cantidadDisponible, codigoUnico, restriccion_edad);
                return p;
            case "CuidadoPersonal":
                p = new CuidadoPersonal(nombre, precio, cantidadDisponible, codigoUnico, restriccion_edad);
                return p;
            case "Telefonia":
                p = new Telefonia(nombre, precio, cantidadDisponible, codigoUnico, restriccion_edad);
                return p;
            case "Alcoholica":
                p = new Alcoholica(nombre, precio, cantidadDisponible, codigoUnico, restriccion_edad);
                return p;
            default:
                p = new Producto(nombre, precio, cantidadDisponible, codigoUnico, restriccion_edad);
                return p;
        }
    }
}
