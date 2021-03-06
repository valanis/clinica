import org.joda.time.DateTime;
import org.junit.Test;
import tp.aed2.clinica.Clinica;
import tp.aed2.empleados.Administrativo;
import tp.aed2.fichaje.Fichaje;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FichajeTest {

    private static final Integer HORA_ENTRADA = Fichaje.getHoraEntrada();
    private static final Integer HORA_SALIDA = Fichaje.getHoraSalida();

    private static Clinica clinica = Clinica.getInstance();
    private static DateTime fecha = clinica.getFecha();

    @Test
    public void newAdministrativoCreaUnFichajeVacio() {
        Integer diasEnMesActual = fecha.dayOfMonth().getMaximumValue();
        Administrativo admin = new Administrativo("Adm_newAdministrativoCreaUnFichajeVacio");
        Fichaje fichaje = admin.getFichaje();
        assertNotNull(fichaje);
        assertEquals(fichaje.getCantidadDeDias(), diasEnMesActual);
        Integer horasATrabajar = diasEnMesActual * fichaje.getHorasATrabajar();
        assertEquals(fichaje.obtenerHorasNoTrabajadas(), horasATrabajar);
    }

    @Test
    public void ficharEntradaValida() {
        Integer dia = fecha.getDayOfMonth();
        Administrativo admin = new Administrativo("Adm_ficharEntradaValida");
        admin.ficharEntrada(fecha);
        Fichaje fichaje = admin.getFichaje();
        ArrayList<DateTime> fichajeDelDia = fichaje.getFichajeDelDia(dia);
        assertEquals(fichajeDelDia.size(), 1);
        assertEquals(fichajeDelDia.get(0), fecha);
    }

    @Test
    public void ficharEntradaNoValida_yaSeHabiaFichadoEntrada() {
        DateTime fecha1 = fecha;
        DateTime fecha2 = fecha.plusMillis(1);

        Integer dia = fecha1.getDayOfMonth();
        Administrativo admin = new Administrativo("Adm_ficharEntradaNoValida_yaSeHabiaFichadoEntrada");
        admin.ficharEntrada(fecha1);
        admin.ficharEntrada(fecha2);

        Fichaje fichaje = admin.getFichaje();
        ArrayList<DateTime> fichajeDelDia = fichaje.getFichajeDelDia(dia);
        assertEquals(fichajeDelDia.size(), 1);
        assertEquals(fichajeDelDia.get(0), fecha1);
    }

    @Test
    public void ficharEntradaNoValida_mesInvalido() {
        DateTime fechaFutura = fecha.plusMonths(1);

        Integer dia = fechaFutura.getDayOfMonth();
        Administrativo admin = new Administrativo("Adm_ficharEntradaNoValida_mesInvalido");
        admin.ficharEntrada(fechaFutura);

        Fichaje fichaje = admin.getFichaje();
        ArrayList<DateTime> fichajeDelDia = fichaje.getFichajeDelDia(dia);
        assertEquals(fichajeDelDia.size(), 0);
    }

    @Test
    public void ficharSalidaValida() {
        DateTime fechaEntrada = fecha;
        DateTime fechaSalida = fecha.plusMillis(1);

        Integer dia = fechaEntrada.getDayOfMonth();
        Administrativo admin = new Administrativo("Adm_ficharSalidaValida");
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
        Integer dia = fecha.getDayOfMonth();
        Administrativo admin = new Administrativo("Adm_ficharSalidaNoValida_noSeFichoEntrada");
        admin.ficharSalida(fecha);

        Fichaje fichaje = admin.getFichaje();
        ArrayList<DateTime> fichajeDelDia = fichaje.getFichajeDelDia(dia);
        assertEquals(fichajeDelDia.size(), 0);
    }

    @Test
    public void ficharSalidaNoValida_salidaEsAnteriorAEntrada() {
        DateTime fechaSalida = fecha;
        DateTime fechaEntrada = fecha.plusMillis(1);

        Integer dia = fechaSalida.getDayOfMonth();
        Administrativo admin = new Administrativo("Adm_ficharSalidaNoValida_salidaEsAnteriorAEntrada");
        admin.ficharEntrada(fechaEntrada);
        admin.ficharSalida(fechaSalida);

        Fichaje fichaje = admin.getFichaje();
        ArrayList<DateTime> fichajeDelDia = fichaje.getFichajeDelDia(dia);
        assertEquals(fichajeDelDia.size(), 1);
        assertEquals(fichajeDelDia.get(0), fechaEntrada);
    }

    @Test
    public void ficharSalidaNoValida_mesInvalido() {
        DateTime fechaEntrada = fecha;
        DateTime fechaSalida = fechaEntrada.plusMonths(1);

        Integer dia = fechaSalida.getDayOfMonth();
        Administrativo admin = new Administrativo("Adm_ficharSalidaNoValida_mesInvalido");
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
        Administrativo admin = new Administrativo("Adm_obtenerHorasNoTrabajas_elEmpleadoFaltoUnDia");
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
        Administrativo admin = new Administrativo("Adm_obtenerHorasNoTrabajas_elEmpleadoLlegaDosHorasTardeSiempre");
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
        Administrativo admin = new Administrativo("Adm_obtenerHorasNoTrabajas_elEmpleadoLlegaDosHorasTardeSiempre");
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
        Administrativo admin = new Administrativo("Adm_obtenerHorasNoTrabajas_elEmpleadoLlegaDosHorasAntesYSeVaDosHorasAntesSiempre");
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
        Administrativo admin = new Administrativo("Adm_obtenerHorasNoTrabajas_elEmpleadoFichoMalTodosLosDias");
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
