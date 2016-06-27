package tp.aed2.sat;

import java.util.ArrayList;
import java.util.List;

public abstract class EmergenciaPublisher {

    protected List<IEmergenciaSuscriptor> suscriptores = new ArrayList<IEmergenciaSuscriptor>();

    /**
     *
     * @param suscriptor Agrega suscriptor a la lista de suscriptores para las emergencias -en nuestro caso, se tratará de los camilleros-
     */
    public void agregarSuscriptor(IEmergenciaSuscriptor suscriptor) {
        this.suscriptores.add(suscriptor);
    }

    /**
     *
     * @param suscriptor: Elimina al suscriptor de la lista de suscriptores a la emergencia
     */
    public void eliminarSuscriptor(IEmergenciaSuscriptor suscriptor) {
        this.suscriptores.remove(suscriptor);
    }

    /**
     * Reinicia la lista de suscriptores
     */
    public void reiniciar() {
        this.suscriptores = new ArrayList<IEmergenciaSuscriptor>();
    }

    public abstract void notificar(Emergencia emergencia);
}
