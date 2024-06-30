package com.jakobniinja;

public class Deck {

  private static final String[] RANKS = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

  private static final String[] SUITSYMBOLS = {"\u2665", "\u2666", "\u2660", "\u2663"};

  private static final int CARDWITH = 30;

  private static final int CARDHEIGHT = 50;


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
}
