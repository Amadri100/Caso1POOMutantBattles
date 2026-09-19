package juego;

import otros.Constantes;
import otros.Punto;

public class CampoDeBatalla {
    private int sizeX;
    private int sizeY;
    private Equipo equipoA;
    private Equipo equipoB;
    private ScoreBoard scoreBoard;

    public CampoDeBatalla(int size) {
        this.sizeX = Constantes.SIZE_X;
        this.sizeY = Constantes.SIZE_Y;
        this.equipoA = new Equipo(TipoEquipo.EQUIPO_A, size);
        this.equipoB = new Equipo(TipoEquipo.EQUIPO_B, size);
        this.scoreBoard = new ScoreBoard(this.equipoA, this.equipoB);
    }

    public boolean terminoElJuego() {
        boolean equipoASinVivos = this.equipoA.getCantidadMutantesVivos(TipoEquipo.EQUIPO_A) == 0;
        boolean equipoBSinVivos = this.equipoB.getCantidadMutantesVivos(TipoEquipo.EQUIPO_B) == 0;
        return equipoASinVivos || equipoBSinVivos; //or logico
    }

    public Punto getPuntoMedio() {
        return new Punto(this.sizeX / 2, this.sizeY / 2);
    }

    public Punto getPuntoMaximo() {
        return new Punto(this.sizeX, this.sizeY);
    }

    public Equipo getEquipoA() {
        return this.equipoA;
    }

    public Equipo getEquipoB() {
        return this.equipoB;
    }

    public ScoreBoard getScoreBoard() {
        return this.scoreBoard;
    }

    public int getSizeX() {
        return this.sizeX;
    }

    public int getSizeY() {
        return this.sizeY;
    }
}