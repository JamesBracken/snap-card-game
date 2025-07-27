package com.snap.cardgame.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private static final List<Player> players = new ArrayList<>();

    private String name;

    public Player(String name) {
        this.name = name;
        players.add(this);
    }

    public static List<Player> getPlayers() {
        return players;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Player: " + name;
    }
}
