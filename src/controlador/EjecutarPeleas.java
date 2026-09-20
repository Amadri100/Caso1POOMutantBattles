package controlador;

import java.util.concurrent.ConcurrentLinkedQueue;

import otros.Constantes;

public class EjecutarPeleas implements Runnable {
    private ConcurrentLinkedQueue<DatosPelea> referenciaCola;
    private EjecutorDePeleas ejecutor;
    private boolean activo = true;

    public EjecutarPeleas(EjecutorDePeleas ejecutor) {
        this.ejecutor = ejecutor;
        this.referenciaCola = ejecutor.getColaPeleas();
    }

    @Override
    public void run() {
        while (this.activo) {
            DatosPelea datoPelea = this.ejecutor.obtenerDatoCola();
            if (datoPelea != null) {
                procesarPelea(datoPelea);
            } else {
                try {
                    Thread.sleep(Constantes.TIEMPO_ESPERA);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    this.activo = false;
                }
            }
        }
    }

    private void procesarPelea(DatosPelea datoPelea) {
        //Revisa que los 2 mutantes no esten en el estado de movimiento
        //Calcula la distancia y si es menor o igual a la del radio entonces aplica las decisiones de cada uno
        //Si uno de los 2 se estan moviendo entonces 
    }

    public boolean isActivo() {
        return this.activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void terminar() {
        this.activo = false;
    }
}