package tp.aed2.empleados;

import java.math.BigDecimal;

public abstract class Empleado {

    private BigDecimal sueldoBasico;

    public BigDecimal getSueldoBasico() {
        return sueldoBasico;
    }

    public void setSueldoBasico(BigDecimal sueldoBasico) {
        this.sueldoBasico = sueldoBasico;
    }
}
