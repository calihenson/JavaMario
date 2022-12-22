// Name: Caroline Henson
// Date: October 31, 2022
// Assignment: #5

import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Fireball extends Sprite
{
    static BufferedImage image;
    double vertVelocity;
    int numFramesInAirF = 0;
    int initial;

    public Fireball(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.w = 47;
        this.h = 47;
        if(image == null)
            image = View.loadImage("fireball.png");
    }

    void update()
    {
        vertVelocity += 2;
        y += vertVelocity;

        if((y > 400-h) || (y < initial))
		{
            vertVelocity *= -1;
			y = 400-h; // snap back to the ground
		}

        x += 6;
    }

    public void drawYourself(Graphics g, int scrollPos)
    {
        g.drawImage(image, x-scrollPos, y, w, h, null);
    }

    void setPreviousPosition()
    {
    }

    void setInitial()
    {
        initial = y;
    }

    boolean ground()
    {
        if(vertVelocity == 0.0)
            return true;

        else
            return false;
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
        return false;
    }

    boolean isFireball()
    {
        return true;
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
    	return "Fireball (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h + "\n";
    }
}