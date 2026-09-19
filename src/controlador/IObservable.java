package controlador;

public interface IObservable {
    public void agregaObservadores(IObservador observador);
    public void quitarObservadores(IObservador observador);
    public void notificar();
}
