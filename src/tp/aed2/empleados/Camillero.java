package tp.aed2.empleados;

import tp.aed2.sat.Emergencia;
import tp.aed2.sat.IEmergenciaSuscriptor;
import tp.aed2.viajes.Viaje;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Camillero implements IEmpleado, IEmergenciaSuscriptor {

    //Constantes
    private static final BigDecimal SUELDO_BASICO = new BigDecimal(10000);

    //Atributos
    String id;
    BigDecimal sueldoBasico;
    ArrayList<Viaje> viajesRealizados;

    //Constructor

    /**
     *
     * @param id: Se genera un nuevo Camillero con el Id pasado por parámetro, con el sueldo básico configurado para los camilleros
     *          y se inicializa la lista vacía para almacenar los viajes realizados por el mismo
     */
    public Camillero(String id) {
        this.id = id;
        this.sueldoBasico = SUELDO_BASICO;
        this.viajesRealizados = new ArrayList<Viaje>();
    }

    //Getters

    /**
     *
     * @return Devuelve el Sueldo Básico de un camillero en BigDecimal
     */
    public BigDecimal getSueldoBasico() {
        return this.sueldoBasico;
    }

    /**
     *
     * @return Devuelve una lista con los viajes realizados por el camillero
     */
    public ArrayList<Viaje> getViajesRealizados() {
        return this.viajesRealizados;
    }

    /**
     *
     * @return Devuelve el string coirrespondiente al ID del Camillero
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param viaje: Agrega el viaje pasado por parámetro a la lista de viajes realizados por el camillero
     */
    public void agregarViaje(Viaje viaje) {
        this.viajesRealizados.add(viaje);
        System.out.println("Viaje realizado.");
    }

    //Métodos

    /**
     *
     * @param emergencia Se genera el evento Emergencia, y se le da aviso al mismo tiempo a todos los camilleros
     *                   disponibles para que sea tomada por y solo por el primero que la atienda
     * @throws InterruptedException para que la emergencia sea atendida solo por un camillero y
     * le avise a los otros que ya fue tomada, al momento que quieran atenderla ellos
     */
    @Override
    public void update(Emergencia emergencia) throws InterruptedException {
        System.out.println(this + " notificado!");

        ReentrantLock sueño = juntarGanasDeTrabajar();

        if(!emergencia.fueAtendida()){
            System.out.println(this + " realizará el viaje.");
            emergencia.marcarComoAtendida();
            agregarViaje(emergencia.getViaje());
        }

        volverADormir(sueño);
    }

    /**
     *
     * @return Devuelve un Lock que se aplica sobre el thread actual por un período de tiempo
     * de entre 0 y 5 seg -5000 milisegundos-
     * @throws InterruptedException
     */
    private ReentrantLock juntarGanasDeTrabajar() throws InterruptedException  {
        Random random = new Random();
        Thread.currentThread().sleep(random.nextInt(5000));
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        return lock;
    }

    /**
     *
     * @param lock Elimina el Lock sobre el thread actual, luego de haber seteado la emergencia como
     */
    private void volverADormir(ReentrantLock lock) {
        lock.unlock();
    }

    /**
     *
     * @return Devuelve un String con el ID del Camillero
     */
    public String toString() {
        return "[Camillero: "+this.getId()+"]";
    }
}
