package modelo;


public class PoderAgua implements IPower{

    public PoderAgua() {
        
    }

    @Override
    public void usarPoder() {
        System.out.println("Tira Agua");
    }

    @Override
    public String nombrePoder() {
        return "PoderAgua";
    }
    
}
