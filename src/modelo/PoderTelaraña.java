package modelo;

import otros.Constantes;

public class PoderTelaraña implements IPower {

    public PoderTelaraña() {
        
    }

    @Override
    public void usarPoder() {
        System.out.println("Tira Telaraña");
    }

    @Override
    public String nombrePoder() {
        return Constantes.NOMBRES_PODERES[3];
    }
    
}