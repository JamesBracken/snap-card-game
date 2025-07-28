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

//Stage 2
//Lets get some core functionality to our CardGame by implementing the following methods: FINISHED
//Card dealCard() FINISHED
//Takes the card from the top of the deck and returns it. FINISHED
//- Create a dealCard method and make it remove a card from the main deck
//- Display the removed card with the previously removed card if there was one
//ArrayList<Card> sortDeckInNumberOrder()  FINISHED
//Sorts the deck in number order (e.g. 2222333344445555 etc) and stores the new shuffled deck back into the deckOfCards attribute.FINISHED
//- Create a method to organise the current deck into a number order
//ArrayList<Card> sortDeckIntoSuits() FINISHED
//Sorts the deck into suits (2,3,4,5,6,7,8,9,10,J,Q,K,A of hearts, then 2,3,4,5,6,7,8,9,10,J,Q,K,A of clubs etc.) and stores the new shuffled deck back into the deckOfCards attribute. FINISHED
//- Create a method to organise the current deck into suits
//ArrayList<Card> shuffleDeck()
//Shuffles the deck into a random order and stores the new shuffled deck back into the deckOfCards attribute.
//- Create a random int generator to generate an integer, this will receive a parameter as a max range
//- Create a method which contains a loop to transfer all deckOfCard items into a temp array in a random order
//- Using the same method transfer the temp array data back into the deckOfCards

import com.snap.cardgame.model.Card;

import java.util.ArrayList;
import java.util.Comparator;

public class CardGame {
    //    No game functionality reason to keep a log of all
    private ArrayList<Card> deckOfCards = new ArrayList<>();

    protected void resetDeck() {
        deckOfCards = new ArrayList<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Symbol symbol : Card.Symbol.values()) {
                Card card = new Card(suit, symbol);
                deckOfCards.add(card);
            }
        }
    }

    protected void displayDeck() {
        for (Card card : deckOfCards) {
            System.out.println(card);
        }
    }

    protected Card dealCard() {
        Card removedCard = deckOfCards.removeFirst();
        return removedCard;
    }

    protected void sortDeckIntoSuits() {
        deckOfCards.sort((a, b) -> b.getSuit() - a.getSuit());
    }

    protected void sortDeckInNumberOrder() {
        deckOfCards.sort((a, b) -> a.getSymbolInt() - b.getSymbolInt());
    }

    protected void shuffleDeck() {
        ArrayList<Card> tempCardArrList = new ArrayList<>();
        for(int i = deckOfCards.size() -1; i >= 0; i--) {
            tempCardArrList.add(deckOfCards.remove(randIntGen(i)));
        }
        deckOfCards = tempCardArrList;
    }

    private int randIntGen(int range) {
        return (int) Math.ceil(Math.random() * range);
    }
}
