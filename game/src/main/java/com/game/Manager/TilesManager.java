package com.game.Manager;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.game.GUI.GameData.GameData;

public class TilesManager extends JPanel {
    private final String backgroundPath = "game/res/Background/Grass.png";
    private final String roadPath = "game/res/Background/Road.png";
    private final String verticalRoadPath = "game/res/Background/VerticalRoad.png";
    private final String aRoadRBPath = "game/res/Background/ARoadRB.png";
    private final String aRoadLBPath = "game/res/Background/ARoadLB.png";
    private final String aRoadLTPath = "game/res/Background/ARoadLT.png";
    private final String aRoadTRPath = "game/res/Background/ARoadTR.png";
    private final String waterPath = "game/res/Background/Water.png";

    private final String enemyPath = "game/res/Enemy/Zombie.png";
    private final String towerPath = "game/res/Tower/Archer.png";

    private BufferedImage backgroundImage, waterImage;
    private BufferedImage aRoadLBImage , aRoadRBImage, aRoadLTImage, aRoadTRImage;
    private BufferedImage roadImage, verticalRoad;
    private BufferedImage enemyImage, towerImage;

    private Map<Point, String> spritePath; 
    private Map<Point, BufferedImage> towerMap;
    private List<TowerManager> towers;

    private int[][] tileMap;
    private List<Point> pathCoordinates;
    private Point enemyPosition;

    private TowerManager towerManager;
    private EnemyManager enemyManager;

    public TilesManager() {
        loadImages();
        initializeMap();
        enemyManager = new EnemyManager(enemyImage, pathCoordinates);
        towerManager = new TowerManager(towerImage);
    }
    
    public void saveGame(String filePath){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))){
            oos.writeObject(new GameData(spritePath));
            System.out.println("Game saved successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadImages() {
        try {
            backgroundImage = ImageIO.read(new File(backgroundPath));
            roadImage = ImageIO.read(new File(roadPath));
            verticalRoad = ImageIO.read(new File(verticalRoadPath));
            aRoadRBImage = ImageIO.read(new File(aRoadRBPath));
            aRoadLBImage = ImageIO.read(new File(aRoadLBPath));
            aRoadLTImage = ImageIO.read(new File(aRoadLTPath));
            aRoadTRImage = ImageIO.read(new File(aRoadTRPath));
            waterImage = ImageIO.read(new File(waterPath));
            enemyImage = ImageIO.read(new File(enemyPath));
            towerImage = ImageIO.read(new File(towerPath));
        } catch (IOException e) {
            System.out.println("Cannot load MAP image: " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (tileMap != null) {
            int tileSize = backgroundImage.getWidth(); // Assuming all tiles are the same size
            for (int i = 0; i < tileMap.length; i++) {
                for (int j = 0; j < tileMap[i].length; j++) {
                    BufferedImage tileImage = getImageForTile(tileMap[i][j]);
                    g.drawImage(tileImage, j * tileSize, i * tileSize, this);
                }
            }
        }
        if (enemyImage != null && enemyPosition != null) {
            g.drawImage(enemyImage, enemyPosition.x, enemyPosition.y, this);
        }
    }
    
    private BufferedImage getImageForTile(int tileType) {
        return switch (tileType) {
            case 1 -> roadImage;
            case 2 -> aRoadRBImage;
            case 3 -> waterImage;
            case 4 -> verticalRoad;
            case 5 -> aRoadLBImage;
            case 6 -> aRoadLTImage;
            case 7 -> aRoadTRImage;
            default -> backgroundImage; // 0 or any other number
        };
    }
    private void initializeMap() {
        //(0: background, 1: road, 2: aRoad, 3: water, 4: verticalRoad, 5: LBRoad, 6: LTRoad, 7: TRROAD)
        tileMap = new int[][] {
            {3, 3, 3, 3, 3, 3, 3},
            {1, 1, 5, 0, 0, 0, 0},
            {0, 0, 4, 0, 0, 0, 0},
            {0, 0, 4, 0, 0, 0, 0},
            {0, 0, 4, 0, 0, 0, 0},
            {0, 0, 7, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 4}
        };

        pathCoordinates = new ArrayList<>();
        // Popola la lista delle coordinate del percorso, escludendo 0 e 3
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[i].length; j++) {
                if (tileMap[i][j] != 0 && tileMap[i][j] != 3) {
                  pathCoordinates.add(new Point(j * backgroundImage.getWidth(), i * backgroundImage.getHeight()));
                }
            }
        }
    }

    /* private void initEnemy() {
        if (pathCoordinates.isEmpty()) {
            enemyPosition = new Point(0, 0);
        } else {
            enemyPosition = pathCoordinates.get(0);
        }
        Timer timer = new Timer(500, (ActionEvent e) -> {
            moveEnemy();
        });
        timer.start();
    }

    private void moveEnemy() {
        int currentIndex = pathCoordinates.indexOf(enemyPosition);
        if (currentIndex < pathCoordinates.size() - 1) {
            enemyPosition = pathCoordinates.get(currentIndex + 1);
        } else {
            enemyPosition = pathCoordinates.get(0); // Ricomincia
        }
        repaint();
    } */
}