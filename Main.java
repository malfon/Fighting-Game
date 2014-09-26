import java.io.IOException;
import java.nio.file.Paths;
import org.jsfml.graphics.*;
import org.jsfml.system.*;
import org.jsfml.window.*;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.event.*;

public class Main
{
	
	public static void main(String args[]) throws IOException, InterruptedException
	{
		int width=800, height=600;
		RenderWindow window = new RenderWindow();
		window.create(new VideoMode(width, height), "Fighting Game");
		
		window.setFramerateLimit(60);
		
		float frameRate=500, switchFrame=100, frameCounter=0;
		Clock animTimer = new Clock();
		Time animDelay;
		int animSec;
		
		final int LEFT 	= 1;
		final int RIGHT = 2;
		
		Vector2i pWalkVec = new Vector2i(0, 0);
		
		//Maps
		
		//UI
		
		//Fighters
		Texture ryuuTexture = new Texture();
		ryuuTexture.loadFromFile(Paths.get("bin/ryuu.png"));
		Sprite ryuuSprite = new Sprite();
		ryuuSprite.setTexture(ryuuTexture);
		ryuuSprite.setPosition(0, height-95);
		
		while(window.isOpen())
		{
			window.clear(Color.WHITE);
			animDelay = animTimer.getElapsedTime();
			animSec = (int) (animDelay.asSeconds());
			
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
			
			frameCounter += frameRate * animTimer.restart().asSeconds()*3/2;
			
			if(frameCounter >= switchFrame && !Keyboard.isKeyPressed(Key.D) && !Keyboard.isKeyPressed(Key.A))
			{
				frameCounter = 0;
				if((pWalkVec.x*60) < ryuuTexture.getSize().x)
				{
					ryuuSprite.setTextureRect(new IntRect(pWalkVec.x*60, pWalkVec.y*95, 60, 95));
					pWalkVec = Vector2i.add(pWalkVec, new Vector2i(1, 0));
				}
				else
				{
					frameCounter = 0;
					pWalkVec = new Vector2i(0, 0);
				}
			}
			
			if(Keyboard.isKeyPressed(Key.D))
			{
				pWalkVec = new Vector2i(pWalkVec.x, RIGHT);
				ryuuSprite.move(1, 0);
			}
			
			if(Keyboard.isKeyPressed(Key.A))
			{
				pWalkVec = new Vector2i(pWalkVec.x, LEFT);
				ryuuSprite.move(-1, 0);
			}
			
			window.draw(ryuuSprite);
			
			window.display();
		}
	}
}
