package View;

public class SpieleController {

    public Spielbrett spielbrett;
    private  Spieler spieler [] = new Spieler[4];
    private Becher becher;
    private int spielerNummer;


    public  SpieleController (){
        becher = new Becher();
    }

    public void erzeugeSpielerObjekt (int spielerNummer, int FigurNummer){
        this.spielerNummer = spielerNummer;
        spieler[spielerNummer] = new Spieler();
        spieler[spielerNummer].spieleController = SpieleController.this;
        for (int i=0;i<4;i++){
            spieler[spielerNummer].erzeugeFigur(FigurNummer);
        }

    }

    public int  wuefeln (){
        int zufallszahl;
        zufallszahl= becher.wuerfeln();
        return zufallszahl;

    }

    public void figurZeichnen (int FigurNummer,String StatusAlt, String StatusNeu,int positionAlt, int positionNeu){
        spielbrett.figurZeichnen(spielerNummer,FigurNummer,StatusAlt,StatusNeu,positionAlt,positionNeu);
    }








}



