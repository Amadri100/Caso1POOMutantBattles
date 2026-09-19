package modelo;


public class PoderFuego implements IPower {

    public PoderFuego() {
        
    }

    @Override
    public void usarPoder() {
        System.out.println("Tira Fuego");
    }

    @Override
    public String nombrePoder() {
        return "PoderFuego";
    }
    
}
