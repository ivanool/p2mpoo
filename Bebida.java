public class Bebida extends Producto{
    public Bebida(){

    }

    public Bebida(String nombre, double precio, int cantidadDisponible, String codigoUnico, int restriccion_edad){
        super(nombre, precio, cantidadDisponible, codigoUnico, restriccion_edad);
    }
    
}