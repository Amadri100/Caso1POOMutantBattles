package controlador;

import juego.CampoDeBatalla;
import juego.TipoEquipo;
import modelo.Mutante;
import java.util.ArrayList;

public class DatosJuego {
    private ArrayList<HiloMutante> listaMutantes;
    private ArrayList<DatosPelea> listaPeleas;
    private String[] simboloPorEquipo;
    // Pendiente agregar color
    public DatosJuego(Controlador controlador) {
        
        this.listaMutantes = new ArrayList<>();
        this.listaPeleas = new ArrayList<>();
        
        int cantidadEquipos = 2; 
        this.simboloPorEquipo = new String[cantidadEquipos];

        this.simboloPorEquipo[TipoEquipo.EQUIPO_A.getIndice()] = "A";

        this.simboloPorEquipo[TipoEquipo.EQUIPO_B.getIndice()] = "B";
        /* 
        if (campoDeBatalla != null) {
            for (Mutante m : campoDeBatalla.getEquipoA().getListaMutantes()) {
                this.listaMutantes.add(new HiloMutante(m));
            }
            for (Mutante m : campoDeBatalla.getEquipoB().getListaMutantes()) {
                this.listaMutantes.add(new HiloMutante(m));
            }
        }*/
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