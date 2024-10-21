/* package com.game.GUI.Editor;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.game.GUI.MenuFrame;
import com.game.Manager.TilesManager;

public class EditorFrame extends JFrame{

    private TilesManager tilesManager;
    private JComboBox<String> spriteSelector;
    private JButton exitButton;

    public EditorFrame(TilesManager tilesManager){
        initComponents();
    }

    private void initComponents() {
        this.tilesManager = tilesManager;
        this.setTitle("Editor");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(1, 2));

        spriteSelector = new JComboBox<>(new String[]{"Road", "Angular ROad", "Water", "Grass"});
        this.add(new JLabel("select Sprite:"));
        this.add(spriteSelector);

        exitButton = new JButton("exit");
        exitButton.addActionListener(e -> {
            MenuFrame menu = new MenuFrame();
            menu.setVisible(true);
            this.dispose();
        });

        this.add(exitButton);

        spriteSelector.addActionListener(e ->{
            String selectedSprite = (String) spriteSelector.getSelectedItem();
            tilesManager.setSelectedSprite(selectedSprite);
        });
        
        this.setVisible(true);
    }
} */