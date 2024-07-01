package com.jakobniinja;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class BakersDozen extends JFrame {

  private static final long serialVersionUID = 1L;

  private TablePanel tablePanel = new TablePanel();

  public BakersDozen() {
    initGui();

    setTitle("Baker's Dozen");
    setResizable(false);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

  }

  private void initGui() {
    TitleLabel titleLabel = new TitleLabel("Baker's Dozen");
    add(titleLabel, BorderLayout.PAGE_START);

    // table button
    add(tablePanel, BorderLayout.CENTER);

    // table button
  }

  public static void main(String[] args) {
    String className = UIManager.getCrossPlatformLookAndFeelClassName();
    try {
      UIManager.setLookAndFeel(className);
    } catch (Exception e) {
      //
    }

    EventQueue.invokeLater(BakersDozen::new);

    // TODO: Complete listing 13-21
  }
}
