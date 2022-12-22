// Name: Caroline Henson
// Date: October 31, 2022
// Assignment: #5

import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class FireGoomba extends Sprite
{
    static BufferedImage image;

    public FireGoomba(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.w = 37;
        this.h = 45;
        if(image == null)
            image = View.loadImage("goomba_fire.png");
    }

    public void drawYourself(Graphics g, int scrollPos)
    {
        g.drawImage(image, x-scrollPos, y, w, h, null);
    }

    void update()
    {
    }

    void setPreviousPosition()
    {
    }

    void getOutOfPipe(Sprite p)
    {   }
    
    boolean isMario()
    {
        return false;
    }

    boolean isPipe()
    {
        return false;
    }

    boolean isGoomba()
    {
        return false;
    }

    boolean isChaos()
    {
        return true;
    }

    boolean isFireball()
    {
        return false;
    }

    boolean isCollisionL()
    {
        return false;
    }

    boolean isCollisionR()
    {
        return false;
    }

    @Override 
    public String toString()
    {
    	return "Goomba (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h + "\n";
    }
}