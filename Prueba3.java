import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;


public class Prueba3 {

    private Inventario inventario;
    private Caja caja;

    public static void main(String[] args) {
        Prueba3 programa = new Prueba3();
        programa.iniciarPrograma();
    }

    private void iniciarPrograma() {
        inventario = new Inventario();
        caja = new Caja(inventario);

        JFrame frame = new JFrame("Menú principal");
        JPanel panel = new JPanel(new GridLayout(2, 1));

        JButton ventaBoton = crearBoton("Venta", e -> manejarVentas());
        JButton inventarioBoton = crearBoton("Inventario", e -> manejarInventario());

        panel.add(ventaBoton);
        panel.add(inventarioBoton);

        configurarVentana(frame, panel, 300, 150);
    }

    private JButton crearBoton(String texto, ActionListener actionListener) {
        JButton boton = new JButton(texto);
        boton.addActionListener(actionListener);
        return boton;
    }

    private void manejarVentas() {
        JFrame ventasFrame = new JFrame("Ventas");
        JPanel ventasPanel = new JPanel(new GridLayout(5, 2));

        JButton agregarCarritoBoton = crearBoton("Agregar al Carrito", e -> agregarProductoAlCarrito());
        JButton verCarritoBoton = crearBoton("Ver Carrito", e -> verCarrito());
        JButton imprimirTicketBoton = crearBoton("Comprarlo", e -> imprimirTicket());
        JButton vaciarCarritoBoton = crearBoton("Vaciar Carrito", e -> caja.vaciarCarrito());
        JButton modificarCantidadBoton = crearBoton("Modificar cantidad", e -> modificarCantidadEnCarrito());

        ventasPanel.add(agregarCarritoBoton);
        ventasPanel.add(verCarritoBoton);
        ventasPanel.add(imprimirTicketBoton);
        ventasPanel.add(vaciarCarritoBoton);
        ventasPanel.add(modificarCantidadBoton);

        configurarVentana(ventasFrame, ventasPanel, 300, 200);
    }

    private void verCarrito() {
        JFrame carritoFrame = new JFrame("Carrito de Compras");
        JPanel carritoPanel = new JPanel(new BorderLayout());

        Object[][] datosCarrito = obtenerDatosCarrito();
        String[] columnas = {"Nombre", "Precio", "Cantidad", "Total"};
        JTable tablaCarrito = new JTable(new DefaultTableModel(datosCarrito, columnas));
        JScrollPane scrollPane = new JScrollPane(tablaCarrito);

        JLabel totalLabel = new JLabel("Total: $" + calcularTotalCarrito());
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setHorizontalAlignment(JLabel.CENTER);

        carritoPanel.add(scrollPane, BorderLayout.CENTER);
        carritoPanel.add(totalLabel, BorderLayout.SOUTH);

        carritoFrame.add(carritoPanel);
        carritoFrame.setSize(400, 300);
        carritoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        carritoFrame.setLocationRelativeTo(null);
        carritoFrame.setVisible(true);
    }

    private Object[][] obtenerDatosCarrito() {
        ArrayList<Caja.ItemCarrito> carritoItems = caja.getCarrito();
        Object[][] datosCarrito = new Object[carritoItems.size()][4];
        for (int i = 0; i < carritoItems.size(); i++) {
            Caja.ItemCarrito item = carritoItems.get(i);
            datosCarrito[i][0] = item.getProducto().getNombre();
            datosCarrito[i][1] = item.getProducto().getPrecio();
            datosCarrito[i][2] = item.getCantidad();
            datosCarrito[i][3] = item.getProducto().getPrecio() * item.getCantidad();
        }
        return datosCarrito;
    }

    private double calcularTotalCarrito() {
        return caja.verTotalDelCarrito();
    }

    private void modificarCantidadEnCarrito() {
        String nombreProducto = JOptionPane.showInputDialog("Ingrese el nombre del producto a modificar:");
        Producto producto = inventario.buscarProducto(nombreProducto);

        if (producto != null) {
            int nuevaCantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad:"));
            caja.modificarCantidadProducto(producto, nuevaCantidad);
            JOptionPane.showMessageDialog(null, "Cantidad de " + producto.getNombre() + " modificada a " + nuevaCantidad);
        } else {
            JOptionPane.showMessageDialog(null, "Producto no encontrado");
        }
    }

    private void imprimirTicket() {
        caja.imprimirTicket();
        JOptionPane.showMessageDialog(null, "Ticket impreso con éxito");
        inventario.modificarCSV("a.csv");
        caja.comprar();
    }

