package tp.aed2.pacientes;

import tp.aed2.obras_sociales.ObraSocial;

public class PacienteConCobertura extends Paciente {

    ObraSocial os;

    public PacienteConCobertura(ObraSocial os) {
        this.os = os;
    }

    public ObraSocial getOs() {
        return os;
    }
}
