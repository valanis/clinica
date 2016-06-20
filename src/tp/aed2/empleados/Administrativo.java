package tp.aed2.empleados;

import org.joda.time.DateTime;
import tp.aed2.pacientes.PacienteMenor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class Administrativo extends Empleado {

    //Constantes
    private static final BigDecimal SUELDO = new BigDecimal(7000);
    private static final BigDecimal VALOR_CONSULTA = new BigDecimal(75);

    //Atributos
    private BigDecimal cotizaciones;
    private HashMap<Integer, ArrayList<DateTime>> fichaje;

    //Constructores
    public Administrativo() {
        this.setSueldoBasico(SUELDO);
        this.cotizaciones = BigDecimal.ZERO;
        this.fichaje = iniciarMes();
    }

    public Administrativo(BigDecimal sueldo) {
        this.setSueldoBasico(sueldo);
        this.cotizaciones = BigDecimal.ZERO;
        this.fichaje = iniciarMes();
    }

    //Getters
    public BigDecimal getCotizaciones() {
        return cotizaciones;
    }

    public HashMap<Integer, ArrayList<DateTime>> getFichaje() {
        return this.fichaje;
    }

    //Métodos
    private HashMap<Integer, ArrayList<DateTime>> iniciarMes() {
        HashMap fichaje = new HashMap();
        DateTime hoy = new DateTime();
        Integer diasEnElMes = hoy.dayOfMonth().getMaximumValue();
        for(int dia = 1; dia <= diasEnElMes; dia++) {
            fichaje.put(dia, new ArrayList(2));
        }
        return fichaje;
    }

    public BigDecimal cotizarConsulta(PacienteMenor paciente) {
        this.cotizaciones.add(BigDecimal.ONE);
        return VALOR_CONSULTA.multiply(new BigDecimal(paciente.getEdad()));
    }

    public void fichar(DateTime fecha) {
        Integer diaDelMes = fecha.getDayOfMonth();
        ArrayList fichajeDeHoy = this.fichaje.get(diaDelMes);
        if(fichajeEsValido(fichajeDeHoy, fecha)) {
            fichajeDeHoy.add(fecha);
            this.fichaje.put(diaDelMes, fichajeDeHoy);
        }
    }

    /**
     * @param fichaje Corresponde a las fichas hechas en el día para el empleado this
     * @param fecha DateTime que se quiere fichar
     * @return true si el fichaje está vacío, o si tiene solo una fecha que es anterior
     * a la que se quiere agregar. En caso contrario, false.
     */
    private Boolean fichajeEsValido(ArrayList<DateTime> fichaje, DateTime fecha){
        if(fichaje.isEmpty()) {
            return true;
        } else if(fichaje.size() == 1) {
            DateTime primerFichaje = fichaje.get(0);
            return primerFichaje.isBefore(fecha);
        } else {
            return false;
        }
    }

}
