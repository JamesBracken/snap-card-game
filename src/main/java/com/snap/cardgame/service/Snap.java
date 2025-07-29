package com.snap.cardgame.service;

import com.snap.cardgame.model.Card;
import com.snap.cardgame.model.Player;

import java.util.*;

/**
 * Snap game logic extending CardGame.
 *
 * Manages turns, players, card dealing, and snap handling.
 */
public class Snap extends CardGame {

    // Fields----------
    private final Scanner scanner = new Scanner(System.in);

    private List<Player> players = new ArrayList<>();
    private int prevPlayerIndex = -1;
    private Player winner = null;
    private boolean isPlayerCardDealt = false;
    private boolean wasSnapAvailable = false;

    private int activePlayerIndex;
    private Card previousCard;
    private Card currentCard;
    private Timer timer;
    private boolean canCallSnap;

    // Public methods---------------

    /**
     * Initializes and starts the game.
     */
    public void startGame() {
        resetDeck();
        sortDeckInNumberOrder();
        displayUserStartOptions();
        handleUserStartOptions();
    }

    // Private methods---------------

    /**
     * Displays start game menu options.
     */
    private void displayUserStartOptions() {
        System.out.println("\n" + "Welcome to Snap! Please input a number from the options below \n" +
                "1) Ready to play! \n" +
                "2) Show instructions? \n" +
                "3) Exit game");
    }

    /**
     * Handles user input for the start menu.
     */
    private void handleUserStartOptions() {
        boolean isHandlerActive = true;

        while (isHandlerActive) {
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    displayPlayerSelect();
                    handlePlayerSelect();
                    isHandlerActive = false;
                    break;
                case "2":
                    displayInstructions();
                    displayUserStartOptions();
                    break;
                case "3":
                    System.out.println("You have quit the game, restart the terminal if you change your mind!");
                    return;
                default:
                    System.out.println("Invalid choice, please input a correct option");
                    displayUserStartOptions();
            }
        }
    }

    /**
     * Displays instructions on how to play the game.
     */
    private void displayInstructions() {
        System.out.println("The main goal of Snap is to get 2 cards of the same value in a row.\n" +
                "If 2 cards of the same value appear consecutively, a user has 2 seconds to call snap.\n" +
                "Snap is based on value, not suit.");
    }

    /**
     * Prompts the user to choose the number of players.
     */
    private void displayPlayerSelect() {
        System.out.println("Please select how many players will be in the game up to a maximum of 2");
    }

    /**
     * Handles player count selection and creates player instances.
     */
    private void handlePlayerSelect() {
        boolean isHandlerActive = true;

        while (isHandlerActive) {
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    new Player("Player 1");
                    players = Player.getPlayers();
                    System.out.println(players.getFirst());
                    isHandlerActive = false;
                    startNewTurn();
                    break;
                case "2":
                    new Player("Player 1");
                    new Player("Player 2");
                    players = Player.getPlayers();
                    players.forEach(System.out::println);
                    isHandlerActive = false;
                    startNewTurn();
                    break;
                default:
                    System.out.println("Invalid choice, please input a correct option");
                    displayPlayerSelect();
            }
        }
    }

    /**
     * Starts a new player's turn, alternating between players.
     */
    private void startNewTurn() {
        activePlayerIndex = (prevPlayerIndex + 1) % players.size();
        handleInGameOptions();
    }

    /**
     * Displays available in-game options during a player's turn.
     */
    private void displayInGameOptions() {
        System.out.println("1) Deal card  \n" +
                "2) Snap \n" +
                "3) End turn \n" +
                "4) Exit game \n");
    }

    /**
     * Handles in-game input during a player's turn.
     */
    private void handleInGameOptions() {
        boolean finishTurn = false;
        while (!finishTurn) {
            displayInGameOptions();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    if (getDeckOfCards().isEmpty()) {
                        handleFinishGame();
                        return;
                    }

                    if (!isPlayerCardDealt) {
                        currentCard = dealCard();
                        isPlayerCardDealt = true;
                    } else {
                        System.out.println("You have already dealt a card on this turn");
                    }

                    if (previousCard != null &&
                            currentCard.getSymbolInt() == previousCard.getSymbolInt() && !wasSnapAvailable) {
                        startSnapTimer();
                    }

                    System.out.println("Previous card: " + previousCard + "\n");
                    System.out.println("Your card: " + currentCard + "\n");
                    break;

                case "2":
                    boolean snapResult = handlePlayerSnap();
                    if (snapResult) {
                        finishTurn = true;
                        winner = players.get(activePlayerIndex);
                        handleFinishGame();
                    }
                    break;

                case "3":
                    if (isPlayerCardDealt) {
                        endPlayerTurn();
                        finishTurn = true;
                    } else {
                        System.out.println("You must deal a card first");
                    }
                    break;

                case "4":
                    System.out.println("You have quit the game, restart the terminal if you change your mind!");
                    return;

                default:
                    System.out.println("Invalid choice, please input a correct option");
            }
        }
    }

    /**
     * Handles the logic when a player attempts to snap.
     *
     * @return true if the snap was valid & within time, false otherwise.
     */
    private boolean handlePlayerSnap() {
        if (!isPlayerCardDealt) {
            System.out.println("You must deal a card first");
        } else if (previousCard == null) {
            System.out.println("Only one card has been dealt so far");
        } else if (currentCard.getSymbolInt() == previousCard.getSymbolInt() && canCallSnap) {
            winner = players.get(activePlayerIndex);
            return true;
        } else if (currentCard.getSymbolInt() == previousCard.getSymbolInt() && !canCallSnap) {
            System.out.println("Snap was too slow. You had 2 seconds to react.");
        } else {
            System.out.println("Cards do not match");
        }
        return false;
    }

    /**
     * Starts a 2 second timer limit for a player to snap.
     */
    private void startSnapTimer() {
        System.out.println("Enabling snap for 2 seconds");
        canCallSnap = true;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                canCallSnap = false;
                // wasSnapAvailable is used to fix a bug where re-calling the deal card option re-enables a snap
                // even w/o dealing a card
                wasSnapAvailable = true;
                timer.cancel();
            }
        }, 2000);
    }

    /**
     * Ends the current player's turn and prepares for the next.
     */
    private void endPlayerTurn() {

        if (wasSnapAvailable) {
            wasSnapAvailable = false;
        }
        previousCard = currentCard;
        isPlayerCardDealt = false;
        prevPlayerIndex = activePlayerIndex;
        startNewTurn();
    }

    /**
     * Displays the game result and ends the game.
     */
    private void handleFinishGame() {
        if (winner != null) {
            System.out.println("Oh Snap! " + players.get(activePlayerIndex) + " won!");
        } else {
            System.out.println("Oh Snap! No snaps were made in this game :(");
        }
        System.out.println("Finishing game.");
    }
}
