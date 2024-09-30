import java.util.ArrayList;
import java.util.List;

public class BaseDeHechos {
    private List<String> hechos;

    public BaseDeHechos() {
        this.hechos = new ArrayList<>();
    }

    public void agregarHecho(String hecho) {
        if (!hechos.contains(hecho)) {
            hechos.add(hecho);
        }
    }

    public void eliminarHecho(String hecho) {
        hechos.remove(hecho);
    }

    public boolean contieneHecho(String hecho) {
        return hechos.contains(hecho);
    }

    public List<String> getHechos() {
        return hechos;
    }
    
}
