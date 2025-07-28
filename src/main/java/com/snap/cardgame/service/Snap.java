package com.snap.cardgame.service;

//Stage 3
//Create class for Snap that extends CardGame.
//This class should use the methods defined above, as well as some new ones,
// to enable the user to play the game snap according to the following rules:
//By pressing enter in the command line, the user takes their turn.
//Each turn, a new card is dealt from the deck.
//The game continues until two cards in a row have the same symbol, at which
// point the “player” wins and the game ends.
//Create a Player class and enable the snap game to be two player, with the users
// taking it in turns to go. If the snap occurs on the users turn, they win.
//Add a timer so that when there is a snap opportunity, the player has 2 seconds to
// submit the word “snap” in order to win. If they don’t type it in time, they lose.

import com.snap.cardgame.model.Card;
import com.snap.cardgame.model.Player;

import java.util.*;

//- Create the startNewGame method, this must initialize game state and start a new turn FINISHED
//- Create a user scanner input FINISHED
//- Create an initial user greeting on game start FINISHED
//- Create a displayUserStartOptions which will display the starting options of a user FINISHED
//- Create a handleUserStartOptions method which will handle the flow of the game by user input FINISHED
//- Make the handleUserStartOptions method display the user options of  Start new game,
// How to play?, Exit game, these should be associated with a number FINISHED
//- The start new game should prompt the user to input how many players will join the game FINISHED
//- Create the startNewTurn method, this must alternate between players on each round and should
// call on the displayUserOptions FINISHED
//- The startNewTurn method should alter some game state values FINISHED
//- Create game state variables of isGameActive, previousCard, currentCard, canSnap, players, isCardDealt defaulted to false FINISHED
//- Create a handleSnap method which will enable user input of snap for 2 seconds
//- The canSnap variable should toggle to true if dealtCard and previousCard is equal in value of symbolInt
// and back to false if no snap click is made within 2 seconds, likely with a HandleSnap method
//- Create a handleEndTurn method to end a players turn and start the next turn FINISHED
//- The handleEndTurn method should reset/set some game state vars like previousCard, previousPlayer FINISHED
//- Create a displayInstructions method to print out some guidance to assist a user in playing FINISHED
//-
//-Close the scanner
//CHANGED-----------
//- Make the displayUserOptions method display the user options of  Start new game, Deal card,
// Snap, End turn, How to play? Exit game, these should be associated with a number
//-
//-
//-
public class Snap extends CardGame {

    private final Scanner scanner = new Scanner(System.in);
    private List<Player> players = new ArrayList<>();
    private int prevPlayerIndex = -1;
    private int activePlayerIndex;
    private Card previousCard;
    private Card currentCard;
    private boolean isGameActive = false;
    private boolean isPlayerCardDealt = false;
    private Timer timer;
    private boolean canCallSnap;

    public void startGame() {
        resetDeck();
        isGameActive = true;
//        shuffleDeck();
//        displayDeck();
//        dealCard();
        sortDeckInNumberOrder();
//        sortDeckIntoSuits();
//        displayDeck();
        displayUserStartOptions();
        handleUserStartOptions();
    }

    private void displayUserStartOptions() {
        System.out.println("\n" + "Welcome to Snap! Please input a number from the options below \n" +
                "1) Ready to play! \n" +
                "2) Show instructions? \n" +
                "3) Exit game");
    }

    private void handleUserStartOptions() {
        String choice = scanner.nextLine().trim();

        displayPlayerSelect();
        switch (choice) {
            case "1":
                System.out.println("------------------------");
                handlePlayerSelect();
                break;
            case "2":
                displayInstructions();
                System.out.println("------------------------");
                handleUserStartOptions();
                break;
            case "3":
                System.out.println("------------------------");
                System.out.println("You have quit the game, restart the terminal if you change your mind!");
                // Add end game method here
                break;
            default:
                System.out.println("------------------------");
                System.out.println("Invalid choice, please input a correct option");
                handleUserStartOptions();
        }
    }

