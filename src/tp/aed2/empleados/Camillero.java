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
    public Camillero(String id) {
        this.id = id;
        this.sueldoBasico = SUELDO_BASICO;
        this.viajesRealizados = new ArrayList<Viaje>();
    }

    //Getters
    public BigDecimal getSueldoBasico() {
        return this.sueldoBasico;
    }

    public ArrayList<Viaje> getViajesRealizados() {
        return this.viajesRealizados;
    }

    public String getId() {
        return id;
    }

    private void agregarViaje(Viaje viaje) {
        this.viajesRealizados.add(viaje);
        System.out.println("Viaje realizado.");
    }

    //Métodos
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

    private ReentrantLock juntarGanasDeTrabajar() throws InterruptedException  {
        Random random = new Random();
        Thread.currentThread().sleep(random.nextInt(5000));
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        return lock;
    }

    private void volverADormir(ReentrantLock lock) {
        lock.unlock();
    }

    public String toString() {
        return "[Camillero: "+this.getId()+"]";
    }
}
