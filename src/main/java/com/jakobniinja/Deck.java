package com.jakobniinja;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Deck {

  private static final String[] RANKS = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

  private static final String[] SUITSYMBOLS = {"\u2665", "\u2666", "\u2660", "\u2663"};

  private static final int[] values = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

  private static final int CARDWITH = 30;

  private static final int CARDHEIGHT = 50;

  private static final String FILENAME = "/cards.png";

  private ArrayList<Card> cards = new ArrayList<>();

  public Deck() {
    Random random = new Random();
    try {
      InputStream input = getClass().getResourceAsStream(FILENAME);
      BufferedImage cardImage = ImageIO.read(input);

      for (int suit = 0; suit < SUITSYMBOLS.length; suit++) {
        for (int rank = 0; rank < RANKS.length; rank++) {
          int pos = 0;
          if (!cards.isEmpty()) {
            pos = random.nextInt(cards.size() + 1);
          }

          int x = rank * CARDWITH;
          int y = suit * CARDHEIGHT;
          Image image = cardImage.getSubimage(x, y, CARDWITH, CARDHEIGHT);

          Card card = new Card(RANKS[rank], suit, values[suit], image);
          cards.add(pos, card);
        }
      }
    } catch (IOException e) {
      String message = "Could not open image file " + FILENAME;
      JOptionPane.showMessageDialog(null, message);
    }
  }

  public static String[] getRanks() {
    return RANKS;
  }

  public static String[] getSuitSymbols() {
    return SUITSYMBOLS;
  }

  public static int getCardHeight() {
    return CARDHEIGHT;
  }


  public static int getCardWidth() {
    return CARDWITH;
  }

  public Card deal() {
    Card card = cards.remove(0);
    return card;
  }

  public int size() {
    return cards.size();
  }
}
