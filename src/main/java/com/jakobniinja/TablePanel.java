package com.jakobniinja;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class TablePanel extends JPanel {

  private static final long serialVersionUID = 1l;

  private static final int CARDWIDTH = Deck.getCardWidth();

  private static final int CARDHEIGHT = Deck.getCardHeight();

  private static final int SPACING = 4;

  private static final int MARGIN = 10;

  private static final int WIDTH = 13 * CARDWIDTH + 12 * SPACING + 2 * MARGIN;

  private static final int HEIGHT = 9 * CARDHEIGHT + 3 * MARGIN;

  private static final int FOUNDATIONX = WIDTH / 2 - (4 * CARDWIDTH + 3 * SPACING) / 2;

  private static final int FOUNDATIONY = MARGIN;

  private static final int BOARDX = MARGIN;

  private static final int BOARDY = CARDHEIGHT + MARGIN + MARGIN;

  private static final int OVERLAP = (int) (CARDHEIGHT * .65);

  private Deck deck;

  private CardStack[] foundation = new CardStack[4];

  private CardStack[] column = new CardStack[13];

  private Card card;

  public TablePanel() {
    int x = FOUNDATIONX;
    int y = FOUNDATIONY;

    for (int i = 0; i < 4; i++) {
      foundation[i] = new CardStack(x, y, 0);
      x += CARDWIDTH + SPACING;
    }

    x = BOARDX;
    y = BOARDY;

    for (int i = 0; i < 13; i++) {
      column[i] = new CardStack(x, y, OVERLAP);
      x += CARDWIDTH + SPACING;
    }

    deck = new Deck();
    deal();
  }

  public void deal() {
    // deal 4 rows of 13 columns
    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < 13; col++) {
        Card card = deck.deal();
        column[col].add(card);
      }
    }
    repaint();
  }

  public Dimension getPreferredSize() {
    Dimension size = new Dimension(WIDTH, HEIGHT);
    return size;
  }

  public void paintComponent(Graphics g) {
    // draw background
    g.setColor(Color.GREEN);
    g.fillRect(0, 0, WIDTH, HEIGHT);

    // draw foundation
    for (int i = 0; i < 4; i++) {
      if (foundation[i].size() > 0) {
        foundation[i].draw(g);
      } else {
        int x = foundation[i].getX();
        int y = foundation[i].getY();
        Card.drawOutline(g, x, y);
      }
    }

    // draw board
    for (int i = 0; i < 13; i++) {
      column[i].draw(g);
    }

    for (int row = 0; row < 4; row++) {
      for (int col = 0; col < 13; col++) {
        Card card = deck.deal();
        if (card.getValue() == 12) {
          column[col].addToBeginning(card);
        } else {
          column[col].add(card);
        }
      }
    }

    // draw moving card
  }
}
