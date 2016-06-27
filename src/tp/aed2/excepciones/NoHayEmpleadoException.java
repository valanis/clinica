package tp.aed2.excepciones;

public class NoHayEmpleadoException extends Exception {

    /**
     *
     * @param tipoEmpleado Excepción que indica que no hay actualmente empleados disponibles en la clínica
     */
    public NoHayEmpleadoException(String tipoEmpleado) {

        super("La clínica no tiene empleado " + tipoEmpleado);

    }

}
