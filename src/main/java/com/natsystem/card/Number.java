package com.natsystem.card;

import lombok.Getter;

public enum Number {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    QUEEN(12),
    KING(13),
    ACE(14);

    @Getter
    final int value;

    final String name;

    Number(int value) {
        this.value = value;

        switch (value) {
            case 11:
                this.name = "Valet";
                break;
            case 12:
                this.name = "Dame";
                break;
            case 13:
                this.name = "Roi";
                break;
            case 14:
                this.name = "As";
                break;
            default:
                this.name = "" + value;
                break;
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}
