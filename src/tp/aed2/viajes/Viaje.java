package tp.aed2.viajes;

import org.joda.time.DateTime;
import tp.aed2.pacientes.Paciente;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.joda.time.DateTimeConstants.SATURDAY;
import static org.joda.time.DateTimeConstants.SUNDAY;

public class Viaje {

    //Atributos
    private ArrayList<Paciente> pacientes;
    private BigDecimal kilometros;
    private DateTime fecha;

    //Getters
    public ArrayList<Paciente> getPacientes() {
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
        return this.pacientes.size();
    }

    public Boolean viajoMasDeUnPaciente() {
        return getCantidadDePacientes() > 1;
    }

    public Boolean fueFinDeSemana() {
        return fecha.getDayOfWeek() == SATURDAY || fecha.getDayOfWeek() == SUNDAY;
    }
}
