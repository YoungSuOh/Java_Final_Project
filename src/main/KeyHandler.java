package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener { // key handle when you press wasd
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    @Override
    public void keyTyped(KeyEvent e) { // don't use
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code==KeyEvent.VK_W){ // when you press W key
            upPressed = true;
        }
        if(code==KeyEvent.VK_S){ // when you press S key
            downPressed=true;
        }
        if(code==KeyEvent.VK_A){ // when you press A key
            leftPressed=true;
        }
        if(code==KeyEvent.VK_D){ // when you press D key
            rightPressed=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code==KeyEvent.VK_W){ // when you press W key
            upPressed = false;
        }
        if(code==KeyEvent.VK_S){ // when you press W key
            downPressed=false;
        }
        if(code==KeyEvent.VK_A){ // when you press W key
            leftPressed=false;
        }
        if(code==KeyEvent.VK_D){ // when you press W key
            rightPressed=false;
        }
    }
}
