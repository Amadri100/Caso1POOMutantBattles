package modelo;


public class PoderRayo implements IPower {

    public PoderRayo() {
        
    }

    @Override
    public void usarPoder() {
        System.out.println("Tira Rayo");
    }

    @Override
    public String nombrePoder() {
        return "PoderRayo";
    }
    
}
