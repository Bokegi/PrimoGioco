package com.game.Manager;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

public class EnemyManager {
    private BufferedImage enemyImage;
    private List<Point> pathCoordinates;
    private List<Point> enemyPositions;
    private Timer enemyCreationTimer, enemyMovementTimer;

    public EnemyManager(BufferedImage enemyImage, List<Point> pathCoordinates) {
        this.enemyImage = enemyImage;
        this.pathCoordinates = pathCoordinates;
        this.enemyPositions = new ArrayList<>();
        initEnemies();
    }

    private void initEnemies() {
        enemyCreationTimer = new Timer(2000, (ActionEvent e) -> {
            createEnemy();
        });
        enemyMovementTimer = new Timer(500, (ActionEvent e) -> {
            moveEnemies();
        });
        enemyCreationTimer.start();
        enemyMovementTimer.start();
    }

    private void createEnemy() {
        if (!pathCoordinates.isEmpty()) {
            enemyPositions.add(new Point(pathCoordinates.get(0)));
        }
    }

    private void moveEnemies() {
        for (int i = 0; i < enemyPositions.size(); i++) {
            Point enemyPosition = enemyPositions.get(i);
            int currentIndex = pathCoordinates.indexOf(enemyPosition);
            if (currentIndex < pathCoordinates.size() - 1) {
                enemyPositions.set(i, pathCoordinates.get(currentIndex + 1));
            } else {
                enemyPositions.remove(i);
                i--;
            }
        }
    }

    public void drawEnemies(Graphics g) {
        for (Point enemyPosition : enemyPositions) {
            if (enemyImage != null) {
                g.drawImage(enemyImage, enemyPosition.x, enemyPosition.y, null);
            }
        }
    }
}
