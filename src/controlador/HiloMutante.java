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

    public HiloMutante(Mutante mutante) {
        this.mutante = mutante;
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

    public void moverse() {
    if (this.mutante != null && this.estado == EstadoMutante.MOVIENDOSE) {
        Punto posicionActual = this.mutante.getPosicion();
        
        if (posicionActual != null) {
            int velocidad = Constantes.VELOCIDAD;
            // se usa la clase Matematicas para generar un desplazamiento aleatorio de -1, 0 o 1
            int tipoMovimiento = Matematicas.intAleatorio(0, 2);
            
            int desplazamientoX = 0;
            int desplazamientoY = 0;

            switch (tipoMovimiento) {
                case 0: // movimiento al centro
                    desplazamientoX = Matematicas.intAleatorio(-1, 1) * velocidad;
                    desplazamientoY = Matematicas.intAleatorio(-1, 1) * velocidad;
                    break;
                        
                case 1: // diagonales
                    desplazamientoX = (Matematicas.intAleatorio(0, 1) == 0 ? 1 : -1) * velocidad;
                    desplazamientoY = (Matematicas.intAleatorio(0, 1) == 0 ? 1 : -1) * velocidad;
                    break;
                        
                case 2: // paso hacia atrás
                    desplazamientoX = Matematicas.intAleatorio(-1, 1) * -velocidad;
                    desplazamientoY = Matematicas.intAleatorio(-1, 1) * -velocidad;
                    break;
            }
        
            
            // se calcula la nueva posición
            int nuevoX = posicionActual.getX() + desplazamientoX;
            int nuevoY = posicionActual.getY() + desplazamientoY;
            
            // validación de límite de campo
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
            
            // se actualiza la posición del mutante
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
