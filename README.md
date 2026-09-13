# Introduccion
Caso I POO II Semestre 

# Estructura del Proyecto
```
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
```

# Spec
## Paquetes

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

### ScoreBoard
- mutantesVivos : int
- mutantesMuertos : int 
- mutantesEquipoA : int
- mutantesEquipoB : int
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
- listaHilosMutantes : ArrayList\<Mutantes>
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
+ actualizar(Datos)

### PantallaJuego (hereda de JPanel)
- principal : VentanaPrincipal
+ actualizar(TempDatos) 

### AnimaciónAtaque
- poder : Poder
- origen : Punto
- destino : Punto
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


