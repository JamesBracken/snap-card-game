package com.snap.cardgame.model;

/**
 * Represents a playing card with a suit and symbol.
 */
public class Card {

    // Enums-----------

    /**
     * Enum for the four card suits.
     */
    public enum Suit {
        SPADE('\u2660'), CLUB('\u2663'), HEART('\u2665'), DIAMOND('\u2666');

        private final char value;

        Suit(char suit) {
            this.value = suit;
        }
    }

    /**
     * Enum for card symbols and their corresponding integer values.
     */
    public enum Symbol {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8),
        NINE(9), JACK(10), Q(12), K(13), A(14);

        private final int value;

        Symbol(int value) {
            this.value = value;
        }
    }

    // Fields-----------

    private String symbolStr;
    private char suit;
    private int symbolInt;

    // Constructor--------------

    /**
     * Constructs a Card with a suit and symbol.
     *
     * @param suit   the card suit -Spade, Club, Heart, Diamond
     * @param symbol the card symbol -TWO, THREE, FOUR +
     */
    public Card(Suit suit, Symbol symbol) {
        this.symbolInt = symbol.value;
        this.symbolStr = symbol.name();
        this.suit = suit.value;
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
