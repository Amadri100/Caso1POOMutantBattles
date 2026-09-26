package controlador;
import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.Executors;
import modelo.Mutante;
import juego.CampoDeBatalla;
import juego.TipoEquipo;
import  otros.Constantes;
import otros.Matematicas;
import java.awt.Color;

public class Controlador implements IObservable {
    private ArrayList<HiloMutante>[] listaHilosMutantes;
    private ArrayList<IObservador> listaObservadores;
    private EjecutorDePeleas ejecutorDePeleas;
    private Notificador notificador;
    private ThreadPoolExecutor executorService;
    private CampoDeBatalla campoDeBatalla;
    private DatosJuego datosJuego;
    private boolean simulacionIniciada = false;
    private Color[] colorSeleccionado;

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
    public void iniciarSimulacion(int cantidadPorEquipo) {
        this.colorSeleccionado = Constantes.COLORES[Matematicas.intAleatorio(0, Constantes.COLORES.length-1)];
        this.iniciarJuego(cantidadPorEquipo);
        for (TipoEquipo tipo : TipoEquipo.values()) {
            for (HiloMutante hilo : this.getListaHilosMutantes(tipo)) {
                this.executorService.execute(hilo);
            }
        }
        this.executorService.execute(this.ejecutorDePeleas);
        this.executorService.execute(this.notificador);
        this.simulacionIniciada = true;
    }


    public void iniciarJuego(int cantidad) {
        this.campoDeBatalla = new CampoDeBatalla(cantidad);
        for (int i = 0; i < cantidad; i++) {
            Mutante mutanteA = this.campoDeBatalla.getEquipoA().getListaMutantes().get(i);
            Mutante mutanteB = this.campoDeBatalla.getEquipoB().getListaMutantes().get(i);
            this.listaHilosMutantes[TipoEquipo.EQUIPO_A.getIndice()].add(new HiloMutante(mutanteA, this));
            this.listaHilosMutantes[TipoEquipo.EQUIPO_B.getIndice()].add(new HiloMutante(mutanteB, this));
        }
        
    }

    public void resetEstadoMutantes() {
        for (TipoEquipo tipo : TipoEquipo.values()) {
            for (HiloMutante hilo : this.listaHilosMutantes[tipo.getIndice()]) {
                hilo.volverAEstadoBase();
            }
        }
    }

    
    public void verificacionesFinCiclo() {
        this.campoDeBatalla.actualizarScoreBoard();
    }

    public void pausar() {
        this.ejecutorDePeleas.setPausado(true);
        this.notificador.setPausado(true);
    }

    public void reanudar() {
        this.ejecutorDePeleas.setPausado(false);
        this.notificador.setPausado(false);
    }

    public void reiniciar(int cantidadPorEquipo) {
        
        this.limpiarListas();   
        this.ejecutorDePeleas = new EjecutorDePeleas(this);
        this.notificador = new Notificador(this);
        iniciarSimulacion(cantidadPorEquipo);
        System.out.println("En teoria se reinicia");
    }

    public void limpiarListas() {
        this.detener();
        this.ejecutorDePeleas.setPausado(false);
        this.notificador.setPausado(false);
        for (TipoEquipo tipo : TipoEquipo.values()) {
            this.listaHilosMutantes[tipo.getIndice()].clear();
        }
    }

    // CAMBIO (UI): detiene ordenadamente los hilos de la simulación actual.
    public void detener() {
        this.ejecutorDePeleas.setActivo(false);
        this.notificador.setActivo(false);
        for (TipoEquipo tipo : TipoEquipo.values()) {
            for (HiloMutante hilo : this.getListaHilosMutantes(tipo)) {
                hilo.setActivo(false);
            }
        }
    }
    public void obtenerDatosJuego() {
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
        this.obtenerDatosJuego();
        if (this.campoDeBatalla != null) {
            this.campoDeBatalla.getScoreBoard().actualizar(this.campoDeBatalla);
        }
        for (IObservador observador : this.listaObservadores) {
            observador.actualizar(this.datosJuego);
        }
    }

    public Color[] getColor() {
        return this.colorSeleccionado;
    }

    public boolean isSimulacionIniciada() {
        return this.simulacionIniciada;
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
    }//Pullprueba
}