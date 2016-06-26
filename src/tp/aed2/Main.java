package tp.aed2;

import org.joda.time.DateTime;
import tp.aed2.clinica.Clinica;
import tp.aed2.empleados.Administrativo;
import tp.aed2.empleados.Camillero;
import tp.aed2.empleados.Doctor;
import tp.aed2.obras_sociales.ObraSocial;
import tp.aed2.ui.Consola;

import java.math.BigDecimal;


public class Main {

    public static void main(String[] args) {
        Clinica clinica = Clinica.getInstance();

        /* DOCTORES */
        Doctor lopez = new Doctor("López");
        Doctor perez = new Doctor("Perez");
        Doctor alanis = new Doctor("Alanis");
        Doctor flores = new Doctor("Flores");
        Doctor mengarda = new Doctor("Mengarda");

        clinica.agregarDoctor(alanis);
        clinica.agregarDoctor(flores);
        clinica.agregarDoctor(mengarda);
        clinica.agregarDoctor(lopez);
        clinica.agregarDoctor(perez);

        /* ADMINISTRATIVOS */
        Administrativo lopezAdmin = new Administrativo("LopezAdmin");
        Administrativo perezAdmin = new Administrativo("PerezAdmin");
        Administrativo alanisAdmin = new Administrativo("AlanisAdmin");
        Administrativo floresAdmin = new Administrativo("FloresAdmin");
        Administrativo mengardaAdmin = new Administrativo("MengardaAdmin");

        clinica.agregarAdministrativo(lopezAdmin);
        clinica.agregarAdministrativo(perezAdmin);
        clinica.agregarAdministrativo(alanisAdmin);
        clinica.agregarAdministrativo(floresAdmin);
        clinica.agregarAdministrativo(mengardaAdmin);

        DateTime fecha = clinica.getFecha();

        for(int dia = 1; dia < fecha.getDayOfMonth(); dia++) {
            DateTime entrada = new DateTime(fecha.getYear(), fecha.getMonthOfYear(), dia, 9, 0, 0);
            DateTime salida = new DateTime(fecha.getYear(), fecha.getMonthOfYear(), dia, 18, 0, 0);
            lopezAdmin.ficharEntrada(entrada);
            lopezAdmin.ficharSalida(salida);
            perezAdmin.ficharEntrada(entrada);
            perezAdmin.ficharSalida(salida);
            alanisAdmin.ficharEntrada(entrada);
            alanisAdmin.ficharSalida(salida);
            floresAdmin.ficharEntrada(entrada);
            floresAdmin.ficharSalida(salida);
            mengardaAdmin.ficharEntrada(entrada);
            mengardaAdmin.ficharSalida(salida);
        }

        lopezAdmin.ficharEntrada(fecha);
        perezAdmin.ficharEntrada(fecha);
        alanisAdmin.ficharEntrada(fecha);
        floresAdmin.ficharEntrada(fecha);
        mengardaAdmin.ficharEntrada(fecha);

        /* CAMILLEROS */
        Camillero lopezCamillero = new Camillero("LopezCamillero");
        Camillero perezCamillero = new Camillero("PerezCamillero");
        Camillero alanisCamillero = new Camillero("AlanisCamillero");
        Camillero floresCamillero = new Camillero("FloresCamillero");
        Camillero mengardaCamillero = new Camillero("MengardaCamillero");

        clinica.agregarCamillero(alanisCamillero);
        clinica.agregarCamillero(floresCamillero);
        clinica.agregarCamillero(mengardaCamillero);
        clinica.agregarCamillero(perezCamillero);
        clinica.agregarCamillero(lopezCamillero);

        /* OBRAS SOCIALES */
        ObraSocial osde = new ObraSocial("OSDE", BigDecimal.valueOf(10));
        ObraSocial osecac = new ObraSocial("OSECAC", BigDecimal.valueOf(20));
        ObraSocial swiss = new ObraSocial("Swiss Medical", BigDecimal.valueOf(30));
        ObraSocial galeno = new ObraSocial("Galeno", BigDecimal.valueOf(40));
        ObraSocial pami = new ObraSocial("PAMI", BigDecimal.valueOf(50));

        clinica.agregarObraSocial(osde);
        clinica.agregarObraSocial(osecac);
        clinica.agregarObraSocial(swiss);
        clinica.agregarObraSocial(galeno);
        clinica.agregarObraSocial(pami);

        new Consola().iniciarSistema(clinica);
    }
}
