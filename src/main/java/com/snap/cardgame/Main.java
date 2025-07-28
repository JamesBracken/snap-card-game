package com.snap.cardgame;

import com.snap.cardgame.service.Snap;

/**
 * The main class to start Snap.
 */
public class Main {

    /**
     * The application entry point.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Snap snap = new Snap();
        snap.startGame();
        System.out.println("Thanks for playing! :)");
    }
}
