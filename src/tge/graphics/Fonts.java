package tge.graphics;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Fonts
{	
	// Fonts
	public static Font fontButton;
	public static Font fontStandard, fontStandardBold;
	public static Font fontEditorMenu, fontEditorMenuBold, fontEditorMenuInfo, fontEditorMenuTitle;
	public static Font fontToolbarBold, fontToolbarStandard;
	
	public static void init()
	{
		initFonts();
	}
	
	public static void initFonts()
	{
		fontButton = new Font("MV Boli", Font.PLAIN, 32);
		fontStandard = new Font("MV Boli", Font.PLAIN, 26);
		fontStandardBold = new Font("MV Boli", Font.BOLD, 26);
		fontEditorMenu = new Font("MV Boli", Font.PLAIN, 18);
		fontEditorMenuBold = new Font("MV Boli", Font.BOLD, 18);
		fontEditorMenuInfo = new Font("MV Boli", Font.ITALIC, 18);
		fontEditorMenuTitle = new Font("MV Boli", Font.BOLD, 18);
		
		fontToolbarBold = new Font("MV Boli", Font.BOLD, 18);
		fontToolbarStandard = new Font("MV Boli", Font.PLAIN, 18);
	}

}