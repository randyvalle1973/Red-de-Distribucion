//Clase para guardar los resultados del dijkstra
package Grafo;

public class resultado {
    private String destino;
    private int distancia;
    private String ruta;

    public resultado(String destino, int distancia, String ruta) {
        this.destino = destino;
        this.distancia = distancia;
        this.ruta = ruta;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
