public class Comestible extends Producto {
    // Atributos
    public String Caducidad;
    public float TemperaturaAlmacenamiento; // [°C]

    // Constructores
    public Comestible() {}

    public Comestible(String nombre, float precio, String proveedor, String lote, int restricciónEdad,
            String caducidad, float temperaturaAlmacenamiento) {
        super(nombre, precio, proveedor, lote, restricciónEdad);
        Caducidad = caducidad;
        TemperaturaAlmacenamiento = temperaturaAlmacenamiento;
    }

    // Métodos
    @Override
    public String ListarInformación() {
        return super.ListarInformación() + "\nCaducidad: " + Caducidad +
        "\nTemperatura de almacenamiento: " + TemperaturaAlmacenamiento;
    }

    @Override
    public String Serializar() {
        return super.Serializar() + Se + Caducidad + Se + TemperaturaAlmacenamiento;
    }

    @Override
    public String[] DeSerializar(String[] Datos_DeSerializados) {
        super.DeSerializar(Datos_DeSerializados);

        this.Caducidad = Datos_DeSerializados[6];
        this.TemperaturaAlmacenamiento = Float.parseFloat(Datos_DeSerializados[7]);

        return Datos_DeSerializados;
    }
}