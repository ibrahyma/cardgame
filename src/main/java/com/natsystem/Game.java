package com.natsystem;

import com.natsystem.deck.Deck;
import com.natsystem.deck.PlacedCard;
import com.natsystem.player.Player;
import com.natsystem.util.Util;

public class Game {
    private static Deck deck = Deck.getInstance();
    private static Player[] players = new Player[] {
        new Player(1), new Player(2), new Player(3), new Player(4)
    };

    public static void main(String[] args) {
        deck.resetCards();
        deck.distributeCards(players);

        for (int round : new int[]{1, 2, 3}) {
            Util.showPlayersCards(players);
            playRound();
        }

        Util.showPlayersCards(players);
    }


    /**
     * Each player place a card into deck, and the winner get all cards.
     */
    private static void playRound() {
        for (Player p : players) {
            p.placeCard(deck);
        }

        System.out.println();

        for (PlacedCard placedCard : deck.getPlacedCards()) {
            System.out.println(placedCard);
        }

        System.out.println("\nCONFRONTATION DES CARTES...\n");
        deck.confrontCards();
    }
}
