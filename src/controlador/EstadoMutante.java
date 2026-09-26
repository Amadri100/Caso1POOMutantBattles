package controlador;

import otros.Constantes;
import otros.Matematicas;

public enum EstadoMutante {
    //El orden importa
    //Estados por defecto
    MOVIENDOSE,
    //Decisiones
    ATAQUE,
    DEFENSA;
    
    //Es static pues no depende del valor del estado actual
    public static EstadoMutante getEstadoAleatorio() {
        EstadoMutante[] estados = EstadoMutante.values();
        return estados[Matematicas.intAleatorio(Constantes.CANTIDAD_ESTADOS_MUTANTE, estados.length - 1)];
    }

}
