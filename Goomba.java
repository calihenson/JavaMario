// Name: Caroline Henson
// Date: October 31, 2022
// Assignment: #5

import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Goomba extends Sprite
{
    static BufferedImage image;
    double vertVelocity;
    int px, py;
    boolean collisionL = false;
    boolean collisionR = false;
    boolean start = true;

    public Goomba(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.w = 37;
        this.h = 45;
        if(image == null)
            image = View.loadImage("goomba.png");
    }

    public Goomba(Json ob)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        w = 37;
        h = 45;
        if(image == null)
            image = View.loadImage("goomba.png");
    }

    Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        return ob;
    }

    public void drawYourself(Graphics g, int scrollPos)
    {
        // g.drawImage(image, x, y, w, h, null);
        g.drawImage(image, x-scrollPos, y, w, h, null);
    }

    public void getOutOfPipe(Sprite p)
    {
        if(((x + w) < p.x + p.w) && (px + w <= p.x))
        {
            // System.out.println("Left side collision");
            x = p.x - w;

            collisionL = true;
            collisionR = false;
        }

        if((x > p.x) && (px >= p.x + p.w))
        {
            // System.out.println("Right side collision");
            x = p.x + p.w;

            collisionL = false;
            collisionR = true;
        }

        if((y + h < p.y + p.h) && (py + h <= p.y))
        {
            //System.out.println("Top collision");
            y = p.y - h;
            vertVelocity = 0.0;
        }

        if((y > p.y) && (py >= p.y + p.h))
        {
            //System.out.println("Bottom collision");
            y = p.y + p.h;
            vertVelocity = 0.0;
        }
    }

    void setPreviousPosition()
    {
        px = x;
        py = y;
    }

    void update()
    {
        vertVelocity += 2;
        y += vertVelocity;

        if(y > 400-h)
		{
			vertVelocity = 0.0;
			y = 400-h; // snap back to the ground
		}

        if (start == true)
        {
            x += 4;
        }
    }
    
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
        return true;
    }

    boolean isChaos()
    {
        return false;
    }

    boolean isFireball()
    {
        return false;
    }

    boolean isCollisionL()
    {
        if (collisionL == true)
        {
            start = false;
            return true;
        }

        else
        {
            return false;
        }
    }

    boolean isCollisionR()
    {
        if (collisionR == true)
        {
            start = false;
            return true;
        }

        else
        {
            return false;
        }
    }

    @Override 
    public String toString()
    {
    	return "Goomba (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h + "\n";
    }
}