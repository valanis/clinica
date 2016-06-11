package tp.aed2;

import tp.aed2.consultas.Consulta;
import tp.aed2.empleados.Doctor;
import tp.aed2.obras_sociales.ObraSocial;
import tp.aed2.pacientes.PacienteConCobertura;

public class Main {

    public static void main(String[] args) {

        Doctor doc = new Doctor();
        ObraSocial osde = new ObraSocial("OSDE", 10);
        PacienteConCobertura pacConOsde = new PacienteConCobertura(osde);
        Consulta cons = new Consulta(pacConOsde);
        doc.atender(cons);

        System.out.println(doc);

    }
}
