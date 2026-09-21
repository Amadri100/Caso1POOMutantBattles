package controlador;

import juego.CampoDeBatalla;
import modelo.Mutante;
import java.util.ArrayList;

public class DatosJuego {
    private ArrayList<HiloMutante> listaMutantes;

    public DatosJuego(CampoDeBatalla campoDeBatalla) {
        this.listaMutantes = new ArrayList<>();

     
        for (Mutante mutante : campoDeBatalla.getEquipoA().getListaMutantes()) {
            HiloMutante hilo = new HiloMutante(mutante);
            this.listaMutantes.add(hilo);
        }

        for (Mutante mutante : campoDeBatalla.getEquipoB().getListaMutantes()) {
            HiloMutante hilo = new HiloMutante(mutante);
            this.listaMutantes.add(hilo);
        }
    }
}