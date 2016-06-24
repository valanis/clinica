package tp.aed2.empleados;

import org.joda.time.DateTime;
import tp.aed2.fichaje.Fichaje;
import tp.aed2.pacientes.PacienteMenor;
import java.math.BigDecimal;

public class Administrativo extends Empleado {

    //Constantes
    private static final BigDecimal SUELDO = new BigDecimal(7000);

    //Atributos
    private BigDecimal cotizaciones;
    private Fichaje fichaje;

    //Constructores
    public Administrativo() {
        this.setSueldoBasico(SUELDO);
        this.cotizaciones = BigDecimal.ZERO;
        this.fichaje = new Fichaje();
    }

    public Administrativo(BigDecimal sueldo) {
        this.setSueldoBasico(sueldo);
        this.cotizaciones = BigDecimal.ZERO;
        this.fichaje = new Fichaje();
    }

    //Getters
    public BigDecimal getCotizaciones() {
        return cotizaciones;
    }

    public Fichaje getFichaje() {
        return this.fichaje;
    }

    //Métodos
    public BigDecimal cotizarConsulta(PacienteMenor paciente, BigDecimal valor) {
        this.cotizaciones = this.cotizaciones.add(BigDecimal.ONE);
        return valor.multiply(new BigDecimal(paciente.getEdad()));
    }

    public void ficharEntrada(DateTime fecha) {
        this.fichaje.ficharEntrada(fecha);
    }

    public void ficharSalida(DateTime fecha) {
        this.fichaje.ficharSalida(fecha);
    }


}
