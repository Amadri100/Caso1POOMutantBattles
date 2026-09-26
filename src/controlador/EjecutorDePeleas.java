package controlador;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

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
    private volatile boolean pausado = false;
    private AtomicInteger tareasPendientes = new AtomicInteger(0); //Cuenta ejecutores activos

    public EjecutorDePeleas(Controlador controlador) {
        this.controlador = controlador;
        this.executorService =  (ThreadPoolExecutor)Executors.newFixedThreadPool(Constantes.CANTIDAD_THREADS);
        this.colaPeleas = new ConcurrentLinkedQueue<DatosPelea>();
        this.listaPeleasCompletadas = new CopyOnWriteArrayList<DatosPelea>();
        this.listaEjecutores = new ArrayList<EjecutarPeleas>();
        this.listaEjecutores = new ArrayList<EjecutarPeleas>();
        for (int i = 0; i<Constantes.CANTIDAD_THREADS;i++){
            this.listaEjecutores.add(new EjecutarPeleas(this));
        }
    }

    @Override
    public void run() {
        while(activo) {
            if (!pausado) {
                if (!procesados) {
                    for (int i = 0 ; i < this.controlador.getListaHilosMutantes(TipoEquipo.EQUIPO_A).size(); i++) {
                        HiloMutante hiloA = this.controlador.getListaHilosMutantes(TipoEquipo.EQUIPO_A).get(i); 
                        for (int j = 0 ; j < this.controlador.getListaHilosMutantes(TipoEquipo.EQUIPO_B).size(); j++) {
                            HiloMutante hiloB = this.controlador.getListaHilosMutantes(TipoEquipo.EQUIPO_B).get(j);
                            if (!hiloA.isEstaVivo() || !hiloB.isEstaVivo()) {
                                continue; // para no armar peleas con mutantes muertos
                            }
                            double radio = Matematicas.calcularRadio(hiloA.getMutante().getPosicion(), hiloB.getMutante().getPosicion());
                            if (radio <= Constantes.RADIO_MAXIMO + Constantes.VELOCIDAD) {
                                DatosPelea pelea = new DatosPelea(hiloA, hiloB);
                                this.colaPeleas.add(pelea);
                                this.tareasPendientes.incrementAndGet();
                            }
                        }
                    }
                    for (EjecutarPeleas ejecutador : this.listaEjecutores) {
                            
                            ejecutador.iniciar(); //Hace todo el setup para prepararse para iniciar
                            this.executorService.execute(ejecutador);
                        }
                    this.procesados = !procesados;
                }
                else {
                    if (this.tareasPendientes.get() <= 0) {
                        this.tareasPendientes.set(0);;
                        this.procesados = !procesados;
                        this.reiniciarMutantes();
                    }
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

    public int obtenerTotalTareas() {
        return this.tareasPendientes.get();
    }

    public void tareaTerminada() { //Funcion usada por un ejecutador para indicar que se desconecta
        this.tareasPendientes.decrementAndGet();
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
        this.controlador.resetEstadoMutantes();
    }

    public ConcurrentLinkedQueue<DatosPelea> getColaPeleas() {
        return this.colaPeleas;
    }

    public ArrayList<DatosPelea> drenarPeleasCompletadas() {
        ArrayList<DatosPelea> copia = new ArrayList<DatosPelea>(this.listaPeleasCompletadas);
        this.listaPeleasCompletadas.removeAll(copia);
        return copia;
    }

    public synchronized void setActivo(boolean activo) {
        this.activo = activo;
    }
    public boolean isActivo() {
        return this.activo;
    }

        public void setPausado(boolean pausado) {
        this.pausado = pausado;
    }

    public boolean isPausado() {
        return this.pausado;
    }
}
