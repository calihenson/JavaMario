// Name: Caroline Henson
// Date: October 31, 2022
// Assignment: #5

import java.util.ArrayList;
import java.util.Iterator;

class Model
{
	ArrayList<Sprite> sprites;
	Mario mario;
	Goomba goomba;
	Fireball fireball;
	int numFramesOnFire = 0;

	Model()
	{
		//initialize
		sprites = new ArrayList<Sprite>();
		mario = new Mario(100,100);
		sprites.add(mario);
		goomba = new Goomba(100,100);
		sprites.add(goomba);

		fireball = new Fireball(100,100);
	}

	void unmarshal(Json ob)
	{
		sprites = new ArrayList<Sprite>();
		sprites.add(mario);

		Json tmpListP = ob.get("pipes");
		Json tmpListG = ob.get("goombas");

		for(int i = 0; i < tmpListP.size(); i++)
		{
			sprites.add(new Pipe(tmpListP.get(i)));
		}

		for(int i = 0; i < tmpListG.size(); i++)
		{
			sprites.add(new Goomba(tmpListG.get(i)));
		}
	}

	Json marshal()
	{
		Json ob = Json.newObject();
		Json tmpListP = Json.newList();
		ob.add("pipes", tmpListP);

		Json tmpListG = Json.newList();
		ob.add("goombas", tmpListG);

		for(int i = 0; i < sprites.size(); i++)
		{
			if(sprites.get(i).isPipe())
				tmpListP.add(((Pipe)sprites.get(i)).marshal());
			if(sprites.get(i).isGoomba())
				tmpListG.add(((Goomba)sprites.get(i)).marshal());
		}
		return ob;
	}

	public void addPipe(int x, int y)
	{
		boolean foundPipe = false;

		Iterator<Sprite> itr = sprites.iterator();

		while(itr.hasNext())
		{
			Sprite temp = itr.next();
		
			if(temp.isPipe())
			{
				if(((Pipe)temp).ClickingTest(x, y))
				{
					itr.remove();
					foundPipe = true;
				}
			}
		}

		if(foundPipe == false)
			sprites.add(new Pipe(x, y));
	}

	public void addGoomba(int x, int y)
	{
		//add goomba into array list
		Sprite g = new Goomba(x, y);
		sprites.add(g);
	}

	public void addFireball(int x, int y)
	{
		Sprite f = new Fireball(x, y);
		sprites.add(f);
		fireball.setInitial();
	}

	public void addChaos(int x, int y)
	{
		Sprite c = new FireGoomba(x, y);
		sprites.add(c);
	}

	boolean isThereACollision(Sprite a, Sprite b)
	{
		//if he is not colliding
		if(a.x + a.w < b.x)
			return false;
		if(a.x > b.x + b.w)
			return false;
		if(a.y + a.h < b.y)
			return false;
		if(a.y > b.y + b.h)
			return false;

		//if he is not NOT colliding
		else
			return true;
	}

	public void update()
	{
		//check if there is a collision
		for(int i = 0; i < sprites.size(); i++)
		{
			sprites.get(i).update();

			if(sprites.get(i).isMario())
			{
				for(int j = 0; j < sprites.size(); j++)
				{
					if(sprites.get(j).isPipe())
					{
						boolean checkM = isThereACollision(sprites.get(i), sprites.get(j));

						if (checkM == true)
						{
							// System.out.println("collision");
							mario.getOutOfPipe(sprites.get(j));
						}
					}
				}
			}

			if(sprites.get(i).isGoomba())
			{
				for(int j = 0; j < sprites.size(); j++)
				{
					//pipe vs goomba 
					if(sprites.get(j).isPipe())
					{
						boolean checkG = isThereACollision(sprites.get(i), sprites.get(j));

						if (checkG == true)
						{
							//System.out.println("collision");
							sprites.get(i).getOutOfPipe(sprites.get(j));
						}
					}

					//fireball vs goomba
					if(sprites.get(j).isFireball())
					{
						boolean checkF = isThereACollision(sprites.get(i), sprites.get(j));

						if (checkF == true)
						{
							sprites.get(i).FireGoomba = true;
						}
					}
				}
			}

			if(sprites.get(i).isChaos())
			{
				numFramesOnFire++;

				if(numFramesOnFire == 15)
				{
					numFramesOnFire = 0;
					sprites.remove(i);
				}
			}
		}
	}
}