    private void agregarProductoAlCarrito() {
        JComboBox<String> listaProductos = new JComboBox<>(obtenerNombresProductos());
        JTextField cantidadField = new JTextField();

        Object[] message = {
                "Seleccione el producto a agregar al carrito:", listaProductos,
                "Ingrese la cantidad:", cantidadField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Agregar al Carrito", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String nombreProducto = (String) listaProductos.getSelectedItem();
            Producto producto = inventario.buscarProducto(nombreProducto);

            if (producto != null) {
                int cantidad;
                try {
                    cantidad = Integer.parseInt(cantidadField.getText());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Ingrese una cantidad válida.");
                    return;
                }

                if (cantidad > 0 && cantidad <= producto.getCantidadDisponible()) {
                    caja.agregarProductoAlCarrito(producto);
                    caja.modificarCantidadProducto(producto, cantidad);
                    JOptionPane.showMessageDialog(null, cantidad + " unidades de " + producto.getNombre() + " agregadas al carrito");
                } else {
                    JOptionPane.showMessageDialog(null, "Cantidad no válida");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado");
            }
        }
    }

    private String[] obtenerNombresProductos() {
        String[] nombresProductos = new String[inventario.productos.size()];

        for (int i = 0; i < inventario.productos.size(); i++) {
            nombresProductos[i] = inventario.productos.get(i).getNombre();
        }

        return nombresProductos;
    }

    private void manejarInventario() {
        JFrame inventarioFrame = new JFrame("Menú Inventario");
        JPanel inventarioPanel = new JPanel(new GridLayout(3, 3, 10, 10));

        String[] opcionesInventario = {
                "Modificar producto", "Eliminar producto", "Agregar producto",
                "Mostrar inventario", "Modificar CSV", "Modificar cantidad del producto", "Buscar Producto"
        };

        for (String opcion : opcionesInventario) {
            JButton button = crearBoton(opcion, e -> handleInventarioOption(opcion));
            inventarioPanel.add(button);
        }

        configurarVentana(inventarioFrame, inventarioPanel, 450, 300);
    }

    private void handleInventarioOption(String opcion) {
        switch (opcion) {
            case "Modificar producto":
                modificarProducto();
                break;
            case "Eliminar producto":
                eliminarProducto();
                break;
            case "Agregar producto":
                agregarProducto();
                break;
            case "Mostrar inventario":
                mostrarInventario();
                break;
            case "Modificar CSV":
                modificarCSV();
                break;
            case "Modificar cantidad del producto":
                modificarCantidadProducto();
                break;
            case "Buscar Producto":
                buscarProducto();
                break;
            default:
                break;
        }
    }

    private void modificarProducto() {
        String nombreProducto = JOptionPane.showInputDialog("Ingrese el nombre del producto a modificar:");
        Producto producto = inventario.buscarProducto(nombreProducto);

        if (producto != null) {
            JFrame modificarFrame = new JFrame("Modificar Producto");
            JPanel modificarPanel = new JPanel(new GridLayout(6, 2, 10, 10));

            JTextField nombreField = new JTextField(producto.getNombre());
            JTextField precioField = new JTextField(String.valueOf(producto.getPrecio()));
            JTextField cantidadField = new JTextField(String.valueOf(producto.getCantidadDisponible()));
            JTextField codigoUnicoField = new JTextField(producto.getCodigoUnico());
            JTextField restriccionEdadField = new JTextField(String.valueOf(producto.getRestriccionEdad()));

            JButton modificarBoton = crearBoton("Modificar", e -> {
                String nuevoNombre = nombreField.getText();
                double nuevoPrecio = Double.parseDouble(precioField.getText());
                int nuevaCantidad = Integer.parseInt(cantidadField.getText());
                String nuevoCodigoUnico = codigoUnicoField.getText();
                int nuevaRestriccionEdad = Integer.parseInt(restriccionEdadField.getText());

                Producto nuevoProducto = new Producto(nuevoNombre, nuevoPrecio, nuevaCantidad, nuevoCodigoUnico, nuevaRestriccionEdad);
                inventario.modificarProducto(nombreProducto, nuevoProducto);
                mostrarInventario();
                modificarFrame.dispose();
            });

            JButton cancelarBoton = crearBoton("Cancelar", e -> modificarFrame.dispose());

            modificarPanel.add(new JLabel("Nombre:"));
            modificarPanel.add(nombreField);
            modificarPanel.add(new JLabel("Precio:"));
            modificarPanel.add(precioField);
            modificarPanel.add(new JLabel("Cantidad Disponible:"));
            modificarPanel.add(cantidadField);
            modificarPanel.add(new JLabel("Código Único:"));
            modificarPanel.add(codigoUnicoField);
            modificarPanel.add(new JLabel("Restricción Edad:"));
            modificarPanel.add(restriccionEdadField);
            modificarPanel.add(modificarBoton);
            modificarPanel.add(cancelarBoton);

            configurarVentana(modificarFrame, modificarPanel, 400, 300);
        } else {
            JOptionPane.showMessageDialog(null, "Producto no encontrado");
        }
    }

    private void eliminarProducto() {
        JFrame eliminarFrame = new JFrame("Eliminar producto");
        JPanel eliminarPanel = new JPanel(new GridLayout(2, 1));

        JComboBox<String> listaProductos = new JComboBox<>(obtenerNombresProductos());

        JButton eliminarBoton = crearBoton("Eliminar", e -> {
            String nombreProducto = (String) listaProductos.getSelectedItem();
            inventario.eliminarProducto(nombreProducto);
            mostrarInventario();
            eliminarFrame.dispose();
        });

        JButton cancelarBoton = crearBoton("Cancelar", e -> eliminarFrame.dispose());

        eliminarPanel.add(new JLabel("Seleccione el producto a eliminar:"));
        eliminarPanel.add(listaProductos);
        eliminarPanel.add(eliminarBoton);
        eliminarPanel.add(cancelarBoton);

        configurarVentana(eliminarFrame, eliminarPanel, 300, 150);
    }

    private void agregarProducto() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del nuevo producto:");
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del nuevo producto:"));
        int cantidadDisponible = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad disponible del nuevo producto:"));
        String codigoUnico = JOptionPane.showInputDialog("Ingrese el código único del nuevo producto:");
        int restriccion_edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la restricción de edad del nuevo producto:"));

        String tipoProducto = seleccionarTipoProducto();

        switch (tipoProducto) {
            case "Alcoholica":
                inventario.agregarProducto(new Alcoholica(nombre, precio, cantidadDisponible, codigoUnico, restriccion_edad));
                break;
            case "Bebida":
                inventario.agregarProducto(new Bebida(nombre, precio, cantidadDisponible, codigoUnico, restriccion_edad));
                break;
            case "Comida":
                inventario.agregarProducto(new Comida(nombre, precio, cantidadDisponible, codigoUnico, restriccion_edad));
                break;
            case "CuidadoPersonal":
                inventario.agregarProducto(new CuidadoPersonal(nombre, precio, cantidadDisponible, codigoUnico, restriccion_edad));
                break;
            default:
        }

        mostrarInventario();
    }

