package tp.aed2.calculador;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import tp.aed2.consultas.Consulta;
import tp.aed2.empleados.Administrativo;
import tp.aed2.empleados.Camillero;
import tp.aed2.empleados.Doctor;
import tp.aed2.excepciones.EmpleadoNoTrabajoException;
import tp.aed2.excepciones.SueldoNegativoException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class CalculadorDeSueldo {

    //Constantes
    private static final BigDecimal VALOR_HORA = new BigDecimal(10);
    private static final BigDecimal VALOR_COTIZACION = new BigDecimal(50);
    private static final Integer HORAS_A_TRABAJAR = 9;

    /**
     * Calcula el sueldo para el corriente mes de un empleado doctor
     * @param doctor
     * @return sueldo del mes (sueldo básico + plus por consultas atendidas)
     */
    //Métodos
    public BigDecimal calcular(Doctor doctor) {
        BigDecimal sueldo = doctor.getSueldoBasico();
        for(Consulta consulta : doctor.getConsultasAtendidas()) {
            sueldo.add(consulta.getValor());
        }
        return sueldo;
    }

    public BigDecimal calcular(Administrativo admin)
            throws EmpleadoNoTrabajoException, SueldoNegativoException {
        BigDecimal sueldo = admin.getSueldoBasico();
        BigDecimal plusPorCotizaciones = obtenerValorDeCotizaciones(admin);
        BigDecimal descuentoPorHorasNoTrabajadas =  obtenerDescuentoPorHorasNoTrabajadas(admin);

        BigDecimal resultado = sueldo.add(plusPorCotizaciones).subtract(descuentoPorHorasNoTrabajadas);

        if(resultado.compareTo(BigDecimal.ZERO) < 0){
            throw new SueldoNegativoException();
        }

        return resultado;
    }

    private BigDecimal obtenerValorDeCotizaciones(Administrativo admin) {
        return this.VALOR_COTIZACION.multiply(admin.getCotizaciones());
    }

    /**
     * Calcula la cantidad de horas no trabajadas por un Empleado Administrativo.
     * El Empleado debería trabajar todos los días del mes, de 9 a 18 horas.
     * En caso de que haya fichado solo entrada (y no salida) se le descuenta el día.
     * @param admin Empleado Administrativo
     * @return cantidad de horas no trabajadas en el mes
     * @throws tp.aed2.excepciones.EmpleadoNoTrabajoException si el empleado no trabajó ningún día el mes.
     */
    private BigDecimal obtenerDescuentoPorHorasNoTrabajadas(Administrativo admin) throws EmpleadoNoTrabajoException {
        HashMap<Integer, ArrayList<DateTime>> fichaje = admin.getFichaje();
        Integer diasDelMes = fichaje.size();
        Integer diasNoTrabajados = 0;
        Integer horasNoTrabajadas = 0;
        for(int dia = 1; dia <= diasDelMes; dia++) {
            ArrayList fichajeDelDia = fichaje.get(dia);
            if(diaNoTrabajado(fichajeDelDia)) {
                horasNoTrabajadas += this.HORAS_A_TRABAJAR;
                diasNoTrabajados++;
            } else if(diaNoFichado(fichajeDelDia)) {
                horasNoTrabajadas += this.HORAS_A_TRABAJAR;
            } else {
                horasNoTrabajadas += horasNoTrabajadasDelDia(fichajeDelDia);
            }
        }

        if(diasNoTrabajados == diasDelMes) {
            throw new EmpleadoNoTrabajoException();
        }

        Integer horasTotales = diasDelMes * this.HORAS_A_TRABAJAR;

        if(horasNoTrabajadas == horasTotales) {
            throw new EmpleadoNoTrabajoException();
        }

        return this.VALOR_HORA.multiply(new BigDecimal(horasNoTrabajadas));
    }

    private Boolean diaNoTrabajado(ArrayList<DateTime> fichajeDelDia) {
        return fichajeDelDia.size() == 0;
    }

    private Boolean diaNoFichado(ArrayList<DateTime> fichajeDelDia) {
        return fichajeDelDia.size() == 1;
    }


    /**
     * Devuelve la cantidad de horas no trabajadas en el día
     * @param fichajeDelDia
     * @return valores posibles: de 0 a 9
     */
    private Integer horasNoTrabajadasDelDia(ArrayList<DateTime> fichajeDelDia) {
        DateTime entrada = fichajeDelDia.get(0);
        DateTime salida = fichajeDelDia.get(1);
        Integer horasTrabajadas = ((Long)new Duration(entrada, salida).getStandardHours()).intValue();
        Integer horasNoTrabajadas = this.HORAS_A_TRABAJAR - horasTrabajadas;
        return (horasNoTrabajadas <= 0) ? 0 : horasNoTrabajadas;
    }

    public BigDecimal calcular(Camillero camillero) {
        return BigDecimal.ZERO;
    }
}
