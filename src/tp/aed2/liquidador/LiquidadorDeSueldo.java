package tp.aed2.liquidador;

import tp.aed2.consultas.Consulta;
import tp.aed2.empleados.Administrativo;
import tp.aed2.empleados.Camillero;
import tp.aed2.empleados.Doctor;
import tp.aed2.excepciones.EmpleadoNoTrabajoException;
import tp.aed2.fichaje.Fichaje;
import tp.aed2.sat.Emergencia;
import tp.aed2.viajes.Viaje;

import java.math.BigDecimal;

public class LiquidadorDeSueldo {

    private static LiquidadorDeSueldo instance;

    //Constantes
    private static final BigDecimal VALOR_HORA = new BigDecimal(10);
    private static final BigDecimal VALOR_COTIZACION = new BigDecimal(50);
    private static final BigDecimal VALOR_VIAJE = new BigDecimal(20);
    private static final BigDecimal VALOR_KM = new BigDecimal(100);
    private static final BigDecimal ADICIONAL_PACIENTE = new BigDecimal(120);
    private static final BigDecimal PLUS_FIN_DE_SEMANA = new BigDecimal(2);

    private LiquidadorDeSueldo() {}

    public static LiquidadorDeSueldo getInstance() {
        if(instance == null) {
            instance = new LiquidadorDeSueldo();
        }
        return instance;
    }

    public BigDecimal getValorCotizacion() {
        return this.VALOR_COTIZACION;
    }

    public BigDecimal getValorHora() {
        return this.VALOR_HORA;
    }

    /**
     * Calcula el sueldo para el corriente mes de un empleado doctor
     * @param doctor
     * @return sueldo del mes (sueldo básico + plus por consultas atendidas)
     */
    //Métodos
    public BigDecimal calcular(Doctor doctor) {
        BigDecimal sueldo = doctor.getSueldoBasico();
        for(Consulta consulta : doctor.getConsultasAtendidas()) {
            sueldo = sueldo.add(consulta.getValor());
        }
        return sueldo;
    }

    public BigDecimal calcular(Administrativo admin) throws EmpleadoNoTrabajoException {
        BigDecimal sueldo = admin.getSueldoBasico();
        BigDecimal plusPorCotizaciones = obtenerValorDeCotizaciones(admin);
        BigDecimal descuentoPorHorasNoTrabajadas =  obtenerDescuentoPorHorasNoTrabajadas(admin);

        return sueldo.add(plusPorCotizaciones).subtract(descuentoPorHorasNoTrabajadas);
    }

    private BigDecimal obtenerValorDeCotizaciones(Administrativo admin) {
        return this.VALOR_COTIZACION.multiply(admin.getCotizaciones());
    }

    /**
     * @param admin Empleado Administrativo
     * @return el monto que se le debe descontar al sueldo por hora no trabajada
     * @throws tp.aed2.excepciones.EmpleadoNoTrabajoException si el empleado no trabajó ningún día el mes.
     */
    private BigDecimal obtenerDescuentoPorHorasNoTrabajadas(Administrativo admin) throws EmpleadoNoTrabajoException {
        Fichaje fichaje = admin.getFichaje();
        Integer horasNoTrabajadas = fichaje.obtenerHorasNoTrabajadas();
        Integer diasDelMes = fichaje.getCantidadDeDias();

        Integer horasATrabajar = diasDelMes * fichaje.getHorasATrabajar();

        if(horasNoTrabajadas.equals(horasATrabajar)) {
            throw new EmpleadoNoTrabajoException();
        }

        return this.VALOR_HORA.multiply(new BigDecimal(horasNoTrabajadas));
    }

    /**
     * @param camillero Empleado camillero
     * @return sueldo del mes (básico + plus por viajes realizados + plus por kms
     * + plus por haber transportado a más de un paciente + plus por trabajar en fin de semana)
     */
    public BigDecimal calcular(Camillero camillero) {
        BigDecimal sueldo = camillero.getSueldoBasico();

        for (Viaje viaje : camillero.getViajesRealizados()){
            BigDecimal valorViaje = VALOR_VIAJE;
            valorViaje.add(viaje.getKilometros().multiply(VALOR_KM));

            if (viaje.viajoMasDeUnPaciente()){
                valorViaje.add(ADICIONAL_PACIENTE);
            }

            if (viaje.fueFinDeSemana()){
                valorViaje.multiply(PLUS_FIN_DE_SEMANA);
            }

            sueldo = sueldo.add(valorViaje);
        }

        return sueldo;
    }
}
