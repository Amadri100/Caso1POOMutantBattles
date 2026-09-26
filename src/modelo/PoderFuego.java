package modelo;

import otros.Constantes;

public class PoderFuego implements IPower {

    public PoderFuego() {
        
    }

    @Override
    public void usarPoder() {
        System.out.println("Tira Fuego");
    }

    @Override
    public String nombrePoder() {
        return Constantes.NOMBRES_PODERES[1];
    }
    
}
