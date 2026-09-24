package controlador;
import modelo.Mutante;

public class HiloMutante implements Runnable {
    private Mutante mutante;
    private EstadoMutante estado;
    private boolean activo;

    public HiloMutante(Mutante mutante) {
        this.mutante = mutante;
        this.activo = true;
        this.estado = EstadoMutante.MOVIENDOSE;
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
}
