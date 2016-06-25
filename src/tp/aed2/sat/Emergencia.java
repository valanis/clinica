package tp.aed2.sat;

import tp.aed2.pacientes.Paciente;
import tp.aed2.viajes.Viaje;

import java.util.ArrayList;

public class Emergencia {

    private Viaje viaje;
    private Boolean atendida;

    public ArrayList<Paciente> getPacientes() {
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
}
