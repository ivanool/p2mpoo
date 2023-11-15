public class Producto {
    protected String nombre;
    protected double precio;
    protected String proveedor;
    protected String codigoUnico;
    protected int restriccion_edad;
    protected int cantidadDisponible;

    public Producto() {}

    public Producto(String nombre, double precio, int cantidadDisponible, String codigoUnico, int restriccion_edad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
        this.codigoUnico = codigoUnico;
        this.restriccion_edad = restriccion_edad;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }
    public String getTipo(){
        return this.getClass().getSimpleName();
    }
    public String getNombre() {
        return nombre;
    }
    public double getPrecio() {
        return precio;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public int getRestriccionEdad() {
        return restriccion_edad;
    }

    public String getTipoProducto() {
        return this.getClass().getSimpleName();
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public String ListarInformacion() {
        return "Nombre: " + nombre + "\nPrecio: " + precio + "\nCantidad Disponible: " + cantidadDisponible + "\nCódigo: " + codigoUnico + "\nRestricción de Edad: " + restriccion_edad;
    }

    public String toCSV() {
        return this.getClass().getSimpleName() +","+ nombre + "," + precio + "," + cantidadDisponible + "," + codigoUnico + "," + restriccion_edad;
    }
  
}
