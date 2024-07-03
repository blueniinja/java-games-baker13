package com.jakobniinja;

import java.awt.Graphics;
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

  public void addToBeginning(Card card) {
    card.setXY(stackX, stackY);
    cards.add(0, card);

    for (int i = 1; i < cards.size(); i++) {
      Card nextCard = cards.get(i);
      nextCard.addToXY(0, overlap);
    }
  }

  public void draw(Graphics g) {
    if (!cards.isEmpty() && overlap == 0) {
      int lastIndex = cards.size() - 1;
      Card card = cards.get(lastIndex);
      card.draw(g);
    } else {
      for (int i = 0; i < cards.size(); i++) {
        Card card = cards.get(i);
        card.draw(g);
      }
    }
  }

  public int size() {
    return cards.size();
  }

  public int getX() {
    return stackX;
  }

  public int getY() {
    return stackY;
  }

  public void clear() {
    cards.clear();
  }
}
