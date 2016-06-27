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

    /**
     * @param pacientes: Cantidad de personas involucradas en el viaje
     * @param kilometros: Distancia en kms a recorrer por camilleros
     * @param fecha: Fecha en la que se genera la emergencia. Debe coincidir con el mes actual
     */
    public Viaje(Integer pacientes, BigDecimal kilometros, DateTime fecha){
        this.pacientes = pacientes;
        this.kilometros = kilometros;
        this.fecha = fecha;
    }

    //Getters

    /**
     *
     * @return Devuelve la cantidad de pacientes involucrados en el viaje
     */
    public Integer getPacientes() {
        return this.pacientes;
    }

    /**
     *
     * @return Devuelve la distancia en kms a recorrer por camilleros
     */
    public BigDecimal getKilometros() {
        return kilometros;
    }

    /**
     *
     * @return Devuelve la fecha en la que se generó la emergencia.
     */
    public DateTime getFecha() {
        return fecha;
    }

    //Métodos
    /**
     *
     * @return Devuelve la cantidad de pacientes involucrados en el viaje
     */
    private Integer getCantidadDePacientes() {
        return this.pacientes;
    }

    /**
     *
     * @return Devuelve true si en el viaje de la emergencia se transportó más de un paciente
     */
    public Boolean viajoMasDeUnPaciente() {
        return getCantidadDePacientes() > 1;
    }

    /**
     *
     * @return Devuelve true si el viaje de la emergencia se llevó a cabo en un día de fin de semana
     */
    public Boolean fueFinDeSemana() {
        return fecha.getDayOfWeek() == SATURDAY || fecha.getDayOfWeek() == SUNDAY;
    }

    /**
     *
     * @return Devuelve un string compuesto pot la cantidad de pacientes, la cantidad de kms del viaje y la fecha de la emergencia
     */
    public String toString() {
        return "[Viaje] #Pacientes: " +this.getPacientes()+ " #KM: " +this.getKilometros()+ " Fecha: " +this.getFecha();
    }
}