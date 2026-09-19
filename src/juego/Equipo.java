package juego;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import modelo.Mutante;
import otros.Constantes;

public class Equipo {
    private ArrayList<Mutante> listaMutantes;
    private TipoEquipo cualEquipo;
    private String simbolo;
    private Color color;
    private int cantidadMutantesVivos;
    private int cantidadMutantesMuertos;

    public Equipo(TipoEquipo equipo, int cantidad) {
        this.listaMutantes = new ArrayList<Mutante>();
        this.cualEquipo = equipo;
        int indiceEquipo = this.cualEquipo.getIndice();
        

        this.cantidadMutantesVivos = cantidad;
        this.cantidadMutantesMuertos = Constantes.MUTANTES_MUERTOS_INICIAL;

        this.simbolo = Constantes.SIMBOLOS[indiceEquipo];
        Random rand = new Random();
        this.color = Constantes.COLORES[rand.nextInt(Constantes.COLORES.length)][indiceEquipo];
         
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public Color getColor() {
        return this.color;
    }

    public String getSimbolo() {
        return this.simbolo;
    }

    public ArrayList<Mutante> getListaMutantes() {
        return this.listaMutantes;
    }

    public TipoEquipo getTipoEquipo() {
        return this.cualEquipo;
    }

    public int getCantidadMutantesVivos() {
        return this.cantidadMutantesVivos;
    }

    public int getCantidadMutantesMuertos() {
        return this.cantidadMutantesMuertos;
    }

    public void registrarMuerte(TipoEquipo dato) {
        // Un mutante muere: baja uno de vivos y sube uno de muertos
        if (this.cantidadMutantesVivos > 0) {
            this.cantidadMutantesVivos--;
            this.cantidadMutantesMuertos++;
        }
    }
}