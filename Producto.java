public class Producto {
    protected String nombre;
    protected float precio;
    protected String proveedor;
    protected String lote;
    protected int restricciónEdad;
    protected int cantidadDisponible;

    public Producto() {}

    public Producto(String nombre, float precio, String proveedor, String lote, int restricciónEdad, int cantidadDisponible) {
        this.nombre = nombre;
        this.precio = precio;
        this.proveedor = proveedor;
        this.lote = lote;
        this.restricciónEdad = restricciónEdad;
        this.cantidadDisponible = cantidadDisponible;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public String ListarInformación() {
        return "Nombre: " + nombre + "\nPrecio: " + precio + "\nProveedor: " + proveedor +
                "\nLote: " + lote + "\nRestricción de edad: " + restricciónEdad + "\nCantidad Disponible: " + cantidadDisponible;
    }

    public String toCSV() {
        return this.getClass().getSimpleName() + "," + nombre + "," + precio + "," +
                cantidadDisponible + "," + lote + "," + restricciónEdad;
    }

   
}
