package otros;

public final class Matematicas {
    //Distancia entre A y B
    public static double calcularRadio(Punto a, Punto b) {
        return Math.sqrt(Math.pow((b.getX() - a.getX()),2) + Math.pow((b.getY() - a.getY()),2));
    }
}
