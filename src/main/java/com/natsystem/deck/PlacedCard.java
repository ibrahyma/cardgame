package com.natsystem.deck;

import com.natsystem.card.Card;
import com.natsystem.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public class PlacedCard {
    @NonNull private Card card;
    @NonNull private Player player;

    @Override
    public String toString() {
        return "Joueur " + player.getId() + " a joué : " + card;
    }
}
