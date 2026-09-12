# Introduccion
Caso I POO II Semestre 
# Spec
## Paquetes

## modelo

## juego

## controlador
### DatosJuego
- listaMutantes : ArrayList\<Mutante>
- simboloPorEquipo : String[]
- colorPorEquipo : Color[]
metodos:
+ gettersYSteers // desarrollar

### IObservable (Intefaz)
+ agregaObservadores(observador : IObservador) : void
+ quitarObservadores(observador : IObservador) : void 
+ notificar() : void

### Controlador (implementa Observable)
- listaMutantes : ArrayList\<Mutates>
- listaObservadores : ArrayList\<IObservador>
- DatosJuego
metodos:
+ agregaObservadores(observador : IObservador) : void
+ quitarObservadores(observador : IObservador) : void 
+ notificar() : void

### IObservador (Intefaz)
+ actualizar(datos : DatosJuego)

## ui
### ObservadorUi (herada de ObserverAbs)
- ventanaPrincipal : VentanaPrincipal
- datos : DatosJuego
metodos 
+ actualizar(datos : DatosJuego)
### VentanaPrincipal (hereda de JFrame)
metodos
+ actualizar(Datos)
### PantallaJuego (hereda de JPanel)
metodos:
+ actualizar(TempDatos) 
## otros

### Constantes


# UML


