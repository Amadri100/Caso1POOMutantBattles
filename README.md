# Introduccion
Caso I POO II Semestre 
Andrés Madrigal y David Zuniga

# Estructura del Proyecto
```
src/
|-- modelo/
    |-- Mutante.java
    |-- IPower.java
    |-- PoderFuego.java
    |-- PoderAgua.java
    |-- PoderRayo.java
    |-- PoderTierra.java
    |-- PoderTelaraña.java
    |-- PruebaModelo.java
|-- juego/
    |-- TipoEquipo.java
    |-- Equipo.java
    |-- CampoDeBatalla.java
    |-- ScoreBoard.java
    |-- PruebaJuego.java
|-- controlador/
    |-- EjecutarPeleas.java
    |-- DatosPelea.java
    |-- EjecutorDePeleas.java
    |-- DatosAtaque.java
    |-- EstadoMutante.java
    |-- HiloMutante.java
    |-- DatosJuego.java
    |-- IObservable.java
    |-- Notificador.java
    |-- Controlador.java
    |-- IObservador.java
    |-- PruebaControlador.java
```

# Spec
## Paquetes

## modelo
### Mutante
### Mutante
- id : int
- nombre : String
- equipo : Equipo
- vida : int
- poder : IPower
- posicion : Punto
- ataque : int
- defensa : int
+ Mutante(id : int, nombre : String, equipo : Equipo) 
+ getId() : int
+ getNombre() : String
+ getEquipo() : Equipo
+ getPoder() : IPower
+ moverse(posicion : Punto) : void
+ getPosicion() : Punto
+ getVida() : int
+ atacar() : int
+ estadoDespuesAtaque(dañoCausado : int) : void
+ recibirDaño(daño : int, defiende : boolean) : int[]
+ estaVivo() : boolean

// id empieza en 0
// recibirDaño ahora recibe si el mutante defiende (mitiga daño con su defensa) y retorna [vidaRestante, dañoReal]
// estadoDespuesAtaque incrementa el ataque del mutante (hasta ATAQUE_MAXIMO) cuando causó daño

### IPower (Interfaz)
+ usarPoder() : void
+ nombrePoder() : String
### PoderFuego (implementa IPower)
+ PoderFuego() 
+ usarPoder() : void
+ nombrePoder() : String
### PoderAgua (implementa IPower)
+ PoderAgua() 
+ usarPoder() : void
+ nombrePoder() : String
### PoderRayo (implementa IPower)
+ PoderRayo()
+ usarPoder() : void
+ nombrePoder() : String
### PoderTierra (implementa IPower)
+ PoderTierra()
+ usarPoder() : void
+ nombrePoder() : String
### PoderTelaraña (implementa IPower)
+ PoderTelaraña()
+ usarPoder() : void
+ nombrePoder() : String

### PoderAleatorio (clase final)
+ poderAleatorio() : static IPower

### PruebaModelo
+ main(args : String[]) : static void

## juego

### TipoEquipo (enum)
+ EQUIPO_A
+ EQUIPO_B
+ getIndice() : int
+ getSimbolo() : String

### Equipo
- listaMutantes : ArrayList\<Mutante>
- cualEquipo : TipoEquipo
- simbolo : String
- color : Color
- cantidadMutantesVivos : int
- cantidadMutantesMuertos : int 
+ Equipo(equipo : TipoEquipo, cantidad : int)
+ setColor(color : Color) : void
+ setSimbolo(simbolo : String) : void
+ getColor() : Color
+ getSimbolo() : String
+ getListaMutantes() : ArrayList\<Mutante>
+ getTipoEquipo() : TipoEquipo
+ getCantidadMutantesVivos() : int
+ getCantidadMutantesMuertos() : int
+ registrarMuerte(dato : TipoEquipo) : void

### CampoDeBatalla
- sizeX : int
- sizeY : int
- equipoA : Equipo
- equipoB : Equipo
- scoreBoard : ScoreBoard
+ CampoDeBatalla(size : int)
+ terminoElJuego() : boolean
+ getPuntoMedio() : Punto
+ getPuntoMaximo() : Punto
+ getEquipoA() : Equipo
+ getEquipoB() : Equipo
+ getCantidadMutantesVivos() : int[]
+ getCantidadMutantesMuertos() : int[]
+ actualizarScoreBoard() : void
+ getScoreBoard() : ScoreBoard
+ getSizeX() : int
+ getSizeY() : int

### ScoreBoard
- mutantesVivos : int
- mutantesMuertos : int 
- mutantesEquipoA : int
- mutantesEquipoB : int
+ ScoreBoard(campo : CampoDeBatalla)
+ actualizar(campo : CampoDeBatalla) : void
+ toString() : String
+ getMutantesVivos() : int
+ getMutantesMuertos() : int
+ getMutantesEquipoA() : int
+ getMutantesEquipoB() : int


### PruebaJuego
+ main(args : String[]) : static void

## controlador

