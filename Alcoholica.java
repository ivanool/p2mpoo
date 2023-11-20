public class Alcoholica extends Producto{

    public Alcoholica() {

    }

    public Alcoholica(String nombre, double precio, int cantidadDisponible, String codigoUnico, int restriccion_edad) {
        super(nombre, precio, cantidadDisponible, codigoUnico, restriccion_edad);
    }

    public static void restriccion_edad(int restriccion_edad) {
        if (restriccion_edad < 18) {
            System.out.println("No puede comprar este producto");
        } else {
            System.out.println("Puede comprar este producto");
        }
    }
}