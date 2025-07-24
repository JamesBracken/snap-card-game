package com.snap.cardgame;


import com.snap.cardgame.service.CardGame;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        CardGame game = new CardGame();
        game.resetDeck();
        game.displayDeck();
        game.dealCard();
        game.dealCard();
        game.displayDeck();
        game.sortDeckIntoSuits();
        game.displayDeck();
    }
}