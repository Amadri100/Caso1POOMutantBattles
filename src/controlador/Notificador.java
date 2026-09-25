package controlador;

import java.lang.invoke.ConstantCallSite;

import otros.Constantes;

public class Notificador implements Runnable{
    private Controlador controlador;
    private boolean activo = true;
    private volatile boolean pausado = false;
    public Notificador(Controlador pControlador) {
        this.controlador = pControlador;
    }
    @Override
    public void run() {
        while(activo) {
            if (!pausado) {
                controlador.notificar();
            }
            
            try {
                Thread.sleep(Constantes.DELAY_DE_THREADS);
            } catch (InterruptedException e) {
                System.out.println("Thread-Notificador interumpido");
                Thread.currentThread().interrupt(); //Limpia la flag de interupción.
            }
        }
    }
    public void setActivo(boolean valor) {
        this.activo = valor;
    }
    public boolean getActivo() {
        return this.activo;
    }
    public void setPausado(boolean pausado) {
        this.pausado = pausado;
    }

    public boolean isPausado() {
        return this.pausado;
    }

}
