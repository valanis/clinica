package tp.aed2.obras_sociales;

import java.math.BigDecimal;

public class NullObraSocial extends ObraSocial {

    //Constantes
    private static final String NAME = "NO_OS";
    private static final BigDecimal CERO = BigDecimal.ZERO;

    //Constructor
    //TODO: hacerlo singleton
    public NullObraSocial() {
        super(NAME, CERO);
    }

    //Métodos
    @Override
    public Boolean cubre() {
        return false;
    }

    public static String getNullId() { return NAME; }

    public static BigDecimal getNullPorcentaje() { return CERO; }

}
