package com.snap.cardgame.service;

import com.snap.cardgame.model.Card;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Manages 52 card deck games.
 *
 * Contains methods for shuffling, dealing, sorting, and resetting a deck.
 */
public class CardGame {

    /**
     * The current deck of cards.
     */
    private ArrayList<Card> deckOfCards = new ArrayList<>();

    /**
     * Resets a deck to the full 52 cards deck with all suits and symbols.
     *
     * Clears any existing cards.
     *
     */
    protected void resetDeck() {
        deckOfCards = new ArrayList<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Symbol symbol : Card.Symbol.values()) {
                Card card = new Card(suit, symbol);
                deckOfCards.add(card);
            }
        }
    }

    /**
     * Prints all cards currently in the deck to the console.
     */
    protected void displayDeck() {
        for (Card card : deckOfCards) {
            System.out.println(card);
        }
    }

    /**
     * Removes and returns the top card from the deck.
     *
     * The card is removed from the deck until deck reset
     *
     * @return the removed card
     * @throws IndexOutOfBoundsException if the deck is empty
     */
    protected Card dealCard() {
        return deckOfCards.removeFirst();
    }

    /**
     * Sorts the deck by suit.
     *
     * Groups all cards of the same suit together.
     */
    protected void sortDeckIntoSuits() {
        deckOfCards.sort((a, b) -> b.getSuit() - a.getSuit());
    }

    /**
     * Sorts the deck by card value in ascending order.
     *
     * All 2s come first, the deck ends with Aces having a value of 14.
     *
     */
    protected void sortDeckInNumberOrder() {
        deckOfCards.sort((a, b) -> a.getSymbolInt() - b.getSymbolInt());
    }

    /**
     * Randomly shuffles the deck into a new order.
     *
     * Uses random selection to reorder all cards.
     *
     */
    protected void shuffleDeck() {
        ArrayList<Card> tempCardArrList = new ArrayList<>();
        for(int i = deckOfCards.size() -1; i >= 0; i--) {
            tempCardArrList.add(deckOfCards.remove(randIntGen(i)));
        }
        deckOfCards = tempCardArrList;
    }

    /**
     * Returns the current deck of cards.
     *
     * @return the current deck as an ArrayList of Card objects
     */
    protected ArrayList<Card> getDeckOfCards() {
        return deckOfCards;
    }

    /**
     * Generates a random integer between 0 and the specified range param.
     *
     * @param range the upper bound (exclusive) for the random number
     * @return a random integer from 0 to range
     */
    private int randIntGen(int range) {
        return (int) Math.ceil(Math.random() * range);
    }
}