package tp.aed2.ui;

import tp.aed2.clinica.Clinica;
import tp.aed2.excepciones.OpcionSeleccionadaIncorrectaException;

import java.util.Scanner;

public class Consola {

    Integer accion;
    Integer tipoDePaciente;
    Integer tipoDeCobertura;
    Clinica clinica;
    Integer distancia;

    public void iniciarSistema(Clinica clinica) throws OpcionSeleccionadaIncorrectaException{

        Scanner sc = new Scanner(System.in);
        this.clinica = clinica;
        this.accion = -1;

        System.out.println("                           \\|||/");
        System.out.println("                           (o o)");
        System.out.println("------------------------ooO-(_)-Ooo------------------------");
        System.out.println("Bienvenido al sistema de Clínica del Dr. Nick Riviera");
        System.out.println("Que desea hacer: ");
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
                ingresarEmergencia();
                break;
            case 2:
                ingresarConsulta();
                break;
            case 3:
                calcularSueldo();
                break;
            default:
                throw new OpcionSeleccionadaIncorrectaException(accion);

        }
    }
    private void ingresarConsulta() throws OpcionSeleccionadaIncorrectaException {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("                           \\|||/");
//        System.out.println("                           (o o)");
//        System.out.println("------------------------ooO-(_)-Ooo------------------------");
//        System.out.println("Ingreando consulta: ");
//        System.out.println("0 - Salir");
//        System.out.println("1 - Volver al menú anterior");
//        System.out.println("2 - Paciente con cobertura");
//        System.out.println("3 - Paciente sin cobertura");
//        tipoDePaciente = sc.nextInt();
//        System.out.println("Usted eligió: " + accion);
//
//        switch (tipoDePaciente) {
//            case 0:
//                System.out.println("Fin del sistema.");
//                break;
//            case 1:
//                iniciarSistema(this.clinica);
//                break;
//            case 2:
//                ingresarConsultaConCobertura();
//                break;
//            case 3:
//                ingresarConsultaSinCobertura();
//                break;
//            default:
//                throw new OpcionSeleccionadaIncorrectaException(tipoDePaciente);
//
//        }

    }

/*
    private void ingresarConsultaConCobertura() throws OpcionSeleccionadaIncorrectaException {
        Scanner sc = new Scanner(System.in);
        System.out.println("                           \\|||/");
        System.out.println("                           (o o)");
        System.out.println("------------------------ooO-(_)-Ooo------------------------");
        System.out.println("Ingreando consulta: ");
        System.out.println("0 - Salir");
        System.out.println("1 - Volver al menú anterior");
        System.out.println(" Seleccione la cobertura correpondiente: ");
        Integer i;
        for(i = 0; i < clinica.getObrasSociales().size(); i++){
            System.out.println((i+2)+" - "+clinica.getObrasSociales().get(i));
        }
        tipoDeCobertura = sc.nextInt();
        System.out.println("Usted eligió: " + tipoDeCobertura);
        switch (tipoDeCobertura) {
            case 0:
                System.out.println("Fin del sistema.");
                break;
            case 1:
                ingresarConsulta();
                break;
            default:
*/
/*
                if (i.compareTo(0) < 0 || tipoDeCobertura.compareTo(i)>= 0){
                   //creo el paciente con la cobertura seleccionada.
                    PacienteConCobertura paciente = new PacienteConCobertura(clinica.getObrasSociales().get(i-1));

                    //Obtengo un doctor.
                    Random ran = new Random();
                    int selectDoctor = ran.nextInt(clinica.getDoctores().size());
                    Doctor doctor = clinica.getDoctores().get(selectDoctor);

                    //Creo la consulta.
                    Consulta consulta = new Consulta(paciente);

                    //Hago que el doctor la atienda.
                    doctor.atender(consulta);

                    //vuelvo al menu inicial
                    iniciarSistema(this.clinica);
                } else {
                    throw new OpcionSeleccionadaIncorrectaException(tipoDeCobertura);
                }
*//*


        }


    }
*/
    private void ingresarConsultaSinCobertura() {
    }

    private void ingresarEmergencia() throws OpcionSeleccionadaIncorrectaException{
        Scanner sc = new Scanner(System.in);
        System.out.println("                           \\|||/");
        System.out.println("                           (o o)");
        System.out.println("------------------------ooO-(_)-Ooo------------------------");
        System.out.println("Ingreando emergencia: ");
        System.out.println("0 - Salir");
        System.out.println("1 - Volver al menú anterior");
        System.out.println("2 - Ingrese distancia:");
        distancia = sc.nextInt();
        System.out.println("3 - Paciente sin cobertura");
        System.out.println("Usted eligió: " + accion);
        switch (accion) {
            case 0:
                System.out.println("Fin del sistema.");
                break;
            case 1:
                ingresarEmergencia();
                break;
            case 2:
                ingresarConsulta();
                break;
            case 3:
                calcularSueldo();
                break;
            default:
                throw new OpcionSeleccionadaIncorrectaException(accion);

        }


    }

    private void calcularSueldo() {
    }
}
