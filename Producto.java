public class Producto {
    // Atributos
    public final String Se = ";";
    protected String Nombre;
    protected float Precio;
    protected String Proveedor;
    protected String Lote;
    protected int RestricciónEdad;
    
    // Constructores
    public Producto() {}

    public Producto(String nombre, float precio, String proveedor, String lote, int restricciónEdad) {
        Nombre = nombre;
        Precio = precio;
        Proveedor = proveedor;
        Lote = lote;
        RestricciónEdad = restricciónEdad;
    }


    // Métodos
    public String ListarInformación() {
        return "Nombre: " + Nombre + "\nPrecio: " + Precio + "\nProveedor: " + Proveedor +
        "\nLote: " + Lote + "\nRestricción de edad: " + RestricciónEdad;
    }

    public String Serializar() {
        return this.getClass().toString() + Se + Nombre + Se + Precio + Se + 
        Proveedor + Se + Lote + Se + RestricciónEdad;
    }

    public String[] DeSerializar(String[] Datos_DeSerializados){      
        this.Nombre = Datos_DeSerializados[1];
        this.Precio = Float.parseFloat(Datos_DeSerializados[2]);
        this.Proveedor = Datos_DeSerializados[3];
        this.Lote = Datos_DeSerializados[4];
        this.RestricciónEdad = Integer.parseInt(Datos_DeSerializados[5]);

        return Datos_DeSerializados;
    }
}