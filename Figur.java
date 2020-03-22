package View;

public class Figur {

    public Spieler spieler;

    private FigurStatus figurStatusAlt;
    private FigurStatus figurStatusNeu;

    private int positionAlt;
    private int positionNeu;

    public enum FigurStatus {
        STARTBOX, SPIELFELD, ZIELBOX, INVALID
    }

    private int []FigurInfo;

    public Figur(int position){
        this.figurStatusAlt = FigurStatus.STARTBOX;
        this.figurStatusNeu = FigurStatus.STARTBOX;
        this.positionAlt = position;
        this.positionNeu = position;
    }

    public void figurZeichnen(int wuerfelergebnis, int FigurNummer){
        this.positionAlt = this.positionNeu;
        this.positionNeu=this.positionAlt + wuerfelergebnis;

        spieler.figurZeichnen(FigurNummer,figurStatusAlt.toString(),figurStatusNeu.toString(),positionAlt,positionNeu);





    }
}
