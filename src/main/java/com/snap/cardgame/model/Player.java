package com.snap.cardgame.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in the Snap card game.
 *
 * Tracks the player's name and stores all created players.
 */
public class Player {
    private static final List<Player> players = new ArrayList<>();

    private String name;

    /**
     * Creates a new player with the given name and adds them to the list of players.
     *
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
        players.add(this);
    }

    /**
     * Returns a list of all players.
     *
     * @return a list of players
     */
    public static List<Player> getPlayers() {
        return players;
    }

    /**
     * Returns the name of the player.
     *
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the player.
     *
     * @param name new name
     */
    //This method is currently unused however I am leaving this for future iteratons
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of the player.
     *
     * @return the player name as a string
     */
    @Override
    public String toString() {
        return "Player: " + name;
    }
}
