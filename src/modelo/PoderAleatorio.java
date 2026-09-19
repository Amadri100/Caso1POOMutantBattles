package modelo;
import otros.Matematicas;

public final class PoderAleatorio {
    
    public static IPower poderAleatorio() {
        IPower poder;
        int aleatorio = Matematicas.intAleatorio(0, 5);
        switch (aleatorio) {
            case 1:
                poder = new PoderFuego();
                break;
            case 2:
                poder = new PoderRayo();
                break;   
            case 3:
                poder = new PoderTelaraña();
                break;
            case 4:
                poder = new PoderTierra();
                break;
            case 5: 
                poder = new PoderAgua();
                break;
            default:
                poder = new PoderAgua();
        }
        return poder;
    }
}
