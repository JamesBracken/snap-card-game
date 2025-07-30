package com.snap.cardgame.model;

/**
 * Enum for the four card suits.
 */
public enum Suit {
    SPADE('\u2660'), CLUB('\u2663'), HEART('\u2665'), DIAMOND('\u2666');

    private final char value;

    Suit(char suit) {
        this.value = suit;
    }

    public char getValue() {
        return value;
    }
}
