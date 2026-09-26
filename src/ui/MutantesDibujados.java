package ui;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import modelo.Mutante;
import otros.Constantes;
import otros.Punto;

// Nota: el spec original preveía campos JLabel/ImageIcon, pero no hay rutas
// de imágenes reales (Constantes.RUTA_IMAGENES_MUTANTES son placeholders
// "TEMP"), así que el mutante se dibuja directamente con Graphics2D
// (círculo + símbolo + nombre + barra de vida) en lugar de un ImageIcon.
public class MutantesDibujados {
    private Mutante mutante;

    public MutantesDibujados(Mutante mutante) {
        this.mutante = mutante;
    }

    public void dibujar(Graphics2D g) {
        if (this.mutante == null || !this.mutante.estaVivo()) {
            return;
        }
        Punto posicion = this.mutante.getPosicion();
        int diametro = Constantes.DIAMETRO_MUTANTE;
        int x = posicion.getX() - diametro / 2;
        int y = posicion.getY() - diametro / 2;

        Color colorEquipo = this.mutante.getEquipo() != null
            ? this.mutante.getEquipo().getColor()
            : Constantes.COLOR_MUTANTE_SIN_EQUIPO;

        dibujarBarraVida(g, x, y, diametro);

        g.setColor(colorEquipo);
        g.fillOval(x, y, diametro, diametro);
        g.setColor(Constantes.COLOR_BORDE_MUTANTE);
        g.drawOval(x, y, diametro, diametro);

        String simbolo = this.mutante.getEquipo() != null ? this.mutante.getEquipo().getSimbolo() : "?";
        g.setFont(Constantes.FUENTE_SIMBOLO);
        g.setColor(Constantes.COLOR_SIMBOLO_MUTANTE);
        FontMetrics metricas = g.getFontMetrics();
        int simboloX = x + (diametro - metricas.stringWidth(simbolo)) / 2;
        int simboloY = y + (diametro + metricas.getAscent()) / 2 - 2;
        g.drawString(simbolo, simboloX, simboloY);

        g.setFont(Constantes.FUENTE_NOMBRE);
        g.setColor(Constantes.COLOR_NOMBRE_MUTANTE);
        g.drawString(
            this.mutante.getNombre(),
            x - Constantes.AJUSTE_X_NOMBRE,
            y + diametro + Constantes.SEPARACION_Y_NOMBRE
        );
    }

    private void dibujarBarraVida(Graphics2D g, int x, int y, int diametro) {
        int barraY = y - Constantes.SEPARACION_BARRA_VIDA;
        g.setColor(Constantes.COLOR_BARRA_VIDA_FONDO);
        g.fillRect(x, barraY, diametro, Constantes.ALTURA_BARRA_VIDA);

        double porcentajeVida = this.mutante.getVida() / (double) Constantes.MAXIMO_VIDA;
        Color colorVida = porcentajeVida <= Constantes.PORCENTAJE_VIDA_BAJA
            ? Constantes.COLOR_BARRA_VIDA_BAJA
            : Constantes.COLOR_BARRA_VIDA_ALTA;
        g.setColor(colorVida);
        int anchoVida = (int) Math.round(diametro * Math.max(0, Math.min(1, porcentajeVida)));
        g.fillRect(x, barraY, anchoVida, Constantes.ALTURA_BARRA_VIDA);
    }

    public Punto obtenerPosicion() {
        return this.mutante != null ? this.mutante.getPosicion() : null;
    }

    @Override
    public String toString() {
        if (this.mutante == null) {
            return "MutanteDibujado vacio";
        }
        return this.mutante.getNombre() + " (vida=" + this.mutante.getVida() + ")";
    }

    public Mutante getMutante() {
        return this.mutante;
    }
}
