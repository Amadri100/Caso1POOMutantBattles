package otros;
import java.awt.Color;

import java.awt.Font;
public final class Constantes {
    //Temp : Significa que no es fijo o es simplemente de prueba
    //Defecto: es el valor recomendado
    public static final int TIEMPO_ESPERA  = 10; //TEMP
    public static final int SIZE_X  = 700; //TEMP
    public static final int SIZE_Y  = 600; //TEMP
    //public static final int CANTIDAD_EQUIPOS = 2; //Defecto = 2
    public static final int CANTIDAD_THREADS = 4; //Defecto = 4
    public static final int PADDING_PANTALLA = 20; //Defecto = 20

    public static final boolean RESIZABLE = false; //Defecto = false, no esta creado para que lo sea.
    public static final int ATAQUE_MINIMO = 1; //Defecto = 1
    public static final int ATAQUE_MAXIMO_POR_DEFECTO = 3; //Defecto = 3
    public static final int ATAQUE_MAXIMO = 7; //Defecto = 7, ataque maximo que puede alcanzar un enemigo

    public static final int CANTIDAD_ESTADOS_MUTANTE = 1; //Defecto 1
    public static final int CANTIDAD_DE_DECISIONES_MUTANTE = 2; //Defecto = 2

    public static final int[] DIMENSIONES_PANTALLA = {1100, 1100}; //Defecto ={700, 500} {width, heigt}

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
    public static Color BACKGROUND = new Color(242, 247, 255);



    public static final int TASA_REFRESCO_DATOS = 20; //TEMP
    public static final int TASA_REFRESCO_PANTALLA = 30; //temp

    public static final int DELAY_DE_THREADS = 1000; //TEMP

    public static final int MUTANTES_MUERTOS_INICIAL = 0; //Defecto = 0

    public static final int TAMANO_ANIMACION = 34; //Temp    
    public static final int NUMERO_FRAMES_ATAQUE = 5; //Temp    
    public static final int DIAMETRO_MUTANTE = 28; //Temp    

    // Colores de MutantesDibujados
    public static final Color COLOR_MUTANTE_SIN_EQUIPO = Color.GRAY;
    public static final Color COLOR_BORDE_MUTANTE = Color.BLACK;
    public static final Color COLOR_SIMBOLO_MUTANTE = Color.WHITE;
    public static final Color COLOR_NOMBRE_MUTANTE = Color.WHITE;

    public static final Color COLOR_BARRA_VIDA_FONDO = Color.DARK_GRAY;
    public static final Color COLOR_BARRA_VIDA_ALTA = new Color(60, 200, 60);
    public static final Color COLOR_BARRA_VIDA_BAJA = new Color(220, 60, 60);

    // Fuentes
    public static final Font FUENTE_SIMBOLO = new Font("SansSerif", Font.BOLD, 9);
    public static final Font FUENTE_NOMBRE = new Font("SansSerif", Font.PLAIN, 12);
    public static final Font FUENTE_INFORMACION = new Font("Monospaced", Font.PLAIN, 12);

    public static final int POSICION_X_INFO = 10;
    public static final int POSICION_Y_INFO = 20;

    // Barra de vida
    public static final int SEPARACION_BARRA_VIDA = 6;
    public static final int ALTURA_BARRA_VIDA = 4;
    public static final double PORCENTAJE_VIDA_BAJA = 1.0 / 3.0;

    // Nombre del mutante
    public static final int AJUSTE_X_NOMBRE = 6;
    public static final int SEPARACION_Y_NOMBRE = 10;

    //Textos en la pantalla
    public static final String TITULO_VENTANA = "Batalla de Mutantes";
    public static final String TEXTO_INICIAR = "Iniciar";
    public static final String TEXTO_PAUSAR = "Pausar";
    public static final String TEXTO_REANUDAR = "Reanudar";
    public static final String TEXTO_REINICIAR = "Reiniciar";

    public static final String TEXTO_MUTANTES_POR_EQUIPO = "Mutantes por equipo:";

    public static final String TEXTO_INSTRUCCIONES = "Elegí la cantidad de mutantes por equipo y presioná Iniciar";

    public static final String SEPARADOR_SCORE = "   |   ";

    public static final String TEXTO_PARTIDA_TERMINADA = "¡Partida terminada!";

    // Borde del marcador
    public static final int BORDE_SCORE_ARRIBA = 4;
    public static final int BORDE_SCORE_IZQUIERDA = 8;
    public static final int BORDE_SCORE_ABAJO = 4;
    public static final int BORDE_SCORE_DERECHA = 8;

    // Spinner
    public static final int INCREMENTO_CANTIDAD_MUTANTES = 1;


}
