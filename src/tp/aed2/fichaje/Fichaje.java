package tp.aed2.fichaje;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import java.util.ArrayList;
import java.util.HashMap;

public class Fichaje {

    //Constantes
    private static final Integer HORA_ENTRADA = 9;
    private static final Integer HORA_SALIDA = 18;
    private static final Integer HORAS_A_TRABAJAR = HORA_SALIDA - HORA_ENTRADA;

    //Atributos
    private HashMap<Integer, ArrayList<DateTime>> fichaje;

    //Constructor
    public Fichaje() {
        this.fichaje = iniciarMes();
    }

    //Métodos

    /**
     * @return inicializa el mapa con
     * claves: días del corriente mes,
     * valores: una lista vacía de tamaño 2.
     */
    private HashMap<Integer, ArrayList<DateTime>> iniciarMes() {
        HashMap fichaje = new HashMap();
        DateTime hoy = new DateTime();
        Integer diasEnElMes = hoy.dayOfMonth().getMaximumValue();
        for(int dia = 1; dia <= diasEnElMes; dia++) {
            fichaje.put(dia, new ArrayList(2));
        }
        return fichaje;
    }

    /**
     * @param fecha: momento en que se quiere fichar la entrada
     * Tiene que existir el día en el mes.
     * Tiene que ser el primer fichaje del día.
     * En caso contrario, no se ficha.
     */
    public void ficharEntrada(DateTime fecha) {
        Integer diaDelMes = fecha.getDayOfMonth();
        ArrayList fichajeDeHoy = this.fichaje.get(diaDelMes);
        if(existeElDia(fichajeDeHoy)) {
            if(noSeFichoPreviamente(fichajeDeHoy)) {
                fichajeDeHoy.add(fecha);
                this.fichaje.put(diaDelMes, fichajeDeHoy);
            } else {
                System.out.println("Fichaje inválido: entrada ya fichada para el día.");
            }
        } else {
            System.out.println("Fichaje inválido: día no existente en el mes.");
        }
    }

    /**
     * @param fecha: momento en que se quiere fichar la salida
     * Tiene que existir el día en el mes.
     * Se tiene que haber fichado la entrada previamente para ese día.
     * La fecha de salida tiene que ser posterior a la de entrada.
     * En caso contrario, no se ficha.
     */
    public void ficharSalida(DateTime fecha) {
        Integer diaDelMes = fecha.getDayOfMonth();
        ArrayList fichajeDeHoy = this.fichaje.get(diaDelMes);

        if(existeElDia(fichajeDeHoy)) {
            if(seFichoPreviamente(fichajeDeHoy)) {
                if(esValido(fichajeDeHoy, fecha)) {
                    fichajeDeHoy.add(fecha);
                    this.fichaje.put(diaDelMes, fichajeDeHoy);
                } else {
                    System.out.println("Fichaje inválido: la fecha de salida debe ser posterior a la de entrada");
                }
            } else {
                System.out.println("Fichaje inválido: no se fichó la entrada");
            }
        } else {
            System.out.println("Fichaje inválido: día no existente en el mes");
        }
    }

    /**
     * @param fichajeDeHoy
     * @return true si fichajeDeHoy es no nulo
     */
    private boolean existeElDia(ArrayList fichajeDeHoy) {
        return fichaje != null;
    }


    /**
     * @param fichajeDeHoy
     * @return true si fichajeDeHoy está vacío
     */
    private boolean noSeFichoPreviamente(ArrayList fichajeDeHoy) {
        return fichajeDeHoy.isEmpty();
    }

    /**
     * @param fichajeDeHoy
     * @return true si fichajeDeHoy contiene un elemento
     */
    private boolean seFichoPreviamente(ArrayList fichajeDeHoy) {
        return fichajeDeHoy.size() == 1;
    }

    /**
     * @param fichajeDeHoy
     * @param fecha
     * @return true si el primer elemento de fichajeDeHoy es anterior a fecha
     */
    private boolean esValido(ArrayList<DateTime> fichajeDeHoy, DateTime fecha) {
        DateTime primerFichaje = fichajeDeHoy.get(0);
        return primerFichaje.isBefore(fecha);
    }

    /**
     * @param fichajeDelDia
     * @return true si fichajeDelDia es vacío
     */
    private Boolean diaNoTrabajado(ArrayList<DateTime> fichajeDelDia) {
        return fichajeDelDia.isEmpty();
    }

    /**
     * @param fichajeDelDia
     * @return true si fichajeDelDia tiene un solo elemento
     */
    private Boolean diaNoFichado(ArrayList<DateTime> fichajeDelDia) {
        return fichajeDelDia.size() == 1;
    }

    /**
     * @return la cantidad de claves en el mapa this.fichaje
     */
    public Integer getCantidadDeDias() {
        return this.fichaje.size();
    }

    /**
     * @return la cantidad de horas que se espera que trabaje un empleado
     */
    public Integer getHorasATrabajar() {
        return this.HORAS_A_TRABAJAR;
    }


    /**
     * @return la cantidad de horas no trabajadas en el mes.
     * Si el empleado fichó mal (es decir, si solo fichó la entrada)
     * se le descuenta el día.
     */
    public Integer obtenerHorasNoTrabajadas() {
        Integer horasNoTrabajadas = 0;
        for(int dia = 1; dia <= this.getCantidadDeDias(); dia++) {
            ArrayList<DateTime> fichajeDelDia = this.fichaje.get(dia);
            if(diaNoTrabajado(fichajeDelDia)) {
                horasNoTrabajadas += this.HORAS_A_TRABAJAR;
            } else if(diaNoFichado(fichajeDelDia)) {
                horasNoTrabajadas += this.HORAS_A_TRABAJAR;
            } else {
                Integer horaEntrada = fichajeDelDia.get(0).getHourOfDay();
                Integer horaSalida = fichajeDelDia.get(1).getHourOfDay();

                if(entroTarde(horaEntrada)) {
                    horasNoTrabajadas += (horaEntrada - this.HORA_ENTRADA);
                }

                if(salioAntes(horaSalida)) {
                    horasNoTrabajadas += (this.HORA_SALIDA - horaSalida);
                }
            }
        }
        return horasNoTrabajadas;
    }

    private boolean entroTarde(Integer horaEntrada) {
        return horaEntrada > this.HORA_ENTRADA;
    }

    private boolean salioAntes(Integer horaSalida) {
        return horaSalida < this.HORA_SALIDA;
    }
}
