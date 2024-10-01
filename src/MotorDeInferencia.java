import java.util.ArrayList;
import java.util.HashMap;

public class MotorDeInferencia {
    private ArrayList<Regla> reglas;
    private BaseHechos baseDeHechos;
    private HashMap<Hecho, Boolean> visitados;

    public MotorDeInferencia(String reglas, String hechos) {
        Parser parser = new Parser();
        this.reglas = parser.transformarArchivoReglas(reglas);
        this.baseDeHechos = new BaseHechos(hechos);
        this.visitados = new HashMap<>();
    }

    private boolean evaluarPremisas(Regla regla) {

        if (regla.getTipo() == TipoRegla.AND) {
            boolean curr = true;
            for (Object premisa: regla.getPremisas()) {
                if (premisa instanceof Regla) {
                    if (!evaluarPremisas((Regla) premisa)) {
                        return false;
                    }
                } else if (premisa instanceof Hecho) {
                    curr = curr && (((Hecho) premisa).getNOT() != baseDeHechos.contieneHecho(((Hecho) premisa)));
                    if (!curr) return false;
                }
            }
            return true;
        } else {
            for (Object premisa: regla.getPremisas()) {
                if (premisa instanceof Regla) {
                    if (evaluarPremisas((Regla) premisa)) return true;
                } else {
                    if ((((Hecho) premisa).getNOT() != baseDeHechos.contieneHecho(((Hecho) premisa)))) return true;
                }
            }
            return false;
        }
    }

    public BaseHechos inferenciaAdelante() {

        boolean visitado = true;
        while (visitado) {
            visitado = false;
            for (Regla regla : reglas) {
                if (evaluarPremisas(regla) && !baseDeHechos.contieneHecho(regla.getConclusion())) {
                    visitado = true;
                    baseDeHechos.agregarHecho(regla.getConclusion());
                }
            }
        }
        return baseDeHechos;
    }

    private boolean backtrackPremisas(Regla regla) {
        if (regla.getTipo() == TipoRegla.AND) {
            for (Object premisa: regla.getPremisas()) {
                if (premisa instanceof Regla) {
                    if (!backtrackPremisas((Regla) premisa)) return false;
                } else {
                    if (!inferenciaAtras((Hecho) premisa)) return false;
                }
            }
            return true;
        } else {
            for (Object premisa: regla.getPremisas()) {
                if (premisa instanceof Regla) {
                    if (backtrackPremisas((Regla) premisa)) return true;
                } else {
                    if (inferenciaAtras((Hecho) premisa)) return true;
                }
            }
            return false;
        }
    }

    // Encadenamiento hacia atrás con backtracking
    public boolean inferenciaAtras(Hecho meta) {
        if (baseDeHechos.contieneHecho(meta)) {
            return true; // Meta ya está en la base de hechos
        }

        for (Regla regla : reglas) {
            if (regla.getConclusion().equals(meta)) {
                boolean valido;

                if (regla.getTipo() == TipoRegla.AND) {
                    valido = true;
                    for (Object premisa : regla.getPremisas()) {
                        if (premisa instanceof Regla) {
                            if (!backtrackPremisas((Regla) premisa)) {
                                valido = false;
                                break;
                            }
                        } else if (premisa instanceof Hecho) {
                            if (!inferenciaAtras((Hecho) premisa)) {
                                valido = false;
                                break;
                            }
                        }
                    }
                } else {
                    valido = false;
                    for (Object premisa : regla.getPremisas()) {
                        if (premisa instanceof Regla) {
                            if (backtrackPremisas((Regla) premisa)){
                                valido = true;
                                break;
                            }
                        } else if (premisa instanceof Hecho) {
                            if (inferenciaAtras((Hecho) premisa)) {
                                valido = true;
                                break;
                            }
                        }
                    }
                }

                if (valido) {
                    //baseDeHechos.agregarHecho(meta);
                    return true;
                }
            }
        }
        return false; // No se pudo demostrar la meta
    }

}
