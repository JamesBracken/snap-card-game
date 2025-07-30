package com.snap.cardgame.model;

/**
 * Represents a playing card with a suit and symbol.
 */
public class Card {

    // Enums-----------

    /**
     * Enum for card symbols and their corresponding integer values.
     */

    // Fields-----------

    private final String symbolStr;
    private final char suit;
    private final int symbolInt;

    // Constructor--------------

    /**
     * Constructs a Card with a suit and symbol.
     *
     * @param suit   the card suit -Spade, Club, Heart, Diamond
     * @param symbol the card symbol -TWO, THREE, FOUR +
     */
    public Card(Suit suit, Symbol symbol) {
        this.symbolInt = symbol.getValue();
        this.symbolStr = symbol.name();
        this.suit = suit.getValue();
    }

    // Getters and setters--------------

    /**
     * @return the Unicode character for the card suit
     */
    public char getSuit() {
        return suit;
    }

    /**
     * @return the cards int value
     */
    public int getSymbolInt() {
        return symbolInt;
    }

    // Overrides---------------

    /**
     * @return a string description of the card
     */
    @Override
    public String toString() {
        return symbolStr + " of " + suit;
    }
}
