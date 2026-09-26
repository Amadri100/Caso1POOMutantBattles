package ui;

import java.awt.Color;
import java.awt.Graphics2D;

import controlador.DatosAtaque;
import otros.Constantes;
import otros.Punto;

// Nota: el spec original define un constructor de un solo argumento
// (AnimaciónAtaque(ataque : Ataque)) y campos JLabel/ArrayList<ImageIcon>.
// Sin assets de imagen reales, y necesitando saber en qué pantalla dibujarse,
// se usa un constructor (PantallaJuego, DatosAtaque) y el avance de frames se
// hace por Graphics2D en cada repaint, en vez de frames pre-renderizados.
// Tampoco existe Constantes.DELAY_ANIMACION_ATAQUE (mencionada en el spec pero
// ausente del Constantes.java real), así que la duración se basa únicamente
// en Constantes.NUMERO_FRAMES_ATAQUE avanzando un frame por cada repaint.
public class AnimacionAtaque extends DatosAtaque {
    private PantallaJuego pantalla;
    private int frameActual;

    public AnimacionAtaque(PantallaJuego pantalla, DatosAtaque ataque) {
        super(ataque);
        this.pantalla = pantalla;
        this.frameActual = 0;
    }

    public void dibujar(Graphics2D g) {
        if (isTerminada()) {
            return;
        }
        Punto origen = getOrigen();
        Punto destino = getDestino();
        if (origen == null || destino == null) {
            this.frameActual = Constantes.NUMERO_FRAMES_ATAQUE;
            return;
        }

        double progreso = (double) this.frameActual / Constantes.NUMERO_FRAMES_ATAQUE;
        int x = origen.getX() + (int) Math.round((destino.getX() - origen.getX()) * progreso);
        int y = origen.getY() + (int) Math.round((destino.getY() - origen.getY()) * progreso);

        int radio = 5;
        g.setColor(colorPorPoder());
        g.fillOval(x - radio, y - radio, radio * 2, radio * 2);

        this.frameActual++;
    }

    private Color colorPorPoder() {
        if (getPoder() == null) {
            return Color.WHITE;
        }
        for (int i = 0; i<Constantes.NOMBRES_PODERES.length-1;i++) {
            if (getPoder().nombrePoder() == Constantes.NOMBRES_PODERES[i]) {
                return Constantes.COLORES_PODERES[i];
            }
        }
        return Constantes.COLORES_PODERES[Constantes.NOMBRES_PODERES.length-1];
    }

    public boolean isTerminada() {
        return this.frameActual >= Constantes.NUMERO_FRAMES_ATAQUE;
    }

    public PantallaJuego getPantalla() {
        return this.pantalla;
    }

    public int getFrameActual() {
        return this.frameActual;
    }

    public void setFrameActual(int frameActual) {
        this.frameActual = frameActual;
    }
}
