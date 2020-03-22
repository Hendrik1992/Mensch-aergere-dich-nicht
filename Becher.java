package View;

public class Becher {
    private Wuerfel wuerfel;

    public Becher () {
        wuerfel = new Wuerfel();
    }

    public int wuerfeln(){
        int zufallszahl;
        zufallszahl= wuerfel.wuerfeln();
        return zufallszahl;
    }
}
