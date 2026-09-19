package modelo;

import java.util.Random;
import otros.Constantes;
import juego.Equipo;
import otros.Punto;

public class Mutante {
    private int id;
    private String nombre;
    private Equipo equipo;
    private int vida;
    private IPower poder;
    private Punto posicion;
    private int ataque;
    private int defensa;
    public Mutante(int pId, String pNombre, Equipo pEquipo) {
        this.id = pId;
        this.nombre = pNombre + id;
        this.equipo = pEquipo;
        this.vida = otros.Constantes.MAXIMO_VIDA;
        Random rand = new Random();
        this.ataque = rand.nextInt(Constantes.ATAQUE_MAXIMO_POR_DEFECTO - Constantes.ATAQUE_MINIMO) + Constantes.ATAQUE_MINIMO;
        this.defensa = rand.nextInt(Constantes.DEFENSA_MAXIMO - Constantes.DEFENSA_MINIMO) + Constantes.DEFENSA_MINIMO;
        this.posicion = new Punto(0, 0);
        //if pEquipo = B -> Punto(SIZEX,SIZEY)
    }
 
}