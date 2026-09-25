package ui;

import modelo.Mutante;
import otros.Constantes;
import otros.Punto;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MutantesDibujados {
    private Mutante mutante;
    private JLabel jLabel;
    private ImageIcon imagen;

    public MutantesDibujados(Mutante mutante) {
        this.mutante = mutante;
        this.imagen = generarImagen();
        this.jLabel = new JLabel(this.imagen);
        this.jLabel.setSize(Constantes.DIAMETRO_MUTANTE, Constantes.DIAMETRO_MUTANTE);
    }

    private ImageIcon generarImagen() {
        BufferedImage img = new BufferedImage(Constantes.DIAMETRO_MUTANTE, Constantes.DIAMETRO_MUTANTE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color color = this.mutante.getEquipo().getColor();
        g2.setColor(color != null ? color : Color.GRAY);
        g2.fillOval(0, 0, Constantes.DIAMETRO_MUTANTE - 1, Constantes.DIAMETRO_MUTANTE - 1);
        g2.setColor(Color.BLACK);
        g2.drawOval(0, 0, Constantes.DIAMETRO_MUTANTE - 1, Constantes.DIAMETRO_MUTANTE - 1);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("SansSerif", Font.BOLD, 12));
        String simbolo = this.mutante.getEquipo().getSimbolo();
        FontMetrics fm = g2.getFontMetrics();
        int tx = (Constantes.DIAMETRO_MUTANTE - fm.stringWidth(simbolo)) / 2;
        int ty = (Constantes.DIAMETRO_MUTANTE - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(simbolo, tx, ty);
        g2.dispose();
        return new ImageIcon(img);
    }

    public void dibujar(Graphics g) {
        if (!this.mutante.estaVivo()) {
            return;
        }
        Punto p = mutante.getPosicion();
        int x = p.getX() - Constantes.DIAMETRO_MUTANTE / 2;
        int y = p.getY() - Constantes.DIAMETRO_MUTANTE / 2;
        g.drawImage(imagen.getImage(), x, y, null);

        // Barra de vida (usa Mutante.getVida(), agregado en esta ronda de cambios).
        int vidaMax = Constantes.MAXIMO_VIDA;
        int vidaActual = Math.max(0, mutante.getVida());
        int anchoLleno = vidaMax > 0 ? (int) (Constantes.DIAMETRO_MUTANTE * (vidaActual / (double) vidaMax)) : 0;
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y - 6, Constantes.DIAMETRO_MUTANTE, 4);
        g.setColor(vidaActual > vidaMax / 3.0 ? new Color(60, 200, 60) : new Color(220, 60, 60));
        g.fillRect(x, y - 6, anchoLleno, 4);

        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.PLAIN, 9));
        g.drawString(mutante.getNombre(), x - 6, y + Constantes.DIAMETRO_MUTANTE + 10);
    }

    public Punto obtenerPosicion() {
        return this.mutante.getPosicion();
    }

    @Override
    public String toString() {
        return this.mutante.getNombre() + " [" + this.mutante.getEquipo().getSimbolo() + "]";
    }

    public Mutante getMutante() {
        return this.mutante;
    }

    public JLabel getJLabel() {
        return this.jLabel;
    }

    public ImageIcon getImagen() {
        return this.imagen;
    }
}
