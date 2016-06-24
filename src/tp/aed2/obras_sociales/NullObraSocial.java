package tp.aed2.obras_sociales;

import java.math.BigDecimal;

//Singleton
public class NullObraSocial implements IObraSocial  {

    //Constantes
    private static final String NAME = "NO_OS";
    private static final BigDecimal CERO = BigDecimal.ZERO;

    private static NullObraSocial instance;

    //Constructor
    private NullObraSocial() {}

    //Métodos
    public static NullObraSocial getInstance() {
        if(instance == null) {
            instance = new NullObraSocial();
        }
        return instance;
    }

    public String getId() {
        return NAME;
    }

    public BigDecimal getPorcentaje() {
        return CERO;
    }

    public Boolean cubre() {
        return false;
    }

}
