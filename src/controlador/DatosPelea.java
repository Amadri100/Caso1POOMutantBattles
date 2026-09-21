package controlador;

import controlador.HiloMutante;
import modelo.Mutante;
import otros.Punto;

public class DatosPelea {
    private Mutante mutanteA;
    private EstadoMutante decisionA;
    private Mutante mutanteB;
    private EstadoMutante decisionB;
    private Punto posA;
    private Punto posB;

    public DatosPelea(HiloMutante hiloMutanteA, HiloMutante hiloMutanteB) {
        
        this.mutanteA = hiloMutanteA.getMutante();
        this.mutanteB = hiloMutanteB.getMutante();
        
       
    }

    public Mutante getMutanteA() {
        return this.mutanteA;
    }

    public EstadoMutante getDecisionA() {
        return this.decisionA;
    }

    public Mutante getMutanteB() {
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