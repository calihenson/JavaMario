// Name: Caroline Henson
// Date: October 31, 2022
// Assignment: #5

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	View view;
	Model model;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean spacebar;
	boolean ctrl;
	boolean edit = false;
	boolean pipe = false;
	boolean goomba = false;

	Controller(Model m)
	{
    	model = m;
	}

	void setView(View v)
	{
		view = v;
	}

	public void mousePressed(MouseEvent e)
	{
		if (edit == true)
		{
			if((pipe == false) && (goomba == false))
			{
				pipe = true;
			}

			if (pipe == true)
			{
				model.addPipe(e.getX() + view.scrollPos, e.getY());
			}

			if (goomba == true)
			{
				model.addGoomba(e.getX() + view.scrollPos, e.getY());
			}
		}
		if (edit == false)
		{
			System.out.println("Press 'e' to begin editting.");
		}
	}

	public void actionPerformed(ActionEvent e) {    }

	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {	}

	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_SPACE: spacebar = true; break;
			case KeyEvent.VK_CONTROL: ctrl = true; break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_SPACE: spacebar = false; break;
			// case KeyEvent.VK_CONTROL: ctrl = false; break;
			case KeyEvent.VK_CONTROL: model.addFireball(model.mario.x, model.mario.y); break;
			case KeyEvent.VK_ESCAPE: System.exit(0);
		}

		char c = Character.toLowerCase(e.getKeyChar());

		if(c == 'q')
			System.exit(0);
		if(c == 's')
		{
			//save file
			Json saveObject = model.marshal();
			saveObject.save("pipemap.json");
			System.out.println("File is saved.");
		}
		if(c == 'l')
		{
			//load file
			Json j = Json.load("pipemap.json");
			model.unmarshal(j);
			System.out.println("File is loaded.");
		}
		if(c == 'e')
		{
			if (edit == true)
			{
				System.out.println("You are playing the game.");
				edit = false;
			}
			else if (edit == false)
			{
				System.out.println("You are editing the map.");
				edit = true;
			}
		}
		if(c == 'p')	
		{
			if (pipe == true)
			{
				System.out.println("Press 'e' to play the game or 'g' to edit goombas.");
			}
			else if (pipe == false)
			{
				System.out.println("Now editing pipes");
				pipe = true;
				goomba = false;
			}
		}
		if(c == 'g')
		{
			if (goomba == true)
			{
				System.out.println("Press 'e' to play the game or 'p' to edit pipes.");
			}
			else if (goomba == false)
			{
				System.out.println("Now editing goombas");
				goomba = true;
				pipe = false;
			}
		}
	}

	public void keyTyped(KeyEvent e) {    }

	void update()
	{	
		for(int i = 0; i < model.sprites.size(); i++)
		{
			model.sprites.get(i).setPreviousPosition();
			
			if(model.sprites.get(i).isGoomba())
			{
				model.goomba.setPreviousPosition();
				
				if (model.sprites.get(i).isCollisionL())
				{
					model.sprites.get(i).x -= 4;
				}
				if (model.sprites.get(i).isCollisionR())
				{
					model.sprites.get(i).x += 4;
				}
			}

			if(model.sprites.get(i).FireGoomba == true)
			{
				model.addChaos(model.sprites.get(i).x, model.sprites.get(i).y);
				model.sprites.remove(i);
			}
		}

		if(keyRight)
		{
			model.mario.x += 4;
			model.mario.rightFacing = true;
			model.mario.changeImageState();
		}
		if(keyLeft)
		{
			model.mario.x -= 4;
			model.mario.rightFacing = false;
			model.mario.changeImageState();
		}
		if(spacebar)
		{
			model.mario.jump();
		}
	}
}
