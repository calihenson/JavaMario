// Name: Caroline Henson
// Date: October 31, 2022
// Assignment: #5

import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public abstract class Sprite
{
    int x, y, h, w;
    boolean FireGoomba;

    abstract void drawYourself(Graphics g, int scrollPos);
    abstract void update();
    abstract void setPreviousPosition();
    abstract void getOutOfPipe(Sprite p);

    abstract boolean isMario();
    abstract boolean isPipe();
    abstract boolean isGoomba();
    abstract boolean isChaos();
    abstract boolean isFireball();
    abstract boolean isCollisionL();
    abstract boolean isCollisionR();

    @Override 
    public String toString()
    {
    	return "Sprite (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h + "\n";
    }
}