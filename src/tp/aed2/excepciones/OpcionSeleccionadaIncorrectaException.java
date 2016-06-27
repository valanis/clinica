package tp.aed2.excepciones;

public class OpcionSeleccionadaIncorrectaException extends Exception {

    /**
     *
     * @param opcion Se indica con esta excepción que el usuario de la UI ha ingresado una opción inválida en la nevagación del menú
     */
    public OpcionSeleccionadaIncorrectaException(Integer opcion) { super("La opcion " +opcion+ " es inválida. Elija nuevamente.");}
}
