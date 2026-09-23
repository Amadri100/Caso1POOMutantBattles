package controlador;

import juego.CampoDeBatalla;
import juego.TipoEquipo;
import modelo.Mutante;
import java.util.ArrayList;

public class DatosJuego {
    private ArrayList<HiloMutante>[] listaMutantes;
    @SuppressWarnings("unchecked")
    public DatosJuego(Controlador controlador) {
        
        this.listaMutantes = new ArrayList[2];
        this.listaMutantes[0] = controlador.getListaHilosMutantes(TipoEquipo.EQUIPO_A);
        this.listaMutantes[1] = controlador.getListaHilosMutantes(TipoEquipo.EQUIPO_B);

    }
}