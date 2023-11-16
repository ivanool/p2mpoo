
import javax.swing.JOptionPane;
public class Prueba3{

    public static void main(String[] args) {
        String opcionPrincipal,opcionVentas, opcionInventario;
        String[] OPCIONES ={
            "Venta", "Inventario"
        };
        String[] OpcionesVentas = {"Agregar producto", "Borrar producto", "ver el total", 
            "Imprimir Ticket", "Listar los objetos", "Vaciar", "Modificar cantidad de un producto"};
        String[] OpcionesInventario = {"Cargar", "Modificar producto", "Buscar producto", "Eliminar producto",
            "Agregar producto", "mostrar inventario", "Modificar CVS", "Modificar cantidad del producto", "Buscar Producto"};
        int  cantidadDisponiblePrincipal2,restriccion_edadPrincipal2, nuevaCantidadPrincipal2;
        double  precioPrincipal2;

        Object Principal = JOptionPane.showInputDialog(null,"Escoja la opción deseada: ", "Menú principal", JOptionPane.DEFAULT_OPTION, null, OPCIONES, OPCIONES[0]);
        opcionPrincipal = (String) Principal;
        switch (opcionPrincipal){
            case "Venta" :
                Object MenuVentas = JOptionPane.showInputDialog(null,"¿Qué desea hacer en el carrito?", "Menú Compra", JOptionPane.DEFAULT_OPTION, null, OpcionesVentas, OpcionesVentas[0]);
                opcionVentas = (String) MenuVentas;
                switch(opcionVentas){
                    
                    case "Agregar producto":
                        //Caja caja = new Caja(inventario);
                        String Nombreproducto = JOptionPane.showInputDialog(null,"¿Qué producto desea agregar?","Agregar producto-Caja", JOptionPane.QUESTION_MESSAGE);
                        //caja.agregarProductoAlCarrito(Producto producto);
                        break;
                    case "Borrar producto":
                    String producto = JOptionPane.showInputDialog(null,"¿Qué producto desea borrar?","Borrar producto-Caja", JOptionPane.QUESTION_MESSAGE);
                        //caja.borrarProductoDelCarrito(Producto producto);
                        break;
                    case "ver el total":
                        //caja.verTotalDelCarrito();
                        break;
                    case "Imprimir Ticket":
                        //caja.imprimirTicket();
                        break;
                    case "Listar los objetos":
                        //caja.listarObjetosEnCarrito();
                        break;
                    case"Vaciar":
                        //caja.vaciarCarrito();
                        break;
                    case "Modificar cantidad de un producto":
                        String nombreProducto = JOptionPane.showInputDialog(null, "¿Qué producto desea modificar?", "Modificar cantidad-Caja",JOptionPane.QUESTION_MESSAGE);
                        String nuevaCantidadProducto = JOptionPane.showInputDialog(null,"¿Cuál sería la nueva cantidad?","Modificar cantidad-Caja", JOptionPane.QUESTION_MESSAGE);
                        //caja.modificarCantidadProducto(Producto product, nuevaCantidad);
                        break;
                    default:
                } 
            case "Inventario" :
                Object MenuInventario = JOptionPane.showInputDialog(null,"¿Qué desea hacer en el menu´de inventario?", "Menú Inventario", JOptionPane.DEFAULT_OPTION, null, OpcionesInventario, OpcionesInventario[0]);
                opcionInventario = (String)MenuInventario;
                switch (opcionInventario){
                    case "Cargar":
                        Inventario inventario = new Inventario();
                        //inventario.cargarInventario(prueba.csv);
                        break;
                    case "Modificar producto":
                    String nombrePrincipalMp = JOptionPane.showInputDialog(null,"¿Qué producto desea modificar?","Modificar producto-Inventario", JOptionPane.QUESTION_MESSAGE);
                        //prueba5.modificarProducto(nombrePrincipal, nuevoProducto);
                        break;
                    case "Eliminar producto":
                    String nombrePrincipalE = JOptionPane.showInputDialog(null,"¿Qué producto desea eliminar?","Eliminar producto-Inventario", JOptionPane.QUESTION_MESSAGE);
                        //prueba5.eliminarProducto(nombrePrincipalE);
                        break;
                    case "Agregar producto": //PASO 1
                        String precioPrincipal = JOptionPane.showInputDialog(null,"Precio del producto ","Agregar producto-Inventario", JOptionPane.QUESTION_MESSAGE);
                        precioPrincipal2 = Double.parseDouble(precioPrincipal);
                        String nombrePrincipal = JOptionPane.showInputDialog(null,"Nombre del producto ","Agregar producto-Inventario", JOptionPane.QUESTION_MESSAGE);
                        String cantidadDisponiblePrincipal = JOptionPane.showInputDialog(null,"Cantidad disponible ","Agregar producto-Inventario", JOptionPane.QUESTION_MESSAGE);
                        cantidadDisponiblePrincipal2 = Integer.parseInt(cantidadDisponiblePrincipal);
                        String codigoUnicoPrincipal = JOptionPane.showInputDialog(null,"Código único ","Agregar producto-Inventario", JOptionPane.QUESTION_MESSAGE);
                        String restriccion_edadPrincipal = JOptionPane.showInputDialog(null,"Edad","Agregar producto-Inventario", JOptionPane.QUESTION_MESSAGE);
                        restriccion_edadPrincipal2 = Integer.parseInt(restriccion_edadPrincipal);
                        
                        Producto producto = new Producto(nombrePrincipal, precioPrincipal2, cantidadDisponiblePrincipal2, codigoUnicoPrincipal, restriccion_edadPrincipal2 );
                        Inventario prueba5 = new Inventario();
                        prueba5.agregarProducto(producto);
                        break;

                    case "mostrar inventario":
                        //inventario.mostrarInventario();
                        break;
                    case "Modificar CSV":
                        //inventario.modificarCSV(csvFileName);
                        break;
                    case "Modificar cantidad de un producto":
                        String nombrePrincipalM = JOptionPane.showInputDialog(null, "¿Qué producto desea modificar?", "Modificar cantidad-Inventario",JOptionPane.QUESTION_MESSAGE);
                        String nuevaCantidadPrincipal = JOptionPane.showInputDialog(null,"¿Cuál sería la nueva cantidad?","Modificar cantidad-Inventario", JOptionPane.QUESTION_MESSAGE);
                        nuevaCantidadPrincipal2 = Integer.parseInt(nuevaCantidadPrincipal);
                        //prueba5.modificarCantidadProducto(nombrePrincipal,nuevaCantidadPrincipal2);
                        break;
                    case "Buscar Producto":
                        String nombreBuscar = JOptionPane.showInputDialog(null,"¿Qué producto desea buscar?", "Buscar producto-Inventario", JOptionPane.QUESTION_MESSAGE);
                        //prueba5.buscarProducto(nombreBuscar);
                        break;
                    default:
                }
                
                break;
            default:
     }
    }
    }




