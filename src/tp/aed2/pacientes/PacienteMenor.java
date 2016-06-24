package tp.aed2.pacientes;

import tp.aed2.obras_sociales.IObraSocial;

public class PacienteMenor extends Paciente {

    public PacienteMenor(IObraSocial os, Integer dni, Integer edad) {
        super(os, dni, edad);
    }

    @Override
    public Boolean esMayorDeEdad() {
        return false;
    }

}
