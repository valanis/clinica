package tp.aed2.excepciones;

public class OpcionSeleccionadaIncorrectaException extends Exception {

    public OpcionSeleccionadaIncorrectaException(Integer opcion) { super("La opcion " +opcion+ " es inválida. Elija nuevamente.");}
}
