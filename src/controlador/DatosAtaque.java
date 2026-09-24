package controlador;

import modelo.IPower; 
import modelo.Mutante;
import otros.Punto;

public class DatosAtaque {
    private IPower poder; 
    private Mutante mutanteOrigen;
    private Mutante mutanteDestino;

    public DatosAtaque(DatosPelea datoPelea, int numMutante) {
        if (numMutante == 1) {
            this.mutanteOrigen = datoPelea.getMutanteA().getMutante();
            this.mutanteDestino = datoPelea.getMutanteB().getMutante();
            this.poder = this.mutanteOrigen.getPoder(); 
        } else {
            this.mutanteOrigen = datoPelea.getMutanteB().getMutante();
            this.mutanteDestino = datoPelea.getMutanteA().getMutante();
            this.poder = this.mutanteOrigen.getPoder(); 
        }
    }

    public DatosAtaque(DatosAtaque ataque) {
        if (ataque != null) {
            this.poder = ataque.poder;
            this.mutanteOrigen = ataque.mutanteOrigen;
            this.mutanteDestino = ataque.mutanteDestino;
        }
    }

    public IPower getPoder() {
        return this.poder;
    }

    public Punto getOrigen() {
        return this.mutanteOrigen != null ? this.mutanteOrigen.getPosicion() : null;
    }

    public Punto getDestino() {
        return this.mutanteDestino != null ? this.mutanteDestino.getPosicion() : null;
    }
}