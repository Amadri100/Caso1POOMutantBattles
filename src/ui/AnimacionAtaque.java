package ui;

import controlador.DatosAtaque;
import otros.Constantes;
import otros.Punto;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Animación de un ataque puntual (hereda de DatosAtaque para saber quién ataca,
 * a quién y con qué poder). Se dibuja como una pequeña explosión de círculos
 * concéntricos a mitad de camino entre origen y destino, con un color según el
 * tipo de poder usado.
 *
 * NOTA (UI): al igual que con los mutantes, no había recursos de imagen para los
 * ataques (Constantes.RUTA_IMAGENES_ATAQUES sin archivos asociados), así que los
 * "frames" se generan proceduralmente en generarFrames().
 */
public class AnimacionAtaque extends DatosAtaque {
    private PantallaJuego pantalla;
    private JLabel jLabel;
    private ArrayList<ImageIcon> dibujos;
    private int frameActual;

    public AnimacionAtaque(DatosAtaque ataque, PantallaJuego pantalla) {
        super(ataque);
        this.pantalla = pantalla;
        this.frameActual = 0;
        this.dibujos = generarFrames();
        this.jLabel = new JLabel();
    }

    private ArrayList<ImageIcon> generarFrames() {
        ArrayList<ImageIcon> frames = new ArrayList<>();
        Color colorBase = colorSegunPoder();
        int totalFrames = Math.max(1, Constantes.NUMERO_FRAMES_ATAQUE);
        for (int f = 0; f < totalFrames; f++) {
            BufferedImage img = new BufferedImage(Constantes.TAMANO_ANIMACION, Constantes.TAMANO_ANIMACION, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = img.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            float progreso = (f + 1f) / totalFrames;
            int radio = (int) (Constantes.TAMANO_ANIMACION / 2 * progreso);
            int alfa = (int) (255 * (1f - progreso));
            g2.setColor(new Color(colorBase.getRed(), colorBase.getGreen(), colorBase.getBlue(), Math.max(alfa, 40)));
            int centro = Constantes.TAMANO_ANIMACION / 2;
            g2.fillOval(centro - radio, centro - radio, Math.max(radio * 2, 1), Math.max(radio * 2, 1));
            g2.dispose();
            frames.add(new ImageIcon(img));
        }
        return frames;
    }

    private Color colorSegunPoder() {
        if (getPoder() == null) {
            return Color.WHITE;
        }
        String nombre = getPoder().nombrePoder();
        switch (nombre) {
            case "PoderFuego":
                return Color.RED;
            case "PoderAgua":
                return Color.BLUE;
            case "PoderRayo":
                return Color.YELLOW;
            case "PoderTierra":
                return new Color(139, 69, 19);
            case "PoderTelaraña":
                return Color.LIGHT_GRAY;
            default:
                return Color.MAGENTA;
        }
    }

    public void dibujar(Graphics g) {
        if (isTerminada()) {
            return;
        }
        Punto origen = getOrigen();
        Punto destino = getDestino();
        if (origen == null || destino == null) {
            // Puede pasar si DatosPelea.guardarDatos() todavía no se llamó; en ese caso
            // simplemente no hay nada que animar en este frame.
            this.frameActual = this.dibujos.size();
            return;
        }
        int x = (origen.getX() + destino.getX()) / 2 - Constantes.TAMANO_ANIMACION / 2;
        int y = (origen.getY() + destino.getY()) / 2 - Constantes.TAMANO_ANIMACION / 2;
        ImageIcon frame = this.dibujos.get(Math.min(this.frameActual, this.dibujos.size() - 1));
        g.drawImage(frame.getImage(), x, y, null);
        this.frameActual++;
    }

    public boolean isTerminada() {
        return this.frameActual >= this.dibujos.size();
    }

    public PantallaJuego getPantalla() {
        return this.pantalla;
    }

    public JLabel getJLabel() {
        return this.jLabel;
    }

    public ArrayList<ImageIcon> getDibujos() {
        return this.dibujos;
    }

    public int getFrameActual() {
        return this.frameActual;
    }

    public void setFrameActual(int frameActual) {
        this.frameActual = frameActual;
    }
}
