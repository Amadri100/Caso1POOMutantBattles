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
+ atacar() : int
+ recibirDaño(daño : int) : int
+ estaVivo() : boolean

// id empieza en 0
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
+ obtenerIndice() : int

### Equipo
- listaMutantes : ArrayList\<Mutante>
- cualEquipo : TipoEquipo
- simbolo : String
- color : Color
- cantidadMutantesVivos : int
- cantidadMutantesMuertos : int 
+ Equipo (equipo : TipoEquipo, cantidad : int)
+ setColor(color : Color) : void
+ setSimbolo(simbolo : String) : void
+ getColor() : Color
+ getSimbolo() : String
+ getListaMutantes() : ArrayList\<Mutante>
+ getTipoEquipo() : TipoEquipo
+ getCantidadMutantesVivos(dato : TipoEquipo) : int
+ getCantidadMutantesMuertos(dato : TipoEquipo) : int
+ registrarMuerte(dato : TipoEquipo) : void

//cantidadMutantesVivos [0] -> Equipo A [1] -> Equipo B

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
+ getScoreBoard() : ScoreBoard
+ getSizeX() : int
+ getSizeY() : int

### ScoreBoard
- mutantesVivos : int
- mutantesMuertos : int 
- mutantesEquipoA : int
- mutantesEquipoB : int
+ ScoreBoard(campo : CampoBatalla)
+ actualizar(campo : CampoBatalla) : void
+ toString() : String
+ getMutantesVivos() : int
+ getMutantesMuertos() : int
+ getMutantesEquipoA() : int
+ getMutantesEquipoB() : int

### PruebaJuego
+ main(args : String[]) : static void : static void

## controlador

### EjecutarPeleas (implementa Runnable)
- refenciaDeCola : ConcurrentLinkedQueue\<int[]>
- ejecutor : EjecutorDePeleas
- activo : boolean
+ EjecutarPeleas(ejecutor : EjecutorDePeleas)
+ run() : void
+ obtenerMutante(id : int, equipo : String) : HiloMutante
+ isActivo() : boolean
+ setActivo(activo : boolean) : void
+ terminar() : void
+ 
//EjecutarPeleas, ejecuta una pelea, revisa si ya se movio el contrincante, si ambos estan listo se ejecuta el proceso y se elimina la "llave" de la cola, de lo contrario se inserta al final para ser revisado despues.


### DatosPelea
- mutanteA : Mutante
- decisionA : EstadoMutante
- mutanteB : Mutante
- decisionB : EstadoMutante
- posA : Punto
- posB : Punto
+ DatosPelea(hiloMutanteA : HiloMutante, hiloMutanteB : HiloMutante)
+ getMutanteA() : Mutante 
+ getDecisionA() : EstadoMutante
+ getMutanteB() : Mutante 
+ getDecisionB() : EstadoMutante
+ getPosA() : Punto 
+ getPosB() : Punto 

### EjecutorDePeleas (implementa Runnable)
- controlador : Controlador
- executorService : ThreadPoolExecutor
- colaPeleas : ConcurrentLinkedQueue\<int[]>
- listaPeleasCompletadas : CopyOnWriteArrayList\<DatosPelea>
- listaEjecutores : ArrayList\<EjecutarPeleas>
- activo : boolean
+ EjecutorDePeleas(controlador : Controlador)
+ run() : void
+ obtenerDatoCola() : int[]
+ agregarDatoCola(int[]) : void 
+ agregarPeleaALista(datoPelea : DatosPelea) : void 
+ reiniciarMutantes() : void 
+ getColaPeleas() : ConcurrentLinkedQueue\<int[]>
+ getListaEjecutores() : EjecutarPeleas[]
+ isActivo() : boolean
+ setActivo(activo : boolean) : void

//Utiliza una cola y una "pool" de threads para procesar todas las peleas entre los mutantes

//reiniciarMutantes() -> Hace que los mutantes vuelvan a estar listos para moverse

// Todos los potenciales oponentes de un mutante son los que estan a radioMaximo + distanciaMaximaDeRecorrido


