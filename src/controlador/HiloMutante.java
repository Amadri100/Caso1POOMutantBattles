package controlador;
import modelo.Mutante;
import otros.Constantes;
import otros.Matematicas;
import otros.Punto;

public class HiloMutante implements Runnable {
    private Mutante mutante;
    private boolean estaVivo;
    private EstadoMutante estado;
    private boolean activo;
    private Controlador controlador;

    public HiloMutante(Mutante mutante, Controlador controlador) {
        this.mutante = mutante;
        this.controlador = controlador;
        this.activo = true;
        this.estado = EstadoMutante.MOVIENDOSE;
        this.estaVivo = mutante != null && mutante.estaVivo();
    }
    

    @Override
    public void run() {
        while (this.mutante.estaVivo() && this.activo) {
            
            moverse();
            actualizar();


            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void volverAEstadoBase() {
        this.estado = EstadoMutante.MOVIENDOSE;
    }

    public void moverse() {

        if (this.mutante != null && this.estado == EstadoMutante.MOVIENDOSE) {

            Punto posicionActual = this.mutante.getPosicion();

            if (posicionActual != null) {

                int velocidad = Constantes.VELOCIDAD;
                Punto centro = this.controlador.getCampoDeBatalla().getPuntoMedio();

                // Dirección hacia el centro
                int direccionX = Integer.compare(
                        centro.getX(),
                        posicionActual.getX()
                );

                int direccionY = Integer.compare(
                        centro.getY(),
                        posicionActual.getY()
                );

                /*
                * Elegimos una variación alrededor de la dirección
                * principal.
                *
                * 0-5  -> movimiento hacia el centro
                * 6-7  -> movimiento exactamente hacia atrás
                */
                int tipoMovimiento = Matematicas.intAleatorio(0, 7);

                int movimientoX = direccionX;
                int movimientoY = direccionY;

                switch (tipoMovimiento) {

                    case 0:
                    case 1:
                    case 2:
                        // Directamente hacia el centro
                        break;

                    case 3:
                        // Variación horizontal
                        movimientoX = direccionX;
                        movimientoY = 0;
                        break;

                    case 4:
                        // Variación vertical
                        movimientoX = 0;
                        movimientoY = direccionY;
                        break;

                    case 5:
                        // Otra variación aleatoria
                        movimientoX = direccionX;
                        movimientoY = direccionY;
                        break;

                    case 6:
                    case 7:
                        // EXACTAMENTE en dirección contraria al centro
                        movimientoX = -direccionX;
                        movimientoY = -direccionY;
                        break;
                }

                int nuevoX = posicionActual.getX()
                        + movimientoX * velocidad;

                int nuevoY = posicionActual.getY()
                        + movimientoY * velocidad;

                // Límites del campo
                int maxX = Constantes.SIZE_X;
                int maxY = Constantes.SIZE_Y;

                if (nuevoX < 0) {
                    nuevoX = 0;
                } else if (nuevoX > maxX) {
                    nuevoX = maxX;
                }

                if (nuevoY < 0) {
                    nuevoY = 0;
                } else if (nuevoY > maxY) {
                    nuevoY = maxY;
                }

                posicionActual.setX(nuevoX);
                posicionActual.setY(nuevoY);
            }
        }
    }
    public void elegirAtaque() {
        if (this.estado == EstadoMutante.MOVIENDOSE) {
            this.estado = EstadoMutante.getEstadoAleatorio();
        }
    }

    public Mutante getMutante() {
        return this.mutante;
    }

    public EstadoMutante getEstado() {
        return this.estado;
    }

    public boolean isActivo() {
        return this.activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void actualizar() {
        if (this.mutante != null) {
            this.estaVivo = this.mutante.estaVivo();
        }
    }

    public boolean isEstaVivo() {
        return this.estaVivo;
    }
}
