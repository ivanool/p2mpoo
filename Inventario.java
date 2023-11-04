import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Inventario {
    // Atributos
    ArrayList<String> Llaves = new ArrayList<String>();
    ArrayList<ArrayList<Producto>> Puertas = new ArrayList<ArrayList<Producto>>();

    // Constructores
    public Inventario() {}

    // Métodos
    public void AgregarProducto(Producto Product) {
        String Clase = Product.getClass().toString();

        if(this.Llaves.contains(Clase)) {
            int ÍndiceClase = Llaves.indexOf(Clase);
            this.Puertas.get(ÍndiceClase).add(Product);
        } else {
            ArrayList<Producto> NuevaClase = new ArrayList<Producto>();

            this.Puertas.add(NuevaClase);
            this.Llaves.add(Clase);

            AgregarProducto(Product);
        }
    }

    public boolean EliminarProducto (Producto Product) {
        String Clase = Product.getClass().toString();

        if(this.Llaves.contains(Clase)){
            int ÍndiceClase = Llaves.indexOf(Clase);

            return this.Puertas.get(ÍndiceClase).remove(Product);
        } else {
            return false;
        }
    }

    public void GuardarInventario(String Nombre) throws IOException {
        BufferedWriter Escritor = new BufferedWriter(new FileWriter(Nombre));
        for(int i = 0; i < this.Puertas.size(); i++){
            for(int j = 0; j < this.Puertas.get(i).size(); j++){
                Escritor.write(this.Puertas.get(i).get(j).Serializar() + "\n");
            }
        }
        Escritor.close();
    }

    public void CargarInventario(String Nombre) throws IOException {
        BufferedReader Lector = new BufferedReader(new FileReader(Nombre));
        Producto Product = new Producto();
        String Línea;
        while((Línea = Lector.readLine()) != null){
            String[] Datos_DeSerializados = Línea.split(Product.Se);
            Product = this.TipoClase(Datos_DeSerializados[0]);
            Product.DeSerializar(Datos_DeSerializados);
            this.AgregarProducto(Product);
        }
        Lector.close();
    }

    private Producto TipoClase(String NombreClase) {
        switch (NombreClase) {
            case "class Bebida_Alcohólica":
                return new Bebida_Alcohólica();
            case "class Bebida":
                return new Bebida();
            case "class Comestible":
                return new Comestible();
            case "class Producto":
                return new Producto();
            case "class Telefonía":
                return new Telefonía();
            default:
                return new Producto();
        }
    }
}