package tp.aed2.excepciones;

public class NoHayAdministrativoException extends Exception {

    public NoHayAdministrativoException() {
        super("La clínica no tiene empleado administrativo.");
    }

}
