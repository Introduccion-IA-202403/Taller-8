import java.util.List;
import java.util.Stack;

public class MotorDeInferencia {
    private List<Regla> reglas;
    private BaseDeHechos baseDeHechos;
    private Stack<Regla> pilaBacktracking;

    public MotorDeInferencia(List<Regla> reglas, BaseDeHechos baseDeHechos) {
        this.reglas = reglas;
        this.baseDeHechos = baseDeHechos;
        this.pilaBacktracking = new Stack<>();
    }

    // Encadenamiento hacia atrás con backtracking
    public boolean inferenciaAtras(String meta) {
        if (baseDeHechos.contieneHecho(meta)) {
            return true; // Meta ya está en la base de hechos
        }

        for (Regla regla : reglas) {
            if (regla.getConclusion().equals(meta)) {
                System.out.println("Intentando demostrar: " + meta);
                boolean todasPremisasVerdaderas = true;

                for (String premisa : regla.getPremisas()) {
                    if (!inferenciaAtras(premisa)) {
                        todasPremisasVerdaderas = false;
                        break;
                    }
                }

                if (todasPremisasVerdaderas) {
                    baseDeHechos.agregarHecho(meta);
                    pilaBacktracking.push(regla);
                    System.out.println("Regla aplicada: " + regla + "\n");
                    return true;
                }
            }
        }

        return false; // No se pudo demostrar la meta
    }

}
