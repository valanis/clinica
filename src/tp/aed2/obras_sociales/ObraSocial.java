package tp.aed2.obras_sociales;

import java.math.BigDecimal;

public class ObraSocial {

    //Atributps
    private String id;
    private BigDecimal porcentajeDescuento;

    //Constructor
    public ObraSocial(String id, BigDecimal porcentaje) {
        this.id = id;
        this.porcentajeDescuento = porcentaje;
    }

    //Getters
    public String getId() {
        return id;
    }

    public BigDecimal getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    //Métodos
    @Override
    public String toString() {
        return "[ID: "+this.getId()+", porcentaje: "+this.getPorcentajeDescuento()+"]";
    }

    public Boolean cubre() {
        return true;
    }

}
