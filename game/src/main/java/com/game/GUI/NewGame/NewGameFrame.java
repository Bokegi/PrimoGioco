package com.game.GUI.NewGame;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.game.GUI.MenuFrame;
import com.game.Manager.TilesManager;
//import com.game.Manager.TowerManager;

public class NewGameFrame extends JFrame {

    private JPanel bottomPanel;
    //private TowerManager towerManager;

    private JButton exitButton, saveButton;
    private TilesManager tilesManager;

    public NewGameFrame() {
        initComponents();
    }

    private void initComponents() {
        this.setTitle("NEW GAME");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        tilesManager = new TilesManager();
        this.add(tilesManager, BorderLayout.CENTER);

        //towerManager = new TowerManager(tilesManager);
        //this.add(towerManager, BorderLayout.SOUTH);

        // Pannello inferiore per i bottoni
        bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.BLACK);

        exitButton = new JButton("Close");
        exitButton.addActionListener(e -> {
            MenuFrame menu = new MenuFrame();
            menu.setVisible(true);
            this.dispose();
        });
        bottomPanel.add(exitButton);
        
        saveButton = new  JButton("Save");
        saveButton.addActionListener(e -> {
            tilesManager.saveGame("savedGame.dat");
        });
        bottomPanel.add(saveButton);

        this.add(bottomPanel, BorderLayout.SOUTH);
    }
}
