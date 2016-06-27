package tp.aed2.obras_sociales;

import java.math.BigDecimal;

public class ObraSocial implements IObraSocial {

    //Atributps
    private String id;
    private BigDecimal porcentaje;

    //Constructor

    /**
     *
     * @param id de la nueva obra social
     * @param porcentaje de descuento que se le hace por la consulta
     */
    public ObraSocial(String id, BigDecimal porcentaje) {
        this.id = id;
        this.porcentaje = porcentaje;
    }

    //Getters

    /**
     *
     * @return devuelve el ID de la obra social
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return Devuelve el porcentaje de cobertura de la obra social
     */
    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    //Métodos

    /**
     *
     * @return Devuelve true si la clínica opera con la obra social
     */
    public Boolean cubre() {
        return true;
    }

    /**
     *
     * @return Devuelve un string que contiene información sobre el ID de la obra social y el porcentaje de descuento de la misma
     */
    @Override
    public String toString() {
        return "[ID: "+this.getId()+", porcentaje: "+this.getPorcentaje()+"]";
    }

}
