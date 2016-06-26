package tp.aed2.viajes;

import org.joda.time.DateTime;

import java.math.BigDecimal;

import static org.joda.time.DateTimeConstants.SATURDAY;
import static org.joda.time.DateTimeConstants.SUNDAY;

public class Viaje {

    //Atributos
    private Integer pacientes;
    private BigDecimal kilometros;
    private DateTime fecha;

    public Viaje(Integer pacientes, BigDecimal kilometros, DateTime fecha){
        this.pacientes = pacientes;
        this.kilometros = kilometros;
        this.fecha = fecha;
    }

    //Getters
    public Integer getPacientes() {
        return this.pacientes;
    }

    public BigDecimal getKilometros() {
        return kilometros;
    }

    public DateTime getFecha() {
        return fecha;
    }

    //Métodos
    private Integer getCantidadDePacientes() {
        return this.pacientes;
    }

    public Boolean viajoMasDeUnPaciente() {
        return getCantidadDePacientes() > 1;
    }

    public Boolean fueFinDeSemana() {
        return fecha.getDayOfWeek() == SATURDAY || fecha.getDayOfWeek() == SUNDAY;
    }

    public String toString() {
        return "[Viaje] #Pacientes: " +this.getPacientes()+ " #KM: " +this.getKilometros()+ " Fecha: " +this.getFecha();
    }
}