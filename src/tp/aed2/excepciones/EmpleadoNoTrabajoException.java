package tp.aed2.excepciones;

public class EmpleadoNoTrabajoException extends Exception {

    public EmpleadoNoTrabajoException() {
        super("El empleado no trabajó en el mes");
    }

}
