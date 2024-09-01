package com.Snakes.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * @author PlagueWZK
 * description: MainPanel
 * date: 2024/8/27 19:28
 */

public class MainPanel extends JPanel implements KeyListener, ActionListener {

    public static boolean gaming;
    public static boolean gameOver;
    public static Random rand;
    public Snake snake;
    public Food food;
    public Timer timer = new Timer(100, this);

    public MainPanel() {
       init();
    }

    public void init() {
        timer.start();

        this.setBackground(Color.WHITE);
        snake = new Snake();

        rand = new Random();
        gaming = true;
        gameOver = false;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(25, 75, 850, 600);

        pString(25,25,"Length : " + snake.getLength(), Color.CYAN, 25, g);
        pString(25,50,"Score : " + (snake.getLength() - 3) * 5, Color.PINK, 25, g);
        for (int i = snake.getLength() - 1; i > 0; i--) {
            Data.body.paintIcon(this, g, snake.getBodyOfX(i), snake.getBodyOfY(i));
        }

        if (food != null) Data.food.paintIcon(this, g, food.getX(), food.getY());

        switch (snake.getDirection()) {
            case 0 -> Data.headOfUp.paintIcon(this, g, snake.getX(), snake.getY());
            case 1 -> Data.headOfRight.paintIcon(this, g, snake.getX(), snake.getY());
            case 2 -> Data.headOfDown.paintIcon(this, g, snake.getX(), snake.getY());
            case 3 -> Data.headOfLeft.paintIcon(this, g, snake.getX(), snake.getY());
        }
        if (!gaming) pString(350, 300, "PAUSE", Color.WHITE, 50, g);
        if (gameOver) pString(300, 300, "GAME OVER", Color.RED, 50, g);
    }


    public static int randomInt(int min, int max, int scale) {
        if (scale <= 0) {
            throw new IllegalArgumentException("Scale must be greater than 0.");
        }
        // 确保min和max是scale的倍数
        min = (min / scale) * scale;
        max = (max / scale) * scale;
        if (max < min) {
            throw new IllegalArgumentException("max must be greater than or equal to min and both must be multiples of scale.");
        }
        // 生成随机数
        int range = (max - min) / scale + 1; // 计算scale倍数后的范围大小
        int randomIndex = rand.nextInt(range); // 生成索引

        // 返回scale的倍数
        return min + randomIndex * scale;
    }

    public void pString(int x,int y ,String str,Color c,int size,Graphics g) {
        g.setColor(c);
        g.setFont(new Font("微软雅黑", Font.BOLD, size));
        g.drawString(str, x, y);
    }

    public boolean isCollision() {
        for (int i = snake.getLength() - 1; i > 0; i--) {
            if (snake.getBodyOfX(i) == snake.getX() && snake.getBodyOfY(i) == snake.getY()) return true;
        }
        return false;
    }

    public void spawnFood() {
        food = new Food(randomInt(25, 850, 25), randomInt(75, 650, 25));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gaming && !gameOver) {
            if (isCollision()) {
                gameOver();
                return;
            }
            if (food == null) {
                spawnFood();
            }
            if (snake.getX() == food.getX() && snake.getY() == food.getY()) {
                snake.length++;
                spawnFood();
            }
            snake.moveBody();
            snake.move();
            repaint();
        }

    }

    private void gameOver() {
        gameOver = true;
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gaming) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W -> {
                    if (snake.getDirection() == 2) return;
                    snake.setDirection(0);
                    return;
                }
                case KeyEvent.VK_D -> {
                    if (snake.getDirection() == 3) return;
                    snake.setDirection(1);
                    return;
                }
                case KeyEvent.VK_S -> {
                    if (snake.getDirection() == 0) return;
                    snake.setDirection(2);
                    return;
                }
                case KeyEvent.VK_A -> {
                    if (snake.getDirection() == 1) return;
                    snake.setDirection(3);
                    return;
                }
                case KeyEvent.VK_R -> {
                    init();
                }
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !gameOver) {
            gaming = !gaming;
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
