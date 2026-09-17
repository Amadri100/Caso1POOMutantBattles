package modelo;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

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
    }
 
}