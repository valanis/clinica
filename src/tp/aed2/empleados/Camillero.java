package tp.aed2.empleados;

import tp.aed2.sat.Emergencia;
import tp.aed2.sat.IEmergenciaSuscriptor;

import java.math.BigDecimal;

public class Camillero implements IEmpleado, IEmergenciaSuscriptor {

    //Constantes
    private static final BigDecimal SUELDO_BASICO = new BigDecimal(10000);

    //Atributos
    BigDecimal sueldoBasico;

    //Constructor
    public Camillero() {
        this.sueldoBasico = SUELDO_BASICO;
    }

    //Getters
    public BigDecimal getSueldoBasico() {
        return this.sueldoBasico;
    }

    //Métodos
    @Override
    public void update(Emergencia emergencia) {

    }
}