    private void displayInstructions() {
        System.out.println("The main goal of Snap is to get 2 cards of the same value in a row. \n" +
                "If 2 cards of the same value appear consecutively a user has 2 seconds to call snap! \n" +
                "If a user calls snap in time they win! \n" +
                "Calling snap is only dependent on the card values not the suits \n" +
                "For example you can call snap on 2 of club and 2 of spades");
    }

    private void displayPlayerSelect() {
        System.out.println("Please select how many players will be in the game up to a maximum of 2");
    }

    private void handlePlayerSelect() {
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                new Player("Player 1");
                players = Player.getPlayers();
                players.forEach(System.out::println);
                startNewTurn();
                break;
            case "2":
//                System.out.println("------------------------");
                new Player("Player 1");
                new Player("Player 2");
                players = Player.getPlayers();
                players.forEach(System.out::println);
                startNewTurn();
                break;
            default:
                System.out.println("------------------------");
                System.out.println("Invalid choice, please input a correct option");
                displayPlayerSelect();
                handlePlayerSelect();
        }
    }

    private void startNewTurn() {
        activePlayerIndex = (prevPlayerIndex + 1) % players.size();
        System.out.println("activePlayerIndex: " + activePlayerIndex + "\n");
        handleInGameOptions();
    }

    private void displayInGameOptions() {
        System.out.println("1) Deal card  \n" +
                "2) Snap \n" +
                "3) End turn \n" +
                "4) Exit game \n"
        );
    }

    private void handleInGameOptions() {
        boolean finishTurn = false;
        while (!finishTurn) {
            System.out.println("In handleInGameOptions isPlayerCardDealt: " + isPlayerCardDealt);
            //Calling in game options here as we need to display it on every iteration except after end turn
            displayInGameOptions();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": // Deal card
                    System.out.println("------------------------");
                    if (!isPlayerCardDealt) {
                        currentCard = dealCard();
                        isPlayerCardDealt = true;
                    } else {
                        System.out.println("You have already dealt a card on this turn");
                    }
                    if (previousCard != null) {
                        System.out.println("previousCard: " + previousCard);
                        if(currentCard.getSymbolInt() == previousCard.getSymbolInt()) {
                            startSnapTimer();
                        }
                    }
                    System.out.println("Your card: " + currentCard + "\n");
                    break;
                case "2": // Snap

                    // Add handlePlayerSnap method here
                    if (!isPlayerCardDealt) {
                        System.out.println("You must deal a card first");
                    } else if(previousCard == null) {
                        System.out.println("There has only been 1 card dealt, no way to snap here!");
                    } else if (currentCard.getSymbolInt() == previousCard.getSymbolInt() && canCallSnap) {
                        System.out.println("Oh Snap!" + players.get(activePlayerIndex) + " won!");
                    } else if(currentCard.getSymbolInt() == previousCard.getSymbolInt() && !canCallSnap) {
                        System.out.println("The cards are the same however you ran out of time, you must call snap within 2 seconds");
                    }else if (currentCard != previousCard) {
                        System.out.println("The card you dealt is not of the same value as the previous card");
                    }
                    System.out.println("------------------------");
                    break;
                case "3": // End turn
                    if (isPlayerCardDealt) {
                        endPlayerTurn();
                        finishTurn = true;
                    } else {
                        System.out.println("You must deal a card first");
                    }
                    System.out.println("------------------------");
                    break;
                case "4": // Exit game
                    System.out.println("------------------------");
                    break;
                default: // Invalid inputs
                    System.out.println("------------------------");
                    System.out.println("Invalid choice, please input a correct option");
            }
        }
    }

    private void handlePlayerSnap() {

    }

    private void startSnapTimer() {
        System.out.println("Enabling snap for 2 seconds");
        canCallSnap = true;
        System.out.println("canCallSnap: " + canCallSnap);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                canCallSnap = false;
                System.out.println("Snap now disabled");
                System.out.println("canCallSnap: " + canCallSnap);
                timer.cancel();
            }
        }, 2000);
    }

    private void endPlayerTurn() {
        previousCard = currentCard;
        isPlayerCardDealt = false;
        prevPlayerIndex = activePlayerIndex;
        // Creating some spacing between each turn
        System.out.println("""
                
                
                """);
        startNewTurn();
    }
}