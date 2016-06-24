package tp.aed2.sat;

import java.util.ArrayList;
import java.util.List;

public abstract class EmergenciaPublisher {

    protected List<IEmergenciaSuscriptor> suscriptores = new ArrayList<IEmergenciaSuscriptor>();

    public void agregarSuscriptor(IEmergenciaSuscriptor suscriptor) {
        this.suscriptores.add(suscriptor);
    }

    public void eliminarSuscriptor(IEmergenciaSuscriptor suscriptor) {
        this.suscriptores.remove(suscriptor);
    }

    public void reiniciar() {
        this.suscriptores = new ArrayList<IEmergenciaSuscriptor>();
    }

    public abstract void notificar(Emergencia emergencia);
}
