package com.natsystem.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Card {
    @NonNull private Number number;
    @NonNull private Color color;

    public String toString() {
        return number + " de " + color;
    }
}
