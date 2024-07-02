package com.jakobniinja;

import java.util.ArrayList;

public class CardStack {

  ArrayList<Card> cards = new ArrayList<>();

  int stackX = 0;

  int stackY = 0;

  int overlap = 0;

  public CardStack(int stackX, int stackY, int overlap) {
    this.stackX = stackX;
    this.stackY = stackY;
    this.overlap = overlap;
  }

  public void add(Card card) {
    int cardx = stackX;
    int cardy = stackY + overlap * cards.size();
    card.setXY(cardx, cardy);
    cards.add(card);
  }
}
