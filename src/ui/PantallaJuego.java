package ui;

import controlador.DatosAtaque;
import controlador.DatosJuego;
import controlador.DatosPelea;
import controlador.EstadoMutante;
import modelo.Mutante;
import otros.Constantes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
        setBackground(new Color(24, 26, 34));
        setPreferredSize(new Dimension(700, 500));

        // CAMBIO (UI): timer propio de repintado, independiente de cada cuánto llega
        // una notificación del modelo (Constantes.DELAY_DE_THREADS). Así las
        // animaciones de ataque se ven fluidas aunque el Notificador refresque el
        // modelo con menos frecuencia.
        new Timer(Constantes.TASA_REFRESCO_PANTALLA, e -> repaint()).start();
    }

    /** Se llama desde ObservadorUi/VentanaPrincipal (ya en el EDT) cada vez que hay datos nuevos. */
    public void actualizar(DatosJuego datos) {
        if (datos == null) {
            return;
        }

        // Reutiliza los MutantesDibujados existentes por id, para no recrear (ni
        // reconstruir el ícono de) un mutante que sigue vivo en cada refresco.
        Map<Integer, MutantesDibujados> existentes = new HashMap<>();
        for (MutantesDibujados md : this.mutantesDibujados) {
            existentes.put(md.getMutante().getId(), md);
        }
        ArrayList<MutantesDibujados> nuevaLista = new ArrayList<>();
        for (Mutante m : datos.getListaMutantes()) {
            MutantesDibujados md = existentes.get(m.getId());
            if (md == null) {
                md = new MutantesDibujados(m);
            }
            nuevaLista.add(md);
        }
        this.mutantesDibujados = nuevaLista;

        // Convierte cada pelea ya resuelta (con datos guardados vía
        // DatosPelea.guardarDatos(), ver el cambio en EjecutarPeleas) en una animación
        // por cada mutante que efectivamente haya atacado.
        for (DatosPelea pelea : datos.getListaPeleas()) {
            if (pelea.getDecisionA() == EstadoMutante.ATAQUE) {
                agregarAnimacionAtaque(new DatosAtaque(pelea, 1));
            }
            if (pelea.getDecisionB() == EstadoMutante.ATAQUE) {
                agregarAnimacionAtaque(new DatosAtaque(pelea, 2));
            }
        }

        repaint();
    }

    public void agregarAnimacionAtaque(DatosAtaque ataque) {
        this.ataquesActivos.add(new AnimacionAtaque(ataque, this));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (MutantesDibujados md : this.mutantesDibujados) {
            md.dibujar(g2);
        }

        Iterator<AnimacionAtaque> it = this.ataquesActivos.iterator();
        while (it.hasNext()) {
            AnimacionAtaque anim = it.next();
            if (anim.isTerminada()) {
                it.remove();
            } else {
                anim.dibujar(g2);
            }
        }

        if (this.infoVisible) {
            dibujarInfo(g2);
        }
    }

    private void dibujarInfo(Graphics2D g2) {
        int vivos = 0;
        for (MutantesDibujados md : this.mutantesDibujados) {
            if (md.getMutante().estaVivo()) {
                vivos++;
            }
        }
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Monospaced", Font.PLAIN, 12));
        g2.drawString("Mutantes en pantalla: " + this.mutantesDibujados.size() + "  (vivos: " + vivos + ")", 10, 20);
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
        repaint();
    }
}
