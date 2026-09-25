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
    private JPanel gameContainer;
    private Controlador controlador;
    private ObservadorUi observador;
    private JSpinner spinnerCantidad;
    private JLabel labelScore;

    private boolean pausado = false;

    public VentanaPrincipal() {

        super(Constantes.TITULO_VENTANA);

        this.controlador = new Controlador();
        this.observador = new ObservadorUi(this);

        this.controlador.agregaObservadores(this.observador);

        inicializarComponentes();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();
        setLocationRelativeTo(null);
    }

    public void inicializarComponentes() {
        this.setResizable(Constantes.RESIZABLE);
        this.pantallaJuego = new PantallaJuego(this);


        this.botonIniciar =
                new JButton(Constantes.TEXTO_INICIAR);

        this.botonPausar =
                new JButton(Constantes.TEXTO_PAUSAR);

        this.botonReiniciar =
                new JButton(Constantes.TEXTO_REINICIAR);

        this.botonPausar.setEnabled(false);
        this.botonReiniciar.setEnabled(false);

        this.spinnerCantidad = new JSpinner(
                new SpinnerNumberModel(
                        Constantes.CANTIDAD_MUTANTES_POR_DEFECTO,
                        Constantes.CANTIDAD_MUTANTES_MINIMO,
                        Constantes.CANTIDAD_MUTANTES_MAXIMO,
                        Constantes.INCREMENTO_CANTIDAD_MUTANTES
                )
        );

        this.labelScore =
                new JLabel(Constantes.TEXTO_INSTRUCCIONES);

        this.labelScore.setBorder(
                BorderFactory.createEmptyBorder(
                        Constantes.BORDE_SCORE_ARRIBA,
                        Constantes.BORDE_SCORE_IZQUIERDA,
                        Constantes.BORDE_SCORE_ABAJO,
                        Constantes.BORDE_SCORE_DERECHA
                )
        );


        //Crea un panel que almacena todos los controles del juego
        JPanel panelControles =
                new JPanel(new FlowLayout(FlowLayout.LEFT));

        panelControles.add(
                new JLabel(Constantes.TEXTO_MUTANTES_POR_EQUIPO)
        );



        panelControles.add(this.spinnerCantidad);
        panelControles.add(this.botonIniciar);
        panelControles.add(this.botonPausar);
        panelControles.add(this.botonReiniciar);



        //Listeners
        this.botonIniciar.addActionListener(
                e -> onIniciar()
        );

        this.botonPausar.addActionListener(
                e -> onPausar()
        );

        this.botonReiniciar.addActionListener(
                e -> onReiniciar()
        );

        setLayout(new BorderLayout());

        this.gameContainer = new JPanel(new BorderLayout());
        this.gameContainer.setBorder(BorderFactory.createEmptyBorder(Constantes.PADDING_PANTALLA, 
                                                                Constantes.PADDING_PANTALLA, 
                                                                Constantes.PADDING_PANTALLA,
                                                                Constantes.PADDING_PANTALLA));
        this.gameContainer.add(this.pantallaJuego, BorderLayout.CENTER);

        add(panelControles, BorderLayout.NORTH);
        add(this.gameContainer, BorderLayout.CENTER);
        add(this.labelScore, BorderLayout.SOUTH);
    }

    //Metodos que usan los listeners
    private void onIniciar() {
        int cantidad =
                (Integer) this.spinnerCantidad.getValue();

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
            this.botonPausar.setText(
                    Constantes.TEXTO_REANUDAR
            );
        } else {
            this.controlador.reanudar();
            this.botonPausar.setText(
                    Constantes.TEXTO_PAUSAR
            );
        }
    }
    private void onReiniciar() {
        int cantidad =
                (Integer) this.spinnerCantidad.getValue();
        this.controlador.reiniciar(cantidad);
        this.pausado = false;
        this.botonPausar.setText(
                Constantes.TEXTO_PAUSAR
        );
        this.botonPausar.setEnabled(true);
    }

    /**
     * Llamado por ObservadorUi, ya dentro del Event Dispatch Thread.
     */
    public void actualizar(DatosJuego datos) {
        this.pantallaJuego.actualizar(datos);
        if (this.controlador.getCampoDeBatalla() != null) {
            String score =
                    this.controlador
                            .getCampoDeBatalla()
                            .getScoreBoard()
                            .toString();
            this.labelScore.setText(score);
            if (this.controlador
                    .getCampoDeBatalla()
                    .terminoElJuego()) {
                this.botonPausar.setEnabled(false);
                this.labelScore.setText(
                        score
                                + Constantes.SEPARADOR_SCORE
                                + Constantes.TEXTO_PARTIDA_TERMINADA
                );
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