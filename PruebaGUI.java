import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PruebaGUI extends JFrame {
    private Inventario inventario;

    private JTable tablaInventario;
    private JButton mostrarButton;
    private JButton modificarCantidadButton;

    public PruebaGUI() {
        // Configurar la ventana
        setTitle("Prueba de Inventario GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        // Crear el inventario y cargar los datos
        inventario = new Inventario();
        inventario.cargarInventario("a.csv");

        // Crear la tabla con los datos y las columnas
        Object[][] datosInventario = inventario.obtenerDatosInventario();
        String[] columnas = {"Tipo", "Nombre", "Precio", "Cantidad", "Código Único", "Restricción Edad"};

        DefaultTableModel model = new DefaultTableModel(datosInventario, columnas);
        tablaInventario = new JTable(model);

        // Agregar la tabla a un JScrollPane para permitir desplazamiento
        JScrollPane scrollPane = new JScrollPane(tablaInventario);

        // Crear botones para acciones
        mostrarButton = new JButton("Mostrar Inventario");
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarInventario();
            }
        });

        modificarCantidadButton = new JButton("Modificar Cantidad");
        modificarCantidadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarCantidadProducto();
            }
        });

        // Agregar componentes a la ventana
        JPanel panelBotones = new JPanel();
        panelBotones.add(mostrarButton);
        panelBotones.add(modificarCantidadButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelBotones, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void mostrarInventario() {
        // Actualizar los datos de la tabla
        Object[][] datosInventario = inventario.obtenerDatosInventario();
        DefaultTableModel model = (DefaultTableModel) tablaInventario.getModel();
        model.setDataVector(datosInventario, new String[]{"Tipo", "Nombre", "Precio", "Cantidad", "Código Único", "Restricción Edad"});
    }

    private void modificarCantidadProducto() {
        // Implementar lógica para modificar cantidad aquí
        // Puedes abrir un cuadro de diálogo para ingresar el producto y la nueva cantidad
        // y luego actualizar el inventario y la tabla.
        // Ejemplo:
        // String nombreProducto = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
        // int nuevaCantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad:"));
        // inventario.modificarCantidadProducto(nombreProducto, nuevaCantidad);
        // mostrarInventario();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PruebaGUI app = new PruebaGUI();
            app.setVisible(true);
        });
    }
}
