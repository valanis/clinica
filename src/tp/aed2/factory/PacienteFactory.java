package tp.aed2.factory;

import tp.aed2.obras_sociales.NullObraSocial;
import tp.aed2.obras_sociales.ObraSocial;
import tp.aed2.pacientes.Paciente;
import tp.aed2.pacientes.PacienteMayor;
import tp.aed2.pacientes.PacienteMenor;

public class PacienteFactory {

    public static Paciente getPaciente(Integer dni, Integer edad) {
        return getPaciente(dni, edad, new NullObraSocial());
    }

    public static Paciente getPaciente(Integer dni, Integer edad, ObraSocial os) {
        if(edad < 18) {
            return new PacienteMenor(os, dni, edad);
        } else {
            return new PacienteMayor(os, dni, edad);
        }
    }

}
