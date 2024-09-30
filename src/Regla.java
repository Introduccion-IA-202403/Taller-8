import java.util.List;
class Regla {
    private List<String> premisas;
    private String conclusion;

    public Regla(List<String> premisas, String conclusion) {
        this.premisas = premisas;
        this.conclusion = conclusion;
    }

    public List<String> getPremisas() {
        return premisas;
    }

    public String getConclusion() {
        return conclusion;
    }

    @Override
    public String toString() {
        return premisas + " => " + conclusion;
    }
}