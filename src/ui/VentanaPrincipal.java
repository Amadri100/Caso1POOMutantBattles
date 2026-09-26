package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import controlador.Controlador;
import controlador.DatosJuego;
import otros.Constantes;

public class VentanaPrincipal extends JFrame {
    private JButton botonIniciar;
    private JButton botonPausar;
    private JButton botonReiniciar;
    private PantallaJuego pantallaJuego;

    // Campos adicionales necesarios para operar (no estaban en el spec original)
    private Controlador controlador;
    private ObservadorUi observador;
    private JSpinner spinnerCantidad;
    private JLabel etiquetaScore;
    private boolean pausado;

    public VentanaPrincipal() {
        super(Constantes.TITULO_VENTANA);
        this.controlador = new Controlador();
        this.observador = new ObservadorUi(this);
        this.controlador.agregaObservadores(this.observador);
        inicializarComponentes();
    }

    public void inicializarComponentes() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(Constantes.RESIZABLE);
        setLayout(new BorderLayout());

        this.pantallaJuego = new PantallaJuego(this);

        SpinnerNumberModel modeloSpinner = new SpinnerNumberModel(
            Constantes.CANTIDAD_MUTANTES_POR_DEFECTO,
            Constantes.CANTIDAD_MUTANTES_MINIMO,
            Constantes.CANTIDAD_MUTANTES_MAXIMO,
            Constantes.INCREMENTO_CANTIDAD_MUTANTES
        );
        this.spinnerCantidad = new JSpinner(modeloSpinner);

        this.botonIniciar = new JButton(Constantes.TEXTO_INICIAR);
        this.botonPausar = new JButton(Constantes.TEXTO_PAUSAR);
        this.botonReiniciar = new JButton(Constantes.TEXTO_REINICIAR);
        this.botonPausar.setEnabled(false);
        this.botonReiniciar.setEnabled(false);

        //Sytaxis de metodo-referencia clase::metodo
        this.botonIniciar.addActionListener(this::alPresionarIniciar);
        this.botonPausar.addActionListener(this::alPresionarPausar);
        this.botonReiniciar.addActionListener(this::alPresionarReiniciar); 

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new JLabel(Constantes.TEXTO_INSTRUCCIONES));
        panelSuperior.add(new JLabel(Constantes.TEXTO_MUTANTES_POR_EQUIPO));
        panelSuperior.add(this.spinnerCantidad);
        panelSuperior.add(this.botonIniciar);
        panelSuperior.add(this.botonPausar);
        panelSuperior.add(this.botonReiniciar);

        this.etiquetaScore = new JLabel(" ");
        this.etiquetaScore.setBorder(BorderFactory.createEmptyBorder(
            Constantes.BORDE_SCORE_ARRIBA,
            Constantes.BORDE_SCORE_IZQUIERDA,
            Constantes.BORDE_SCORE_ABAJO,
            Constantes.BORDE_SCORE_DERECHA
        ));

        add(panelSuperior, BorderLayout.NORTH);
        add(this.pantallaJuego, BorderLayout.CENTER);
        add(this.etiquetaScore, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void alPresionarIniciar(ActionEvent evento) {
        int cantidad = (Integer) this.spinnerCantidad.getValue();
        this.controlador.iniciarSimulacion(cantidad);
        this.pausado = false;
        this.botonPausar.setText(Constantes.TEXTO_PAUSAR);
        this.botonIniciar.setEnabled(false);
        this.spinnerCantidad.setEnabled(false);
        this.botonPausar.setEnabled(true);
        this.botonReiniciar.setEnabled(true);
    }

    private void alPresionarPausar(ActionEvent evento) {
        this.pausado = !this.pausado;
        if (this.pausado) {
            this.controlador.pausar();
            this.botonPausar.setText(Constantes.TEXTO_REANUDAR);
        } else {
            this.controlador.reanudar();
            this.botonPausar.setText(Constantes.TEXTO_PAUSAR);
        }
    }

    private void alPresionarReiniciar(ActionEvent evento) {
        int cantidad = (Integer) this.spinnerCantidad.getValue();
        this.controlador.reiniciar(cantidad);
        this.pausado = false;
        this.botonPausar.setText(Constantes.TEXTO_PAUSAR);
        this.botonPausar.setEnabled(true);
        this.botonReiniciar.setEnabled(true);
    }

    // Invocado por ObservadorUi ya dentro del EDT.
    public void actualizar(DatosJuego datos) {
        this.pantallaJuego.actualizar(datos);
        if (this.controlador.getCampoDeBatalla() != null) {
            String texto = this.controlador.getCampoDeBatalla().getScoreBoard().toString();
            if (this.controlador.getCampoDeBatalla().terminoElJuego()) {
                texto = Constantes.TEXTO_PARTIDA_TERMINADA + Constantes.SEPARADOR_SCORE + texto;
                this.botonPausar.setEnabled(false);
            }
            this.etiquetaScore.setText(texto);
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
