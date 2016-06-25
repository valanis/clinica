package tp.aed2;

import tp.aed2.clinica.Clinica;
import tp.aed2.empleados.Administrativo;
import tp.aed2.empleados.Camillero;
import tp.aed2.empleados.Doctor;
import tp.aed2.excepciones.OpcionSeleccionadaIncorrectaException;
import tp.aed2.obras_sociales.ObraSocial;
import tp.aed2.ui.Consola;

import java.math.BigDecimal;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws OpcionSeleccionadaIncorrectaException {
        /* DOCTOR */
        Doctor lopez = new Doctor();
        Doctor perez = new Doctor();
        Doctor alanis = new Doctor();
        Doctor flores = new Doctor();
        Doctor mengarda = new Doctor();

        /* ADMINISTRATIVOS */
        Administrativo lopezAdmin = new Administrativo();
        Administrativo perezAdmin = new Administrativo();
        Administrativo alanisAdmin = new Administrativo();
        Administrativo floresAdmin = new Administrativo();
        Administrativo mengardaAdmin = new Administrativo();

        /* CAMILLEROS */
        Camillero lopezCamillero = new Camillero();
        Camillero perezCamillero = new Camillero();
        Camillero alanisCamillero = new Camillero();
        Camillero floresCamillero = new Camillero();
        Camillero mengardaCamillero = new Camillero();

        /* OBRAS SOCIALES */
        ArrayList<ObraSocial> obrasSociales = new ArrayList<ObraSocial>();

        ObraSocial osde = new ObraSocial("OSDE", BigDecimal.valueOf(10));
        ObraSocial osecac = new ObraSocial("OSECAC", BigDecimal.valueOf(20));
        ObraSocial swiss = new ObraSocial("Swiss Medical", BigDecimal.valueOf(30));
        ObraSocial galeno = new ObraSocial("Galeno", BigDecimal.valueOf(40));
        ObraSocial pami = new ObraSocial("PAMI", BigDecimal.valueOf(50));

        Clinica clinica = Clinica.getInstance();

        clinica.agregarAdministrativo(alanisAdmin);
        clinica.agregarAdministrativo(floresAdmin);
        clinica.agregarAdministrativo(mengardaAdmin);
        clinica.agregarAdministrativo(perezAdmin);
        clinica.agregarAdministrativo(lopezAdmin);

        clinica.agregarCamillero(alanisCamillero);
        clinica.agregarCamillero(floresCamillero);
        clinica.agregarCamillero(mengardaCamillero);
        clinica.agregarCamillero(perezCamillero);
        clinica.agregarCamillero(lopezCamillero);

        clinica.agregarDoctor(alanis);
        clinica.agregarDoctor(flores);
        clinica.agregarDoctor(mengarda);
        clinica.agregarDoctor(lopez);
        clinica.agregarDoctor(perez);

        clinica.agregarObraSocial(osde);
        clinica.agregarObraSocial(osecac);
        clinica.agregarObraSocial(swiss);
        clinica.agregarObraSocial(galeno);
        clinica.agregarObraSocial(pami);

        Consola inicio =  new Consola();

        try {
            inicio.iniciarSistema(clinica);
        } catch (OpcionSeleccionadaIncorrectaException e) {
            System.out.println(e.getMessage());
            inicio.iniciarSistema(clinica);
        }

    }
}
