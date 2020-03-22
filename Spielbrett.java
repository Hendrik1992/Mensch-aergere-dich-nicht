package View;


//Java Fx intstalliernen
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Spielbrett extends Application {

    private Circle [] SpielfeldKreiseObjekte =new Circle[40];
    private int [][] SpielfeldPosition = {{5,0},{6,0},{7,0},{7,1},{7,2},{7,3},{7,4},{8,4},{9,4},{10,4},{11,4},{11,5},{11,6},{10,6},{9,6},{8,6},{7,6},{7,7},{7,8},{7,9},{7,10},{6,10},{5,10},{5,9},{5,8},{5,7},{5,6},{4,6},{3,6},{2,6},{1,6},{1,5},{1,4},{2,4},{3,4},{4,4},{5,4},{5,3},{5,2},{5,1}};
    private int [][][] Startbox = {{{1,0},{2,0},{1,1},{2,1}},{{10,0},{11,0},{10,1},{11,1}},{{1,9},{2,9},{1,10},{2,10}},{{10,9},{11,9},{10,10},{11,10}}};
    private int [][][] Zielbox = {{{5,5},{4,5},{3,5},{2,5}},{{6,4},{6,3},{6,2},{6,1}},{{6,6},{6,7},{6,8},{6,9}},{{7,5},{8,5},{9,5},{10,5}}};
    private int [][]Ansatzpunkte = {{1,6},{7,0},{11,6},{5,10}};

    public Button [][] ZiehButtonObjekte = new Button [4][4];
    public int [][][] ZiehButtonPosition = {{{3,0},{4,0},{3,1},{4,1}},{{8,0},{9,0},{8,1},{9,1}},{{3,9},{4,9},{3,10},{4,10}},{{8,9},{9,9},{8,10},{9,10}}};


    private int [] wuerfelButtonPosition={6,5};

    private GridPane gridPane =null;

    private VBox vBox;
    public Button wuerfelbutton;
    private Label wuerfelLabel;
    private Label spielStatus;
    private boolean besetzt;
    private SimpleIntegerProperty wuerfelErgebnis;

    private SpieleController spieleController;


    public Spielbrett (){
        spieleController = new SpieleController();
        spieleController.spielbrett = Spielbrett.this;

    }
    public static void main(String []args){

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {

        gridPane = new GridPane();
        gridPane.setBackground(new Background(new BackgroundFill(Color.LIGHTYELLOW,null,null)));
        gridPane.setAlignment(Pos.CENTER);


        ErzeugeLeeresSpielfeld();

        erzeugeStartBoxen();
        erzeugeZielBoxen();
        erzeugeZiehButtons();
        erzeugeInnenAnzeige();

        this.gridPane.setGridLinesVisible(true);
        wuerfelbutton.setOnAction(this::handlewuefel);

        StackPane stackPane = new StackPane(this.gridPane);
        Scene scene = new Scene(stackPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mensch ärgere dich nicht");
        primaryStage.show();

    }
    public void handlewuefel (ActionEvent event){

        wuerfelErgebnis = new SimpleIntegerProperty();
        wuerfelErgebnis.setValue(spieleController.wuefeln());
        wuerfelLabel.textProperty().bind(wuerfelErgebnis.asString());

    }


    private GridPane ErzeugeLeeresSpielfeld(){

        for (int i = 0; i< SpielfeldKreiseObjekte.length; i++){
            SpielfeldKreiseObjekte[i]=new Circle(30);
        }

        for(int i = 0; i< SpielfeldPosition.length; i++){
            SpielfeldKreiseObjekte[i].setFill(Color.WHITE);
            SpielfeldKreiseObjekte[i].setStroke(Color.BLACK);
            SpielfeldKreiseObjekte[i].setStrokeWidth(5);
            //Spalte, Zeile
            gridPane.add(SpielfeldKreiseObjekte[i], SpielfeldPosition[i][0], SpielfeldPosition[i][1]);
        }

        SpielfeldKreiseObjekte[2].setFill(Color.GREEN);
        SpielfeldKreiseObjekte[12].setFill(Color.RED);
        SpielfeldKreiseObjekte[22].setFill(Color.BLACK);
        SpielfeldKreiseObjekte[32].setFill(Color.YELLOW);

        return gridPane;
    }

    private void erzeugeStartBoxen (){
        besetzt=false;

        for (int i =0;i<4;i++){
            ErzeugeStartBox(i,besetzt);
        }

    }

    private GridPane ErzeugeStartBox(int SpielerNummer, boolean besetzt){
        SimpleIntegerProperty nummerierungsProperty = new SimpleIntegerProperty();

        for (int j = 0; j< Startbox[SpielerNummer].length; j++){
            Circle circle = new Circle(30);
            circle.setStrokeWidth(5);
            nummerierungsProperty.setValue(j+1);

            Text text  = new Text(nummerierungsProperty.getValue().toString());
            text.setFont(Font.font("Arial", FontWeight.BOLD,25));
            text.setFill(Color.WHITE);
            StackPane stackPane = new StackPane();

                switch (SpielerNummer) {
                    case 0:
                        circle.setFill(Color.GOLD);
                        circle.setStroke(Color.GOLD);
                        break;

                    case 1:
                        circle.setFill(Color.GREEN);
                        circle.setStroke(Color.GREEN);
                        break;

                    case 2:
                        circle.setFill(Color.BLACK);
                        circle.setStroke(Color.BLACK);

                        break;

                    case 3:
                        circle.setFill(Color.RED);
                        circle.setStroke(Color.RED);
                        break;
                }
                if(besetzt==false) {
                    circle.setFill(Color.WHITESMOKE);
                }
            stackPane.getChildren().addAll(circle,text);
            gridPane.add(stackPane, Startbox[SpielerNummer][j][0], Startbox[SpielerNummer][j][1]);
            gridPane.setHalignment(circle, HPos.CENTER);

        }
        return gridPane;
    }

    private void erzeugeZielBoxen (){
        besetzt=true;

        for (int i =0;i<4;i++){
            erzeugeZielBox(i,besetzt);
        }

    }

    private GridPane erzeugeZielBox(int SpielerNummer, boolean besetzt) {
        SimpleIntegerProperty nummerierungsProperty = new SimpleIntegerProperty();

        for (int j=0;j<Zielbox[SpielerNummer].length;j++){
            Circle circle = new Circle(30);
            circle.setStrokeWidth(5);

            switch (SpielerNummer) {
                case 0:
                    circle.setFill(Color.GOLD);
                    circle.setStroke(Color.GOLD);
                    break;

                case 1:
                    circle.setFill(Color.GREEN);
                    circle.setStroke(Color.GREEN);
                    break;

                case 2:
                    circle.setFill(Color.BLACK);
                    circle.setStroke(Color.BLACK);
                    break;

                case 3:
                    circle.setFill(Color.RED);
                    circle.setStroke(Color.RED);
                    break;
            }
            if(besetzt==false) {
                circle.setFill(Color.WHITESMOKE);
            }
            gridPane.add(circle, Zielbox[SpielerNummer][j][0], Zielbox[SpielerNummer][j][1]);
            gridPane.setHalignment(circle, HPos.CENTER);
        }
        return gridPane;
    }

    private GridPane erzeugeZiehButtons(){

        for(int i =0;i<ZiehButtonObjekte.length;i++){
            for(int j =0;j<ZiehButtonObjekte[i].length;j++){

                this.ZiehButtonObjekte[i][j] = new Button("Figur "+(j+1));

                if(j==0){
                    ZiehButtonObjekte[i][j].setDisable(false);
                }
                else{
                    ZiehButtonObjekte[i][j].setDisable(true);
                }
                ZiehButtonObjekte[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        for(int i=0;i<ZiehButtonObjekte.length;i++){
                            for(int j=0;j<ZiehButtonObjekte[i].length;j++){

                                if(event.getSource()==ZiehButtonObjekte[i][j]){

                                    spieleController.erzeugeSpielerObjekt(i,j);
                                }
                            }
                        }

                    }
                });
                gridPane.add(ZiehButtonObjekte[i][j],ZiehButtonPosition[i][j][0],ZiehButtonPosition[i][j][1]);
                gridPane.setHalignment(ZiehButtonObjekte[i][j], HPos.CENTER);

            }
        }
    return gridPane;
    }

    public GridPane erzeugeInnenAnzeige(){

        vBox = new VBox();

        wuerfelLabel = new Label();
        wuerfelLabel.setFont(Font.font("Arial",FontWeight.BOLD,30));
        wuerfelbutton = new Button("Bitte wuerfeln");
        spielStatus = new Label();



        vBox.getChildren().addAll(wuerfelbutton, wuerfelLabel,spielStatus);
        gridPane.add(vBox,wuerfelButtonPosition[0],wuerfelButtonPosition[1]);
        gridPane.setAlignment(Pos.CENTER);

    return gridPane;
    }

    public GridPane figurZeichnen (int Spieler,int FigurNummer,String StatusAlt, String StatusNeu,int positionAlt, int positionNeu){
        System.out.println(StatusAlt + StatusNeu);

        return gridPane;
    }


}
