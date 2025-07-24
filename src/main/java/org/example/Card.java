package org.example;

//Has a String suit. Use the unicode characters of heart, club, diamond and spade.
//- +2660 (Spade)
//- +2663 (Club)
//- +2665 (Heart)
//- +2666 (Diamond)
//Has a String symbol (2,3,4,5,6,7,8,9,10,J,Q,K,A) FINISHED
//Has an int value (2,3,4,5,6,7,8,9,10,11,12,13,14) FINISHED
//- Each symbol is created as a part of an Enum FINISHED
//- Each symbol has a string and int property of itself FINISHED
//- Create a card constructor which will initialise the fields of suit and symbol FINISHED
//- Create an array to push all cards to upon creation
//- Create a for loop which will create all cards using both symbol and suit enums
//Has a toString method that describes the class
//- Create a toString method that will display each card class???

public class Card {

    enum Suit {
        SPADE('\u2660'), CLUB('\u2663'), HEART('\u2665'), DIAMOND('\u2666');

        private final char value;
        Suit(char suit) {
            this.value = suit;
        }
    }

    enum Symbol {
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

    String symbolStr;
    char suit;
    int symbolInt;

    public Card(Suit suit, Symbol symbol) {
        this.symbolInt = symbol.value;
//      This takes the enum given name and assigns it as the string
        this.symbolStr = symbol.name();
        this.suit = suit.value;
    }

    @Override
    public String toString() {
        return symbolStr + " of " + suit;
    }


}
