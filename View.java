// Name: Caroline Henson
// Date: October 31, 2022
// Assignment: #5

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

class View extends JPanel
{
	Model model;
	Mario mario;
	Sprite sprite;
	int speed = 4;
	int scrollPos;

	View(Controller c, Model m)
	{
		scrollPos = 0;
		model = m;
		c.setView(this);
	}

	static BufferedImage loadImage(String filename)
	{
		BufferedImage im = null;
		try
		{
			im = ImageIO.read(new File(filename));
		}
		
		catch(Exception e) 
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}

		System.out.println("Successfully loaded " + filename + " image." );

		return im;
	}

	public void scrollPos(int s)
	{
		scrollPos = s;
	}

	public void setModel(Model m)
	{
		model = m;
	}

	//actually paints the pipe in the program
	public void paintComponent(Graphics g)
	{
		// camera follows mario
		for(int i = 0; i < model.sprites.size(); i++)
		{
			if(model.sprites.get(i).isMario())
			{
				scrollPos = model.mario.x - 100;
			}
		}

		//set color changes the background of the game
		g.setColor(new Color(128, 255, 255));
    	g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.gray);
		g.drawLine(0, 400, 2000, 400);

		//draws Sprites
		for(int i = 0; i < model.sprites.size(); i++)
		{
			model.sprites.get(i).drawYourself(g, scrollPos);
		}
	}
}
