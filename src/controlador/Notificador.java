package controlador;


public class Notificador implements Runnable{
    private Controlador controlador;
    private boolean activo = true;
    public Notificador(Controlador pControlador) {
        this.controlador = pControlador;
    }
    @Override
    public void run() {
        //Accede a controlador cada cierto tiempo para mandar la notificación a la UI
        //while(activo) {actualizar; sleep(Constante.tasaRefresco);}
    }
    public void setActivo(boolean valor) {
        this.activo = valor;
    }
    public boolean getActivo() {
        return this.activo;
    }
}
