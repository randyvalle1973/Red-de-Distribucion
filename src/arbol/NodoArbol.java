package arbol;

import java.util.LinkedList;

public class NodoArbol {
    private String nombre;
    private LinkedList<NodoArbol> hijos;

    public NodoArbol(String nombre) {
        this.nombre = nombre;
        hijos = new LinkedList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LinkedList<NodoArbol> getHijos() {
        return hijos;
    }

    public void setHijos(LinkedList<NodoArbol> hijos) {
        this.hijos = hijos;
    }
}
