package com.jakobniinja;

import java.awt.Color;
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
    g.setColor(Color.GREEN);
    g.fillRect(0, 0, cardWidth, cardHeight);

    for (int row = 0, y = 0; row < 4; row++, y += cardHeight) {
      for (int col = 0, x = 0; col < 13; col++, x += cardWidth) {

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

    // TODO: Complete listing 13-10
  }
}
