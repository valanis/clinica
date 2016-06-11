package tp.aed2.obras_sociales;

public class ObraSocial {

    private String id;
    private Integer porcentajeDescuento;

    public ObraSocial(String id, Integer porcentaje) {
        this.id = id;
        this.porcentajeDescuento = porcentaje;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(Integer porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

}
