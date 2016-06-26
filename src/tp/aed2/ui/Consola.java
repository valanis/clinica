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

        accion = sc.nextInt();
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
                iniciarSistema(clinica);
        }
    }

    private void ingresarEmergencia() {
        Integer cantPacientes;
        BigDecimal cantKm = BigDecimal.valueOf(0);

        Scanner sc = new Scanner(System.in);
        System.out.println("                           \\|||/");
        System.out.println("                           (o o)");
        System.out.println("------------------------ooO-(_)-Ooo------------------------");
        System.out.println("Ingresando emergencia: ");
        System.out.println("Ingrese Cantidad de pacientes:");
        cantPacientes = sc.nextInt();
        System.out.println("Ingrese los km:");
        cantKm = sc.nextBigDecimal();

        DateTime dateTime = new DateTime();

        Viaje viaje = new Viaje(cantPacientes, cantKm, dateTime);
        Emergencia emergencia = new Emergencia(viaje);
        System.out.println("Se notificará: " + emergencia);

        this.clinica.getSat().notificar(emergencia);
        this.iniciarSistema(this.clinica);
    }

    private void ingresarConsulta() {
        Paciente pacienteAAtender;
        IObraSocial obraSocialElegida;
        Consulta consulta;
        Integer tipoDeCobertura;
        Integer edad;
        Integer dni;
        Integer doctorSeleccionado;

        Scanner sc = new Scanner(System.in);
        System.out.println("                           \\|||/");
        System.out.println("                           (o o)");
        System.out.println("------------------------ooO-(_)-Ooo------------------------");
        System.out.println("Ingresando consulta: ");
        System.out.println(" Seleccione la cobertura correpondiente: ");
        System.out.println("0 - Sin cobertura ");
        Integer i;
        for(i = 0; i < clinica.getObrasSociales().size(); i++){
            System.out.println( (i+1)+" - "+clinica.getObrasSociales().get(i));
        }
        tipoDeCobertura = sc.nextInt();
        System.out.println("Usted eligió: " + tipoDeCobertura);
        if (tipoDeCobertura != 0) {
            obraSocialElegida = clinica.getObrasSociales().get(tipoDeCobertura-1);
        } else {
            obraSocialElegida = NullObraSocial.getInstance();
        }
        System.out.println(" Ingrese la edad del paciente: ");
        edad = sc.nextInt();

        System.out.println(" Ingrese el dni del paciente: ");
        dni = sc.nextInt();

        pacienteAAtender = PacienteFactory.getPaciente(dni, edad, obraSocialElegida);
        consulta = ConsultaFactory.getConsulta(pacienteAAtender);

        System.out.println(" Seleccione el doctor que quiere que lo atienda: ");
        Integer j;
        for(j = 0; j < clinica.getDoctores().size(); j++){
            System.out.println( (j)+" - "+clinica.getDoctores().get(j).getId());
        }
        doctorSeleccionado = sc.nextInt();

        Doctor doctor = clinica.getDoctores().get(doctorSeleccionado);

        System.out.println(" El doctor que la atenderá sera: " + doctor);
        System.out.println(consulta);
        doctor.atender(consulta);

        this.iniciarSistema(this.clinica);
    }

    private void calcularSueldo() {
        //Obtengo todos los empleados de la clinica
        Scanner sc = new Scanner(System.in);
        System.out.println("                           \\|||/");
        System.out.println("                           (o o)");
        System.out.println("------------------------ooO-(_)-Ooo------------------------");
        System.out.println("Los sueldos de los empleados son: ");
        Integer i;
        for(i = 0; i < clinica.getDoctores().size(); i++){
            Doctor doctor = clinica.getDoctores().get(i);
            System.out.println("Doctor: "+doctor.getId()+" sueldo: "+clinica.getLiquidadorDeSueldo().calcular(doctor));
        }
        Integer j;
        for(j = 0; j < clinica.getAdministrativos().size(); j++){
            Administrativo administrativo = clinica.getAdministrativos().get(j);
            System.out.println("Administrativo: "+administrativo.getId()+" sueldo: "+clinica.getLiquidadorDeSueldo().calcular(administrativo));
        }
        Integer k;
        for(k = 0; k < clinica.getAdministrativos().size(); k++){
            Camillero camillero = clinica.getCamilleros().get(k);
            System.out.println("Camillero: "+camillero.getId()+" sueldo: "+clinica.getLiquidadorDeSueldo().calcular(camillero));
        }

        this.iniciarSistema(this.clinica);
    }
}
