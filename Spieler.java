package View;

public class Spieler {

    public SpieleController spieleController;
    private Figur figuren [] = new Figur[4];

    public Spieler(){
    }

    public void erzeugeFigur (int FigurNummer){
       int i =0;
       figuren[i] = new Figur(i);
       figuren[i].spieler = Spieler.this;
       figuren[i].figurZeichnen(0,FigurNummer);
       i++;
    }

    public void figurZeichnen (int FigurNummer,String StatusAlt, String StatusNeu,int positionAlt, int PositonNeu){
        spieleController.figurZeichnen(FigurNummer,StatusAlt,StatusNeu,positionAlt,PositonNeu);
    }

    public void getSpielerNummer(){
        for(int i=0; i<figuren.length;i++){
            if(this.figuren[i] == figuren[i]){
                System.out.println(i);
            }
        }
    }

}
