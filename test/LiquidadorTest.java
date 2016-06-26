import org.junit.After;
import org.junit.Test;
import tp.aed2.clinica.Clinica;
import tp.aed2.consultas.Consulta;
import tp.aed2.empleados.Administrativo;
import tp.aed2.empleados.Camillero;
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
        Doctor doctor = new Doctor("Doc");
        BigDecimal sueldo = liquidador.calcular(doctor);
        assertEquals(doctor.getSueldoBasico(), sueldo);
    }

    @Test
    public void calcularDoctorConConsultaAtendidas() {
        Doctor doctor = new Doctor("Doc");
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
        Doctor doctor = new Doctor("Doc");
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
        BigDecimal sueldo = liquidador.calcular(administrativo);
        assertEquals(expected, sueldo);
    }

    @Test
    public void calcularAdministrativoQueNoFaltoNuncaYCotizoConsultas() {
        Administrativo administrativo = Fixture.getAdministrativoSinFaltas();
        Fixture.getConsultaMenorNoCubierto(administrativo);
        //El empleado cotizó una consulta y no se le descontó por horas no trabajadas,
        //el sueldo debe ser el básico + el valor de la cotización.
        BigDecimal sueldoBasico = administrativo.getSueldoBasico();
        BigDecimal plus = liquidador.getValorCotizacion();
        BigDecimal expected = sueldoBasico.add(plus);
        BigDecimal sueldo = liquidador.calcular(administrativo);
        assertEquals(expected, sueldo);
    }

    public void calcularAdministrativoQueNoTrabajoNunca() {
        Administrativo administrativo = new Administrativo("Adm");
        BigDecimal sueldo = liquidador.calcular(administrativo);
        BigDecimal expected = BigDecimal.ZERO;
        assertEquals(expected, sueldo);

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
        BigDecimal sueldo = liquidador.calcular(administrativo);
        assertEquals(expected, sueldo);
    }

    @Test
    public void calcularCamilleroQueNoRealizoViajes() {
        Camillero camillero = new Camillero("Camillero");
        BigDecimal sueldo = liquidador.calcular(camillero);
        BigDecimal expected = camillero.getSueldoBasico();
        assertEquals(expected, sueldo);
    }

}
