package ui;

import javax.swing.SwingUtilities;

import controlador.DatosJuego;
import controlador.IObservador;

public class ObservadorUi implements IObservador {
    private VentanaPrincipal ventanaPrincipal;
    private DatosJuego datos;

    public ObservadorUi(VentanaPrincipal ventana) {
        this.ventanaPrincipal = ventana;
    }

    // El Notificador llama a actualizar() desde un hilo de fondo, por lo que
    // el trabajo sobre componentes Swing se despacha al EDT con invokeLater.
    @Override
    public void actualizar(DatosJuego datos) {
        this.datos = datos;
        if (this.ventanaPrincipal != null) {
            SwingUtilities.invokeLater(() -> this.ventanaPrincipal.actualizar(datos));
        }
    }

    public VentanaPrincipal getVentanaPrincipal() {
        return this.ventanaPrincipal;
    }

    public DatosJuego getDatos() {
        return this.datos;
    }
}
