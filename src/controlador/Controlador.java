package controlador;

import java.io.Console;
import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.Executors;
import modelo.Mutante;
import juego.CampoDeBatalla;
import juego.TipoEquipo;
import  otros.Constantes;
import controlador.HiloMutante;
import controlador.DatosJuego;

public class Controlador implements IObservable {
    private ArrayList<HiloMutante>[] listaHilosMutantes;
    private ArrayList<IObservador> listaObservadores;
    private EjecutorDePeleas ejecutorDePeleas;
    private Notificador notificador;
    private ThreadPoolExecutor executorService;
    private CampoDeBatalla campoDeBatalla;
    private DatosJuego datosJuego;

    @SuppressWarnings("unchecked")
    public Controlador() {
        this.listaHilosMutantes = new ArrayList[TipoEquipo.values().length]; //Unchecked warning
        for (int i = 0; i < TipoEquipo.values().length; i++) {
            this.listaHilosMutantes[i] = new ArrayList<HiloMutante>();
        }
        this.listaObservadores = new ArrayList<IObservador>();
        this.ejecutorDePeleas = new EjecutorDePeleas(this);
        this.notificador = new Notificador(this);
        this.executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        
    }

    public void iniciarJuego(int cantidad) {
        this.campoDeBatalla = new CampoDeBatalla(cantidad);
        for (int i = 0; i < cantidad; i++) {
            Mutante mutanteA = this.campoDeBatalla.getEquipoA().getListaMutantes().get(i);
            Mutante mutanteB = this.campoDeBatalla.getEquipoB().getListaMutantes().get(i);
            this.listaHilosMutantes[TipoEquipo.EQUIPO_A.getIndice()].add(new HiloMutante(mutanteA));
            this.listaHilosMutantes[TipoEquipo.EQUIPO_B.getIndice()].add(new HiloMutante(mutanteB));
        }
        
    }

    public void obtenerDatosJuego() {
        // Pendiente agregar más detalles...
        this.datosJuego = new DatosJuego(this);
    }

    @Override
    public void agregaObservadores(IObservador observador) {
        this.listaObservadores.add(observador);
    }

    @Override
    public void quitarObservadores(IObservador observador) {
        this.listaObservadores.remove(observador);
    }

    @Override
    public void notificar() {
        for (IObservador observador : this.listaObservadores) {
            observador.actualizar(this.datosJuego);
        }
    }

    public CampoDeBatalla getCampoDeBatalla() {
        return this.campoDeBatalla;
    }

    public DatosJuego getDatosJuego() {
        return this.datosJuego;
    }

    public EjecutorDePeleas getEjecutorDePeleas() {
        return this.ejecutorDePeleas;
    }

    public ArrayList<HiloMutante> getListaHilosMutantes(TipoEquipo tipo) {
        return this.listaHilosMutantes[tipo.getIndice()];
    }

    public ArrayList<IObservador> getListaObservadores() {
        return this.listaObservadores;
    }

    public int[] getMedidasCampoBatalla() {
        return new int[] { this.campoDeBatalla.getSizeX(), this.campoDeBatalla.getSizeY() };
    }
}