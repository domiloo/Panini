package model;

import sample.Main;

import java.util.ArrayList;

public class Player {

    private ArrayList<Boolean> cards;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Player(String name) {
        this.name = name;
        cards = new ArrayList<>(Main.NUMBER_OF_CARDS);
        for (int i = 0; i < Main.NUMBER_OF_CARDS; i++) {
            cards.add(i,false);
        }
    }

    public void setFound(int index) {
        cards.set(index,true);
    }

    public boolean hasAll() {
        for (int i = 0; i < Main.NUMBER_OF_CARDS; i++) {
            if (cards.get(i) == false) {
                return false;
            }
        }
        return true;
    }

    public boolean hasCard(int index) {
        return cards.get(index);
    }

    public int numberCards() {
        int num=0;
        for (int i = 0; i < Main.NUMBER_OF_CARDS; i++) {
            if (cards.get(i) == true) {
                num++;
            }
        }
        return num;
    }
}
