import org.junit.After;
import org.junit.Test;
import tp.aed2.clinica.Clinica;
import tp.aed2.consultas.Consulta;
import tp.aed2.empleados.Administrativo;
import tp.aed2.empleados.Doctor;
import tp.aed2.excepciones.EmpleadoNoTrabajoException;
import tp.aed2.liquidador.LiquidadorDeSueldo;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LiquidadorTest {

    LiquidadorDeSueldo liquidador = LiquidadorDeSueldo.getInstance();

    @After
    public void reiniciarClinica() {
        Clinica.getInstance().reiniciar();
    }

    @Test
    public void calcularDoctorSinConsultasAtendidas() {
        Doctor doctor = new Doctor();
        BigDecimal sueldo = liquidador.calcular(doctor);
        assertEquals(doctor.getSueldoBasico(), sueldo);
    }

    @Test
    public void calcularDoctorConConsultaAtendidas() {
        Doctor doctor = new Doctor();
        Consulta consulta = Fixture.getConsultaMayorNoCubierto();
        //El valor de la consulta debe ser el base porque es un paciente mayor sin OS
        BigDecimal valorConsulta = consulta.getValor();
        doctor.atender(consulta);
        BigDecimal sueldo = liquidador.calcular(doctor);
        BigDecimal expected = doctor.getSueldoBasico().add(valorConsulta);
        assertEquals(expected, sueldo);
    }

    @Test
    public void calcularDoctorConConsultasAtendidas() {
        Doctor doctor = new Doctor();
        Consulta consulta = Fixture.getConsultaMayorNoCubierto();
        //El valor de la consulta debe ser el base porque es un paciente mayor sin OS
        BigDecimal valorConsulta = consulta.getValor();
        doctor.atender(consulta);
        doctor.atender(consulta);
        BigDecimal sueldo = liquidador.calcular(doctor);
        BigDecimal expected = doctor.getSueldoBasico().add(valorConsulta).add(valorConsulta);
        assertEquals(expected, sueldo);
    }

    @Test
    public void calcularAdministrativoQueNoFaltoNuncaYNoCotizoConsultas() {
        Administrativo administrativo = Fixture.getAdministrativoSinFaltas();
        //El empleado no cotizó consultas ni se le descontó por horas no trabajadas,
        //el sueldo debe ser el básico.
        BigDecimal expected = administrativo.getSueldoBasico();
        try {
            BigDecimal sueldo = liquidador.calcular(administrativo);
            assertEquals(expected, sueldo);
        } catch(Exception exc) {
            fail("No debe arrojar excepción");
        }
    }

    @Test
    public void calcularAdministrativoQueNoFaltoNuncaYCotizoConsultas() {
        Administrativo administrativo = Fixture.getAdministrativoSinFaltas();
        Consulta consulta = Fixture.getConsultaMenorNoCubierto(administrativo);
        //El empleado cotizó una consulta y no se le descontó por horas no trabajadas,
        //el sueldo debe ser el básico + el valor de la cotización.
        BigDecimal sueldoBasico = administrativo.getSueldoBasico();
        BigDecimal plus = liquidador.getValorCotizacion();
        BigDecimal expected = sueldoBasico.add(plus);
        try {
            BigDecimal sueldo = liquidador.calcular(administrativo);
            assertEquals(expected, sueldo);
        } catch(Exception exc) {
            fail("No debe arrojar excepción");
        }
    }

    @Test(expected = EmpleadoNoTrabajoException.class)
    public void calcularAdministrativoQueNoTrabajoNunca() throws Exception {
        Administrativo administrativo = new Administrativo();
        try {
            liquidador.calcular(administrativo);
            fail("Debe arrojar EmpleadoNoTrabajoException");
        } catch(Exception exc) {
            throw exc;
        }
    }

    @Test
    public void calcularAdministrativoQueFaltoYCotizoConsultas() {
        Administrativo administrativo = Fixture.getAdministrativoConUnaFalta();
        Consulta consulta = Fixture.getConsultaMenorNoCubierto(administrativo);
        //El empleado cotizó una consulta y se le descontó un día,
        //el sueldo debe ser el básico + el valor de la cotización - (horas no trabajadas * valor hora).
        BigDecimal sueldoBasico = administrativo.getSueldoBasico();
        BigDecimal plus = liquidador.getValorCotizacion();
        Integer horasATrabajar = administrativo.getFichaje().getHorasATrabajar();
        BigDecimal descuento = liquidador.getValorHora().multiply(new BigDecimal(horasATrabajar));

        BigDecimal expected = sueldoBasico.add(plus).subtract(descuento);
        try {
            BigDecimal sueldo = liquidador.calcular(administrativo);
            assertEquals(expected, sueldo);
        } catch(Exception exc) {
            fail("No debe arrojar excepción");
        }
    }

}