### EstadoMutante (enum)
+ MOVIENDOSE
+ ATAQUE
+ DEFENSA
+ getEstadoAleatorio() : static EstadoMutante

### HiloMutante (implementa Runnable)
- controlador : Controlador
- estaVivo : boolean
- estado : EstadoMutante
- mutante : Mutante
- activo : boolean
+ HiloMutante(mutante : Mutante, controlador : Controlador)
+ run() : void
+ moverse() : void
+ elegirAtaque() : void
+ volverAEstadoBase() : void
+ actualizar() : void
+ getMutante() : Mutante
+ getEstado() : EstadoMutante
+ isEstaVivo() : boolean
+ isActivo() : boolean
+ setActivo(activo : boolean) : void

### DatosPelea
- mutanteA : HiloMutante
- decisionA : EstadoMutante
- mutanteB : HiloMutante
- decisionB : EstadoMutante
- posA : Punto
- posB : Punto
+ DatosPelea(hiloMutanteA : HiloMutante, hiloMutanteB : HiloMutante)
+ guardarDatos() : void
+ getMutanteA() : HiloMutante
+ getDecisionA() : EstadoMutante
+ getMutanteB() : HiloMutante
+ getDecisionB() : EstadoMutante
+ getPosA() : Punto
+ getPosB() : Punto

### EjecutarPeleas (implementa Runnable)
- ejecutor : EjecutorDePeleas
- activo : boolean
+ EjecutarPeleas(ejecutor : EjecutorDePeleas)
+ run() : void
+ isActivo() : boolean
+ iniciar() : void
+ terminar() : void

### EjecutorDePeleas (implementa Runnable)
- controlador : Controlador
- executorService : ThreadPoolExecutor
- colaPeleas : ConcurrentLinkedQueue\<DatosPelea>
- listaPeleasCompletadas : CopyOnWriteArrayList\<DatosPelea>
- listaEjecutores : ArrayList\<EjecutarPeleas>
- procesados : boolean
- activo : boolean
- pausado : volatile boolean
- tareasPendientes : AtomicInteger
+ EjecutorDePeleas(controlador : Controlador)
+ run() : void
+ obtenerTotalTareas() : int
+ tareaTerminada() : void
+ obtenerDatoCola() : DatosPelea
+ agregarDatoCola(datosPelea : DatosPelea) : void
+ agregarPeleaLista(datoPelea : DatosPelea) : void
+ accionesFinalCiclo() : void
+ getColaPeleas() : ConcurrentLinkedQueue\<DatosPelea>
+ drenarPeleasCompletadas() : ArrayList\<DatosPelea>
+ setActivo(activo : boolean) : synchronized void
+ isActivo() : boolean
+ setPausado(pausado : boolean) : void
+ isPausado() : boolean


### DatosAtaque
- poder : IPower
- mutanteOrigen : Mutante
- mutanteDestino : Mutante
+ DatosAtaque(datoPelea : DatosPelea, numMutante : int)
+ DatosAtaque(ataque : DatosAtaque)
+ getPoder() : IPower
+ getOrigen() : Punto
+ getDestino() : Punto

### DatosJuego
- listaMutantes : ArrayList\<HiloMutante>
- listaPeleas : ArrayList\<DatosPelea>
- simboloPorEquipo : String[]
- colorPorEquipo : Color[]
+ DatosJuego(controlador : Controlador)
+ getListaMutantes() : ArrayList\<Mutante>
+ getSimboloPorEquipo() : String[]
+ getColorPorEquipo() : Color[]
+ getListaHilosMutantes() : ArrayList\<HiloMutante>
+ getListaPeleas() : ArrayList\<DatosPelea>

### IObservable (Interfaz)
+ agregaObservadores(observador : IObservador) : void
+ quitarObservadores(observador : IObservador) : void 
+ notificar() : void

### Notificador (implementa Runnable)
- controlador : Controlador 
- activo : boolean
- pausado : volatile boolean
+ Notificador(controlador : Controlador)
+ run() : void
+ setActivo(valor : boolean) : void
+ getActivo() : boolean
+ setPausado(pausado : boolean) : void
+ isPausado() : boolean

### Controlador (implementa IObservable)
- listaHilosMutantes : ArrayList\<HiloMutante>[]
- listaObservadores : ArrayList\<IObservador>
- ejecutorDePeleas : EjecutorDePeleas
- notificador : Notificador
- executorService : ThreadPoolExecutor
- campoDeBatalla : CampoDeBatalla
- datosJuego : DatosJuego
- simulacionIniciada : boolean
- colorSeleccionado : Color[]
+ Controlador() 
+ iniciarSimulacion(cantidadPorEquipo : int) : void
+ iniciarJuego(cantidad : int) : void
+ resetEstadoMutantes() : void
+ verificacionesFinCiclo() : void
+ pausar() : void
+ reanudar() : void
+ reiniciar(cantidadPorEquipo : int) : void
+ limpiarListas() : void
+ detener() : void
+ obtenerDatosJuego() : void
+ agregaObservadores(observador : IObservador) : void
+ quitarObservadores(observador : IObservador) : void 
+ notificar() : void
+ getColor() : Color[]
+ isSimulacionIniciada() : boolean
+ getCampoDeBatalla() : CampoDeBatalla
+ getDatosJuego() : DatosJuego
+ getEjecutorDePeleas() : EjecutorDePeleas
+ getListaHilosMutantes(tipo : TipoEquipo) : ArrayList\<HiloMutante>
+ getListaObservadores() : ArrayList\<IObservador>
+ getMedidasCampoBatalla() : int[]

