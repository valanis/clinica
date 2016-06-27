package tp.aed2.sat;

public class Sat extends EmergenciaPublisher {

    private static Sat instance;

    private Sat() {}

    /**
     *
     * @return Devuelve una instancia del SAT, si no existe, la crea
     */
    public static Sat getInstance() {
        if(instance == null) {
            instance = new Sat();
        }
        return instance;
    }

    /**
     *
     * @param emergencia: Se realiza la notificación EN PARALELO de la emergencia a todos los suscriptores
     *                  y se espera a que alguno la tome
     */
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
