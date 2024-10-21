package com.game.Manager;

import java.awt.Point;
import java.awt.image.BufferedImage;

public class Tower {
    private Point position;
    private BufferedImage image;

    public Tower(Point position, BufferedImage image) {
        this.position = position;
        this.image = image;
    }

    public Point getPosition() {
        return position;
    }

    public BufferedImage getImage() {
        return image;
    }
}
