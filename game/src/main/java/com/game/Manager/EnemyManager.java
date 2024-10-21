package com.game.Manager;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class EnemyManager {
    private BufferedImage enemyImage;
    private List<Point> pathCoordinates;
    private List<Point> enemyPositions;
    private List<Integer> pathIndexes;
    private Timer enemyCreationTimer, enemyMovementTimer;

    private final int enemyCreationInterval = 2000; // Millisecondi
    private final int enemyMovementInterval = 500; // Millisecondi
    private final String enemyPath = "game/res/Enemy/Zombie.png";

    public EnemyManager(List<Point> pathCoordinates) {
        this.pathCoordinates = pathCoordinates;
        this.enemyPositions = new ArrayList<>();
        this.pathIndexes = new ArrayList<>();
        loadEnemyImage();
        initEnemies();
        System.out.println("EnemyManager initialized."); // Debug
    }

    private void loadEnemyImage() {
        try {
            enemyImage = ImageIO.read(new File(enemyPath));
        } catch (IOException e) {
            // Imposta un'immagine di default se non riesce a caricare
            enemyImage = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB); 
        }
    }

    private void initEnemies() {
        enemyCreationTimer = new Timer(enemyCreationInterval, (ActionEvent e) -> createEnemy());
        enemyMovementTimer = new Timer(enemyMovementInterval, (ActionEvent e) -> moveEnemies());
        enemyCreationTimer.start(); // Assicurati che il timer sia avviato
        enemyMovementTimer.start();  // Assicurati che il timer sia avviato
        System.out.println("Enemy timers started."); // Debug
    }

    private void createEnemy() { 
        if (!pathCoordinates.isEmpty()) {
            enemyPositions.add(new Point(pathCoordinates.get(0)));
            pathIndexes.add(0);
            System.out.println("Enemy created at: " + enemyPositions.get(enemyPositions.size() - 1)); // Debug
        }
    }
    
    private void moveEnemies() {
        for (int i = 0; i < enemyPositions.size(); i++) {
            int currentIndex = pathIndexes.get(i);
            if (currentIndex < pathCoordinates.size() - 1) {
                enemyPositions.set(i, pathCoordinates.get(currentIndex + 1));
                pathIndexes.set(i, currentIndex + 1);
                System.out.println("Enemy moved to: " + enemyPositions.get(i)); // Debug
            } else {
                enemyPositions.remove(i);
                pathIndexes.remove(i);
                i--;
            }
        }
    }

    public void drawEnemies(Graphics g) {
        System.out.println("Drawing enemies..."); // Debug
        for (Point enemyPosition : enemyPositions) {
            if (enemyImage != null) {
                g.drawImage(enemyImage, enemyPosition.x, enemyPosition.y, null);
                System.out.println("Drawing enemy at: " + enemyPosition); // Debug
            }
        }
    }
}