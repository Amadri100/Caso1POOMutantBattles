package ui;

import javax.swing.SwingUtilities;

import controlador.DatosJuego;
import controlador.IObservador;

public class ObservadorUi implements IObservador {
    private VentanaPrincipal ventanaPrincipal;

    public ObservadorUi(VentanaPrincipal ventana) {
        this.ventanaPrincipal = ventana;
    }

    @Override
    public void actualizar(DatosJuego datos) {
        SwingUtilities.invokeLater(() -> this.ventanaPrincipal.actualizar(datos));
    }

    public VentanaPrincipal getVentanaPrincipal() {
        return this.ventanaPrincipal;
    }

}
