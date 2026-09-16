# Introduccion
Caso I POO II Semestre 

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

### PruebaModelo
+ main(args : String[]) : static void

## juego

### TipoEquipo (enum)
+ EQUIPO_A
+ EQUIPO_B

### Equipo
- listaMutantes : ArrayList\<Mutante>
- cualEquipo : TipoEquipo
- simbolo : String
- color : Color
+ Equipo (equipo : TipoEquipo, cantidad : int)
+ setColor(color : Color) : void
+ setSimbolo(simbolo : String) : void
+ getColor() : Color
+ getSimbolo() : String
+ getListaMutantes() : ArrayList\<Mutante>
+ getTipoEquipo() : TipoEquipo

### CampoDeBatalla
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

### ScoreBoard
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
- listaPeleasCompletadas : CopyOnWriteArrayList\<int[]>
- listaEjecutores : EjecutarPeleas[]
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
- origen : Punto
- destino : Punto
+ Ataque(datoPelea : DatosPelea, cual : int)
+ Ataque(ataque : Ataque)
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
- listaHilosMutantes : ArrayList\<Mutante>
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
+ getListaHilosMutantes() : ArrayList<HiloMutante>
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

### AnimaciónAtaque (hereda de Ataque)
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
+ MAXIMO_ATAQUE               : static final int
+ MAXIMO_POR_DEFECTO_ATAQUE   : static final int
+ MAXIMO_VIDA                 : static final int
+ RADIO_MAXIMO                : static final double
+ DISTANCIA_MAXIMA_RECORRIDO  : static final int
+ RUTA_IMAGENES_MUTANTES      : static final String[]
+ RUTA_IMAGENES_ATAQUES       : static final String
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
### Matematicas
+ calcularRadio(Punto a, Punto b) : double

### PruebaUI
+ main(args : String[]) : static void : static void

## main
### main
+ main(args : String[]) : static void : static void

# UML


