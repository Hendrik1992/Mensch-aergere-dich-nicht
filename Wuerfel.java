package View;

public class Wuerfel {

    public int wuerfeln(){
        int zufallszahl;
        zufallszahl= (int)(Math.random()*6+1);
        return zufallszahl;

    }
}
