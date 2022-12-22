// Name: Caroline Henson
// Date: October 31, 2022
// Assignment: #5

import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Pipe extends Sprite
{
    static BufferedImage image;

    public Pipe(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.w = 55;
        this.h = 400;
        if(image == null)
            image = View.loadImage("pipe.png");
    }

    public Pipe(Json ob)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        w = 55;
        h = 400;
        if(image == null)
            image = View.loadImage("pipe.png");
    }

    boolean ClickingTest(int mouse_x, int mouse_y)
    {
        //check bounds && return yes or no for mouse click inside bound box
        if ((mouse_x >= x) && (mouse_y >= y) &&
            (mouse_x <= x + w) && (mouse_y <= y + h))
        {
            return true;
        }
        
        else
            return false;
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
        g.drawImage(image, x-scrollPos, y, w, h, null);
    }

    public void update()
    {

    }

    void setPreviousPosition()
    {   }

    void getOutOfPipe(Sprite p)
    {   }

    boolean isMario()
    {
        return false;
    }

    boolean isPipe()
    {
        return true;
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
    	return "Pipe (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h + "\n";
    }
}