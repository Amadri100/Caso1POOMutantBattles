package juego;

public class ScoreBoard {
    private int mutantesVivos;
    private int mutantesMuertos;
    private int mutantesEquipoA;
    private int mutantesEquipoB;

    public ScoreBoard(Equipo equipoA, Equipo equipoB) {
        actualizar(equipoA, equipoB);
    }

    public void actualizar(Equipo equipoA, Equipo equipoB) {
        int vivosA = equipoA.getCantidadMutantesVivos(TipoEquipo.EQUIPO_A);
        int vivosB = equipoB.getCantidadMutantesVivos(TipoEquipo.EQUIPO_B);
        int muertosA = equipoA.getCantidadMutantesMuertos(TipoEquipo.EQUIPO_A);
        int muertosB = equipoB.getCantidadMutantesMuertos(TipoEquipo.EQUIPO_B);

        this.mutantesEquipoA = vivosA;
        this.mutantesEquipoB = vivosB;
        this.mutantesVivos = vivosA + vivosB;
        this.mutantesMuertos = muertosA + muertosB;
    }

    @Override
    public String toString() {
        String texto = 
        "Mutantes vivos totales: " + this.mutantesVivos + '\n' +
        "Mutantes muertos totales: " + this.mutantesMuertos+ '\n'  +
        "Mutantes vivos Equipo A: " + this.mutantesEquipoA + '\n' +
        "Mutantes vivos Equipo B: " + this.mutantesEquipoB;
        return texto;
    }

    public int getMutantesVivos() {
        return this.mutantesVivos;
    }

    public int getMutantesMuertos() {
        return this.mutantesMuertos;
    }

    public int getMutantesEquipoA() {
        return this.mutantesEquipoA;
    }

    public int getMutantesEquipoB() {
        return this.mutantesEquipoB;
    }
}