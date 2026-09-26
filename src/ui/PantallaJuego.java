package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import controlador.DatosAtaque;
import controlador.DatosJuego;
import controlador.DatosPelea;
import controlador.EstadoMutante;
import modelo.Mutante;
import otros.Constantes;

public class PantallaJuego extends JPanel {
    private ArrayList<MutantesDibujados> mutantesDibujados;
    private VentanaPrincipal principal;
    private ArrayList<AnimacionAtaque> ataquesActivos;
    private boolean infoVisible;

    public PantallaJuego(VentanaPrincipal principal) {
        this.principal = principal;
        this.mutantesDibujados = new ArrayList<>();
        this.ataquesActivos = new ArrayList<>();
        this.infoVisible = true;
        setPreferredSize(new Dimension(
            Constantes.SIZE_X + Constantes.PADDING_PANTALLA * 2,
            Constantes.SIZE_Y + Constantes.PADDING_PANTALLA * 2
        ));
        setBackground(Constantes.BACKGROUND);
    }

    // Invocado (en el EDT) cada vez que llega una notificación del Controlador.
    public void actualizar(DatosJuego datos) {
        if (datos == null) {
            return;
        }
        sincronizarMutantes(datos.getListaMutantes());
        agregarNuevasAnimaciones(datos.getListaPeleas());
        repaint();
    }

    private void sincronizarMutantes(ArrayList<Mutante> mutantes) {
        Map<Integer, MutantesDibujados> existentes = new HashMap<>();
        for (MutantesDibujados dibujado : this.mutantesDibujados) {
            existentes.put(claveMutante(dibujado.getMutante()), dibujado);
        }
        ArrayList<MutantesDibujados> nuevaLista = new ArrayList<>();
        for (Mutante mutante : mutantes) {
            MutantesDibujados dibujado = existentes.get(claveMutante(mutante));
            if (dibujado == null) {
                dibujado = new MutantesDibujados(mutante);
            }
            nuevaLista.add(dibujado);
        }
        this.mutantesDibujados = nuevaLista;
    }

    // El id de Mutante reinicia en 0 por cada equipo, así que se combina con
    // el índice de equipo para tener una clave única entre A y B.
    private int claveMutante(Mutante mutante) {
        return mutante.getEquipo().getTipoEquipo().getIndice() * 1000 + mutante.getId();
    }

    private void agregarNuevasAnimaciones(ArrayList<DatosPelea> peleas) {
        if (peleas == null) {
            return;
        }
        for (DatosPelea pelea : peleas) {
            if (pelea.getDecisionA() == EstadoMutante.ATAQUE) {
                agregarAnimacionAtaque(new DatosAtaque(pelea, 1));
            }
            if (pelea.getDecisionB() == EstadoMutante.ATAQUE) {
                agregarAnimacionAtaque(new DatosAtaque(pelea, 2));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int padding = Constantes.PADDING_PANTALLA;
        g2.translate(padding, padding);

        g2.setColor(Color.LIGHT_GRAY);
        g2.drawRect(0, 0, Constantes.SIZE_X, Constantes.SIZE_Y);

        for (MutantesDibujados dibujado : this.mutantesDibujados) {
            dibujado.dibujar(g2);
        }

        ArrayList<AnimacionAtaque> terminadas = new ArrayList<>();
        for (AnimacionAtaque animacion : this.ataquesActivos) {
            animacion.dibujar(g2);
            if (animacion.isTerminada()) {
                terminadas.add(animacion);
            }
        }
        this.ataquesActivos.removeAll(terminadas);

        g2.translate(-padding, -padding);

        if (this.infoVisible) {
            g2.setFont(Constantes.FUENTE_INFORMACION);
            g2.setColor(Color.DARK_GRAY);
            //g2.drawString(Constantes.TEXTO_INSTRUCCIONES, Constantes.POSICION_X_INFO, Constantes.POSICION_Y_INFO); Ya se pinta
        }
    }

    public ArrayList<MutantesDibujados> getMutantesDibujados() {
        return this.mutantesDibujados;
    }

    public VentanaPrincipal getPrincipal() {
        return this.principal;
    }

    public ArrayList<AnimacionAtaque> getAtaquesActivos() {
        return this.ataquesActivos;
    }

    public boolean isInfoVisible() {
        return this.infoVisible;
    }

    public void setInfoVisible(boolean infoVisible) {
        this.infoVisible = infoVisible;
    }

    public void agregarAnimacionAtaque(DatosAtaque ataque) {
        this.ataquesActivos.add(new AnimacionAtaque(this, ataque));
    }
}
