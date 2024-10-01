import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private int i;

    ArrayList<Hecho> transformarArchivoHechos(String archivo) {
        ArrayList<Hecho> hechos = new ArrayList<>();
        try {
            List<String> lineas = Files.readAllLines(Paths.get(archivo));
            for (String line : lineas) {
                Hecho hecho = new Hecho();
                String[] tokens = line.split(" ");
                if (tokens.length == 2) {
                    hecho.setNOT(true);
                    hecho.setId(tokens[1]);
                } else {
                    hecho.setId(tokens[0]);
                }
                hechos.add(hecho);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hechos;
    }

    ArrayList<Regla> transformarArchivoReglas(String archivo) {
        ArrayList<Regla> reglas = new ArrayList<>();
        try {
            List<String> lineas = Files.readAllLines(Paths.get(archivo));
            for (String line : lineas) {
                reglas.add(trasnformarRegla(line, 0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reglas;
    }

    Regla trasnformarRegla(String linea, int idx) {
        Regla respuesta = transformarHechos(linea, idx);
        String[] partes = linea.split(" ");
        if (partes[partes.length-2].equals("IMPLIES")) {
            respuesta.setConclusion(new Hecho(false, partes[partes.length-1]));
        } else if (partes[partes.length-2].equals("NOT")) {
            respuesta.setConclusion(new Hecho(true, partes[partes.length-1]));
        }
        return respuesta;
    }

    Regla transformarHechos(String linea, int idx) {
        Regla regla = new Regla();
        Hecho hecho = new Hecho();
        i = idx;
        i++;
        StringBuilder token = new StringBuilder();
        while (i < linea.length()) {
            char c = linea.charAt(i);
            if (c == ')') {
                if (!token.isEmpty()) {
                    hecho.setId(token.toString());
                    regla.addPremisa(hecho);
                }
                break;
            }
            if (c == ' ') {
                if (token.length() == 1) {
                    hecho.setId(token.toString());
                    token = new StringBuilder();
                    regla.addPremisa(hecho);
                    hecho = new Hecho();
                } else {
                    if (token.toString().equals("NOT")) {
                        hecho.setNOT(true);
                    }
                    if (token.toString().equals("AND")) {
                        regla.setTipo(TipoRegla.AND);
                    }
                    if (token.toString().equals("OR")) {
                        regla.setTipo(TipoRegla.OR);
                    }
                    token = new StringBuilder();
                }
            } else if (c == '(') {
                regla.addPremisa(transformarHechos(linea, i));
            } else token.append(c);
            i++;
        }
        return regla;
    }
}
