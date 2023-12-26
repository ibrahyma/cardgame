package com.natsystem.deck;

import com.natsystem.card.Card;
import com.natsystem.card.Color;
import com.natsystem.card.Number;
import com.natsystem.player.Player;
import com.natsystem.util.Util;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class Deck {
    private static Deck instance = null;
    private Card[] cards = new Card[52];
    private List<PlacedCard> placedCards = new ArrayList<>();

    private Deck() {
        resetCards();
    }

    public static Deck getInstance() {
        if (instance == null) {
            instance = new Deck();
        }
        return instance;
    }

    /**
     * Compare 2 cards weight and returns the strongest.
     * @param firstCard The first card
     * @param secondCard The second card
     * @return The strongest card (or any of the two if it is a tie)
     */
    private static Card compareCards(Card firstCard, Card secondCard) {
        int firstCardWeight = firstCard.getNumber().getValue();
        int secondCardWeight = secondCard.getNumber().getValue();

        if (firstCardWeight != secondCardWeight) {
            return firstCardWeight > secondCardWeight ? firstCard : secondCard;
        }

        int random = new Random().nextInt(2);

        return random == 0 ? firstCard : secondCard;
    }

    /**
     * Withdraw all cards.
     */
    public void resetCards() {
        int cardIndex = 0;

        for (Number number : Number.class.getEnumConstants()) {
            for (Color color : Color.class.getEnumConstants()) {
                cards[cardIndex] = new Card(number, color);
                cardIndex++;
            }
        }
    }

    /**
     * Shuffle all cards of the deck.
     */
    public void shuffleCards() {
        cards = Util.shuffleArray(cards);
    }

    /**
     * Distribute all the cards, one by one, for 4 players.
     */
    public void distributeCards(Player[] players) {
        System.out.println("Distribution des cartes...");

        this.shuffleCards();

        for (int cardIndex = 0; cardIndex < this.cards.length; cardIndex++) {
            Card currentCard = this.cards[cardIndex];
            players[cardIndex % 4].takeCard(currentCard);
            cards[cardIndex] = null;
        }

        System.out.println("Cartes distribuées !\n");
    }

    /**
     * Add a card at the placed cards list.
     * @param cardToPlace The card to place
     * @param playerWhoPlace The player who place the card
     */
    public void placeCard(Card cardToPlace, Player playerWhoPlace) {
        this.placedCards.add(new PlacedCard(cardToPlace, playerWhoPlace));
        playerWhoPlace.getCards().remove(cardToPlace);
    }

    /**
     * Compare the weight of all cards and give cards to the winner.
     */
    public void confrontCards() {
        PlacedCard placedCardWinning = this.placedCards.get(0);
        Player winner = placedCardWinning.getPlayer();

        for (int placedCardIndex = 1; placedCardIndex < this.placedCards.size(); placedCardIndex++) {
            PlacedCard placedCardOutsider = this.placedCards.get(placedCardIndex);
            Player outsider = placedCardOutsider.getPlayer();

            System.out.println(
                "Joueur " + winner.getId()
                + " vs Joueur " + outsider.getId()
            );
            Card winningCard = compareCards(
                placedCardWinning.getCard(),
                placedCardOutsider.getCard()
            );

            placedCardWinning = this.getPlacedCardByCard(winningCard);
            winner = this.getPlayerByCard(winningCard);
            System.out.println("Joueur " + winner.getId() + " gagne le duel\n");
        }

        System.out.println("\nJ" + winner.getId() + " gagne les cartes suivantes :");

        for (Card card : this.getPlacedCardsList()) {
            System.out.println(card);
        }

        this.provideCardsForWinner(winner);
    }

    /**
     * Get the player who played a card.
     * @param card The played card
     * @return null if the card isn't played else the player who played the card.
     */
    private Player getPlayerByCard(Card card) {
        for (PlacedCard placedCard : this.placedCards) {
            if (placedCard.getCard().equals(card)) {
                return placedCard.getPlayer();
            }
        }

        return null;
    }

    /**
     * Get the placed cards.
     * @return The placed cards list
     */
    private List<Card> getPlacedCardsList() {
        List<Card> cardsList = new ArrayList<>();

        for (PlacedCard placedCard : this.placedCards) {
            cardsList.add(placedCard.getCard());
        }

        return cardsList;
    }

    /**
     * Give the placed cards to winner.
     * @param winner The winner of cards comparison
     */
    private void provideCardsForWinner(Player winner) {
        for (Card placedCardItem : this.getPlacedCardsList()) {
            winner.takeCard(placedCardItem);
        }

        this.placedCards.clear();
    }

    /**
     * Get a placed card association by card.
     * @param card a card
     * @return The card association or null if the card isn't placed
     */
    private PlacedCard getPlacedCardByCard(Card card) {
        for (PlacedCard placedCardItem : this.placedCards) {
            if (placedCardItem.getCard().equals(card)) {
                return placedCardItem;
            }
        }

        return null;
    }
}
