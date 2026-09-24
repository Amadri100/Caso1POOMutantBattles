package controlador;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import juego.TipoEquipo;
import otros.Constantes;
import otros.Matematicas;
 
public class EjecutorDePeleas implements Runnable {
    private Controlador controlador;
    private ThreadPoolExecutor executorService;
    private ConcurrentLinkedQueue<DatosPelea> colaPeleas;
    private CopyOnWriteArrayList<DatosPelea> listaPeleasCompletadas;
    private ArrayList<EjecutarPeleas> listaEjecutores;
    private boolean procesados = false;
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
        while(activo) {
            if (!procesados) {
                for (int i = 0 ; i < this.controlador.getListaHilosMutantes(TipoEquipo.EQUIPO_A).size(); i++) {
                HiloMutante hiloA = this.controlador.getListaHilosMutantes(TipoEquipo.EQUIPO_A).get(i); 
                for (int j = 0 ; j < this.controlador.getListaHilosMutantes(TipoEquipo.EQUIPO_B).size(); j++) {
                    HiloMutante hiloB = this.controlador.getListaHilosMutantes(TipoEquipo.EQUIPO_B).get(j);
                    double radio = Matematicas.calcularRadio(hiloA.getMutante().getPosicion(), hiloB.getMutante().getPosicion());
                    if (radio <= Constantes.RADIO_MAXIMO + Constantes.VELOCIDAD) {
                        DatosPelea pelea = new DatosPelea(hiloA, hiloB);
                        this.colaPeleas.add(pelea);
                    }
                }
                this.procesados = !procesados;
                }
                
            }
            try {
                    Thread.sleep(Constantes.DELAY_DE_THREADS);
                } catch (InterruptedException e) {
                    System.out.println("Thread-Ejecutor interumpido");
                    Thread.currentThread().interrupt(); //Limpia la flag de interupción.
            }
        }
       

        //Revisa la lista de mutantes A uno a uno y luego pasa por los de la lista B
        //Los guarda si el radio entre estos es radio + desplazamientoMaximo como maximo.
        //Inicia los ejecutadores y espera a que terminen
    }

    public void iniciarEjecutores() {
        
    }

    public DatosPelea obtenerDatoCola() {
        return this.colaPeleas.poll();
    }

    public void agregarDatoCola(DatosPelea datosPelea) {
        this.colaPeleas.add(datosPelea);
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