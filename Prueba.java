public class Prueba {
    public static void main(String[] args){

        //Funcionalidades de inventario
        Inventario i = new Inventario();
        i.cargarInventario("a.csv");
        i.mostrarInventario();

        i.modificarCantidadProducto("Coca cola", 100);
        i.mostrarInventario();
        //i.eliminarProducto("Coca cola");
        System.out.println(i.buscarProducto("Coca cola"));

        i.modificarCSV("a.csv");

        //Funcionalidades de caja
        System.out.println("----- Funcionalidades de caja -----");
        Caja c = new Caja(i);
        i.buscarProducto("Coca cola");
        c.agregarProductoAlCarrito(i.buscarProducto("Coca cola"));
        System.out.println(c.verTotalDelCarrito());
        c.listarObjetosEnCarrito();
        c.vaciarCarrito();
        c.listarObjetosEnCarrito();
        c.agregarProductoAlCarrito(i.buscarProducto("Coca cola"));
        c.agregarProductoAlCarrito(i.buscarProducto("Hamburguesa"));
        c.modificarCantidadProducto(i.buscarProducto("Coca cola"), 15);
        c.imprimirTicket();

        i.mostrarInventario();

        lista 
    }
}