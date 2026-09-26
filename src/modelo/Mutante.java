package modelo;

import otros.Constantes;
import otros.Matematicas;
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
        this.poder = PoderAleatorio.poderAleatorio();
        this.ataque = Matematicas.intAleatorio(Constantes.ATAQUE_MINIMO, Constantes.ATAQUE_MAXIMO_POR_DEFECTO);
        this.defensa = Matematicas.intAleatorio(Constantes.DEFENSA_MINIMO, Constantes.DEFENSA_MAXIMO);
        switch (this.equipo.getTipoEquipo()) {
            case EQUIPO_A:
                this.posicion = new Punto(Constantes.SIZE_X + 50, Constantes.SIZE_Y - 5);
                break;
            case EQUIPO_B:
                this.posicion = new Punto(Constantes.SIZE_X, Constantes.SIZE_Y);
                break;
        }
    }

    public int getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Equipo getEquipo() {
        return this.equipo;
    }

    public IPower getPoder() {
        return this.poder;
    }

    public void moverse(Punto posicion) {
        this.posicion = posicion;
    }

    public Punto getPosicion() {
        return this.posicion;
    }

    public int atacar() {
        return this.ataque;
    }
    
    public int getVida() {
        return this.vida;
    }

    public void estadoDespuesAtaque(int dañoCausado) {
        if (dañoCausado > 0 && this.ataque < Constantes.ATAQUE_MAXIMO) {
            this.ataque++;
        }
    }


    public int[] recibirDaño(int daño, boolean defiende) {
        int modDefensa = defiende ? 1 : 0; //true = 1, false = 0
        int dañoReal = daño - this.defensa*modDefensa;
        if (dañoReal < 0) {
            dañoReal = 0;
        }
        this.vida -= dañoReal;
        if (this.vida < 0) {
            this.vida = 0;
        }
        int[] retornado = {this.vida, dañoReal};
        return retornado;
    }

    public boolean estaVivo() {
        return this.vida > 0;
    }
}