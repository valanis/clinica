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
        System.out.println("Notificando la emergencia a " + suscriptores.size() + " camilleros.");
        suscriptores.parallelStream().forEach(
                suscriptor -> {
                    try {
                        suscriptor.update(emergencia);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );


    }
}
