package controlador;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * EjecutorDePeleas
 */
public class EjecutorDePeleas implements Runnable {
    private Controlador controlador;
    private ThreadPoolExecutor executorService;
    private ConcurrentLinkedQueue<int[]> colaPeleas;
    private CopyOnWriteArrayList<DatosPelea> listaPeleasCompletadas;
    private ArrayList<EjecutarPeleas> listaEjecutores;
    private boolean activo;

    @Override
    public void run() {
 
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    public boolean isActivo() {
        return this.activo;
    }
    
}