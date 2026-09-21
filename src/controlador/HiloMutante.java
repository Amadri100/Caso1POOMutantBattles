package controlador;
import modelo.Mutante;

public class HiloMutante implements Runnable {
    private Mutante mutante;
    private boolean activo;

    public HiloMutante(Mutante mutante) {
        this.mutante = mutante;
        this.activo = true;
    }

    public Mutante getMutante() {
        return this.mutante;
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
