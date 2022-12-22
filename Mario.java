// Name: Caroline Henson
// Date: October 31, 2022
// Assignment: #5

import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Mario extends Sprite
{
    int currentImage;
    static BufferedImage[] images;
    boolean rightFacing = true;
    double vertVelocity;
    int numFramesInAir = 0;
    boolean start = true;
    int px, py;

    public Mario(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.h = 100;
        this.w = 60;
        currentImage = 0;
        if(images == null)
        {
            images = new BufferedImage[5];
            images[0] = View.loadImage("mario1.png");
            images[1] = View.loadImage("mario2.png");
            images[2] = View.loadImage("mario3.png");
            images[3] = View.loadImage("mario4.png");
            images[4] = View.loadImage("mario5.png");
        }
    }

    void changeImageState()
    {
        currentImage++;
        if(currentImage > 4)
            currentImage = 0;
    }

    public void drawYourself(Graphics g, int scrollPos)
    {
        if(rightFacing)
        {
            g.drawImage(images[currentImage], x-scrollPos, y, w, h, null);
        }
        else
            g.drawImage(images[currentImage], x-scrollPos+w, y, -w, h, null);
    }

    public void getOutOfPipe(Sprite p)
    {
        if((x + w < p.x + p.w) && (px + w <= p.x))
        {
            // System.out.println("Left side collision");
            x = p.x - w;
        }

        if((x > p.x) && (px >= p.x + p.w))
        {
            // System.out.println("Right side collision");
            x = p.x + p.w;
        }

        if((y + h < p.y + p.h) && (py + h <= p.y))
        {
            // System.out.println("Top collision");
            y = p.y - h;
            numFramesInAir = 0;
            vertVelocity = 0.0;
        }

        if((y > p.y) && (py >= p.y + p.h))
        {
            //System.out.println("Bottom collision");
            y = p.y + p.h;
            numFramesInAir = 0;
            vertVelocity = 0.0;
        }
    }

    boolean pipeOrGround()
    {
        if(vertVelocity == 0.0)
            return true;

        else
            return false;
    }

    public void jump()
	{
        if (numFramesInAir < 5)
        {
            if(pipeOrGround() == true)
            {
                y += vertVelocity;
                vertVelocity += -20;
            }
        }

        if ((numFramesInAir < 20) && (numFramesInAir >=5))
        {
            if(pipeOrGround() == true)
            {
                y += vertVelocity;
                vertVelocity += -20;
            }
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
        numFramesInAir++;
        //System.out.println(numFramesInAir);

        if(y > 400-h)
		{
			vertVelocity = 0.0;
			y = 400-h; // snap back to the ground
            numFramesInAir = 0;
		}
	}

    boolean isMario()
    {
        return true;
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
    	return "Mario (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h + "\n";
    }
}