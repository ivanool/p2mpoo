public class Bebida_Alcohólica extends Bebida {
    // Atributo
    public float PorcentajeAlcohol;

    // Constructores
    public Bebida_Alcohólica() {}

    public Bebida_Alcohólica(String nombre, float precio, String proveedor, String lote, int restricciónEdad,
            String caducidad, float temperaturaAlmacenamiento, float volúmen, float porcentajeAlcohol) {
        super(nombre, precio, proveedor, lote, restricciónEdad, caducidad, temperaturaAlmacenamiento, volúmen);
        PorcentajeAlcohol = porcentajeAlcohol;
    }



    // Métodos
    @Override
    public String ListarInformación() {
        return super.ListarInformación() + "\nPorcentaje de alcohol: " + PorcentajeAlcohol;
    }

    @Override
    public String Serializar() {
        return super.Serializar() + Se + PorcentajeAlcohol;
    }

    @Override
    public String[] DeSerializar(String[] Datos_DeSerializados) {
        super.DeSerializar(Datos_DeSerializados);

        this.PorcentajeAlcohol = Float.parseFloat(Datos_DeSerializados[9]);

        return Datos_DeSerializados;
    }
}