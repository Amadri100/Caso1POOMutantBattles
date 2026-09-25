package controlador;

import modelo.Mutante;
import otros.Constantes;
import otros.Matematicas;

public class EjecutarPeleas implements Runnable {
    //private ConcurrentLinkedQueue<DatosPelea> referenciaCola;
    private EjecutorDePeleas ejecutor;
    private boolean activo = true;

    public EjecutarPeleas(EjecutorDePeleas ejecutor) {
        this.ejecutor = ejecutor;
    }

    @Override
    public void run() {
        while (this.activo) {
            DatosPelea datoPelea = this.ejecutor.obtenerDatoCola();
            if (datoPelea != null) {
                procesarPelea(datoPelea);
            } else {
                
            }
            if (this.ejecutor.obtenerTotalTareas() == 0) {
                this.terminar(); //Cuando no quedan tareas pendientes termina
            }
            try {
                    Thread.sleep(Constantes.TIEMPO_ESPERA);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    this.activo = false;
            }
        }
    }

    private void procesarPelea(DatosPelea datoPelea) {
        boolean valido = true;
        if (datoPelea.getMutanteA().getEstado() != EstadoMutante.MOVIENDOSE &&
            datoPelea.getMutanteB().getEstado() != EstadoMutante.MOVIENDOSE) {
            double radio = Matematicas.calcularRadio(
                datoPelea.getMutanteA().getMutante().getPosicion(),
                datoPelea.getMutanteB().getMutante().getPosicion()
            );
            if (radio <= Constantes.RADIO_MAXIMO) {
                    Mutante mutanteA = datoPelea.getMutanteA().getMutante();
                    Mutante mutanteB = datoPelea.getMutanteB().getMutante();
                    boolean mutanteAAtaca = datoPelea.getMutanteA().getEstado() == EstadoMutante.ATAQUE; // !mutanteAAtaca = valor de verdad de si defiende
                    boolean mutanteBAtaca = datoPelea.getMutanteB().getEstado() == EstadoMutante.ATAQUE; 
                    if (mutanteAAtaca) {
                        int[] res = mutanteB.recibirDaño(mutanteA.atacar(), !mutanteBAtaca);
                        mutanteA.estadoDespuesAtaque(res[1]);
                    }
                    if (mutanteBAtaca) {
                        int[] res = mutanteA.recibirDaño(mutanteB.atacar(), !mutanteAAtaca);
                        mutanteB.estadoDespuesAtaque(res[1]);
                    }
                    datoPelea.guardarDatos(); //Guarda los datos especificos de este momento en la clase
                    this.ejecutor.agregarPeleaLista(datoPelea); //Se inserta en la lista de finalizados
            }
            else {
                //Se descarta la pelea
            }
            this.ejecutor.tareaTerminada();
        }
        else 
            valido = false;
        if (!valido) {
            this.ejecutor.agregarDatoCola(datoPelea); //Se vuelve a insertar en la cola
        }
    }

    public boolean isActivo() {
        return this.activo;
    }

    public void iniciar() {
        this.activo = true;
    }

    public void terminar() {
        this.activo = false;
    }
}