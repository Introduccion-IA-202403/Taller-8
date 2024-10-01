import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BaseHechos {
    private List<Hecho> hechos;

    public BaseHechos(String hechos) {
        Parser parser = new Parser();
        this.hechos = parser.transformarArchivoHechos(hechos);
    }

    public void agregarHecho(Hecho hecho) {
        if (!hechos.contains(hecho)) {
            hechos.add(hecho);
        }
    }

    public void eliminarHecho(Hecho hecho) {
        hechos.remove(hecho);
    }

    public boolean contieneHecho(Hecho hecho) {
        return hechos.stream().anyMatch(h -> h.equals(hecho));
    }

    public List<Hecho> getHechos() {
        return hechos;
    }
    
}
