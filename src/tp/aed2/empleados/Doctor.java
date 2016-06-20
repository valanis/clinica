package tp.aed2.empleados;

import tp.aed2.consultas.Consulta;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Doctor extends Empleado {

    private static final BigDecimal SUELDO = new BigDecimal(50000);

    ArrayList<Consulta> consultasAtendidas;

    public Doctor() {
        this.setSueldoBasico(SUELDO);
        this.consultasAtendidas = new ArrayList<Consulta>();
    }

    public Doctor(BigDecimal sueldo) {
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
        String doctorToString = "[[Doctor]: \n  [Sueldo:" + this.getSueldoBasico() + "], \n" +
                "   [Consultas:\n";
        for (int i = 0; i < this.consultasAtendidas.size(); i++) {
            doctorToString += "         "+ (i+1)+':'+ this.consultasAtendidas.get(i) + "\n";
        }
        doctorToString += "]]";
        return doctorToString;
    }


}