### DatosAtaque
- poder : Poder
- mutanteOrigen : Mutante
- mutanteDestino : Mutante
+ DatosAtaque(datoPelea : DatosPelea, cual : int)
+ DatosAtaque(ataque : DatosAtaque)
+ getPoder() : Poder 
+ getOrigen() : Punto
+ getDestino() : Punto 

### EstadoMutante (enum)
+ MOVIENDOSE
+ ATAQUE
+ DEFENSA

### HiloMutante (implementa Runnable)
- controlador : Controlador
- estaVivo : boolean
- estado : EstadoMutante
- mutante : Mutante
- activo : boolean
+ HiloMutante(mutante : Mutante)
+ run() : void
+ isEstaVivo() : boolean
+ isEstado() : EstadoMutante
+ elegirAtaque() : void
+ regresarAEstadoBase() : void
+ actualizar() : void 
+ recibirResultadoPelea(dañoRecibido: int, poderObtenido : int) : void 
//Maneja el movimiento y decision del mutante
// recibirResultadoPelea() es un metodo Synchronized 
// decide 

### DatosJuego
- listaMutantes : ArrayList\<HiloMutante>
- listaPeleas : ArrayList\<DatosPelea>
- simboloPorEquipo : String[]
- colorPorEquipo : Color[]
+ DatosJuego(campoDeBatalla : CampoDeBatalla)  
+ getListaMutantes() : ArrayList\<Mutante>
+ getSimboloPorEquipo : String[]
+ getColorPorEquipo : Color[]

### IObservable (Intefaz)
+ agregaObservadores(observador : IObservador) : void
+ quitarObservadores(observador : IObservador) : void 
+ notificar() : void
//Permite notificar a a los observadores de cualquier cambio facilmente

### Notificador (Implementa Runnable)
- controlador : Controlador 
+ Notificador(controlador : Controlador)
+ run() : void

//Se encarga de cumplir la tasa de refresco del observer

### Controlador (Implementa IObservable)
- listaHilosMutantes : ArrayList\<Mutante>[]
- listaObservadores : ArrayList\<IObservador>
- ejecutorDePeleas : EjecutorDePeleas 
- notificador : Notificador
- executorService : ThreadPoolExecutor
- campoDeBatalla : CampoDeBatalla
- DatosJuego : DatosJuego
+ Controlador() 
+ iniciarJuego() : void
+ obtenerDatosJuego() : void
+ agregaObservadores(observador : IObservador) : void
+ quitarObservadores(observador : IObservador) : void 
+ notificar() : void
+ getCampoDeBatalla() : CampoDeBatalla
+ getDatosJuego() : DatosJuego
+ getEjecutorDePeleas() : EjecutorDePeleas
+ getListaHilosMutantes(TipoEquipo tipo) : ArrayList<HiloMutante>
+ getListaObservadores() : ArrayList<IObservador>
+ getMedidasCampoBatalla() : int[]

//Controla todo el flujo del juego y sirve de interfaz entre la UI y el sistema.

### IObservador (Intefaz)
+ actualizar(datos : DatosJuego) : void
//Permite ser notificado de cualquier cambio en los objetos a los que se esta suscrito

### PruebaControlador
+ main(args : String[]) : static void : static void

## ui
### ObservadorUi (implementa IObservador)
- ventanaPrincipal : VentanaPrincipal
- datos : DatosJuego
+ ObservadorUi(ventana : VentanaPrincipal)
+ actualizar(datos : DatosJuego)
+ getVentanaPrincipal() : VentanaPrincipal
+ getDatos() : DatosJuego

// Manda los datos actualizados al resto de la interfaz

### VentanaPrincipal (hereda de JFrame)
- botonIniciar : JButton     
- botonPausar : JButton      
- botonReiniciar : JButton   
- pantallaJuego : PantallaJuego
+ VentanaPrincipal() : void
+ actualizar(datos : DatosJuego)
+ inicializarComponentes() : void
+ getBotonIniciar() : JButton
+ getBotonPausar() : JButton
+ getBotonReiniciar() : JButton
+ getPantallaJuego() : PantallaJuego

