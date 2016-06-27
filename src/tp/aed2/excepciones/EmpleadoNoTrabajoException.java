package tp.aed2.excepciones;

public class EmpleadoNoTrabajoException extends Exception {

    /**
     * Excepción que va a indicar que el empleado no llevó a cabo las tareas que tiene asignadas durande el mes
     */
    public EmpleadoNoTrabajoException() {
        super("El empleado no trabajó en el mes");
    }

}
