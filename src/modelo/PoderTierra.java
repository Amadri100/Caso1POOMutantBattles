package modelo;


public class PoderTierra implements IPower {

    public PoderTierra() {
        
    }

    @Override
    public void usarPoder() {
        System.out.println("Tira Tierra");
    }

    @Override
    public String nombrePoder() {
        return "PoderTierra";
    }
    
}
