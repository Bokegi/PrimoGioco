package com.game.GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.game.GUI.NewGame.NewGameFrame;
import com.game.Manager.TilesManager;

public class MenuFrame extends JFrame{    
    
    private final JButton startButton; 
    private final JButton loadButton;  
    private final JButton quitButton;
    private final JButton editorButton;
    private final JPanel panel;

    private TilesManager tilesManager;

    public MenuFrame(){
        this.setTitle("Menu");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.panel = new JPanel();
        this.panel.setLayout(null);

        startButton = new JButton("New Game"); 
        loadButton = new JButton("Load Game"); 
        editorButton = new JButton("Editor"); 
        quitButton = new JButton("Quit");
        buildFrame();
    }
    
    private  void buildFrame() {

        this.add(panel);
        
        this.panel.add(startButton).setBounds(300, 100, 200, 50);
        this.panel.add(loadButton).setBounds(300, 200, 200, 50);
        this.panel.add(editorButton).setBounds(300, 300, 200, 50);
        this.panel.add(quitButton).setBounds(300, 400, 200, 50);

        tilesManager = new TilesManager();

        addAction();
    }

    private void addAction(){
        startButton.addActionListener(e -> {
            NewGameFrame gameFrame = new NewGameFrame();
            gameFrame.setVisible(true);
            this.dispose();
        });
        /* 
        loadButton.addActionListener(e -> {
            LoadGameFrame loadFrame = new LoadGameFrame();
            //loadFrame.setVisible(true);
            this.dispose();
        });
        */
        /* editorButton.addActionListener(e -> {
            EditorFrame editorFrame = new EditorFrame(tilesManager);
            editorFrame.setVisible(true);
            this.dispose();

        }); */
        quitButton.addActionListener(e -> {
            System.exit(0);
        });
    }
    
}
