package tp.aed2.excepciones;

public class SueldoNegativoException extends Exception {

    public SueldoNegativoException() {
        super("El sueldo calculado para el empleado, con sus respectivos descuentos quedó en negativo.");
    }

}
