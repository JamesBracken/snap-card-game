package org.example;


//Contains an ArrayList<Card> for the deckOfCards that contains all 52 cards. FINISHED
//- Create the array FINISHED
//This is created and populated when the game is constructed.
//- Create a initialiseDeck method to populate the deckOfCards with 52 cards
//- The method should clear the deckOfCards prior to population to create a clean game state OR
//you can create a new round function which will do this and a separate initialiseDeck function
//Has a name which is also defined in the constructor.
//- a name for what????
//Has a getDeck method that lists out the cards in the deck.

import java.util.ArrayList;

public class CardGame {

    ArrayList<Card> deckOfCards = new ArrayList<>();


    public void initialiseDeck() {
        for(Card.Suit suit: Card.Suit.values()) {
            for(Card.Symbol symbol: Card.Symbol.values()) {
                Card card = new Card(suit, symbol);
                deckOfCards.add(card);
            }
        }
    }
}