    private String seleccionarTipoProducto() {
        String[] opcionesTipos = {"Alcoholica", "Bebida", "Comida", "CuidadoPersonal"};
        JComboBox<String> listaTipos = new JComboBox<>(opcionesTipos);
        JOptionPane.showMessageDialog(null, listaTipos, "Selecciona el tipo de producto", JOptionPane.QUESTION_MESSAGE);
        return (String) listaTipos.getSelectedItem();
    }

    private void mostrarInventario() {
        JFrame inventarioFrame = new JFrame("Inventario");
        JPanel inventarioPanel = new JPanel(new BorderLayout());
        Object[][] datosInventario = inventario.obtenerDatosInventario();
        String[] columnas = {"Tipo", "Nombre", "Precio", "Cantidad", "Código Único", "Restricción Edad"};
        JTable tablaInventario = new JTable(new DefaultTableModel(datosInventario, columnas));
        JScrollPane scrollPane = new JScrollPane(tablaInventario);

        inventarioPanel.add(scrollPane, BorderLayout.CENTER);
        inventarioFrame.add(inventarioPanel);
        inventarioFrame.setSize(600, 400);
        inventarioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inventarioFrame.setLocationRelativeTo(null);
        inventarioFrame.setVisible(true);
    }

    private void modificarCSV() {
        inventario.modificarCSV("a.csv");
    }

    private void modificarCantidadProducto() {
        String nombreProducto = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
        int nuevaCantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad:"));
        Producto producto = inventario.buscarProducto(nombreProducto);

        if (producto != null) {
            producto.setCantidadDisponible(nuevaCantidad);
            mostrarInventario();
        } else {
            JOptionPane.showMessageDialog(null, "Producto no encontrado");
        }
    }

    private void buscarProducto() {
        String nombreProducto = JOptionPane.showInputDialog("Ingrese el nombre del producto a buscar:");
        Producto producto = inventario.buscarProducto(nombreProducto);

        if (producto != null) {
            JOptionPane.showMessageDialog(null, "Producto encontrado:\n" + producto.ListarInformacion());
        } else {
            JOptionPane.showMessageDialog(null, "Producto no encontrado");
        }
    }




    private void configurarVentana(JFrame frame, JPanel panel, int ancho, int alto) {
        frame.add(panel);
        frame.setSize(ancho, alto);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
