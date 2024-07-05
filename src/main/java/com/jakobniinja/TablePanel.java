package com.jakobniinja;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JOptionPane;
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

  private Card movingCard;

  private int mouseX = 0;

  private int mouseY = 0;

  private int fromCol = 0;

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

    newGame();

    // mouse listeners
    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        clicked(x, y);
      }

      @Override
      public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        released(x, y);
      }
    });

    addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        dragged(x, y);
      }
    });
  }

  private void released(int x, int y) {
    if (movingCard != null) {
      boolean validMove = false;

      // play on foundation?
      for (int i = 0; i < 4 && !validMove; i++) {
        int foundationX = foundation[i].getX();
        int foundationY = foundation[i].getY();

        if (movingCard.isNear(foundationX, foundationY)) {
          // empty foundation?
          if (foundation[i].size() == 0) {
            if (movingCard.getValue() == 0) {
              validMove = true;
              foundation[i].add(movingCard);
              movingCard = null;
            }
          }

          // otherwise, valid suit and rank?
          else {
            Card topCard = foundation[i].getLast();
            if (movingCard.getSuit() == topCard.getSuit() && movingCard.getValue() == topCard.getValue() + 1) {
              validMove = true;
              foundation[i].add(movingCard);
              movingCard = null;
              isGameOver();
            }
          }
        }
      }
      // play on a column?
      for (int i = 0; i < 13 && !validMove; i++) {
        if (column[i].size() > 0) {
          Card card = column[i].getLast();

          if (movingCard.isNear(card) && movingCard.getValue() == card.getValue() - 1) {
            validMove = true;
            column[i].add(movingCard);
            movingCard = null;
          }
        }
      }

      // otherwise, return to column
      if (!validMove) {
        column[fromCol].add(movingCard);
        movingCard = null;
      }

      repaint();
    }
  }

  private void isGameOver() {
    boolean gameOver = true;
    for (int i = 0; i < 4 && gameOver; i++) {
      if (foundation[i].size() != 13) {
        gameOver = false;
      }
    }

    if (gameOver) {
      String message = "You won! Do you want to play again?";
      int option = JOptionPane.showConfirmDialog(this, message);
      if (option == JOptionPane.YES_OPTION) {
        newGame();
      } else {
        System.exit(0);
      }

    }
  }

  private void newGame() {
    deck = new Deck();
    deal();
  }

  private void dragged(int x, int y) {
    if (movingCard != null) {
      int changeX = x - mouseX;
      int changeY = x - mouseY;
      movingCard.addToXY(changeX, changeY);
      mouseX = x;
      mouseY = y;
      repaint();
    }
  }


  private void clicked(int x, int y) {
    movingCard = null;
    for (int col = 0; col < 13 && movingCard == null; col++) {

      if (column[col].size() > 0) {
        Card card = column[col].getLast();
        if (card.contains(x, y)) {
          movingCard = card;
          mouseX = x;
          mouseY = y;
          column[col].removeLast();
          fromCol = col;
        }
      }
    }
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
    if (movingCard != null) {
      movingCard.draw(g);
    }
  }
}