### PantallaJuego (hereda de JPanel)
- mutantesDibujados : ArrayList\<MutantesDibujados>
- principal : VentanaPrincipal
- ataquesActivos : ArrayList\<AnimaciónAtaque>
- infoVisible : boolean
+ PantallaJuego(principal : VentanaPrincipal)
+ actualizar(datos : DatosJuego) 
+ getMutantesDibujados() : ArrayList\<MutantesDibujados>
+ getPrincipal() : VentanaPrincipal
+ getAtaquesActivos() : ArrayList\<AnimaciónAtaque>
+ isInfoVisible() : boolean
+ setInfoVisible(infoVisible : boolean) : void
+ agregarAnimacionAtaque(ataque : Ataque) : void
+ paintComponent(g : Graphics) : void   

### MutantesDibujados
- mutante : Mutante
- jLabel : JLabel
- imagen : ImageIcon
+ MutantesDibujados(mutante : Mutante)
+ dibujar() : void
+ obtenerPosicion() : Punto
+ toString() : String
+ getMutante() : Mutante
+ getJLabel() : JLabel
+ getImagen() : ImageIcon

### AnimacionAtaque (hereda de DatosAtaque)
- pantalla : PantallaJuego
- jLabel : JLabel
- dibujos : ArrayList\<ImageIcon>
+ AnimaciónAtaque(ataque : Ataque)
+ dibujar() : void
+ isTerminada() : boolean
+ getPantalla() : PantallaJuego
+ getJLabel() : JLabel
+ getDibujos() : ArrayList\<ImageIcon>
+ getFrameActual() : int
+ setFrameActual(frameActual : int) : void

### PruebaUi
+ main(args : String[]) : static void : static void

## otros

### Constantes (clase final)
+ TIEMPO_ESPERA               : static final int
+ SIZE_X                      : static final int
+ SIZE_Y                      : static final int
+ ATAQUE_MAXIMO               : static final int
+ TASA_REFRESCO               : static final int
+ VELOCIDAD                   : static final int
+ TAMAÑO_MAXIMO               : static final int
+ MAXIMO_DEFENSA              : static final int 
+ MAXIMO_ATAQUE               : static final int *
+ MAXIMO_POR_DEFECTO_ATAQUE   : static final int
+ MAXIMO_VIDA                 : static final int
+ RADIO_MAXIMO                : static final double
+ DISTANCIA_MAXIMA_RECORRIDO  : static final int
+ RUTA_IMAGENES_MUTANTES      : static final String[]
+ RUTA_IMAGENES_ATAQUES       : static final String[]
+ NUMERO_FRAMES_ATAQUE        : static final int
+ DELAY_ANIMACION_ATAQUE      : static final int  
+ TASA_REFRESCO_OBSERVABLE    : static final int

### Punto
- x : int
- y : int
+ Punto(x : int, y : int)
+ setX(x : int)
+ setY(y : int)
+ getX() : int
+ getY() : int

### Matematicas (clase final)
+ calcularRadio(Punto a, Punto b) : static double
+ doubleAleatorio(minimo : double , maximo : double) : static double
+ intAleatorio(minimo : int, maximo : int) : static int  

## main
### main
+ main(args : String[]) : static void 

