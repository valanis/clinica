package tp.aed2.empleados;

import tp.aed2.consultas.Consulta;
import java.util.ArrayList;

public class Doctor extends Empleado {

    private static final Long SUELDO = Long.valueOf(50000);

    ArrayList<Consulta> consultasAtendidas;

    public Doctor() {
        this.setSueldoBasico(SUELDO);
        this.consultasAtendidas = new ArrayList<Consulta>();
    }

    public Doctor(Long sueldo) {
        this.setSueldoBasico(sueldo);
        this.consultasAtendidas = new ArrayList<Consulta>();
    }

    public ArrayList<Consulta> getConsultasAtendidas() {
        return consultasAtendidas;
    }

    public void atender(Consulta consulta) {
        this.consultasAtendidas.add(consulta);
    }

    @Override
    public String toString() {
        return "[[Doctor]: [Sueldo:" + this.getSueldoBasico() + "], [Consultas: "+ this.consultasAtendidas + "]]";
    }

}
