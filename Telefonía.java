public class Telefonía extends Producto{
    // Atributos
    public String Compañía;
    public float Comisión;

    // Constructores
    public Telefonía() {}

    public Telefonía(String nombre, float precio, String proveedor, String lote, int restricciónEdad, String compañía,
            float comisión) {
        super(nombre, precio, proveedor, lote, restricciónEdad);
        Compañía = compañía;
        Comisión = comisión;
    }

    // Métodos
    @Override
    public String ListarInformación() {
        return super.ListarInformación() + "\nCompañía: " + Compañía +
        "\nComisión: " + Comisión;
    }

    @Override
    public String Serializar() {
        return super.Serializar() + Se + Compañía + Se + Comisión;
    }

    @Override
    public String[] DeSerializar(String[] Datos_DeSerializados) {
        super.DeSerializar(Datos_DeSerializados);

        this.Compañía = Datos_DeSerializados[6];
        this.Comisión = Float.parseFloat(Datos_DeSerializados[7]);

        return Datos_DeSerializados;
    }
}