# UML
```
@startuml DiagramaClases

skinparam classAttributeIconSize 0
skinparam packageStyle rectangle
hide empty members

package modelo {

    interface IPower {
        + usarPoder() : void
        + nombrePoder() : String
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
        + atacar() : int
        + recibirDaño(daño : int) : int
        + estaVivo() : boolean
    }

    class PruebaModelo {
        + {static} main(args : String[]) : void
    }

    Mutante "1" *-- "1" IPower : poder >
    Mutante "1" o-- "1" modelo.Mutante : usa Punto <

}

package juego {

    enum TipoEquipo {
        EQUIPO_A
        EQUIPO_B
    }

    class Equipo {
        - listaMutantes : ArrayList<Mutante>
        - cualEquipo : TipoEquipo
        - simbolo : String
        - color : Color
        + Equipo(equipo : TipoEquipo, cantidad : int)
        + setColor(color : Color) : void
        + setSimbolo(simbolo : String) : void
        + getColor() : Color
        + getSimbolo() : String
        + getListaMutantes() : ArrayList<Mutante>
        + getTipoEquipo() : TipoEquipo
    }

    class CampoDeBatalla {
        - sizeX : int
        - sizeY : int
        - equipoA : Equipo
        - equipoB : Equipo
        - scoreBoard : ScoreBoard
        + CampoDeBatalla(sizeX : int, sizeY : int)
        + crearEquipos(size : int) : void
        + terminoElJuego() : boolean
        + getPuntoMedio() : Punto
        + getPuntoMaximo() : Punto
        + getEquipoA() : Equipo
        + getEquipoB() : Equipo
        + getScoreBoard() : ScoreBoard
        + getSizeX() : int
        + getSizeY() : int
    }

    class ScoreBoard {
        - mutantesVivos : int
        - mutantesMuertos : int
        - mutantesEquipoA : int
        - mutantesEquipoB : int
        + ScoreBoard(equipoA : Equipo, equipoB : Equipo)
        + actualizar(equipoA : Equipo, equipoB : Equipo) : void
        + toString() : String
        + getMutantesVivos() : int
        + getMutantesMuertos() : int
        + getMutantesEquipoA() : int
        + getMutantesEquipoB() : int
    }

    class PruebaJuego {
        + {static} main(args : String[]) : void
    }

    Equipo "1" -- "*" modelo.Mutante : contiene >
    Equipo "1" -- "1" TipoEquipo
    CampoDeBatalla "1" *-- "2" Equipo
    CampoDeBatalla "1" *-- "1" ScoreBoard
    ScoreBoard ..> Equipo : usa

}

package controlador {

    enum EstadoMutante {
        MOVIENDOSE
        ATAQUE
        DEFENSA
    }

    interface IObservable {
        + agregaObservadores(observador : IObservador) : void
        + quitarObservadores(observador : IObservador) : void
        + notificar() : void
    }

    interface IObservador {
        + actualizar(datos : DatosJuego) : void
    }

    class HiloMutante implements Runnable {
        - controlador : Controlador
        - estaVivo : boolean
        - estado : EstadoMutante
        - mutante : Mutante
        - activo : boolean
        + HiloMutante(mutante : Mutante)
        + run() : void
        + isEstaVivo() : boolean
        + isEstado() : EstadoMutante
        + elegirAtaque() : void
        + regresarAEstadoBase() : void
        + actualizar() : void
        + recibirResultadoPelea(dañoRecibido : int, poderObtenido : int) : void
    }

    class DatosPelea {
        - mutanteA : Mutante
        - decisionA : EstadoMutante
        - mutanteB : Mutante
        - decisionB : EstadoMutante
        - posA : Punto
        - posB : Punto
        + DatosPelea(hiloMutanteA : HiloMutante, hiloMutanteB : HiloMutante)
        + getMutanteA() : Mutante
        + getDecisionA() : EstadoMutante
        + getMutanteB() : Mutante
        + getDecisionB() : EstadoMutante
        + getPosA() : Punto
        + getPosB() : Punto
    }

    class DatosAtaque {
        - poder : Poder
        - origen : Punto
        - destino : Punto
        + Ataque(datoPelea : DatosPelea, cual : int)
        + Ataque(ataque : Ataque)
        + getPoder() : Poder
        + getOrigen() : Punto
        + getDestino() : Punto
    }

    class EjecutarPeleas implements Runnable {
        - refenciaDeCola : ConcurrentLinkedQueue<int[]>
        - ejecutor : EjecutorDePeleas
        - activo : boolean
        + EjecutarPeleas(ejecutor : EjecutorDePeleas)
        + run() : void
        + obtenerMutante(id : int, equipo : String) : HiloMutante
        + isActivo() : boolean
        + setActivo(activo : boolean) : void
        + terminar() : void
    }

    class EjecutorDePeleas implements Runnable {
        - controlador : Controlador
        - executorService : ThreadPoolExecutor
        - colaPeleas : ConcurrentLinkedQueue<int[]>
        - listaPeleasCompletadas : CopyOnWriteArrayList<int[]>
        - listaEjecutores : EjecutarPeleas[]
        - activo : boolean
        + EjecutorDePeleas(controlador : Controlador)
        + run() : void
        + obtenerDatoCola() : int[]
        + agregarDatoCola(int[]) : void
        + agregarPeleaALista(datoPelea : DatosPelea) : void
        + reiniciarMutantes() : void
        + getColaPeleas() : ConcurrentLinkedQueue<int[]>
        + getListaEjecutores() : EjecutarPeleas[]
        + isActivo() : boolean
        + setActivo(activo : boolean) : void
    }

    class DatosJuego {
        - listaMutantes : ArrayList<HiloMutante>
        - simboloPorEquipo : String[]
        - colorPorEquipo : Color[]
        + DatosJuego(campoDeBatalla : CampoDeBatalla)
        + getListaMutantes() : ArrayList<Mutante>
        + getSimboloPorEquipo() : String[]
        + getColorPorEquipo() : Color[]
    }

    class Notificador implements Runnable {
        - controlador : Controlador
        + Notificador(controlador : Controlador)
        + run() : void
    }

    class Controlador implements IObservable {
        - listaHilosMutantes : ArrayList<Mutante>
        - listaObservadores : ArrayList<IObservador>
        - ejecutorDePeleas : EjecutorDePeleas
        - notificador : Notificador
        - executorService : ThreadPoolExecutor
        - campoDeBatalla : CampoDeBatalla
        - datosJuego : DatosJuego
        + Controlador()
        + iniciarJuego() : void
        + obtenerDatosJuego() : void
        + agregaObservadores(observador : IObservador) : void
        + quitarObservadores(observador : IObservador) : void
        + notificar() : void
        + getCampoDeBatalla() : CampoDeBatalla
        + getDatosJuego() : DatosJuego
        + getEjecutorDePeleas() : EjecutorDePeleas
        + getListaHilosMutantes() : ArrayList<HiloMutante>
        + getListaObservadores() : ArrayList<IObservador>
        + getMedidasCampoBatalla() : int[]
    }

    class PruebaControlador {
        + {static} main(args : String[]) : void
    }

    HiloMutante "1" o-- "1" modelo.Mutante
    HiloMutante --> EstadoMutante
    HiloMutante --> Controlador : usa >
    DatosPelea --> HiloMutante : construido con >
    DatosPelea --> modelo.Mutante
    DatosPelea --> EstadoMutante
    DatosAtaque --> DatosPelea : construido con >
    EjecutarPeleas --> EjecutorDePeleas : usa >
    EjecutarPeleas --> HiloMutante : obtiene >
    EjecutorDePeleas "1" *-- "*" EjecutarPeleas
    EjecutorDePeleas --> Controlador : usa >
    EjecutorDePeleas --> DatosPelea
    DatosJuego "1" o-- "*" HiloMutante
    DatosJuego --> juego.CampoDeBatalla : construido con >
    Notificador --> Controlador : usa >
    Controlador "1" *-- "1" EjecutorDePeleas
    Controlador "1" *-- "1" Notificador
    Controlador "1" *-- "1" juego.CampoDeBatalla
    Controlador "1" *-- "1" DatosJuego
    Controlador "1" o-- "*" HiloMutante
    Controlador "1" o-- "*" IObservador
    Controlador ..|> IObservable

}

package ui {

    class ObservadorUi implements controlador.IObservador {
        - ventanaPrincipal : VentanaPrincipal
        - datos : DatosJuego
        + ObservadorUi(ventana : VentanaPrincipal)
        + actualizar(datos : DatosJuego) : void
        + getVentanaPrincipal() : VentanaPrincipal
        + getDatos() : DatosJuego
    }

    class VentanaPrincipal {
        - botonIniciar : JButton
        - botonPausar : JButton
        - botonReiniciar : JButton
        - pantallaJuego : PantallaJuego
        + VentanaPrincipal()
        + actualizar(datos : DatosJuego) : void
        + inicializarComponentes() : void
        + getBotonIniciar() : JButton
        + getBotonPausar() : JButton
        + getBotonReiniciar() : JButton
        + getPantallaJuego() : PantallaJuego
    }
    class JFrame <<Java Swing>>
    VentanaPrincipal --|> JFrame

    class PantallaJuego {
        - mutantesDibujados : ArrayList<MutantesDibujados>
        - principal : VentanaPrincipal
        - ataquesActivos : ArrayList<AnimaciónAtaque>
        - infoVisible : boolean
        + PantallaJuego(principal : VentanaPrincipal)
        + actualizar(datos : DatosJuego) : void
        + getMutantesDibujados() : ArrayList<MutantesDibujados>
        + getPrincipal() : VentanaPrincipal
        + getAtaquesActivos() : ArrayList<AnimaciónAtaque>
        + isInfoVisible() : boolean
        + setInfoVisible(infoVisible : boolean) : void
        + agregarAnimacionAtaque(ataque : Ataque) : void
        + paintComponent(g : Graphics) : void
    }
    class JPanel <<Java Swing>>
    PantallaJuego --|> JPanel

    class MutantesDibujados {
        - mutante : Mutante
        - jLabel : JLabel
        - imagen : ImageIcon
        + MutantesDibujados(mutante : Mutante)
        + dibujar() : void
        + obtenerPosicion() : Punto
        + toString() : String
        + getMutante() : Mutante
        + getJLabel() : JLabel
        + getImagen() : ImageIcon
    }

    class AnimaciónAtaque {
        - pantalla : PantallaJuego
        - jLabel : JLabel
        - dibujos : ArrayList<ImageIcon>
        + AnimaciónAtaque(ataque : Ataque)
        + dibujar() : void
        + isTerminada() : boolean
        + getPantalla() : PantallaJuego
        + getJLabel() : JLabel
        + getDibujos() : ArrayList<ImageIcon>
        + getFrameActual() : int
        + setFrameActual(frameActual : int) : void
    }
    AnimaciónAtaque --|> controlador.DatosAtaque

    ObservadorUi "1" o-- "1" VentanaPrincipal
    ObservadorUi --> controlador.DatosJuego
    VentanaPrincipal "1" *-- "1" PantallaJuego
    VentanaPrincipal --> controlador.DatosJuego
    PantallaJuego "1" o-- "*" MutantesDibujados
    PantallaJuego "1" o-- "*" AnimaciónAtaque
    PantallaJuego --> controlador.DatosJuego
    MutantesDibujados --> modelo.Mutante

}

package otros {

    class Constantes <<final>> {
        + {static} TIEMPO_ESPERA : final int
        + {static} SIZE_X : final int
        + {static} SIZE_Y : final int
        + {static} ATAQUE_MAXIMO : final int
        + {static} TASA_REFRESCO : final int
        + {static} VELOCIDAD : final int
        + {static} TAMAÑO_MAXIMO : final int
        + {static} MAXIMO_DEFENSA : final int
        + {static} MAXIMO_ATAQUE : final int
        + {static} MAXIMO_POR_DEFECTO_ATAQUE : final int
        + {static} MAXIMO_VIDA : final int
        + {static} RADIO_MAXIMO : final double
        + {static} DISTANCIA_MAXIMA_RECORRIDO : final int
        + {static} RUTA_IMAGENES_MUTANTES : final String[]
        + {static} RUTA_IMAGENES_ATAQUES : final String
        + {static} NUMERO_FRAMES_ATAQUE : final int
        + {static} DELAY_ANIMACION_ATAQUE : final int
        + {static} TASA_REFRESCO_OBSERVABLE : final int
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

    class Matematicas {
        + {static} calcularRadio(a : Punto, b : Punto) : double
    }

    class PruebaUI {
        + {static} main(args : String[]) : void
    }

    Matematicas ..> Punto : usa

}

package main {
    class main {
        + {static} main(args : String[]) : void
    }
    main --> controlador.Controlador : crea >
    main --> ui.VentanaPrincipal : crea >
}

modelo.Mutante "1" o-- "1" otros.Punto : posicion >
modelo.Mutante "1" o-- "1" juego.Equipo : equipo >

@enduml
```