### PruebaControlador
+ main(args : String[]) : static void

## ui

### PantallaJuego (hereda de JPanel)
- mutantesDibujados : ArrayList\<MutantesDibujados>
- principal : VentanaPrincipal
- ataquesActivos : ArrayList\<AnimacionAtaque>
- infoVisible : boolean
+ PantallaJuego(principal : VentanaPrincipal)
+ actualizar(datos : DatosJuego) : void
- sincronizarMutantes(mutantes : ArrayList\<Mutante>) : void
- claveMutante(mutante : Mutante) : int
- agregarNuevasAnimaciones(peleas : ArrayList\<DatosPelea>) : void
# paintComponent(g : Graphics) : void   
+ agregarAnimacionAtaque(ataque : DatosAtaque) : void
+ getMutantesDibujados() : ArrayList\<MutantesDibujados>
+ getPrincipal() : VentanaPrincipal
+ getAtaquesActivos() : ArrayList\<AnimacionAtaque>
+ isInfoVisible() : boolean
+ setInfoVisible(infoVisible : boolean) : void

// sincronizarMutantes, claveMutante y agregarNuevasAnimaciones son privados: helpers internos de actualizar()
// paintComponent es protected porque sobrescribe el metodo protected de JPanel

### VentanaPrincipal (hereda de JFrame)
- botonIniciar : JButton     
- botonPausar : JButton      
- botonReiniciar : JButton   
- pantallaJuego : PantallaJuego
- controlador : Controlador
- observador : ObservadorUi
- spinnerCantidad : JSpinner
- etiquetaScore : JLabel
- pausado : boolean
+ VentanaPrincipal()
+ inicializarComponentes() : void
- alPresionarIniciar(evento : ActionEvent) : void
- alPresionarPausar(evento : ActionEvent) : void
- alPresionarReiniciar(evento : ActionEvent) : void
+ actualizar(datos : DatosJuego) : void
+ getBotonIniciar() : JButton
+ getBotonPausar() : JButton
+ getBotonReiniciar() : JButton
+ getPantallaJuego() : PantallaJuego

// los tres manejadores de botones son privados: solo se usan como listeners internos por metodo-referencia

### MutantesDibujados
- mutante : Mutante
+ MutantesDibujados(mutante : Mutante)
+ dibujar(g : Graphics2D) : void
- dibujarBarraVida(g : Graphics2D, x : int, y : int, diametro : int) : void
+ obtenerPosicion() : Punto
+ toString() : String
+ getMutante() : Mutante

// dibujarBarraVida es privado: helper interno de dibujar()

### AnimacionAtaque (hereda de DatosAtaque)
- pantalla : PantallaJuego
- frameActual : int
+ AnimacionAtaque(pantalla : PantallaJuego, ataque : DatosAtaque)
+ dibujar(g : Graphics2D) : void
- colorPorPoder() : Color
+ isTerminada() : boolean
+ getPantalla() : PantallaJuego
+ getFrameActual() : int
+ setFrameActual(frameActual : int) : void

// colorPorPoder es privado: helper interno de dibujar()

### ObservadorUi (implementa IObservador)
- ventanaPrincipal : VentanaPrincipal
- datos : DatosJuego
+ ObservadorUi(ventana : VentanaPrincipal)
+ actualizar(datos : DatosJuego) : void
+ getVentanaPrincipal() : VentanaPrincipal
+ getDatos() : DatosJuego
## otros

