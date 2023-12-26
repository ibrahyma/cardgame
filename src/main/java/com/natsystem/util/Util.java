package com.natsystem.util;

import com.natsystem.player.Player;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Util {
    public static final int CONSOLE_SIZE = 30;

    /**
     * Shuffle the array indexes.
     * @param array The array to shuffle
     * @return The shuffled array
     */
    public static <T> T[] shuffleArray(T[] array) {
        List<T> list = Arrays.asList(array);
        Collections.shuffle(list);
        return list.toArray(array);
    }

    public static void showPlayersCards(Player[] players) {
        int maxPlayerCardsCount = Math.max(players[0].getCards().size(), players[1].getCards().size());
        maxPlayerCardsCount = Math.max(maxPlayerCardsCount, players[2].getCards().size());
        maxPlayerCardsCount = Math.max(maxPlayerCardsCount, players[3].getCards().size());

        System.out.println(StringUtils.center("CARTES DES JOUEURS\n", CONSOLE_SIZE * 4));

        for (Player player : players) {
            System.out.print(StringUtils.center("Joueur " + player.getId(), CONSOLE_SIZE));
        }

        System.out.println("\n");

        for (int cardIndex = 0; cardIndex < maxPlayerCardsCount; cardIndex++) {
            for (Player player : players) {
                System.out.print(
                    player.getCards().size() > cardIndex ?
                        StringUtils.center(player.getCards().get(cardIndex).toString(), CONSOLE_SIZE)
                        : ""
                );
            }
            System.out.println();
        }
    }
}
