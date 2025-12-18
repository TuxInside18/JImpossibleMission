package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 32; // Standard sprites resolution (32x32)
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 128x128 tile
    final int maxScreenColumns = 16;
    final int maxScreenRows = 12;
    final int screenWidth = tileSize * maxScreenColumns; // 2048 pixels
    final int screenHeight = tileSize * maxScreenRows; // 1536 pixels

    // FPS
    int FPS = 60;


    KeyHandler keyH = new KeyHandler();
    Thread gameThread;


    // SET PLAYER'S DEFAULT POSITION
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);   /* All the drawing from this component will be done in an offscreen
                                           painting buffer. This solution can improve game's performances */
        this.addKeyListener(keyH);
        this.setFocusable(true);        // this GamePanel can be "focused" to receive key input
    }


    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {
        // DELTA METHOD
        double drawInterval = 1000000000.0/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {
            currentTime = System.nanoTime(); // returns the current value of JVM's high resolution time source, in nanoseconds

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1) {
                // 1 UPDATE: update information such as character positions
                update();

                // 2 DRAW: draw the screen with the updated information
                repaint();

                delta--;
            }
        }

    }

    public void update() {
        if(keyH.upPressed) {playerY -= playerSpeed;}
        else if(keyH.downPressed) {playerY += playerSpeed;}
        else if(keyH.leftPressed) {playerX -= playerSpeed;}
        else if(keyH.rightPressed) {playerX += playerSpeed;}
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; // More complex graphics management of the previous Graphics g

        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);// Will draw a rectangle on the screen
        g2.dispose(); // Dispose of this graphics context and release any system resources that it is using
    }

}