### Constantes (clase final)
+ TIEMPO_ESPERA                    : static final int
+ SIZE_X                           : static final int
+ SIZE_Y                           : static final int
+ CANTIDAD_THREADS                 : static final int
+ PADDING_PANTALLA                 : static final int
+ RESIZABLE                        : static final boolean
+ ATAQUE_MINIMO                    : static final int
+ ATAQUE_MAXIMO_POR_DEFECTO        : static final int
+ ATAQUE_MAXIMO                    : static final int
+ CANTIDAD_ESTADOS_MUTANTE         : static final int
+ CANTIDAD_DE_DECISIONES_MUTANTE   : static final int
+ DIMENSIONES_PANTALLA             : static final int[]
+ VELOCIDAD                        : static final int
+ TAMAÑO_MINIMO                    : static final int
+ TAMAÑO_MAXIMO                    : static final int
+ NOMBRES_PODERES                  : static final String[]
+ COLORES_PODERES                  : static final Color[]
+ DEFENSA_MINIMO                   : static final int
+ DEFENSA_MAXIMO                   : static final int
+ MAXIMO_VIDA                      : static final int
+ CANTIDAD_EQUIPOS                 : static final int
+ CANTIDAD_MUTANTES_POR_DEFECTO    : static final int
+ CANTIDAD_MUTANTES_MAXIMO         : static final int
+ CANTIDAD_MUTANTES_MINIMO         : static final int
+ POSICION_ARREGLO                 : static final int[]
+ SIMBOLOS                         : static final String[]
+ RADIO_MAXIMO                     : static final double
+ DISTANCIA_MAXIMA_RECORRIDO       : static final int
+ RUTA_IMAGENES_MUTANTES           : static final String[]
+ RUTA_IMAGENES_ATAQUES            : static final String[]
+ COLORES                          : static Color[][]
+ BACKGROUND                       : static Color
+ TASA_REFRESCO_DATOS              : static final int
+ TASA_REFRESCO_PANTALLA           : static final int
+ DELAY_DE_THREADS                 : static final int
+ MUTANTES_MUERTOS_INICIAL         : static final int
+ TAMANO_ANIMACION                 : static final int
+ NUMERO_FRAMES_ATAQUE             : static final int
+ DIAMETRO_MUTANTE                 : static final int
+ COLOR_MUTANTE_SIN_EQUIPO         : static final Color
+ COLOR_BORDE_MUTANTE              : static final Color
+ COLOR_SIMBOLO_MUTANTE            : static final Color
+ COLOR_NOMBRE_MUTANTE             : static final Color
+ COLOR_BARRA_VIDA_FONDO           : static final Color
+ COLOR_BARRA_VIDA_ALTA            : static final Color
+ COLOR_BARRA_VIDA_BAJA            : static final Color
+ FUENTE_SIMBOLO                   : static final Font
+ FUENTE_NOMBRE                    : static final Font
+ FUENTE_INFORMACION               : static final Font
+ POSICION_X_INFO                  : static final int
+ POSICION_Y_INFO                  : static final int
+ SEPARACION_BARRA_VIDA            : static final int
+ ALTURA_BARRA_VIDA                : static final int
+ PORCENTAJE_VIDA_BAJA             : static final double
+ AJUSTE_X_NOMBRE                  : static final int
+ SEPARACION_Y_NOMBRE              : static final int
+ TITULO_VENTANA                   : static final String
+ TEXTO_INICIAR                    : static final String
+ TEXTO_PAUSAR                     : static final String
+ TEXTO_REANUDAR                   : static final String
+ TEXTO_REINICIAR                  : static final String
+ TEXTO_MUTANTES_POR_EQUIPO        : static final String
+ TEXTO_INSTRUCCIONES              : static final String
+ SEPARADOR_SCORE                  : static final String
+ TEXTO_PARTIDA_TERMINADA          : static final String
+ BORDE_SCORE_ARRIBA               : static final int
+ BORDE_SCORE_IZQUIERDA            : static final int
+ BORDE_SCORE_ABAJO                : static final int
+ BORDE_SCORE_DERECHA              : static final int
+ INCREMENTO_CANTIDAD_MUTANTES     : static final int

// POSICION_ARREGLO y SIMBOLOS: [0] -> Equipo A, [1] -> Equipo B

### Punto
- x : int
- y : int
+ Punto(x : int, y : int)
+ setX(x : int) : void
+ setY(y : int) : void
+ getX() : int
+ getY() : int

### Matematicas (clase final)
+ calcularRadio(a : Punto, b : Punto) : static double
+ doubleAleatorio(minimo : double, maximo : double) : static double
+ intAleatorio(minimo : int, maximo : int) : static int

// Todos los rangos son inclusivos

## main
### main
+ main(args : String[]) : static void 

