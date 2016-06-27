package tp.aed2.sat;

import tp.aed2.viajes.Viaje;

public class Emergencia {

    private Viaje viaje;
    private Boolean atendida;

    /**
     * @param viaje: Asigna el viaje a la nueva Emergencia y setea a la misma como no atendida
     */
    public Emergencia(Viaje viaje){
        this.viaje = viaje;
        this.atendida = false;

    }

    /**
     * @return Devuelve la cantidad de pacientes involucrados en el viaje
     */
    public Integer getPacientes() {
        return this.viaje.getPacientes();
    }

    /**
     * @return Obtiene el viaje correspondiente a la Emergencia, con todos los datos asociados al mismo
     */
    public Viaje getViaje() {
        return this.viaje;
    }

    /**
     * @return Devuelve true si la Emergencia fue atendida y false si no
     */
    public Boolean fueAtendida() {
        return atendida;
    }

    /**
     * Setea a la Emergencia como atendida
     */
    public void marcarComoAtendida() {
        this.atendida = true;
    }

    /**
     * @return Convierte a String la información sobre la Emergencia: si fue atendida y datos del viaje
     */
    public String toString() {
        return "[Emergencia] Atendida: " +this.fueAtendida()+ " " +this.getViaje()+"]";
    }
}
