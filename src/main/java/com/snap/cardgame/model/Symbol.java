package com.snap.cardgame.model;

public enum Symbol {
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8),
    NINE(9), JACK(10), Q(12), K(13), A(14);

    private final int value;

    Symbol(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}