package tp.aed2.ui;

import org.joda.time.DateTime;
import tp.aed2.clinica.Clinica;
import tp.aed2.consultas.Consulta;
import tp.aed2.empleados.Administrativo;
import tp.aed2.empleados.Camillero;
import tp.aed2.empleados.Doctor;
import tp.aed2.factory.ConsultaFactory;
import tp.aed2.factory.PacienteFactory;
import tp.aed2.obras_sociales.IObraSocial;
import tp.aed2.obras_sociales.NullObraSocial;
import tp.aed2.pacientes.Paciente;
import tp.aed2.sat.Emergencia;
import tp.aed2.viajes.Viaje;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Consola {

    Integer accion;
    Clinica clinica;


    public void iniciarSistema(Clinica clinica) {

        Scanner sc = new Scanner(System.in);
        this.clinica = clinica;
        this.accion = -1;

        System.out.println("                           \\|||/");
        System.out.println("                           (o o)");
        System.out.println("------------------------ooO-(_)-Ooo------------------------");
        System.out.println("Bienvenido al sistema de Clínica del Dr. Nick Riviera");
        System.out.println("Qué desea hacer: ");
        System.out.println("0 - Salir");
        System.out.println("1 - Ingresar una emergencia");
        System.out.println("2 - Ingresar una consulta");
        System.out.println("3 - Calcular el sueldo de los empleados");
        try {
            accion = sc.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println("Opción incorrecta, vuelva a intentar. \n");
            iniciarSistema(clinica);
        }

        System.out.println("Usted eligió: " + accion);

        switch (accion) {
            case 0:
                System.out.println("Fin del sistema.");
                break;
            case 1:
                this.ingresarEmergencia();
                break;
            case 2:
                this.ingresarConsulta();
                break;
            case 3:
                this.calcularSueldo();
                break;
            default:
                System.out.println("Opción incorrecta, vuelva a intentar. \n");
                this.iniciarSistema(clinica);
        }
    }

    private void ingresarEmergencia() {
        Integer cantPacientes = 0;
        BigDecimal cantKm = BigDecimal.valueOf(0);
        DateTime dateTime = new DateTime();

        Scanner sc = new Scanner(System.in);
        System.out.println("                           \\|||/");
        System.out.println("                           (o o)");
        System.out.println("------------------------ooO-(_)-Ooo------------------------");
        System.out.println("Ingresando emergencia: ");
        System.out.println("Ingrese Cantidad de pacientes:");
        try {
            cantPacientes = sc.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println("Opción incorrecta, vuelva a intentar ingresar la emergencia. \n");
            this.ingresarEmergencia();
        }
        System.out.println("Ingrese los km: (Utilizando '.')");

        try {
            cantKm = sc.nextBigDecimal();
        } catch (InputMismatchException exception) {
            System.out.println("Km's incorrectos, vuelva a intentar ingresar la emergencia. \n");
            this.ingresarEmergencia();
        }
        if(cantPacientes > 0 && cantKm.compareTo(BigDecimal.ZERO) == 1) {
            Viaje viaje = new Viaje(cantPacientes, cantKm, dateTime);
            Emergencia emergencia = new Emergencia(viaje);
            System.out.println("Se notificará: " + emergencia);

            this.clinica.getSat().notificar(emergencia);
            this.iniciarSistema(this.clinica);
        } else {
            System.out.println("Ingreso de datos erroneo. Ingrese los datos nuevamente \n");
            this.ingresarEmergencia();
        }
    }

    private void ingresarConsulta() {
        Paciente pacienteAAtender;
        IObraSocial obraSocialElegida;
        Consulta consulta;
        Integer tipoDeCobertura = -1;
        Integer edad = -1;
        Integer dni = -1;
        Integer doctorSeleccionado = -1;

        Scanner sc = new Scanner(System.in);
        System.out.println("                           \\|||/");
        System.out.println("                           (o o)");
        System.out.println("------------------------ooO-(_)-Ooo------------------------");
        System.out.println("Ingresando consulta: ");

        //COBERTURA
        System.out.println(" Seleccione la cobertura correpondiente: ");
        System.out.println("0 - Sin cobertura ");
        int i;
        for(i = 0; i < clinica.getObrasSociales().size(); i++){
            System.out.println( (i+1)+" - "+clinica.getObrasSociales().get(i));
        }
        try {
            tipoDeCobertura = sc.nextInt();
            System.out.println("Usted eligió: " + tipoDeCobertura);
        } catch (InputMismatchException exception) {
            System.out.println("Ingreso de dato erroneo. Intente cargar la consulta nuevamente \n");
            this.ingresarConsulta();
        }
        if(tipoDeCobertura.compareTo(Integer.valueOf(0)) == 1) {
        //if (tipoDeCobertura >  0) {
            obraSocialElegida = clinica.getObrasSociales().get(tipoDeCobertura-1);
        } else {
            obraSocialElegida = NullObraSocial.getInstance();
        }

        //EDAD
        System.out.println(" Ingrese la edad del paciente: ");
        try {
            edad = sc.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println("Ingreso de dato erroneo. Intente cargar la consulta nuevamente \n");
            this.ingresarConsulta();
        }

        //DNI
        System.out.println(" Ingrese el dni del paciente: ");
        try {
            dni = sc.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println("Ingreso de dato erroneo. Intente cargar la consulta nuevamente \n");
            this.ingresarConsulta();
        }

        //DOCTOR
        System.out.println(" Seleccione el doctor que quiere que lo atienda: ");
        Integer j;
        for(j = 0; j < clinica.getDoctores().size(); j++){
            System.out.println( (j)+" - "+clinica.getDoctores().get(j).getId());
        }
        try {
            doctorSeleccionado = sc.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println("Ingreso de dato erroneo. Intente cargar la consulta nuevamente \n");
            this.ingresarConsulta();
        }
        if ( edad <= -1 || dni <= -1 || tipoDeCobertura <= -1 || doctorSeleccionado <=-1) {
            System.out.println("Ingreso de datos erroneo. Intente nuevamente \n");
            this.ingresarConsulta();
        } else {
            pacienteAAtender = PacienteFactory.getPaciente(dni, edad, obraSocialElegida);
            consulta = ConsultaFactory.getConsulta(pacienteAAtender);
            Doctor doctor = clinica.getDoctores().get(doctorSeleccionado);

            System.out.println(" El doctor que la atenderá sera: " + doctor);
            System.out.println(consulta);
            doctor.atender(consulta);
            this.iniciarSistema(this.clinica);
        }
    }

    private void calcularSueldo() {
        //Obtengo todos los empleados de la clinica
        Scanner sc = new Scanner(System.in);
        System.out.println("                           \\|||/");
        System.out.println("                           (o o)");
        System.out.println("------------------------ooO-(_)-Ooo------------------------");
        System.out.println("Los sueldos de los empleados son: ");
        int i;
        for(i = 0; i < clinica.getDoctores().size(); i++){
            Doctor doctor = clinica.getDoctores().get(i);
            System.out.println("Doctor: "+doctor.getId()+" sueldo: "+clinica.getLiquidadorDeSueldo().calcular(doctor));
        }
        int j;
        for(j = 0; j < clinica.getAdministrativos().size(); j++){
            Administrativo administrativo = clinica.getAdministrativos().get(j);
            System.out.println("Administrativo: "+administrativo.getId()+" sueldo: "+clinica.getLiquidadorDeSueldo().calcular(administrativo));
        }
        int k;
        for(k = 0; k < clinica.getAdministrativos().size(); k++){
            Camillero camillero = clinica.getCamilleros().get(k);
            System.out.println("Camillero: "+camillero.getId()+" sueldo: "+clinica.getLiquidadorDeSueldo().calcular(camillero));
        }
        this.iniciarSistema(this.clinica);
    }
}
