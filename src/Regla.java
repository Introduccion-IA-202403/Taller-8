import java.util.ArrayList;
import java.util.List;

enum TipoRegla {
    AND, // Quiere decir que todas las reglas o hechos tienen que ser validados por AND
    OR
}

class Regla {
    private List<Object> premisas; // Object porque Hecho | Regla
    private Hecho conclusion;
    private TipoRegla tipo;

    public Regla(){
        premisas = new ArrayList<>();
        conclusion = new Hecho();
        tipo = TipoRegla.AND;
    }

    public Regla(List<Object> premisas, Hecho conclusion, TipoRegla tipo) {
        this.premisas = premisas;
        this.conclusion = conclusion;
        this.tipo = tipo;
    }

    public List<Object> getPremisas() {
        return premisas;
    }

    public void setPremisas(List<Object> premisas) {
        this.premisas = premisas;
    }

    public Hecho getConclusion() {
        return conclusion;
    }

    public void setConclusion(Hecho conclusion) {
        this.conclusion = conclusion;
    }

    public TipoRegla getTipo() {
        return tipo;
    }

    public void setTipo(TipoRegla tipo) {
        this.tipo = tipo;
    }

    public void addPremisa(Object premisa) {
        premisas.add(premisa);
    }
}