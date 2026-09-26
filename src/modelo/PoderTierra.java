package modelo;

import otros.Constantes;

public class PoderTierra implements IPower {

    public PoderTierra() {
        
    }

    @Override
    public void usarPoder() {
        System.out.println("Tira Tierra");
    }

    @Override
    public String nombrePoder() {
        return Constantes.NOMBRES_PODERES[4];
    }
    
}
