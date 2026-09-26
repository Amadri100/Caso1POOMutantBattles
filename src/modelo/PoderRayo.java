package modelo;

import otros.Constantes;

public class PoderRayo implements IPower {

    public PoderRayo() {
        
    }

    @Override
    public void usarPoder() {
        System.out.println("Tira Rayo");
    }

    @Override
    public String nombrePoder() {
        return Constantes.NOMBRES_PODERES[2];
    }
    
}
