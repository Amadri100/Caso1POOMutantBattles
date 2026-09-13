# Introduccion
Caso I POO II Semestre 
# Spec
## Paquetes
src/
|-- modelo
|    |-- Mutantes.java
|-- DatosJuego.java
|-- juego
     |-- IObservador.java
     |-- IObservable.java
|-- controlador
     |-- Controlador.java


## modelo
### Mutantes
- nombre : String
- equipo : int
- vida : int
- ataque : int
- defensa : int
+ estaVivo() : boolean
### Poderes
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

### ScoreBoard
- mutantesVivos : int
- mutantesMuertos : int 
- mutantesEquipoA : int
- mutantesEquipoB : int
+ actualizar(equipoA : int, Equipo : Equipo)


## controlador
### DatosJuego
- listaMutantes : ArrayList\<Mutante>
- simboloPorEquipo : String[]
- colorPorEquipo : Color[]
+ gettersYSteers // desarrollar

### IObservable (Intefaz)
+ agregaObservadores(observador : IObservador) : void
+ quitarObservadores(observador : IObservador) : void 
+ notificar() : void

### Controlador (implementa Observable)
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


# UML


