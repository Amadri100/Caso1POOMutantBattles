package modelo;

import otros.Constantes;

public class PoderAgua implements IPower{

    public PoderAgua() {
        
    }

    @Override
    public void usarPoder() {
        System.out.println("Tira Agua");
    }

    @Override
    public String nombrePoder() {
        return Constantes.NOMBRES_PODERES[0];
    }
    
}
