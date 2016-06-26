package tp.aed2.empleados;

import org.joda.time.DateTime;
import tp.aed2.fichaje.Fichaje;
import tp.aed2.pacientes.PacienteMenor;
import java.math.BigDecimal;

public class Administrativo implements IEmpleado {

    //Constantes
    private static final BigDecimal SUELDO = new BigDecimal(7000);

    //Atributos
    private String id;
    private BigDecimal sueldoBasico;
    private BigDecimal cotizaciones;
    private Fichaje fichaje;

    //Constructor
    public Administrativo(String id) {
        this.id = id;
        this.sueldoBasico = SUELDO;
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

    public BigDecimal getSueldoBasico() {
        return this.sueldoBasico;
    }

    public String getId() {
        return id;
    }

    //Métodos
    public BigDecimal cotizarConsulta(PacienteMenor paciente, BigDecimal valor) {
        System.out.println(this + " cotizando consulta.");
        this.cotizaciones = this.cotizaciones.add(BigDecimal.ONE);
        return valor.multiply(new BigDecimal(paciente.getEdad()));
    }

    public void ficharEntrada(DateTime fecha) {
        this.fichaje.ficharEntrada(fecha);
    }

    public void ficharSalida(DateTime fecha) {
        this.fichaje.ficharSalida(fecha);
    }

    public String toString() {
        return "[Administrativo: "+this.getId()+"]";
    }
}
