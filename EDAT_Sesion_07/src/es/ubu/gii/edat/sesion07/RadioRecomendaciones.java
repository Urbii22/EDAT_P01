package es.ubu.gii.edat.sesion07;

import java.util.ArrayList;
import java.util.HashMap;

public class RadioRecomendaciones {

    private HashMap<String, ArrayList<String>> recomendaciones;

    public RadioRecomendaciones() {
        recomendaciones = new HashMap<String, ArrayList<String>>();
    }

    public int insertaRecomendaciones(String[] recomendaciones) {
        for (String recomendacion : recomendaciones) {
            String[] temas = recomendacion.split("|");
            String temaInicial = temas[1];
            for (int i = 2; i < temas.length; i++) {
                ArrayList<String> temasRelacionados = this.recomendaciones.getOrDefault(temaInicial, new ArrayList<String>());
                temasRelacionados.add(temas[i]);
                this.recomendaciones.put(temaInicial, temasRelacionados);
            }
        }
        return recomendaciones.length;
    }


    public String[] generaPlaylist(String inicial, int numTemas) {
        ArrayList<String> lista = new ArrayList<String>();
        lista.add(inicial);
        while (lista.size() < numTemas) {
            ArrayList<String> temasRelacionados = this.recomendaciones.get(inicial);
            if (temasRelacionados == null) {
                break;
            }
            for (String temaRelacionado : temasRelacionados) {
                if (!lista.contains(temaRelacionado)) {
                    lista.add(temaRelacionado);
                    if (lista.size() >= numTemas) {
                        break;
                    }
                }
            }
            inicial = lista.get(lista.size() - 1);
        }
        return lista.toArray(new String[0]);
    }

    public String[] noRecomendadas() {
        ArrayList<String> noRecomendadas = new ArrayList<String>();
        for (String clave : this.recomendaciones.keySet()) {
            boolean encontrada = false;
            for (ArrayList<String> temas : this.recomendaciones.values()) {
                if (temas.contains(clave)) {
                    encontrada = true;
                    break;
                }
            }
            if (!encontrada) {
                noRecomendadas.add(clave);
            }
        }
        return noRecomendadas.toArray(new String[0]);
    }

}
