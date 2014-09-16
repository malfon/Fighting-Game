import java.io.IOException;
import java.nio.file.Paths;
import org.jsfml.graphics.*;
import org.jsfml.system.*;
import org.jsfml.window.*;
import org.jsfml.window.event.*;

public class Main
{
	public static void main(String args[]) throws IOException, InterruptedException
	{
		int width=800, height=600;
		RenderWindow window = new RenderWindow();
		window.create(new VideoMode(width, height), "Fighting Game");
		
		Clock clock = new Clock();
		Time dt = clock.restart();
		
		//Maps
		
		//UI
		
		//Fighters
		boolean moving  = false;
		
		Texture p1t = new Texture();
		p1t.loadFromFile(Paths.get("bin/p1.png"));
		Sprite p1 = new Sprite();
		p1.setTexture(p1t);
		p1.setScale(.5f, .5f);
		p1.setPosition(5, 390);
		
		Texture p1_idle1t = new Texture();
		p1_idle1t.loadFromFile(Paths.get("bin/p1_idle1.png"));
		Sprite p1_idle1 = new Sprite();
		p1_idle1.setTexture(p1_idle1t);
		p1_idle1.setScale(.5f, .5f);
		p1_idle1.setPosition(p1.getPosition());
		
		while(window.isOpen())
		{
			window.clear(Color.WHITE);
			for(Event event : window.pollEvents())
			{
				switch(event.type)
				{
				case CLOSED:
					window.close();
					break;
				default:
					break;
				}
			}
			if(!moving)
			{
				float count=0;
				if(count>=0 && count<1)
				{
					window.draw(p1);
				}
				else if(count>=1 && count<2)
				{
					window.draw(p1_idle1);
				}
				if(count>2)
					count=0;
				else
					count+=10;
				System.out.println(count);
			}
			window.display();
		}
	}
}
