package controlador;


import otros.Punto;

public class DatosPelea {
    private HiloMutante mutanteA;
    private EstadoMutante decisionA;
    private HiloMutante mutanteB;
    private EstadoMutante decisionB;
    private Punto posA;
    private Punto posB;

    public DatosPelea(HiloMutante hiloMutanteA, HiloMutante hiloMutanteB) {
        this.mutanteA = hiloMutanteA;
        this.mutanteB = hiloMutanteB;
    }

    public void guardarDatos() {
        this.decisionA = this.mutanteA.getEstado();
        this.decisionB = this.mutanteB.getEstado();
        this.posA = this.mutanteA.getMutante().getPosicion();
        this.posB = this.mutanteB.getMutante().getPosicion();
    }

    public HiloMutante getMutanteA() {
        return this.mutanteA;
    }

    public EstadoMutante getDecisionA() {
        return this.decisionA;
    }

    public HiloMutante getMutanteB() {
        return this.mutanteB;
    }

    public EstadoMutante getDecisionB() {
        return this.decisionB;
    }

    public Punto getPosA() {
        return this.posA;
    }

    public Punto getPosB() {
        return this.posB;
    }
}