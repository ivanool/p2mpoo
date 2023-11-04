import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException{
        /*Producto BA = new Bebida_Alcohólica("Coronita Extra 34 Botellas", 310,
         "Cervecería Modelo S.A. de C.V.", "E342a", 18, "24/01/2024",
         -5, (float)0.210, (float)0.045);
        Producto B = new Bebida("Coca-Cola vidrio", 14, "Femsa", "O408b",
         0, "31/10/2026", -2, (float)0.500);
        Producto C = new Comestible("Takis Fuego", (float)14.50,"Sabritas", "I521c",
         0, "17/05/2024", 30);
        Producto T = new Telefonía("Recarga 500 pesos", 500, "AT&T", "Q382u",
         18, "Telcel", 10);
        Producto P = new Producto("Cigarros Malboro", 70, "Cancer limited", "D135u", 
        18);*/

        Inventario Alm = new Inventario();
        Alm.CargarInventario("Salida.csv");

        /*Alm.AgregarProducto(BA);
        Alm.AgregarProducto(B);
        Alm.AgregarProducto(C);
        Alm.AgregarProducto(T);
        Alm.AgregarProducto(P);

        try {
            Alm.GuardarInventario("Salida.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        ListAll(Alm);

        /*System.out.println("\n\n-----\n");
        Alm.EliminarProducto(P);
        Alm.EliminarProducto(C);
        Alm.EliminarProducto(BA);
        
        ListAll(Alm);

        if(Alm.EliminarProducto(P)){
            System.out.println("Producto eliminado con éxito");
        } else {
            System.out.println("El producto no ha podido ser eliminado");
        }*/
    }

    static void ListAll(Inventario Alm){ // ¡BORRAR! Método temporal, solo para diagnosticar errores
        System.out.println("Número de categorías de producto: " + Alm.Puertas.size());
        for(int i = 0; i < Alm.Puertas.size(); i++){
            for(int j = 0; j < Alm.Puertas.get(i).size(); j++){
                System.out.println(Alm.Puertas.get(i).get(j).ListarInformación() + "\n");
            }
        }
    }
}
