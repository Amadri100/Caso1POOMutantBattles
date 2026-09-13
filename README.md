# Introduccion
Caso I POO II Semestre 
# Spec
## Paquetes
src/
|-- modelo
|    |-- Mutantes.java
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


## modelo
### Mutantes
- id : int
- nombre : String
- equipo : int
- vida : int
- poder : IPower
- posicion : Punto
- ataque : int
- defensa : int
+ estaVivo() : boolean

// id empieza en 0
### IPower (Interfaz)
+ usarPoder() : void
### PoderFuego (implementa IPower)
+ usarPoder() : void
### PoderAgua (implementa IPower)
+ usarPoder() : void
### PoderRayo (implementa IPower)
+ usarPoder() : void
### PoderTierra (implementa IPower)
+ usarPoder() : void
### PoderTelaraña (implementa IPower)
+ usarPoder() : void

## juego

### Equipo
- listaMutantes : ArrayList\<Mutantes>
- simbolo : String
- color : Color
+ generarMutantes(cantidad : int) : void
+ setColor(color : Color) : void
+ setSimbolo(simbolo : String) : void
+ getColor() : Color
+ getSimbolo() : String
+ getListaMutantes() : ArrayList\<Mutantes>


### CampoDeBatalla
- sizeX : int
- sizeY : int
- equipoA : Equipo
- equipoB : Equipo
+ crearEquipos(size : int) : void
+ terminoElJuego() : boolean
+ getDimensiones() : int[]
+ getEquipoA() : Equipo
+ getEquipoB() : Equipo
+ getTablaRadios() : double[][]

// tabla de radios = tabla[indiceEquipoA][indiceEquipoB]

### ScoreBoard
- mutantesVivos : int
- mutantesMuertos : int 
- mutantesEquipoA : int
- mutantesEquipoB : int
+ actualizar(equipoA : int, Equipo : Equipo)


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
- executorService0 : ExecutorService
- colaPeleas : ConcurrentLinkedQueue\<int[]>
- listaEjecutores : EjecutarPeleas[]
- activo : boolean
+ run() : void
+ obtenerDatoCola() : int[]
+ agregarDatoCola(int[]) : void 
+ reiniciarMutantes() : void


// Todos los potenciales oponentes de un mutante son los que estan a radioMaximo + distanciaMaximaDeRecorrido

### HiloMutante (implementa Runnable)
- estaVivo : boolean
- listoParaAtacar : boolean
- mutante : synchronized Mutante
+ run() : void
+ isEstaVivo() : boolean
+ isListoParaAtacar() : boolean
+ setListoParaAtacar(valor : boolean) : void
+ actualizar() : void 
+ getMutante() : mutante
### DatosJuego
- listaMutantes : ArrayList\<Mutante>
- simboloPorEquipo : String[]
- colorPorEquipo : Color[]
+ gettersYSteers // desarrollar

### IObservable (Intefaz)
+ agregaObservadores(observador : IObservador) : void
+ quitarObservadores(observador : IObservador) : void 
+ notificar() : void

### Controlador (Implementa IObservable)
- listaMutantes : ArrayList\<Mutantes>
- listaObservadores : ArrayList\<IObservador>
- DatosJuego
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
+ actualizar(Datos)

### PantallaJuego (hereda de JPanel)
+ actualizar(TempDatos) 

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


