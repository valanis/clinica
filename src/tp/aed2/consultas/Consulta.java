package tp.aed2.consultas;

import tp.aed2.empleados.Administrativo;
import tp.aed2.pacientes.Paciente;
import tp.aed2.pacientes.PacienteConCobertura;
import tp.aed2.pacientes.PacienteSinCoberturaMayor;
import tp.aed2.pacientes.PacienteSinCoberturaMenor;

public class Consulta {

    private static final Long VALOR_BASE = Long.valueOf(100);

    Long valor;
    Paciente paciente;

    public Consulta(PacienteConCobertura paciente) {
        Integer porcentaje = paciente.getOs().getPorcentajeDescuento();
        this.valor = VALOR_BASE - (VALOR_BASE * porcentaje / 100);
        this.paciente = paciente;
    }

    public Consulta(PacienteSinCoberturaMayor paciente) {
        this.valor = VALOR_BASE;
        this.paciente = paciente;
    }

    public Consulta(PacienteSinCoberturaMenor paciente, Administrativo admin) {
        this.valor = admin.cotizarConsulta(paciente);
        this.paciente = paciente;
    }
}
