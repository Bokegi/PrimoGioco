package com.game.Manager;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.MouseInputAdapter;

public class TowerManager extends MouseInputAdapter {
    private BufferedImage towerImage;
    private List<Tower> towers;

    public TowerManager(BufferedImage towerImage) {
        this.towerImage = towerImage;
        this.towers = new ArrayList<>();
    }

    public void addTower(Point position) {
        towers.add(new Tower(position, towerImage));
    }

    public void drawTowers(Graphics g) {
        for (Tower tower : towers) {
            if (tower.getImage() != null) {
                g.drawImage(tower.getImage(), tower.getPosition().x, tower.getPosition().y, null);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point clickPoint = e.getPoint();
        addTower(clickPoint);
    }
}

