import org.joda.time.DateTime;
import org.junit.Test;
import tp.aed2.empleados.Administrativo;
import tp.aed2.fichaje.Fichaje;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FichajeTest {

    private static final Integer HORA_ENTRADA = Fichaje.getHoraEntrada();
    private static final Integer HORA_SALIDA = Fichaje.getHoraSalida();

    @Test
    public void newAdministrativoCreaUnFichajeVacio() {
        Integer diasEnMesActual = new DateTime().dayOfMonth().getMaximumValue();
        Administrativo admin = new Administrativo();
        Fichaje fichaje = admin.getFichaje();
        assertNotNull(fichaje);
        assertEquals(fichaje.getCantidadDeDias(), diasEnMesActual);
        Integer horasATrabajar = diasEnMesActual * fichaje.getHorasATrabajar();
        assertEquals(fichaje.obtenerHorasNoTrabajadas(), horasATrabajar);
    }

    @Test
    public void ficharEntradaValida() {
        DateTime fecha = new DateTime();
        Integer dia = fecha.getDayOfMonth();
        Administrativo admin = new Administrativo();
        admin.ficharEntrada(fecha);
        Fichaje fichaje = admin.getFichaje();
        ArrayList<DateTime> fichajeDelDia = fichaje.getFichajeDelDia(dia);
        assertEquals(fichajeDelDia.size(), 1);
        assertEquals(fichajeDelDia.get(0), fecha);
    }

    @Test
    public void ficharEntradaNoValida_yaSeHabiaFichadoEntrada() {
        DateTime fecha1 = new DateTime();
        DateTime fecha2 = new DateTime().plusMillis(1);

        Integer dia = fecha1.getDayOfMonth();
        Administrativo admin = new Administrativo();
        admin.ficharEntrada(fecha1);
        admin.ficharEntrada(fecha2);

        Fichaje fichaje = admin.getFichaje();
        ArrayList<DateTime> fichajeDelDia = fichaje.getFichajeDelDia(dia);
        assertEquals(fichajeDelDia.size(), 1);
        assertEquals(fichajeDelDia.get(0), fecha1);
    }

    @Test
    public void ficharEntradaNoValida_mesInvalido() {
        DateTime fecha = new DateTime().plusMonths(1);

        Integer dia = fecha.getDayOfMonth();
        Administrativo admin = new Administrativo();
        admin.ficharEntrada(fecha);

        Fichaje fichaje = admin.getFichaje();
        ArrayList<DateTime> fichajeDelDia = fichaje.getFichajeDelDia(dia);
        assertEquals(fichajeDelDia.size(), 0);
    }

    @Test
    public void ficharSalidaValida() {
        DateTime fechaEntrada = new DateTime();
        DateTime fechaSalida = fechaEntrada.plusMillis(1);

        Integer dia = fechaEntrada.getDayOfMonth();
        Administrativo admin = new Administrativo();
        admin.ficharEntrada(fechaEntrada);
        admin.ficharSalida(fechaSalida);

        Fichaje fichaje = admin.getFichaje();
        ArrayList<DateTime> fichajeDelDia = fichaje.getFichajeDelDia(dia);
        assertEquals(fichajeDelDia.size(), 2);
        assertEquals(fichajeDelDia.get(0), fechaEntrada);
        assertEquals(fichajeDelDia.get(1), fechaSalida);
    }

    @Test
    public void ficharSalidaNoValida_noSeFichoEntrada() {
        DateTime fecha = new DateTime();

        Integer dia = fecha.getDayOfMonth();
        Administrativo admin = new Administrativo();
        admin.ficharSalida(fecha);

        Fichaje fichaje = admin.getFichaje();
        ArrayList<DateTime> fichajeDelDia = fichaje.getFichajeDelDia(dia);
        assertEquals(fichajeDelDia.size(), 0);
    }

    @Test
    public void ficharSalidaNoValida_salidaEsAnteriorAEntrada() {
        DateTime fechaSalida = new DateTime();
        DateTime fechaEntrada = fechaSalida.plusMillis(1);

        Integer dia = fechaSalida.getDayOfMonth();
        Administrativo admin = new Administrativo();
        admin.ficharEntrada(fechaEntrada);
        admin.ficharSalida(fechaSalida);

        Fichaje fichaje = admin.getFichaje();
        ArrayList<DateTime> fichajeDelDia = fichaje.getFichajeDelDia(dia);
        assertEquals(fichajeDelDia.size(), 1);
        assertEquals(fichajeDelDia.get(0), fechaEntrada);
    }

    @Test
    public void ficharSalidaNoValida_mesInvalido() {
        DateTime fechaEntrada = new DateTime();
        DateTime fechaSalida = fechaEntrada.plusMonths(1);

        Integer dia = fechaSalida.getDayOfMonth();
        Administrativo admin = new Administrativo();
        admin.ficharEntrada(fechaEntrada);
        admin.ficharSalida(fechaSalida);

        Fichaje fichaje = admin.getFichaje();
        ArrayList<DateTime> fichajeDelDia = fichaje.getFichajeDelDia(dia);
        assertEquals(fichajeDelDia.size(), 1);
        assertEquals(fichajeDelDia.get(0), fechaEntrada);
    }

    @Test
    public void obtenerHorasNoTrabajas_elEmpleadoTrabajoTodoElMes() {
        Administrativo admin = Fixture.getAdministrativoSinFaltas();

        Integer horasNoTrabajadas = admin.getFichaje().obtenerHorasNoTrabajadas();
        assertEquals(horasNoTrabajadas, new Integer(0));
    }

    @Test
    public void obtenerHorasNoTrabajas_elEmpleadoFaltoUnDia() {
        Administrativo admin = new Administrativo();
        Fichaje fichaje = admin.getFichaje();
        Integer mes = fichaje.getMes();
        Integer diasEnElMes = fichaje.getCantidadDeDias();

        for(int dia = 1; dia < diasEnElMes; dia++) {
            DateTime entrada = new DateTime(2016, mes, dia, HORA_ENTRADA, 0, 0);
            DateTime salida = new DateTime(2016, mes, dia, HORA_SALIDA, 0, 0);
            admin.ficharEntrada(entrada);
            admin.ficharSalida(salida);
        }

        Integer horasNoTrabajadas = fichaje.obtenerHorasNoTrabajadas();
        Integer horasATrabajar = fichaje.getHorasATrabajar();
        assertEquals(horasNoTrabajadas, horasATrabajar);
    }

    @Test
    public void obtenerHorasNoTrabajas_elEmpleadoLlegaDosHorasTardeSiempre() {
        Administrativo admin = new Administrativo();
        Fichaje fichaje = admin.getFichaje();
        Integer mes = fichaje.getMes();
        Integer diasEnElMes = fichaje.getCantidadDeDias();
        Integer horasTarde = 2;

        for(int dia = 1; dia <= diasEnElMes; dia++) {
            DateTime entrada = new DateTime(2016, mes, dia, HORA_ENTRADA + horasTarde, 0, 0);
            DateTime salida = new DateTime(2016, mes, dia, HORA_SALIDA, 0, 0);
            admin.ficharEntrada(entrada);
            admin.ficharSalida(salida);
        }

        Integer horasNoTrabajadas = fichaje.obtenerHorasNoTrabajadas();
        Integer expected = diasEnElMes * horasTarde;
        assertEquals(horasNoTrabajadas, expected);
    }

    @Test
    public void obtenerHorasNoTrabajas_elEmpleadoSeVaDosHorasAntesSiempre() {
        Administrativo admin = new Administrativo();
        Fichaje fichaje = admin.getFichaje();
        Integer mes = fichaje.getMes();
        Integer diasEnElMes = fichaje.getCantidadDeDias();
        Integer horasAntes = 2;

        for(int dia = 1; dia <= diasEnElMes; dia++) {
            DateTime entrada = new DateTime(2016, mes, dia, HORA_ENTRADA, 0, 0);
            DateTime salida = new DateTime(2016, mes, dia, HORA_SALIDA - horasAntes, 0, 0);
            admin.ficharEntrada(entrada);
            admin.ficharSalida(salida);
        }

        Integer horasNoTrabajadas = fichaje.obtenerHorasNoTrabajadas();
        Integer expected = diasEnElMes * horasAntes;
        assertEquals(horasNoTrabajadas, expected);
    }

    @Test
    public void obtenerHorasNoTrabajas_elEmpleadoLlegaDosHorasAntesYSeVaDosHorasAntesSiempre() {
        Administrativo admin = new Administrativo();
        Fichaje fichaje = admin.getFichaje();
        Integer mes = fichaje.getMes();
        Integer diasEnElMes = fichaje.getCantidadDeDias();
        Integer horasTarde = 2;
        Integer horasAntes = 2;

        for(int dia = 1; dia <= diasEnElMes; dia++) {
            DateTime entrada = new DateTime(2016, mes, dia, HORA_ENTRADA + horasTarde, 0, 0);
            DateTime salida = new DateTime(2016, mes, dia, HORA_SALIDA - horasAntes, 0, 0);
            admin.ficharEntrada(entrada);
            admin.ficharSalida(salida);
        }

        Integer horasNoTrabajadas = fichaje.obtenerHorasNoTrabajadas();
        Integer expected = diasEnElMes * horasAntes * horasTarde;
        assertEquals(horasNoTrabajadas, expected);
    }

    @Test
    public void obtenerHorasNoTrabajas_elEmpleadoFichoMalTodosLosDias() {
        Administrativo admin = new Administrativo();
        Fichaje fichaje = admin.getFichaje();
        Integer mes = fichaje.getMes();
        Integer diasEnElMes = fichaje.getCantidadDeDias();

        for(int dia = 1; dia <= diasEnElMes; dia++) {
            DateTime entrada = new DateTime(2016, mes, dia, HORA_ENTRADA, 0, 0);
            admin.ficharEntrada(entrada);
        }

        Integer horasNoTrabajadas = fichaje.obtenerHorasNoTrabajadas();
        Integer expected = diasEnElMes * fichaje.getHorasATrabajar();
        assertEquals(horasNoTrabajadas, expected);
    }

}
