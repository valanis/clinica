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

    /**
     *
     * @return Devuelve una instancia de NULLObraSocial, si no existe una, la crea
     */
    public static NullObraSocial getInstance() {
        if(instance == null) {
            instance = new NullObraSocial();
        }
        return instance;
    }

    /**
     *
     * @return Devuelve "NO_OS"
     */
    public String getId() {
        return NAME;
    }

    /**
     *
     * @return Devuelve Cero, que es el porcentaje que se le descuenta a los pacientes por la consulta
     */
    public BigDecimal getPorcentaje() {
        return CERO;
    }

    /**
     *
     * @return Devuelve siempre false porque la clínica sólo cubre obras sociales
     */
    public Boolean cubre() {
        return false;
    }

    /**
     *
     * @return Devuelve el String "Sin Cobertura"
     */
    @Override
    public String toString() {
        return "[Sin Cobertura]";
    }

}
