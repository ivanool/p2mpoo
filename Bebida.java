public class Bebida extends Comestible{
    // Atributos
    public float Volúmen; // [L]

    // Constructores
    public Bebida(){}

    public Bebida(String nombre, float precio, String proveedor, String lote, int restricciónEdad,
            String caducidad, float temperaturaAlmacenamiento, float volúmen) {
        super(nombre, precio, proveedor, lote, restricciónEdad, caducidad, temperaturaAlmacenamiento);
        Volúmen = volúmen;
    }

    // Métodos
    @Override
    public String ListarInformación() {
        return super.ListarInformación() + "\nVolúmen: " + Volúmen;
    }

    @Override
    public String Serializar() {
        return super.Serializar() + Se + Volúmen;
    }

    @Override
    public String[] DeSerializar(String[] Datos_DeSerializados) {
        super.DeSerializar(Datos_DeSerializados);

        this.Volúmen = Float.parseFloat(Datos_DeSerializados[8]);

        return Datos_DeSerializados;
    }
}