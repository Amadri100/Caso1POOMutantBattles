package controlador;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import juego.TipoEquipo;
import otros.Constantes;
 
public class EjecutorDePeleas implements Runnable {
    private Controlador controlador;
    private ThreadPoolExecutor executorService;
    private ConcurrentLinkedQueue<DatosPelea> colaPeleas;
    private CopyOnWriteArrayList<DatosPelea> listaPeleasCompletadas;
    private ArrayList<EjecutarPeleas> listaEjecutores;
    private boolean activo = true;

    public EjecutorDePeleas(Controlador controlador) {
        this.controlador = controlador;
        this.executorService =  (ThreadPoolExecutor)Executors.newFixedThreadPool(Constantes.CANTIDAD_THREADS);
        this.colaPeleas = new ConcurrentLinkedQueue<DatosPelea>();
        this.listaPeleasCompletadas = new CopyOnWriteArrayList<DatosPelea>();
        this.listaEjecutores = new ArrayList<EjecutarPeleas>();
        this.listaEjecutores = new ArrayList<EjecutarPeleas>();
        for (int i = 0; i<listaEjecutores.size();i++){
            this.listaEjecutores.set(i, new EjecutarPeleas(this));
        }
    }

    @Override
    public void run() {
        for (int i = 0 ; i < this.controlador.getListaHilosMutantes(TipoEquipo.EQUIPO_A).size(); i++) {
            HiloMutante hiloA = this.controlador.getListaHilosMutantes(TipoEquipo.EQUIPO_A).get(i); 
            for (int j = 0 ; j < this.controlador.getListaHilosMutantes(TipoEquipo.EQUIPO_B).size(); j++) {
                HiloMutante hiloB = this.controlador.getListaHilosMutantes(TipoEquipo.EQUIPO_B).get(j);
            }
        }
        //Revisa la lista de mutantes A uno a uno y luego pasa por los de la lista B
        //Los guarda si el radio entre estos es radio + desplazamientoMaximo como maximo.
        //Inicia los ejecutadores y espera a que terminen
    }

    public DatosPelea obtenerDatoCola() {
        return this.colaPeleas.poll();
    }

    public void agregarPeleaLista(DatosPelea datoPelea) {
        listaPeleasCompletadas.add(datoPelea);
    }

    public void reiniciarMutantes() {

    }

    public ConcurrentLinkedQueue<DatosPelea> getColaPeleas() {
        return this.colaPeleas;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    public boolean isActivo() {
        return this.activo;
    }
}