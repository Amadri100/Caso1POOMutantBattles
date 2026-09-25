package controlador;

import juego.CampoDeBatalla;
import juego.TipoEquipo;
import modelo.Mutante;
import otros.Constantes;

import java.awt.Color;
import java.io.Console;
import java.util.ArrayList;
import java.awt.Color; // Este import es necesario para usar la clase Color

public class DatosJuego {
    private ArrayList<HiloMutante> listaMutantes;
    private ArrayList<DatosPelea> listaPeleas;
    private String[] simboloPorEquipo;
    private Color[] colorPorEquipo;
    // Pendiente agregar color
    public DatosJuego(Controlador controlador) {
        
        this.listaMutantes = new ArrayList<>();
        this.listaPeleas = new ArrayList<>();
        
        this.simboloPorEquipo = new String[Constantes.CANTIDAD_EQUIPOS];

        this.simboloPorEquipo[TipoEquipo.EQUIPO_A.getIndice()] = TipoEquipo.EQUIPO_A.getSimbolo();

        this.simboloPorEquipo[TipoEquipo.EQUIPO_B.getIndice()] = TipoEquipo.EQUIPO_B.getSimbolo();

        this.colorPorEquipo = controlador.getColor();

        if (controlador != null) {
            for (TipoEquipo tipo : TipoEquipo.values()) {
                for (HiloMutante hilo : controlador.getListaHilosMutantes(tipo)) {
                    this.listaMutantes.add(hilo);
                }
            }
            if (controlador.getCampoDeBatalla() != null) {
                this.colorPorEquipo[TipoEquipo.EQUIPO_A.getIndice()] =
                    controlador.getCampoDeBatalla().getEquipoA().getColor();
                this.colorPorEquipo[TipoEquipo.EQUIPO_B.getIndice()] =
                    controlador.getCampoDeBatalla().getEquipoB().getColor();
            }
            if (controlador.getEjecutorDePeleas() != null) {
                this.listaPeleas.addAll(controlador.getEjecutorDePeleas().drenarPeleasCompletadas());
            }
        }
    }
    
    public Color[] getColorPorEquipo() {
        return this.colorPorEquipo;
    }

    public ArrayList<Mutante> getListaMutantes() {
        ArrayList<Mutante> mutantesDeHilos = new ArrayList<>();
        for (HiloMutante hilo : this.listaMutantes) {
            if (hilo != null && hilo.getMutante() != null) {
                mutantesDeHilos.add(hilo.getMutante());
            }
        }
        return mutantesDeHilos;
    }

    public String[] getSimboloPorEquipo() {
        return this.simboloPorEquipo;
    }

    public ArrayList<HiloMutante> getListaHilosMutantes() {
        return this.listaMutantes;
    }

    public ArrayList<DatosPelea> getListaPeleas() {
        return this.listaPeleas;
    }
}