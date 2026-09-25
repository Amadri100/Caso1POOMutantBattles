package controlador;
import modelo.Mutante;
import otros.Constantes;
import otros.Matematicas;

public class HiloMutante implements Runnable {
    private Mutante mutante;
    private boolean estaVivo;
    private EstadoMutante estado;
    private boolean activo;

    public HiloMutante(Mutante mutante) {
        this.mutante = mutante;
        this.activo = true;
        this.estado = EstadoMutante.MOVIENDOSE;
        this.estaVivo = mutante != null && mutante.estaVivo();
    }

    @Override
    public void run() {
        while (this.mutante.estaVivo() && this.activo) {
            
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void moverse() {

    }
    public void elegirAtaque() {
        if (this.estado == EstadoMutante.MOVIENDOSE) {
            this.estado = EstadoMutante.getEstadoAleatorio();
        }
    }

    public Mutante getMutante() {
        return this.mutante;
    }

    public EstadoMutante getEstado() {
        return this.estado;
    }

    public boolean isActivo() {
        return this.activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }



    public void actualizar() {
        if (this.mutante != null) {
            this.estaVivo = this.mutante.estaVivo();
        }
    }

    public boolean isEstaVivo() {
        return this.estaVivo;
    }
}
