package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        setUp();
        buyEndless();
        /*
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Panini Collector");
        primaryStage.setScene(new Scene(root, 1800, 1000));
        controller = loader.getController();
        primaryStage.show();
        //controller.reDraw();
        */
    }

    public final static int NUMBER_OF_CARDS = 281;
    public final static int NUMBER_OF_PLAYER = 15;
    public static int DOUBLES = 0;
    public static int GIVEN_CARDS = 0;

    private ArrayList<Player> players;
    private Controller controller;

    private void setUp() {
        players = new ArrayList<>();
        players.add(new Player("Homer"));
        players.add(new Player("Marge"));
        players.add(new Player("Maggie"));
        players.add(new Player("Bart"));
        players.add(new Player("Lisa"));
        players.add(new Player("Smithers"));
        players.add(new Player("Milhouse"));
        players.add(new Player("Mr Burns"));
        players.add(new Player("Barney"));
        players.add(new Player("Nedd Flanders"));
        players.add(new Player("Rodd"));
        players.add(new Player("Todd"));
        players.add(new Player("Mona"));
        players.add(new Player("Abe"));
        players.add(new Player("Santa"));

    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    private void buyEndless(){
        int MAX_CARDS=800000;
        do {
            buyRandomCard();
        } while (CARDS() < MAX_CARDS && !allPlayerComplete());
        log("Cards per Player: " + CARDS() / NUMBER_OF_PLAYER);
        System.exit(1);
    }

    private void buyRandomCard() {
        int newCard = ThreadLocalRandom.current().nextInt(0,NUMBER_OF_CARDS);
        boolean cardGiven = false;
        for (Player playerToCheck:players) {
            if (!playerToCheck.hasCard(newCard)) {
                playerToCheck.setFound(newCard);
                GIVEN_CARDS++;
                log("Card " + CARDS() + " #" + newCard + " given to " + playerToCheck.getName() + ". This is given card number " + GIVEN_CARDS + " - " + numbersOfCards());
                cardGiven = true;
                break;
            }
        }
        if (cardGiven == false) {
            DOUBLES++ ;
            log("Card " + CARDS() + " #" + newCard + " not given to anyone. This is the double number " + DOUBLES + " - " + numbersOfCards());
        }

    }

    private int CARDS() {
        return DOUBLES + GIVEN_CARDS;
    }

    private boolean allPlayerComplete() {
        for (Player playerToCheck:players) {
            if (!playerToCheck.hasAll()) {
                return false;
            }
        }
        log("All players complete");
        return true;
    }

    private void log(String text) {
        System.out.println(text);
    }
    public static void main(String[] args) {
        launch(args);
    }

    private String numbersOfCards() {
        String ret="";
        for (Player playerToCheck:players) {
            ret = ret + " " + playerToCheck.getName() + ": " + playerToCheck.numberCards();
         }
        return ret;
    }
}
