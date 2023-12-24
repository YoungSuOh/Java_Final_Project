package main;

import entity.Player;
import tile.TileManager;
import Object.SuperObject;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{ // implements Runnable to use Thread
    // screen settings
    final int originalTileSize=16;
    final int scale = 3;
    public final int tileSize= originalTileSize*scale;  // 48x48 tile
    public final int maxScreenCol=16;
    public final int maxScreenRow=12;
    public final int screenWidth = tileSize*maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize*maxScreenRow; // 576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    int FPS = 60; // create restriction 60fps

    KeyHandler keyH = new KeyHandler();
    // Thread -> something you can start and stop
    Thread gameThread;
    public Player player = new Player(this,keyH); // create Player instance -> entity.Player
    TileManager tileM = new TileManager(this);
    public CollisionChecker cChecker = new CollisionChecker(this);

    public SuperObject obj[] =new SuperObject[10];
    public AssetSetter aSetter = new AssetSetter(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public UI ui = new UI(this);
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH); // recognize key input -> main.KeyHandler Class
        this.setFocusable(true);
    }

    public void setupGame(){

        aSetter.setObject();
        playMusic(0);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start(); // starting Thread
    }

    @Override
    public void run() { // create game loop -> core of game programming
        double drawInterval = 1000000000/FPS; // => 60 times per sec
        double nextDrawTime = System.nanoTime() + drawInterval;
        while(gameThread!=null){
            long currentTime = System.nanoTime();
            update();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if(remainingTime<0){
                    remainingTime=0;
                }
                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        // Tile
        tileM.draw(g2); // tile appear first -> if not character will hide the tiles
        // Object
        for(int i=0;i<obj.length;i++){
            if(obj[i]!=null){
                obj[i].draw(g2,this);
            }
        }
        // Player
        player.draw(g2);

        // UI
        ui.draw(g2);
        g2.dispose();
    }
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSoundEffect(int i){
        se.setFile(i);
        se.play();
    }
}
