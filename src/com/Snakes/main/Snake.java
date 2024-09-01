package com.Snakes.main;

import javax.swing.*;
import java.util.Objects;

/**
 * @author PlagueWZK
 * description: Snake
 * date: 2024/8/31 15:38
 */

public class Snake {

    protected int[] x;
    protected int[] y;
    protected int length;
    protected int direction;
    private static final int THE_MAX_LENGTH = Main.BoundOfY * Main.BoundOfX / 25 / 25;

    public Snake() {
        x = new int[THE_MAX_LENGTH];
        y = new int[THE_MAX_LENGTH];
        length = 3;
        //0：上 1：右 2：下 3：左
        direction = 1;
        x[0] = 100;
        y[0] = 100;
        x[1] = 75;
        y[1] = 100;
        x[2] = 50;
        y[2] = 100;
    }

    public void move() {
        switch (direction) {
            case 0 -> {
                y[0] -= 25;
                if (y[0] < 75) y[0] = 650;
            }
            case 1 -> {
                x[0] += 25;
                if (x[0] > 850) x[0] = 25;
            }
            case 2 -> {
                y[0] += 25;
                if (y[0] > 650) y[0] = 75;
            }
            case 3 -> {
                x[0] -= 25;
                if (x[0] < 25) x[0] = 850;
            }
        }
    }

    public void moveBody() {
        for (int i = length - 1; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getX() {
        return x[0];
    }

    public int getY() {
        return y[0];
    }

    public int getLength() {
        return length;
    }

    public int getBodyOfX(int i) {
        return x[i];
    }

    public int getBodyOfY(int i) {
        return y[i];
    }


    public int getDirection() {
        return direction;
    }
}
