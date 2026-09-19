package otros;

import java.util.Random;

public final class Matematicas {
    //Distancia entre A y B
    public static double calcularRadio(Punto a, Punto b) {
        return Math.sqrt(Math.pow((b.getX() - a.getX()),2) + Math.pow((b.getY() - a.getY()),2));
    }
    public static double doubleAleatorio(double minimo, double maximo) { //"""INCLUSIVO"""
        Random random = new Random();
        double res = minimo + (maximo - minimo) * random.nextDouble();
        return res;
    }
    public static int intAleatorio(int minimo, int maximo) { //Es inclusivo
        //Conseguir total de numeros entre estos
        int total = maximo - minimo + 1;
        Random random = new Random();
        int res = random.nextInt(total);
        return res+minimo;
    }
}
