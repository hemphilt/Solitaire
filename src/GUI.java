import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private ImageView imageTest, imageTest2, imageTest3, imageTest4;
    private int offset = -50;
    private int cen = -250;
    int pile1x, pile2x, pile3x, pile4x, pile5x, pile6x, pile7x;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        //get the size of the monitor
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenHeight = screenBounds.getHeight()-50;
        double screenWidth = screenBounds.getWidth();

        //X and Y Coordinates of the piles
        pile1x = -cen;
        pile2x = pile1x + 50;


        //set background image of the main menu and play screen
        BackgroundImage mainBG = new BackgroundImage(new Image("main menu background.jpg", screenWidth, screenHeight, false, true),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        BackgroundImage playBG = new BackgroundImage(new Image("wood-background.jpg", screenWidth, screenHeight, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);


        //Play Screen
        BorderPane layout2 = new BorderPane();
        layout2.setBackground(new Background(playBG));
//        HBox addHBox = new HBox();
//        addHBox.setPadding(new Insets(15, 12, 15, 12));
//        addHBox.setSpacing(50);
//        layout2.setTop(addHBox);
        Scene playScreen = new Scene(layout2, screenWidth, screenHeight);

        //Label for the number of moves
        Label moveCounter = new Label("Number of Moves: " + Integer.toString(numberOfMoves));
        moveCounter.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        moveCounter.setTextFill(Color.WHITE);
        moveCounter.setTranslateY(400);


        Deck deck = new Deck();
        deck.getCard(0);
        Card card1 = deck.getCard(0);
        Card card2 = deck.getCard(1);
        Card card3 = deck.getCard(2);
        Card card4 = deck.getCard(3);


        imageTest = new ImageView(new Image(card1.getCardURL()));
        imageTest2 = new ImageView(new Image(card2.getCardURL()));
        imageTest3 = new ImageView(new Image(card3.getCardURL()));
        imageTest4 = new ImageView(new Image(card4.getCardURL()));


        imageTest.setFitHeight(250);
        imageTest.setFitWidth(150);

        imageTest2.setFitHeight(250);
        imageTest2.setFitWidth(150);
        imageTest2.setTranslateY(cen);

        DropShadow borderGlow = new DropShadow();
        borderGlow.setColor(Color.YELLOW);
        borderGlow.setOffsetX(0f);
        borderGlow.setOffsetY(0f);
        borderGlow.setHeight(80);


        imageTest4.setPickOnBounds(false);
        imageTest4.setOnMouseClicked((MouseEvent e) -> {
            System.out.println("Clicked!");
            imageTest4.setEffect(borderGlow);
        });

        imageTest3.setFitHeight(250);
        imageTest3.setFitWidth(150);
        imageTest3.setTranslateY(cen - offset);

        imageTest4.setFitHeight(250);
        imageTest4.setFitWidth(150);
        imageTest4.setTranslateY(cen - 2 * offset);
        imageTest4.setTranslateX(-cen);

        //HBox layout for playScreen, top part of the screen
        Region region1 = new Region();
        Region region2 = new Region();


        HBox topLayout = new HBox(region1, region2);

        for (int i = 0; i < deck.size(); i++){
            ImageView deckImage = new ImageView(new Image(deck.getCard(i).getCardURL()));
            deckImage.setFitHeight(250);
            deckImage.setFitWidth(150);
            topLayout.getChildren().add(deckImage);
        }

        topLayout.setHgrow(region1, Priority.ALWAYS);
        topLayout.setHgrow(region2, Priority.ALWAYS);

        topLayout.setPadding(new Insets(15, 12, 15, 12));
        topLayout.setSpacing(50);

        //HBox layout for the center of the playScreen

        StackPane piles = new StackPane(imageTest2, imageTest3, imageTest4);
        final ImageView mainImageView = new ImageView();
        final ImageView bgImageView = new ImageView();
        layout2.getChildren().addAll(mainImageView, bgImageView);


        HBox centerLayout = new HBox(piles);

        piles.setPadding(new Insets(15, 12, 15, 12));

        centerLayout.setPadding(new Insets(15,12,15,12));
        centerLayout.setSpacing(50);



        layout2.setTop(topLayout);
        layout2.setCenter(centerLayout);




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
            moveCounter.setText("Number of Moves: " + numberOfMoves);
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
