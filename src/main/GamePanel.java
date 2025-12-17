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

    Thread gameThread;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);   /* All the drawing from this component will be done in an offscreen
                                           painting buffer. This solution can improve game's performances */
    }


    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {
        while(gameThread != null) {
            System.out.println("The game loop is running!");
        }

    }
}


