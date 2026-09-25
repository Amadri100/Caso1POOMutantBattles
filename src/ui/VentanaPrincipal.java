package ui;

import controlador.Controlador;
import controlador.DatosJuego;
import otros.Constantes;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private JButton botonIniciar;
    private JButton botonPausar;
    private JButton botonReiniciar;
    private PantallaJuego pantallaJuego;

    // CAMBIO (UI): campos que no estaban en el spec original de VentanaPrincipal pero
    // que hacen falta para que la ventana pueda hablar con el Controlador (arrancar,
    // pausar, reiniciar) y mostrar el marcador (ScoreBoard).
    private Controlador controlador;
    private ObservadorUi observador;
    private JSpinner spinnerCantidad;
    private JLabel labelScore;
    private boolean pausado = false;

    public VentanaPrincipal() {
        super("Batalla de Mutantes");
        this.controlador = new Controlador();
        this.observador = new ObservadorUi(this);
        this.controlador.agregaObservadores(this.observador);

        inicializarComponentes();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public void inicializarComponentes() {
        this.pantallaJuego = new PantallaJuego(this);

        this.botonIniciar = new JButton("Iniciar");
        this.botonPausar = new JButton("Pausar");
        this.botonReiniciar = new JButton("Reiniciar");
        this.botonPausar.setEnabled(false);
        this.botonReiniciar.setEnabled(false);

        this.spinnerCantidad = new JSpinner(new SpinnerNumberModel(
                Constantes.CANTIDAD_MUTANTES_POR_DEFECTO, Constantes.CANTIDAD_MUTANTES_MINIMO, Constantes.CANTIDAD_MUTANTES_MAXIMO, 1));

        this.labelScore = new JLabel("Elegí la cantidad de mutantes por equipo y presioná Iniciar");
        this.labelScore.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));

        JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelControles.add(new JLabel("Mutantes por equipo:"));
        panelControles.add(this.spinnerCantidad);
        panelControles.add(this.botonIniciar);
        panelControles.add(this.botonPausar);
        panelControles.add(this.botonReiniciar);

        this.botonIniciar.addActionListener(e -> onIniciar());
        this.botonPausar.addActionListener(e -> onPausar());
        this.botonReiniciar.addActionListener(e -> onReiniciar());

        setLayout(new BorderLayout());
        add(panelControles, BorderLayout.NORTH);
        add(this.pantallaJuego, BorderLayout.CENTER);
        add(this.labelScore, BorderLayout.SOUTH);
    }

    private void onIniciar() {
        int cantidad = (Integer) this.spinnerCantidad.getValue();
        this.controlador.iniciarSimulacion(cantidad);
        this.botonIniciar.setEnabled(false);
        this.spinnerCantidad.setEnabled(false);
        this.botonPausar.setEnabled(true);
        this.botonReiniciar.setEnabled(true);
    }

    private void onPausar() {
        this.pausado = !this.pausado;
        if (this.pausado) {
            this.controlador.pausar();
            this.botonPausar.setText("Reanudar");
        } else {
            this.controlador.reanudar();
            this.botonPausar.setText("Pausar");
        }
    }

    private void onReiniciar() {
        int cantidad = (Integer) this.spinnerCantidad.getValue();
        this.controlador.reiniciar(cantidad);
        this.pausado = false;
        this.botonPausar.setText("Pausar");
        this.botonPausar.setEnabled(true);
    }

    /** Llamado por ObservadorUi, ya dentro del Event Dispatch Thread. */
    public void actualizar(DatosJuego datos) {
        this.pantallaJuego.actualizar(datos);
        if (this.controlador.getCampoDeBatalla() != null) {
            String score = this.controlador.getCampoDeBatalla().getScoreBoard().toString().replace("\n", "   |   ");
            this.labelScore.setText(score);
            if (this.controlador.getCampoDeBatalla().terminoElJuego()) {
                this.botonPausar.setEnabled(false);
                this.labelScore.setText(score + "   |   ¡Partida terminada!");
            }
        }
    }

    public JButton getBotonIniciar() {
        return this.botonIniciar;
    }

    public JButton getBotonPausar() {
        return this.botonPausar;
    }

    public JButton getBotonReiniciar() {
        return this.botonReiniciar;
    }

    public PantallaJuego getPantallaJuego() {
        return this.pantallaJuego;
    }
}
