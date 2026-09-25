package main;

import ui.VentanaPrincipal;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Toda la creación/actualización de componentes Swing debe hacerse en el
        // Event Dispatch Thread, por eso se arranca la ventana dentro de invokeLater.
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}
