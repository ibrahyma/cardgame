package com.natsystem.card;

public enum Color {
    SPADE("pique"),
    HEART("coeur"),
    DIAMOND("carreau"),
    CLOVER("trèfle");

    final String value;

    Color(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
