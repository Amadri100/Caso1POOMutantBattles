# Introduccion
Caso I POO II Semestre 

# Estructura del Proyecto
```
src/
|-- modelo
|    |-- Mutante.java
|    |-- DatosJuego.java
|    |-- IPower.java
|-- juego
     |-- IObservador.java
     |-- IObservable.java
     |-- ScoreBoard.java
     |-- CampoDeBatalla.java
     |-- Equipo.java
|-- controlador
     |-- Controlador.java
     |-- HiloMutante.java
     |-- EjecutarPeleas.java
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
+ getPosicion() : Punto
+ atacar() : int
+ recibirDaño(daño : int) : int
+ estaVivo() : boolean

// id empieza en 0
### IPower (Interfaz)
+ usarPoder() : void
### PoderFuego (implementa IPower)
+ PoderFuego() 
+ usarPoder() : void
### PoderAgua (implementa IPower)
+ PoderAgua() 
+ usarPoder() : void
### PoderRayo (implementa IPower)
+ PoderRayo()
+ usarPoder() : void
### PoderTierra (implementa IPower)
+ PoderTierra()
+ usarPoder() : void
### PoderTelaraña (implementa IPower)
+ PoderTelaraña()
+ usarPoder() : void

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

### ScoreBoard
- mutantesVivos : int
- mutantesMuertos : int 
- mutantesEquipoA : int
- mutantesEquipoB : int
+ ScoreBoard(equipoA : Equipo, equipoB : Equipo)
+ actualizar(equipoA : Equipo, equipoB : Equipo) : void
+ toString() : String


## controlador

### EjecutarPeleas (implementa Runnable)
- refenciaDeCola : ConcurrentLinkedQueue\<int[]>
- ejecutor : EjecutorDePeleas
- activo : boolean
+ run() : void
+ obtenerMutante(id : int, equipo : String) : HiloMutante
+ terminar()

### EjecutorDePeleas (implementa Runnable)
- controlador : Contraladors
- executorService : ThreadPoolExecutor
- colaPeleas : ConcurrentLinkedQueue\<int[]>
- listaEjecutores : EjecutarPeleas[]
- activo : boolean
+ run() : void
+ obtenerDatoCola() : int[]
+ agregarDatoCola(int[]) : void 
+ reiniciarMutantes() : void 

//reiniciarMutantes() -> Hace que los mutantes vuelvan a estar listos para moverse
// Todos los potenciales oponentes de un mutante son los que estan a radioMaximo + distanciaMaximaDeRecorrido

### HiloMutante (implementa Runnable)
- Controlador
- estaVivo : boolean
- listoParaAtacar : boolean
- mutante :  Mutante
+ run() : void
+ isEstaVivo() : boolean
+ isListoParaAtacar() : boolean
+ setListoParaAtacar(valor : boolean) : void
+ actualizar() : void 
+ recibirResultadoPelea(dañoRecibido: int, poderObtenido : int) : void 

// recibirResultadoPelea() es un metodo Synchronized 
### DatosJuego
- listaMutantes : ArrayList\<HiloMutante>
- simboloPorEquipo : String[]
- colorPorEquipo : Color[]
+ getListaMutantes() : ArrayList\<Mutante>
+ getSimboloPorEquipo : String[]
+ ColorPorEquipo : Color[]

### IObservable (Intefaz)
+ agregaObservadores(observador : IObservador) : void
+ quitarObservadores(observador : IObservador) : void 
+ notificar() : void

### Controlador (Implementa IObservable)
- listaHilosMutantes : ArrayList\<Mutante>
- listaObservadores : ArrayList\<IObservador>
- ejecutorDePeleas : EjecutorDePeleas 
- executorService : ThreadPoolExecutor
- campoDeBatalla : CampoDeBatalla
- DatosJuego : DatosJuego
+ medidasCamposBatalla : int[]
+ iniciarJuego() : void
+ obtenerDatosJuego() : void
+ agregaObservadores(observador : IObservador) : void
+ quitarObservadores(observador : IObservador) : void 
+ notificar() : void

### IObservador (Intefaz)
+ actualizar(datos : DatosJuego)

## ui
### ObservadorUi (herada de ObserverAbs)
- ventanaPrincipal : VentanaPrincipal
- datos : DatosJuego
+ actualizar(datos : DatosJuego)

### VentanaPrincipal (hereda de JFrame)
- botonIniciar : JButton     // Biblioteca javax.swing.JButton
- botonPausar : JButton      // Biblioteca javax.swing.JButton
- botonReiniciar : JButton   // Biblioteca javax.swing.JButton
+ actualizar(Datos)
+ inicializarComponentes() : void

### PantallaJuego (hereda de JPanel)
- mutantesDibujados : ArrayList\<MutantesDibujados>
- principal : VentanaPrincipal
- ataquesActivos : ArrayList\<AnimaciónAtaque>
- infoVisible : boolean
+ actualizar(TempDatos) 

### MutantesDibujados
- mutante : Mutante
- jLabel : JLable
- imagen : ImageIcon
+ dibujar() : void
+ obtenerPosicion() : Punto
+ toString() : String
### AnimaciónAtaque
- pantalla : PantallaJuego
- jLabel : JLable
- poder : Poder
- origen : Punto
- destino : Punto
- dibujos : ArrayList\<ImageIcon>
+ dibujar() : void
## otros

### Constantes
+ TIEMPO_ESPERA : static int
### Punto
- x : int
- y : int
+ setX(x : int)
+ setY(y : int)
+ getX() : int
+ getY() : int
### Matematicas
+ calcularRadio(Punto a, Punto b) : double


# UML


