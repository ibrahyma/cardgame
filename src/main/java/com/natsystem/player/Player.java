package com.natsystem.player;

import com.natsystem.card.Card;
import com.natsystem.deck.Deck;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class Player {
    @NonNull
    @Setter
    private Integer id;

    final List<Card> cards = new ArrayList<>();

    /**
     * The player take a card
     * @param card The card the player take
     */
    public void takeCard(Card card) {
        cards.add(card);
    }

    /**
     * The player give all his cards.
     */
    public void giveAllCards() {
        cards.clear();
    }

    /**
     * Plays the card that will be in opposition to the other players' cards
     * @param deck the deck where the card will be placed
     */
    public void placeCard(Deck deck) {
        Card lastCard = this.cards.get(this.cards.size() - 1);
        deck.placeCard(lastCard, this);
    }
}
