package com.jakobniinja;

import java.awt.Dimension;
import javax.swing.JPanel;

public class TablePanel extends JPanel {

  private static final long serialVersionUID = 1l;

  private static final int CARDWIDTH = Deck.getCardWidth();

  private static final int CARDHEIGHT = Deck.getCardHeight();

  private static final int SPACING = 4;

  private static final int MARGIN = 10;

  private static final int WIDTH = 13 * CARDWIDTH + 12 * SPACING + 2 * MARGIN;

  private static final int HEIGHT = 9 * CARDHEIGHT + 3 * MARGIN;


  public Dimension getPreferredSize() {
    Dimension size = new Dimension(WIDTH, HEIGHT);
    return size;
  }
}
