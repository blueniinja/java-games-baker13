package com.jakobniinja;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class DrawDeckImage {

  public static void main(String[] args) {
    String[] suits = Deck.getSuitSymbols();

    String[] ranks = Deck.getRanks();

    int cardWidth = Deck.getCardWidth();

    int cardHeight = Deck.getCardHeight();

    int imageWidth = 13 * cardWidth;
    int imageHeight = 4 * cardHeight;
    BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

    Graphics g = image.getGraphics();
    g.setColor(new Color(0, 0, 0, 0));
    g.fillRect(0, 0, cardWidth, cardHeight);

    Font font = new Font(Font.DIALOG, Font.BOLD, 24);
    g.setFont(font);
    FontMetrics fm = g.getFontMetrics();

    for (int row = 0, y = 0; row < 4; row++, y += cardHeight) {
      for (int col = 0, x = 0; col < 13; col++, x += cardWidth) {
        g.setColor(Color.WHITE);
        g.fillRoundRect(x, y, cardWidth - 1, cardHeight - 1, 8, 8);
        g.setColor(Color.BLACK);
        g.drawRoundRect(x, y, cardWidth - 1, cardHeight - 1, 8, 8);

        if (row < 2) {
          g.setColor(Color.RED);
        }
        String rank = ranks[col];
        int rankWidth = fm.stringWidth(rank);
        int fromLeft = x + cardWidth / 2 - rankWidth / 2;
        int fromTop = y + 20;
        g.drawString(rank, fromLeft, fromTop);

        String suit = ranks[row];
        int suitWidth = fm.stringWidth(suit);
        fromLeft = x + cardWidth / 2 - suitWidth / 2;
        fromTop = y + 45;
        g.drawString(suit, fromLeft, fromTop);
      }
    }

    String filename = "cards.png";
    File file = new File(filename);

    try {
      ImageIO.write(image, "png", file);
    } catch (IOException e) {
      String message = "Could not save " + filename;
      JOptionPane.showMessageDialog(null, message);
    }
  }
}
