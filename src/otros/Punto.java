package otros;

public class Punto {
    private int x;
    private int y;
    public Punto(int pX, int pY) {
        this.x = pX;
        this.y = pY;
    }
    public void setX(int pX) {
        this.x = pX;
    } 
    public void setY(int pY) {
        this.y = pY;
    } 
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
}
