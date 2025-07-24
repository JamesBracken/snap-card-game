package com.snap.cardgame.service;


//Contains an ArrayList<Card> for the deckOfCards that contains all 52 cards. FINISHED
//- Create the array FINISHED
//This is created and populated when the game is constructed. FINISHED
//- Create a resetDeck method to populate the deckOfCards with 52 cards FINISHED
//- The method should clear the deckOfCards prior to population to create a clean game state OR FINISHED
//you can create a new round function which will do this and a separate initialiseDeck function FINISHED
//Has a name which is also defined in the constructor.------------------------???
//- a name for what???? -----------------------------------------------???
//Has a getDeck method that lists out the cards in the deck. FINISHED

import com.snap.cardgame.model.Card;

import java.util.ArrayList;

public class CardGame {
    //    No game functionality reason to keep a log of all
    Card previousCard;
    ArrayList<Card> deckOfCards = new ArrayList<>();

    public void resetDeck() {
        deckOfCards = new ArrayList<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Symbol symbol : Card.Symbol.values()) {
                Card card = new Card(suit, symbol);
                deckOfCards.add(card);
            }
        }
    }

    public void displayDeck() {
        for (Card card : deckOfCards) {
            System.out.println(card);
        }
    }

    public Card dealCard() {
        Card removedCard = deckOfCards.removeFirst();
        if (previousCard != null) {
            System.out.println("previousCard: " + previousCard);
        }
        System.out.println("Your card: " + removedCard + "\n");
        previousCard = removedCard; // May have to move this depending on implementation
        return removedCard;
    }

}


