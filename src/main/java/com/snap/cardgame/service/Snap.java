package com.snap.cardgame.service;

import com.snap.cardgame.model.Card;
import com.snap.cardgame.model.Player;

import java.util.*;

/**
 * Snap game logic extending CardGame.
 * <p>
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
        System.out.println("Please select how many players will be in the game up to a maximum of 8");
    }

    /**
     * Handles player count selection and creates player instances.
     */
    private void handlePlayerSelect() {
        boolean isHandlerActive = true;

        while (isHandlerActive) {

            System.out.println("Would you like to make custom names for each player? Input y/n.");
            String wantCustomNames = scanner.nextLine().trim().toLowerCase();
            System.out.println("Wants custom names: " + wantCustomNames);
            displayPlayerSelect();
            int playerAmount = Integer.parseInt(scanner.nextLine().trim());
            System.out.println("Player amount: " + playerAmount);
            if (playerAmount > 0 && playerAmount < 9 && wantCustomNames.equals("y")) {
                createPlayerWithCustomNames(playerAmount);
                players = Player.getPlayers();
                System.out.println("Players for this game " + "\n");
                players.forEach(System.out::println);
                startNewTurn();
                isHandlerActive = false;
            } else if (playerAmount > 0 && playerAmount < 9 && wantCustomNames.equals("n")) {
                for (int i = 0; i < playerAmount; i++) {
                    new Player(String.format("%d", i + 1));
                }
                players = Player.getPlayers();
                System.out.println("Players for this game " + "\n");
                players.forEach(System.out::println);
                startNewTurn();
                isHandlerActive = false;
            } else if ((playerAmount <= 0 || playerAmount >= 9) && (!wantCustomNames.equals("n") && !wantCustomNames.equals("y"))) {
                System.out.println("Invalid choices, please input a player amount inbetween 1-8 " + "\n"
                        + "and type in y/n for name customisation");
            } else if (playerAmount <= 0 || playerAmount >= 9) {
                System.out.println("Invalid choice, please input a correct player amount");
            } else if (!wantCustomNames.equals("n") && !wantCustomNames.equals("y")) {
                System.out.println("Invalid choice, please input y/n ");
            }
        }
    }

    /** Creates players with customised names
     *
     * @param playerAmount How many players a user wants to play with
     */
    private void createPlayerWithCustomNames(int playerAmount) {
        System.out.println("Please input the player names and press enter after each name");
        for (int i = 0; i < playerAmount; i++) {
            if(!(i == 0)) {
            System.out.println("Please input the next player name");
            }
            String playerName = scanner.nextLine().trim();
            System.out.println("Player " + (i + 1) + " name: " + playerName + "\n");
            new Player(playerName);
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
            System.out.println("---------------------");
            System.out.println("Current player turn: " + players.get(activePlayerIndex));
            displayInGameOptions();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    if (getDeckOfCards().isEmpty()) {
                        handleFinishRound();
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

                    if (previousCard != null) {
                        System.out.println("\n" + "Previous card: " + previousCard);
                    }
                    System.out.println("Your card: " + currentCard + "\n");
                    break;

                case "2":
                    boolean snapResult = handlePlayerSnap();
                    if (snapResult) {
                        finishTurn = true;
                        winner = players.get(activePlayerIndex);
                        handleFinishRound();
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
    private void handleFinishRound() {
        // Make this handle either finishing games or starting a new game with the previously input players
        boolean isHandlerActive = true;

        if (winner != null) {
            System.out.println("Oh Snap! " + players.get(activePlayerIndex) + " won!");
        } else {
            System.out.println("Oh Snap! No snaps were made in this game :(");
        }
        while (isHandlerActive) {
            System.out.println("Would you like to play again? y/n");
            String choice = scanner.nextLine().trim().toLowerCase(); // If player wants to play again
            if (choice.equals("y")) {
                // Restart game
                handleRestartGame();
            } else if (choice.equals("n")) {
                // End game
                isHandlerActive = false;
                handleEndGame();
            } else {
                System.out.println("Incorrect input, please try again");
            }

        }
    }

    private void handleRestartGame() {
        //Resetting game back to clean state and starting a new turn
        resetDeck();
        sortDeckInNumberOrder();
        prevPlayerIndex = -1;
        Player winner = null;
        isPlayerCardDealt = false;
        wasSnapAvailable = false;
        previousCard = null;
        currentCard = null;
        canCallSnap = false;
        startNewTurn();
    }

    private void handleEndGame() {
        System.out.println("Finishing game.");
    }
}
