package com.snap.cardgame.model;

public enum Symbol {
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8),
    NINE(9), JACK(10), QUEEN(12), KING(13), ACE(14);

    // Values are left as int rather than byte for cleaner code
    private final int value;

    Symbol(int value) {
        this.value = value;
    }

    public byte getValue() {
        return (byte) value;
    }
}