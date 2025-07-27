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

import com.snap.cardgame.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//- Create the startNewGame method, this must initialize game state and start a new turn FINISHED
//- Create a user scanner input FINISHED
//- Create an initial user greeting on game start FINISHED
//- Create a displayUserStartOptions which will display the starting options of a user FINISHED
//- Create a handleUserStartOptions method which will handle the flow of the game by user input FINISHED
//- Make the handleUserStartOptions method display the user options of  Start new game,
// How to play?, Exit game, these should be associated with a number FINISHED
//- The start new game should prompt the user to input how many players will join the game FINISHED
//- Create the startNewTurn method, this must alternate between players on each round and should
// call on the displayUserOptions
//- The startNewTurn method should alter some game state values
//- Create the Player class which will have properties of isPlayerTurn default of false, isCardDealt defaulted to false
//- Create game state variables of isGameActive, previousCard, currentCard, canSnap, players
//- Create a handleSnap method which will enable user input of snap for 2 seconds
//- The canSnap variable should toggle to true if dealtCard and previousCard is equal in value of symbolInt
// and back to false if no snap click is made within 2 seconds, likely with a HandleSnap method
//- Create a handleEndTurn method to end a players turn and start the next turn
//- The handleEndTurn method should reset/set some game state vars like previousCard, currentCard,
//- Create a displayInstructions method to print out some guidance to assist a user in playing
//-
//-Close the scanner
//CHANGED-----------
//- Make the displayUserOptions method display the user options of  Start new game, Deal card,
// Snap, End turn, How to play? Exit game, these should be associated with a number
//-
//-
//-
public class Snap extends CardGame {

    private Scanner scanner = new Scanner(System.in);
    private List<Player> players = new ArrayList<>();

    //
    public void startGame() {
//        resetDeck();
//        shuffleDeck();
//        displayDeck();
//        dealCard();
//        sortDeckInNumberOrder();
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

        switch (choice) {
            case "1":
                System.out.println("------------------------");
                displayPlayerSelect();
                handlePlayerSelect();
                break;
            case "2":
                displayInstructions();
                System.out.println("------------------------");
                displayUserStartOptions();
                handleUserStartOptions();
                break;
            case "3":
                System.out.println("------------------------");
                System.out.println("You have quit the game, restart the terminal if you change your mind!");
                break;
            default:
                System.out.println("------------------------");
                System.out.println("Invalid choice, please input a correct option");
                displayUserStartOptions();
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
                break;
            case "2":
//                System.out.println("------------------------");
                new Player("Player 1");
                new Player("Player 2");
                players = Player.getPlayers();
                players.forEach(System.out::println);
                break;
            default:
                System.out.println("------------------------");
                System.out.println("Invalid choice, please input a correct option");
                displayPlayerSelect();
                handlePlayerSelect();
        }
    }

    //- Make the displayUserOptions method display the user options of  Start new game, Deal card,
// Snap, End turn, How to play? Exit game, these should be associated with a number

}
