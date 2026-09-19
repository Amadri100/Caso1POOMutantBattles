package juego;

public enum TipoEquipo {
    EQUIPO_A,
    EQUIPO_B;

    public int getIndice() {
        if (this == EQUIPO_A)
            return 0;
        else 
            return 1;
    }
}
