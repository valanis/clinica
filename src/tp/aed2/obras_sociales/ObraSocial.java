package tp.aed2.obras_sociales;

import java.math.BigDecimal;

public class ObraSocial implements IObraSocial {

    //Atributps
    private String id;
    private BigDecimal porcentaje;

    //Constructor
    public ObraSocial(String id, BigDecimal porcentaje) {
        this.id = id;
        this.porcentaje = porcentaje;
    }

    //Getters
    public String getId() {
        return id;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    //Métodos
    public Boolean cubre() {
        return true;
    }

    @Override
    public String toString() {
        return "[ID: "+this.getId()+", porcentaje: "+this.getPorcentaje()+"]";
    }

}
