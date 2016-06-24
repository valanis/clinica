package tp.aed2.excepciones;

public class NoHayEmpleadoException extends Exception {

    public NoHayEmpleadoException(String tipoEmpleado) {

        super("La clínica no tiene empleado " + tipoEmpleado);

    }

}
