package otros;
import java.awt.Color;
public final class Constantes {
    //Temp : Significa que no es fijo o es simplemente de prueba
    //Defecto: es el valor recomendado
    public static final int TIEMPO_ESPERA  = 10; //TEMP
    public static final int SIZE_X  = 1000; //TEMP
    public static final int SIZE_Y  = 1000; //TEMP
    //public static final int CANTIDAD_EQUIPOS = 2; //Defecto = 2
    public static final int CANTIDAD_THREADS = 4; //Defecto = 4

    public static final int ATAQUE_MINIMO = 1; //Defecto = 1
    public static final int ATAQUE_MAXIMO_POR_DEFECTO = 3; //Defecto = 3
    public static final int ATAQUE_MAXIMO = 7; //Defecto = 7, ataque maximo que puede alcanzar un enemigo



    public static final int VELOCIDAD = 3; //TEMP
    public static final int TAMAÑO_MINIMO = 3; //Defecto = 3
    public static final int TAMAÑO_MAXIMO = 11; //Defecto = 11

    public static final int DEFENSA_MINIMO = 1; //Defecto = 1
    public static final int DEFENSA_MAXIMO = 3; //Defecto = 3

    public static final int MAXIMO_VIDA  = 100; //Defecto = 100

    public static final int CANTIDAD_EQUIPOS = 2; //Valor = 2
    public static final int CANTIDAD_MUTANTES_POR_DEFECTO = 8; //Defecto = 8
    public static final int CANTIDAD_MUTANTES_MAXIMO = 11; //Defecto = 8
    public static final int CANTIDAD_MUTANTES_MINIMO = 3; //Defecto = 3

    //No es recomendable cambiarlos
    public static final int[] POSICION_ARREGLO = {0, 1}; //Defecto = {0,1}
    public static final String[] SIMBOLOS = {"A", "B"}; //Defecto = {"A", "B"} 

    public static final double RADIO_MAXIMO = 5; //TEMP

    public static final int DISTANCIA_MAXIMA_RECORRIDO = 5; //TEMP

    public static final String[] RUTA_IMAGENES_MUTANTES = {"TEMP", "TEMP"}; //TEMP

    public static final String[] RUTA_IMAGENES_ATAQUES = {"TEMP", "TEMP"}; //TEMP

    public static Color[][] COLORES = {{Color.blue, Color.red}, {Color.MAGENTA, Color.GREEN}, {Color.white, Color.black}};

    public static final int TASA_REFRESCO_DATOS = 20; //TEMP
    public static final int TASA_REFRESCO_PANTALLA = 30; //temp

    public static final int DELAY_DE_THREADS = 1000; //TEMP

    public static final int MUTANTES_MUERTOS_INICIAL = 0; //Defecto = 0

    public static final int TAMANO_ANIMACION = 34; //Temp    
    public static final int NUMERO_FRAMES_ATAQUE = 5; //Temp    
    public static final int DIAMETRO_MUTANTE = 28; //Temp    
}
