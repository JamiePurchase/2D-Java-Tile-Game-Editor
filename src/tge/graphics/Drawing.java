package tge.graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import tge.graphics.Fonts;

public class Drawing
{
	public static void drawStringShadow(Graphics g, String text, int x, int y)
	{
		drawStringShadow(g, text, x, y, 2, Color.BLACK);
	}
		
	public static void drawStringShadow(Graphics g, String text, int x, int y, int shadow, Color color)
	{
		g.setFont(Fonts.fontStandard);
		g.setColor(color);
		for(int n=1;n<=shadow;n+=1)
		{
			g.drawString(text, x+n, y+n);
		}
		g.setColor(Color.WHITE);
		g.drawString(text, x, y);
	}
	
	public static void drawFrame(Graphics g, int x, int y, int width, int height)
	{
		// Shadow
		g.setColor(Color.gray);
		g.fillRect(x+5, y+5, width, height);
		
		// Background
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
		
		// Border
		g.setColor(Color.white);
		g.drawRect(x, y, width, height);
	}
	
	public static void drawMenuItem(Graphics g, String text, int x, int y, int hover)
	{
		// Temp (move to arguments)
		int width = 200;
		int height = 50;
		
		// Shadow
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x+1, y+1, width, height);
		g.fillRect(x+2, y+2, width, height);
		g.fillRect(x+3, y+3, width, height);
		
		// Background
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
		
		// Border
		g.setColor(Color.BLUE);
		g.drawRect(x, y, width, height);
		g.drawRect(x+1, y+1, width-2, height-2);
		
		// Text
		g.setColor(Color.BLUE);
		g.setFont(Fonts.fontButton);
		g.drawString(text, x+15, y+35);
	}
	
	public static Color getColorRGB(int r, int g, int b)
	{
		float hsb[] = Color.RGBtoHSB(185,122,87,null);
		return Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
	}
	
	public static BufferedImage getImage(String filepath)
	{
		filepath = "C:/Eclipse/Workspace/TileGameEditor/res/" + filepath;
		BufferedImage image = null;
		try
		{
			image = ImageIO.read(new File(filepath));
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
		return image;
	}
	
}