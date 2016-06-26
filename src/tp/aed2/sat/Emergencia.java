package tp.aed2.sat;

import tp.aed2.viajes.Viaje;

public class Emergencia {

    private Viaje viaje;
    private Boolean atendida;

    public Emergencia(Viaje viaje){
        this.viaje = viaje;
        this.atendida = false;

    }

    public Integer getPacientes() {
        return this.viaje.getPacientes();
    }

    public Viaje getViaje() {
        return this.viaje;
    }

    public Boolean fueAtendida() {
        return atendida;
    }

    public void marcarComoAtendida() {
        this.atendida = true;
    }

    public String toString() {
        return "[Emergencia] Atendida: " +this.fueAtendida()+ " " +this.getViaje()+"]";
    }
}
