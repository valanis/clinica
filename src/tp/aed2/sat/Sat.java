package tp.aed2.sat;

public class Sat extends EmergenciaPublisher {

    private static Sat instance;

    private Sat() {}

    public static Sat getInstance() {
        if(instance == null) {
            instance = new Sat();
        }
        return instance;
    }

    @Override
    public void notificar(Emergencia emergencia) {
        for(IEmergenciaSuscriptor suscriptor : suscriptores) {
            suscriptor.update(emergencia);
        }
    }
}
