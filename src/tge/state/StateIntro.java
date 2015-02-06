package tge.state;

import tge.Editor;
import tge.graphics.Drawing;
import tge.graphics.ImageLoader;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class StateIntro extends State
{
	private int tickCount = 0;
	private int tickFrame = 1;

	public StateIntro()
	{
		
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1366, 768);
		g.drawImage(Drawing.getImage("/interface/editorIntro.png"),  0, 0, null);
	}
	
	public void tick()
	{
		tickClick();
		tickTime();
	}
	
	public void tickClick()
	{
		if(Editor.mouse.mouseActionPressedL==true)
		{
			Editor.mouse.mouseActionDone();
			Editor.setStateChange(new StateMain());
		}
	}
	
	public void tickTime()
	{
		tickCount+=1;
		if(tickCount>=60)
		{
			tickFrame+=1;
			tickCount=0;
			if(tickFrame>10)
			{
				Editor.setStateChange(new StateMain());
			}
		}
	}

}