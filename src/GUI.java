import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;


public class GUI extends Application{

    Button playButton, optionsButton, quitButton, backButton, backButton1;
    Stage window;
    int numberOfMoves = 0;


    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        //get the size of the monitor
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenHeight = screenBounds.getHeight()-50;
        double screenWidth = screenBounds.getWidth();

        //set background image of the main menu and play screen
        BackgroundImage mainBG = new BackgroundImage(new Image("main menu background.jpg", screenWidth, screenHeight, false, true),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        BackgroundImage playBG = new BackgroundImage(new Image("wood-background.jpg", screenWidth, screenHeight, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);


        //Play Screen
        StackPane layout2 = new StackPane();
        layout2.setBackground(new Background(playBG));
        Scene playScreen = new Scene(layout2, screenWidth, screenHeight);

        //Label for the number of moves
        Label moveCounter = new Label("Number of Moves: " + Integer.toString(numberOfMoves));
        moveCounter.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        moveCounter.setTextFill(Color.WHITE);
        moveCounter.setTranslateY(400);

        //layout for the cards in the play screen
//        Rectangle deck = new Rectangle();
//        deck.setWidth(100);
//        deck.setHeight(150);
//        deck.setTranslateX(-400);
//        deck.setTranslateY(-150);
//        deck.setFill(Color.WHITE);
//
//        Rectangle ace1 = new Rectangle();
//        ace1.setWidth(100);
//        ace1.setHeight(150);
//        ace1.setTranslateX(0);
//        ace1.setTranslateY(-150);
//        ace1.setFill(Color.WHITE);
//
//        Rectangle ace2 = new Rectangle();
//        ace2.setWidth(100);
//        ace2.setHeight(150);
//        ace2.setTranslateX(125);
//        ace2.setTranslateY(-150);
//        ace2.setFill(Color.WHITE);
//
//        Rectangle ace3 = new Rectangle();
//        ace3.setWidth(100);
//        ace3.setHeight(150);
//        ace3.setTranslateX(250);
//        ace3.setTranslateY(-150);
//        ace3.setFill(Color.WHITE);
//
//        Rectangle ace4 = new Rectangle();
//        ace4.setWidth(100);
//        ace4.setHeight(150);
//        ace4.setTranslateX(375);
//        ace4.setTranslateY(-150);
//        ace4.setFill(Color.WHITE);
//
//        Rectangle pile4 = new Rectangle();
//        pile4.setWidth(100);
//        pile4.setHeight(150);
//        pile4.setTranslateX(0);
//        pile4.setTranslateY(25);
//        pile4.setFill(Color.WHITE);
//
//        layout2.getChildren().addAll(deck, ace1, ace2, ace3, ace4, pile4);

        //Options Screen
        StackPane layout3 = new StackPane();
        layout3.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        Scene optionsScreen = new Scene(layout3, screenWidth, screenHeight);

        //add buttons: play, options, and quit for the main menu screen
        playButton = new Button();
        playButton.setText("Play");
        playButton.setOnAction(e -> window.setScene(playScreen));
        playButton.setMaxWidth(100);

        optionsButton = new Button();
        optionsButton.setText("Options");
        optionsButton.setTranslateY(50);
        optionsButton.setOnAction(e -> window.setScene(optionsScreen));
        optionsButton.setMaxWidth(100);

        quitButton = new Button();
        quitButton.setText("Quit");
        quitButton.setTranslateY(100);
        quitButton.setOnAction(e -> window.close());
        quitButton.setMaxWidth(100);

        //Title Box for Main Menu Screen
        Text mainTitle = new Text("Solitaire");
        mainTitle.setTranslateY(-100);
        mainTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 32));

        Rectangle mainRectangle = new Rectangle();
        mainRectangle.setTranslateY(-100);
        mainRectangle.setWidth(300);
        mainRectangle.setHeight(100);
        mainRectangle.setFill(Color.WHITE);

        //Scene layout of the main menu
        StackPane layout = new StackPane();
        layout.getChildren().add(playButton);
        layout.getChildren().add(optionsButton);
        layout.getChildren().add(quitButton);
        layout.setBackground(new Background(mainBG));
        layout.getChildren().add(mainRectangle);
        layout.getChildren().add(mainTitle);

        //Create a new scene for the Main Menu
        Scene mainMenu = new Scene(layout, screenWidth, screenHeight);

        //back button for play screen and options screen
        backButton = new Button();
        backButton.setText("Back");
        backButton.setTranslateY(-240);
        backButton.setTranslateX(-480);
        backButton.setOnAction(e -> window.setScene(mainMenu));

        backButton1 = new Button();
        backButton1.setText("Back");
        backButton1.setTranslateY(-240);
        backButton1.setTranslateX(-480);
        backButton1.setOnAction(e -> window.setScene(mainMenu));

        Button moveCounterButtonTest = new Button();
        moveCounterButtonTest.setText("Click me");
        moveCounterButtonTest.setOnAction(e -> {
            moveCounter.setText("Number of Moves: " + Integer.toString(numberOfMoves));
            counter();
        });
        moveCounterButtonTest.setTranslateY(50);

        layout2.getChildren().add(backButton);
        layout2.getChildren().addAll(moveCounter,moveCounterButtonTest);
        layout3.getChildren().add(backButton1);

        window.setScene(mainMenu);
        window.setTitle("Solitaire");
        window.show();

    }

    public void counter(){
        numberOfMoves++;
    }


    public static void main(String[] args) {
        launch(args);
    }

}