# UML
```
startuml BatallaDeMutantes

skinparam classAttributeIconSize 0
skinparam packageStyle rectangle
hide empty members

' ============================================================
' PAQUETE modelo
' ============================================================
package modelo {

    interface IPower {
        + usarPoder() : void
        + nombrePoder() : String
    }

    class Mutante {
        - id : int
        - nombre : String
        - equipo : Equipo
        - vida : int
        - poder : IPower
        - posicion : Punto
        - ataque : int
        - defensa : int
        + Mutante(id : int, nombre : String, equipo : Equipo)
        + getId() : int
        + getNombre() : String
        + getEquipo() : Equipo
        + getPoder() : IPower
        + moverse(posicion : Punto) : void
        + getPosicion() : Punto
        + getVida() : int
        + atacar() : int
        + estadoDespuesAtaque(dañoCausado : int) : void
        + recibirDaño(daño : int, defiende : boolean) : int[]
        + estaVivo() : boolean
    }

    class PoderFuego implements IPower {
        + PoderFuego()
        + usarPoder() : void
        + nombrePoder() : String
    }

    class PoderAgua implements IPower {
        + PoderAgua()
        + usarPoder() : void
        + nombrePoder() : String
    }

    class PoderRayo implements IPower {
        + PoderRayo()
        + usarPoder() : void
        + nombrePoder() : String
    }

    class PoderTierra implements IPower {
        + PoderTierra()
        + usarPoder() : void
        + nombrePoder() : String
    }

    class PoderTelaraña implements IPower {
        + PoderTelaraña()
        + usarPoder() : void
        + nombrePoder() : String
    }

    class PoderAleatorio <<final>> {
        + {static} poderAleatorio() : IPower
    }

    class PruebaModelo {
        + {static} main(args : String[]) : void
    }

    Mutante "1" *-- "1" IPower : poder >
    Mutante ..> otros.Punto : posicion
    Mutante ..> juego.Equipo : equipo
    PoderAleatorio ..> IPower : crea >
    PoderAleatorio ..> otros.Matematicas : usa >
}

' ============================================================
' PAQUETE juego
' ============================================================
package juego {

    enum TipoEquipo {
        EQUIPO_A
        EQUIPO_B
        + getIndice() : int
        + getSimbolo() : String
    }

    class Equipo {
        - listaMutantes : ArrayList<Mutante>
        - cualEquipo : TipoEquipo
        - simbolo : String
        - color : Color
        - cantidadMutantesVivos : int
        - cantidadMutantesMuertos : int
        + Equipo(equipo : TipoEquipo, cantidad : int)
        + setColor(color : Color) : void
        + setSimbolo(simbolo : String) : void
        + getColor() : Color
        + getSimbolo() : String
        + getListaMutantes() : ArrayList<Mutante>
        + getTipoEquipo() : TipoEquipo
        + getCantidadMutantesVivos() : int
        + getCantidadMutantesMuertos() : int
        + registrarMuerte(dato : TipoEquipo) : void
    }

    class CampoDeBatalla {
        - sizeX : int
        - sizeY : int
        - equipoA : Equipo
        - equipoB : Equipo
        - scoreBoard : ScoreBoard
        + CampoDeBatalla(size : int)
        + terminoElJuego() : boolean
        + getPuntoMedio() : Punto
        + getPuntoMaximo() : Punto
        + getEquipoA() : Equipo
        + getEquipoB() : Equipo
        + getCantidadMutantesVivos() : int[]
        + getCantidadMutantesMuertos() : int[]
        + actualizarScoreBoard() : void
        + getScoreBoard() : ScoreBoard
        + getSizeX() : int
        + getSizeY() : int
    }

    class ScoreBoard {
        - mutantesVivos : int
        - mutantesMuertos : int
        - mutantesEquipoA : int
        - mutantesEquipoB : int
        + ScoreBoard(campo : CampoDeBatalla)
        + actualizar(campo : CampoDeBatalla) : void
        + toString() : String
        + getMutantesVivos() : int
        + getMutantesMuertos() : int
        + getMutantesEquipoA() : int
        + getMutantesEquipoB() : int
    }

    class PruebaJuego {
        + {static} main(args : String[]) : void
    }

    Equipo "1" o-- "*" modelo.Mutante : listaMutantes
    Equipo --> TipoEquipo : cualEquipo
    CampoDeBatalla "1" *-- "2" Equipo
    CampoDeBatalla "1" *-- "1" ScoreBoard
    ScoreBoard ..> CampoDeBatalla : actualiza >
}

' ============================================================
' PAQUETE controlador
' ============================================================
package controlador {

    enum EstadoMutante {
        MOVIENDOSE
        ATAQUE
        DEFENSA
        + {static} getEstadoAleatorio() : EstadoMutante
    }

    class HiloMutante implements Runnable {
        - controlador : Controlador
        - estaVivo : boolean
        - estado : EstadoMutante
        - mutante : modelo.Mutante
        - activo : boolean
        + HiloMutante(mutante : modelo.Mutante, controlador : Controlador)
        + run() : void
        + moverse() : void
        + elegirAtaque() : void
        + volverAEstadoBase() : void
        + actualizar() : void
        + getMutante() : modelo.Mutante
        + getEstado() : EstadoMutante
        + isEstaVivo() : boolean
        + isActivo() : boolean
        + setActivo(activo : boolean) : void
    }

    class DatosPelea {
        - mutanteA : HiloMutante
        - decisionA : EstadoMutante
        - mutanteB : HiloMutante
        - decisionB : EstadoMutante
        - posA : otros.Punto
        - posB : otros.Punto
        + DatosPelea(hiloMutanteA : HiloMutante, hiloMutanteB : HiloMutante)
        + guardarDatos() : void
        + getMutanteA() : HiloMutante
        + getDecisionA() : EstadoMutante
        + getMutanteB() : HiloMutante
        + getDecisionB() : EstadoMutante
        + getPosA() : otros.Punto
        + getPosB() : otros.Punto
    }

    class EjecutarPeleas implements Runnable {
        - ejecutor : EjecutorDePeleas
        - activo : boolean
        + EjecutarPeleas(ejecutor : EjecutorDePeleas)
        + run() : void
        - procesarPelea(datoPelea : DatosPelea) : void
        + isActivo() : boolean
        + iniciar() : void
        + terminar() : void
    }

    class EjecutorDePeleas implements Runnable {
        - controlador : Controlador
        - executorService : ThreadPoolExecutor
        - colaPeleas : ConcurrentLinkedQueue<DatosPelea>
        - listaPeleasCompletadas : CopyOnWriteArrayList<DatosPelea>
        - listaEjecutores : ArrayList<EjecutarPeleas>
        - procesados : boolean
        - activo : boolean
        - pausado : boolean
        - tareasPendientes : AtomicInteger
        + EjecutorDePeleas(controlador : Controlador)
        + run() : void
        + obtenerTotalTareas() : int
        + tareaTerminada() : void
        + obtenerDatoCola() : DatosPelea
        + agregarDatoCola(datosPelea : DatosPelea) : void
        + agregarPeleaLista(datoPelea : DatosPelea) : void
        + accionesFinalCiclo() : void
        + getColaPeleas() : ConcurrentLinkedQueue<DatosPelea>
        + drenarPeleasCompletadas() : ArrayList<DatosPelea>
        + setActivo(activo : boolean) : void
        + isActivo() : boolean
        + setPausado(pausado : boolean) : void
        + isPausado() : boolean
    }

    class DatosAtaque {
        - poder : modelo.IPower
        - mutanteOrigen : modelo.Mutante
        - mutanteDestino : modelo.Mutante
        + DatosAtaque(datoPelea : DatosPelea, numMutante : int)
        + DatosAtaque(ataque : DatosAtaque)
        + getPoder() : modelo.IPower
        + getOrigen() : otros.Punto
        + getDestino() : otros.Punto
    }

    class DatosJuego {
        - listaMutantes : ArrayList<HiloMutante>
        - listaPeleas : ArrayList<DatosPelea>
        - simboloPorEquipo : String[]
        - colorPorEquipo : Color[]
        + DatosJuego(controlador : Controlador)
        + getListaMutantes() : ArrayList<modelo.Mutante>
        + getSimboloPorEquipo() : String[]
        + getColorPorEquipo() : Color[]
        + getListaHilosMutantes() : ArrayList<HiloMutante>
        + getListaPeleas() : ArrayList<DatosPelea>
    }

    interface IObservable {
        + agregaObservadores(observador : IObservador) : void
        + quitarObservadores(observador : IObservador) : void
        + notificar() : void
    }

    interface IObservador {
        + actualizar(datos : DatosJuego) : void
    }

    class Notificador implements Runnable {
        - controlador : Controlador
        - activo : boolean
        - pausado : boolean
        + Notificador(controlador : Controlador)
        + run() : void
        + setActivo(valor : boolean) : void
        + getActivo() : boolean
        + setPausado(pausado : boolean) : void
        + isPausado() : boolean
    }

    class Controlador implements IObservable {
        - listaHilosMutantes : ArrayList<HiloMutante>[]
        - listaObservadores : ArrayList<IObservador>
        - ejecutorDePeleas : EjecutorDePeleas
        - notificador : Notificador
        - executorService : ThreadPoolExecutor
        - campoDeBatalla : juego.CampoDeBatalla
        - datosJuego : DatosJuego
        - simulacionIniciada : boolean
        - colorSeleccionado : Color[]
        + Controlador()
        + iniciarSimulacion(cantidadPorEquipo : int) : void
        + iniciarJuego(cantidad : int) : void
        + resetEstadoMutantes() : void
        + verificacionesFinCiclo() : void
        + pausar() : void
        + reanudar() : void
        + reiniciar(cantidadPorEquipo : int) : void
        + limpiarListas() : void
        + detener() : void
        + obtenerDatosJuego() : void
        + agregaObservadores(observador : IObservador) : void
        + quitarObservadores(observador : IObservador) : void
        + notificar() : void
        + getColor() : Color[]
        + isSimulacionIniciada() : boolean
        + getCampoDeBatalla() : juego.CampoDeBatalla
        + getDatosJuego() : DatosJuego
        + getEjecutorDePeleas() : EjecutorDePeleas
        + getListaHilosMutantes(tipo : juego.TipoEquipo) : ArrayList<HiloMutante>
        + getListaObservadores() : ArrayList<IObservador>
        + getMedidasCampoBatalla() : int[]
    }

    class PruebaControlador {
        + {static} main(args : String[]) : void
    }

    HiloMutante "*" --> "1" Controlador
    HiloMutante "1" *-- "1" modelo.Mutante
    HiloMutante --> EstadoMutante : estado
    DatosPelea "1" o-- "2" HiloMutante
    DatosPelea --> EstadoMutante : decisionA / decisionB
    EjecutarPeleas "*" --> "1" EjecutorDePeleas
    EjecutarPeleas ..> DatosPelea : procesa >
    EjecutorDePeleas "1" --> "1" Controlador
    EjecutorDePeleas "1" o-- "*" EjecutarPeleas
    EjecutorDePeleas "1" o-- "*" DatosPelea
    DatosAtaque ..> DatosPelea
    DatosAtaque --> modelo.IPower : poder
    DatosAtaque --> modelo.Mutante : mutanteOrigen / mutanteDestino
    DatosJuego "1" o-- "*" HiloMutante
    DatosJuego "1" o-- "*" DatosPelea
    DatosJuego --> Controlador : construido a partir de >
    Notificador "1" --> "1" Controlador
    Controlador "1" *-- "1" EjecutorDePeleas
    Controlador "1" *-- "1" Notificador
    Controlador "1" *-- "1" juego.CampoDeBatalla
    Controlador "1" *-- "0..1" DatosJuego
    Controlador "1" o-- "*" HiloMutante
    Controlador "1" o-- "*" IObservador
    Notificador ..|> Runnable
    Controlador ..|> IObservable
}

' ============================================================
' PAQUETE ui
' ============================================================
package ui {

    class VentanaPrincipal {
        - botonIniciar : JButton
        - botonPausar : JButton
        - botonReiniciar : JButton
        - pantallaJuego : PantallaJuego
        - controlador : controlador.Controlador
        - observador : ObservadorUi
        - spinnerCantidad : JSpinner
        - etiquetaScore : JLabel
        - pausado : boolean
        + VentanaPrincipal()
        + inicializarComponentes() : void
        - alPresionarIniciar(evento : ActionEvent) : void
        - alPresionarPausar(evento : ActionEvent) : void
        - alPresionarReiniciar(evento : ActionEvent) : void
        + actualizar(datos : controlador.DatosJuego) : void
        + getBotonIniciar() : JButton
        + getBotonPausar() : JButton
        + getBotonReiniciar() : JButton
        + getPantallaJuego() : PantallaJuego
    }
    VentanaPrincipal --|> JFrame

    class PantallaJuego {
        - mutantesDibujados : ArrayList<MutantesDibujados>
        - principal : VentanaPrincipal
        - ataquesActivos : ArrayList<AnimacionAtaque>
        - infoVisible : boolean
        + PantallaJuego(principal : VentanaPrincipal)
        + actualizar(datos : controlador.DatosJuego) : void
        - sincronizarMutantes(mutantes : ArrayList<modelo.Mutante>) : void
        - claveMutante(mutante : modelo.Mutante) : int
        - agregarNuevasAnimaciones(peleas : ArrayList<controlador.DatosPelea>) : void
        # paintComponent(g : Graphics) : void
        + agregarAnimacionAtaque(ataque : controlador.DatosAtaque) : void
        + getMutantesDibujados() : ArrayList<MutantesDibujados>
        + getPrincipal() : VentanaPrincipal
        + getAtaquesActivos() : ArrayList<AnimacionAtaque>
        + isInfoVisible() : boolean
        + setInfoVisible(infoVisible : boolean) : void
    }
    PantallaJuego --|> JPanel

    class MutantesDibujados {
        - mutante : modelo.Mutante
        + MutantesDibujados(mutante : modelo.Mutante)
        + dibujar(g : Graphics2D) : void
        - dibujarBarraVida(g : Graphics2D, x : int, y : int, diametro : int) : void
        + obtenerPosicion() : otros.Punto
        + toString() : String
        + getMutante() : modelo.Mutante
    }

    class AnimacionAtaque {
        - pantalla : PantallaJuego
        - frameActual : int
        + AnimacionAtaque(pantalla : PantallaJuego, ataque : controlador.DatosAtaque)
        + dibujar(g : Graphics2D) : void
        - colorPorPoder() : Color
        + isTerminada() : boolean
        + getPantalla() : PantallaJuego
        + getFrameActual() : int
        + setFrameActual(frameActual : int) : void
    }
    AnimacionAtaque --|> controlador.DatosAtaque

    class ObservadorUi implements controlador.IObservador {
        - ventanaPrincipal : VentanaPrincipal
        - datos : controlador.DatosJuego
        + ObservadorUi(ventana : VentanaPrincipal)
        + actualizar(datos : controlador.DatosJuego) : void
        + getVentanaPrincipal() : VentanaPrincipal
        + getDatos() : controlador.DatosJuego
    }

    class PruebaUi {
        + {static} main(args : String[]) : void
    }

    VentanaPrincipal "1" *-- "1" PantallaJuego
    VentanaPrincipal "1" *-- "1" controlador.Controlador
    VentanaPrincipal "1" *-- "1" ObservadorUi
    PantallaJuego "1" o-- "*" MutantesDibujados
    PantallaJuego "1" o-- "*" AnimacionAtaque
    PantallaJuego "1" --> "1" VentanaPrincipal
    MutantesDibujados "1" --> "1" modelo.Mutante
    AnimacionAtaque "1" --> "1" PantallaJuego
    ObservadorUi "1" --> "1" VentanaPrincipal
}

' ============================================================
' PAQUETE otros
' ============================================================
package otros {

    class Constantes <<final>> {
        + {static} TIEMPO_ESPERA : int
        + {static} SIZE_X : int
        + {static} SIZE_Y : int
        + {static} CANTIDAD_THREADS : int
        + {static} PADDING_PANTALLA : int
        + {static} RESIZABLE : boolean
        + {static} ATAQUE_MINIMO : int
        + {static} ATAQUE_MAXIMO_POR_DEFECTO : int
        + {static} ATAQUE_MAXIMO : int
        + {static} CANTIDAD_ESTADOS_MUTANTE : int
        + {static} CANTIDAD_DE_DECISIONES_MUTANTE : int
        + {static} DIMENSIONES_PANTALLA : int[]
        + {static} VELOCIDAD : int
        + {static} TAMAÑO_MINIMO : int
        + {static} TAMAÑO_MAXIMO : int
        + {static} NOMBRES_PODERES : String[]
        + {static} COLORES_PODERES : Color[]
        + {static} DEFENSA_MINIMO : int
        + {static} DEFENSA_MAXIMO : int
        + {static} MAXIMO_VIDA : int
        + {static} CANTIDAD_EQUIPOS : int
        + {static} CANTIDAD_MUTANTES_POR_DEFECTO : int
        + {static} CANTIDAD_MUTANTES_MAXIMO : int
        + {static} CANTIDAD_MUTANTES_MINIMO : int
        + {static} POSICION_ARREGLO : int[]
        + {static} SIMBOLOS : String[]
        + {static} RADIO_MAXIMO : double
        + {static} DISTANCIA_MAXIMA_RECORRIDO : int
        + {static} RUTA_IMAGENES_MUTANTES : String[]
        + {static} RUTA_IMAGENES_ATAQUES : String[]
        + {static} COLORES : Color[][]
        + {static} BACKGROUND : Color
        + {static} TASA_REFRESCO_DATOS : int
        + {static} TASA_REFRESCO_PANTALLA : int
        + {static} DELAY_DE_THREADS : int
        + {static} MUTANTES_MUERTOS_INICIAL : int
        + {static} TAMANO_ANIMACION : int
        + {static} NUMERO_FRAMES_ATAQUE : int
        + {static} DIAMETRO_MUTANTE : int
        + {static} COLOR_MUTANTE_SIN_EQUIPO : Color
        + {static} COLOR_BORDE_MUTANTE : Color
        + {static} COLOR_SIMBOLO_MUTANTE : Color
        + {static} COLOR_NOMBRE_MUTANTE : Color
        + {static} COLOR_BARRA_VIDA_FONDO : Color
        + {static} COLOR_BARRA_VIDA_ALTA : Color
        + {static} COLOR_BARRA_VIDA_BAJA : Color
        + {static} FUENTE_SIMBOLO : Font
        + {static} FUENTE_NOMBRE : Font
        + {static} FUENTE_INFORMACION : Font
        + {static} POSICION_X_INFO : int
        + {static} POSICION_Y_INFO : int
        + {static} SEPARACION_BARRA_VIDA : int
        + {static} ALTURA_BARRA_VIDA : int
        + {static} PORCENTAJE_VIDA_BAJA : double
        + {static} AJUSTE_X_NOMBRE : int
        + {static} SEPARACION_Y_NOMBRE : int
        + {static} TITULO_VENTANA : String
        + {static} TEXTO_INICIAR : String
        + {static} TEXTO_PAUSAR : String
        + {static} TEXTO_REANUDAR : String
        + {static} TEXTO_REINICIAR : String
        + {static} TEXTO_MUTANTES_POR_EQUIPO : String
        + {static} TEXTO_INSTRUCCIONES : String
        + {static} SEPARADOR_SCORE : String
        + {static} TEXTO_PARTIDA_TERMINADA : String
        + {static} BORDE_SCORE_ARRIBA : int
        + {static} BORDE_SCORE_IZQUIERDA : int
        + {static} BORDE_SCORE_ABAJO : int
        + {static} BORDE_SCORE_DERECHA : int
        + {static} INCREMENTO_CANTIDAD_MUTANTES : int
    }

    class Punto {
        - x : int
        - y : int
        + Punto(x : int, y : int)
        + setX(x : int) : void
        + setY(y : int) : void
        + getX() : int
        + getY() : int
    }

    class Matematicas <<final>> {
        + {static} calcularRadio(a : Punto, b : Punto) : double
        + {static} doubleAleatorio(minimo : double, maximo : double) : double
        + {static} intAleatorio(minimo : int, maximo : int) : int
    }
}

' ============================================================
' PAQUETE main
' ============================================================
package main {
    class main {
        + {static} main(args : String[]) : void
    }
    main ..> ui.VentanaPrincipal : crea >
}

@enduml
```
