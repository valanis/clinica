package tp.aed2.obras_sociales;

import java.math.BigDecimal;

public class NullObraSocial extends ObraSocial {

    //TODO: hacerlo singleton
    public NullObraSocial() {
        super("NO_OS", BigDecimal.ZERO);
    }

    @Override
    public Boolean cubre() {
        return false;
    }

}
