package model;

import sample.Controller;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Calculator {

    private Calculator() {

    }

    private static Calculator INSTANCE;
    public final int NUMBER_OF_CARDS = 281;
    public final int NUMBER_OF_PLAYER = 5;
    public int DOUBLES = 0;
    public int GIVEN_CARDS = 0;

    private ArrayList<Player> players;
    private Controller controller;


    public static Calculator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Calculator();
        }
        return INSTANCE;
    }

    public void setUp() {
        players = new ArrayList<>();
        players.add(new Player("Homer"));
        players.add(new Player("Marge"));
        players.add(new Player("Maggie"));
        players.add(new Player("Bart"));
        players.add(new Player("Lisa"));
        /*
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
*/
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    public void buyEndless(){
        log("Cards per Player: " + CARDS() / NUMBER_OF_PLAYER);
    }

    public void buyRandomCard() {
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

    public int CARDS() {
        return DOUBLES + GIVEN_CARDS;
    }

    public boolean allPlayersComplete() {
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


    private String numbersOfCards() {
        String ret="";
        for (Player playerToCheck:players) {
            ret = ret + " " + playerToCheck.getName() + ": " + playerToCheck.numberCards();
        }
        return ret;
    }
}
