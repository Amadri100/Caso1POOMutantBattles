package juego;

import otros.Constantes;

public enum TipoEquipo {
    EQUIPO_A,
    EQUIPO_B;

    public int getIndice() {
        if (this == EQUIPO_A)
            return Constantes.POSICION_ARREGLO[0];
        else 
            return Constantes.POSICION_ARREGLO[1];
    }
    public String getSimbolo() {
        if (this == EQUIPO_A)
            return Constantes.SIMBOLOS[0];
        else 
            return Constantes.SIMBOLOS[1];
    }
}
