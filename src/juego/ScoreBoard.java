package juego;

public class ScoreBoard {
    private int mutantesVivos;
    private int mutantesMuertos;
    private int mutantesEquipoA;
    private int mutantesEquipoB;

    public ScoreBoard(CampoDeBatalla campo) {
        actualizar(campo);
    }

    public void actualizar(CampoDeBatalla campo) {
        int[] vivos = campo.getCantidadMutantesVivos();
        int[] muertos = campo.getCantidadMutantesMuertos();
        int vivosA = vivos[0];
        int vivosB = vivos[1];
        int muertosA = muertos[0];
        int muertosB = muertos[1];

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