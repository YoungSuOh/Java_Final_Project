package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import Object.*;

public class UI {
    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn=false;
    public String message="";
    int timeCounter=0;
    public boolean gameFinish=false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    public UI(GamePanel gp){
        this.gp=gp;

        arial_40=new Font("Arial",Font.PLAIN,20);
        arial_80B=new Font("Arial",Font.BOLD,40);
        OBJ_Key key = new OBJ_Key();
        keyImage=key.image;
    }
    public void showMessage(String text){
        message=text;
        messageOn=true;
    }
    public void draw(Graphics2D g2){
        if(gameFinish==true){
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            String text;
            int textlength; int x, y;
            text="You found the treasure!";
            textlength=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x = gp.screenWidth/2-textlength/2;
            y = gp.screenHeight/2+(gp.tileSize*4);
            g2.drawString(text,x,y);

            text="You spent "+dFormat.format(playTime)+" seconds!!";
            textlength=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x = gp.screenWidth/2-textlength/2;
            y = gp.screenHeight/2-(gp.tileSize*3);
            g2.drawString(text,x,y);
            g2.setFont(arial_80B);
            g2.setColor(Color.YELLOW);
            text="Congratulations!!";
            textlength=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
            x = gp.screenWidth/2-textlength/2;
            y = gp.screenHeight/2+(gp.tileSize*3);

            g2.drawString(text,x,y);

            gp.gameThread=null;
        }
        else{
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage,gp.tileSize/2,gp.tileSize/2,gp.tileSize,gp.tileSize,null);
            g2.drawString("x "+gp.player.hashKey,74,65);

            // play time
            playTime+=(double) 1/60;
            g2.drawString("Time:"+dFormat.format(playTime),gp.tileSize*11,65);

            if(messageOn==true){
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message,gp.tileSize/2,gp.tileSize*5);
                timeCounter++;

                if(timeCounter>120){ // 2 sec in 60 fps
                    timeCounter=0; messageOn=false;
                }
            }
        }
    }
}
