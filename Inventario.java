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
            if (parts.length >= 5) {
                String tipo = parts[0];
                String nombre = parts[1];
                double precio = Double.parseDouble(parts[2]);
                int cantidadDisponible = Integer.parseInt(parts[3]);
                String codigoUnico = parts[4];
                Producto producto;

                if ("Agua".equals(tipo)) {
                    producto = new Agua(nombre, precio, cantidadDisponible, codigoUnico);
                } else if ("Azucarada".equals(tipo)) {
                    producto = new Azucarada(nombre, precio, cantidadDisponible, codigoUnico);
                } else if ("Alcohólica".equals(tipo)) {
                    producto = new Alcoholica(nombre, precio, cantidadDisponible, codigoUnico);
                } else if ("Salado".equals(tipo)) {
                    producto = new Salado(nombre, precio, cantidadDisponible, codigoUnico);
                } else if ("Dulces".equals(tipo)) {
                    producto = new Dulce(nombre, precio, cantidadDisponible, codigoUnico);
                } else if ("Comida Rápida".equals(tipo)) {
                    producto = new Rapida(nombre, precio, cantidadDisponible, codigoUnico);
                } else if ("Embutidos".equals(tipo)) {
                    producto = new Embutidos(nombre, precio, cantidadDisponible, codigoUnico);
                } else if ("Pan".equals(tipo)) {
                    producto = new Pan(nombre, precio, cantidadDisponible, codigoUnico);
                } else if ("Quesos".equals(tipo)) {
                    producto = new Queso(nombre, precio, cantidadDisponible, codigoUnico);
                } else if ("Comida para Mascotas".equals(tipo)) {
                    producto = new Mascotas(nombre, precio, cantidadDisponible, codigoUnico);
                } else if ("Salsas".equals(tipo)) {
                    producto = new Salsa(nombre, precio, cantidadDisponible, codigoUnico);
                } else if ("Sal y Parecidos".equals(tipo)) {
                    producto = new Complementos(nombre, precio, cantidadDisponible, codigoUnico);
                } else if ("Tortillas".equals(tipo)) {
                    producto = new Tortillas(nombre, precio, cantidadDisponible, codigoUnico);
                } else if ("Bloqueador Solar".equals(tipo)) {
                    producto = new BloqueadorSolar(nombre, precio, cantidadDisponible, codigoUnico);
                } else if ("Cremas".equals(tipo)) {
                    producto = new Crema(nombre, precio, cantidadDisponible, codigoUnico);
                } else if ("Serums".equals(tipo)) {
                    producto = new Serum(nombre, precio, cantidadDisponible, codigoUnico);
                } else if ("Toners".equals(tipo)) {
                    producto = new Toner(nombre, precio, cantidadDisponible, codigoUnico);
                } else if ("Limpiadores Faciales".equals(tipo)) {
                    producto = new LimpiadorFacial(nombre, precio, cantidadDisponible, codigoUnico);
                } else if ("Shampoo".equals(tipo)) {
                    producto = new Shampoo(nombre, precio, cantidadDisponible, codigoUnico);
                } else if ("Jabones".equals(tipo)) {
                    producto = new Jabon(nombre, precio, cantidadDisponible, codigoUnico);
                } else if ("Bálsamos".equals(tipo)) {
                    producto = new Balsamos(nombre, precio, cantidadDisponible, codigoUnico);
                } else {
                    producto = new Producto(nombre, precio, cantidadDisponible, codigoUnico);
                }

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
            System.out.println(producto.toString());
        }
    }

    public void modificarCSV(String csvFileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(csvFileName));
            for (Producto producto : productos) {
                String line = producto.toCSV(); 
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }

    public void modificarCantidadProducto(String nombre, int nuevaCantidad) {
        for (Producto producto : productos) {
            if (producto.getNombre().equals(nombre)) {
                producto.setCantidadDisponible(nuevaCantidad);
                break;
            }
        }
    }
}
