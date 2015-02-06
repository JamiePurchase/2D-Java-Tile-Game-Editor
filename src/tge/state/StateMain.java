package tge.state;

import tge.Editor;
import tge.graphics.Drawing;
import tge.graphics.Fonts;
import tge.graphics.ImageLoader;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class StateMain extends State
{
	// Mouse Events
	private boolean mouseListenClick = true;
	
	// Toolbar
	private String toolbarMenuActive = "None";
	
	// Close Button
	private boolean toolbarCloseActive = false;
	private int toolbarCloseTick = 0;

	public StateMain()
	{
		initNexus();
	}
	
	public void initNexus()
	{
		// Create Blank Nexus
		Editor.mouse.nexusClear();
		
		// Main Toolbar
		Editor.mouse.nexusAdd("MainEditor", 0, 0, 250, 32);
		Editor.mouse.nexusAdd("MainFile", 300, 0, 250, 32);
		Editor.mouse.nexusAdd("MainClose", 1334, 0, 32, 32);
		
		// Editor Menu
		Editor.mouse.nexusAdd("EditorProject",0, 32, 250, 32);
		Editor.mouse.nexusAdd("EditorTileset",0, 64, 250, 32);
		Editor.mouse.nexusAdd("EditorBoard",0, 96, 250, 32);
		Editor.mouse.nexusAdd("EditorClose",0, 128, 250, 32);
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1366, 768);
		//renderEditor();
		renderToolbar(g);
		
		// Test
		String coords = "Mouse: " + Editor.mouse.mouseCoordsX + ", " + Editor.mouse.mouseCoordsY;
		g.setColor(Color.WHITE);
		g.drawString(coords, 1000, 400);
	}
	
	public void renderToolbar(Graphics g)
	{
		String ref = Editor.mouse.nexusCheckRef();
		renderToolbarMain(g, ref);
		if(toolbarMenuActive=="Editor"){renderToolbarEditor(g, ref);}
		if(toolbarMenuActive=="File"){renderToolbarFile(g, ref);}
	}
	
	public void renderToolbarEditor(Graphics g, String ref)
	{
		if(ref=="EditorProject"){g.drawImage(Drawing.getImage("/interface/toolbarEditorH1.png"),  0, 32, null);}
		else if(ref=="EditorTileset"){g.drawImage(Drawing.getImage("/interface/toolbarEditorH2.png"),  0, 32, null);}
		else if(ref=="EditorBoard"){g.drawImage(Drawing.getImage("/interface/toolbarEditorH3.png"),  0, 32, null);}
		else if(ref=="EditorClose"){g.drawImage(Drawing.getImage("/interface/toolbarEditorH4.png"),  0, 32, null);}
		else{g.drawImage(Drawing.getImage("/interface/toolbarEditor.png"),  0, 32, null);}
		g.setFont(Fonts.fontToolbarStandard);
		g.drawString("Project Settings", 25, 54);
		g.drawString("Tileset Manager", 25, 86);
		g.drawString("Board Editor", 25, 118);
		g.drawString("Close Application", 25, 150);
	}
	
	public void renderToolbarFile(Graphics g, String ref)
	{
		g.drawImage(Drawing.getImage("/interface/toolbarFile.png"),  300, 32, null);
		g.setFont(Fonts.fontToolbarStandard);
		g.drawString("New", 325, 54);
	}
	
	public void renderToolbarMain(Graphics g, String ref)
	{
		// Toolbar
		g.drawImage(Drawing.getImage("/interface/toolbarMain.png"),  0, 0, null);

		// Title
		g.setFont(Fonts.fontToolbarBold);
		g.setColor(Color.BLACK);
		g.drawString("TILE GAME EDITOR", 20, 22);
		
		// Options
		g.setFont(Fonts.fontToolbarStandard);
		g.drawString("FILE", 320, 22);
		g.drawString("???", 500, 22);
		g.drawString("HELP", 700, 22);
		
		// Close
		if(toolbarCloseActive==true){g.drawImage(Drawing.getImage("/interface/toolbarMainCloseC.png"),  1334, 0, null);}
		else if(ref=="MainClose"){g.drawImage(Drawing.getImage("/interface/toolbarMainCloseH.png"),  1334, 0, null);}
		else{g.drawImage(Drawing.getImage("/interface/toolbarMainClose.png"),  1334, 0, null);}
	}
	
	public void tick()
	{
		if(toolbarCloseActive==true){tickClose();}
		if(mouseListenClick==true){tickClick();}
	}
	
	public void tickClick()
	{
		if(Editor.mouse.mouseActionPressedL==true)
		{
			Editor.mouse.mouseActionDone();
			String ref = Editor.mouse.nexusCheckRef();
			tickClickToolbar(ref);
			if(toolbarMenuActive!=""){tickClickMenu(ref);}
		}
	}
	
	public void tickClickMenu(String ref)
	{
		if(toolbarMenuActive=="Editor"){tickClickMenuEditor(ref);}
		if(toolbarMenuActive=="File"){tickClickMenuFile(ref);}
	}
	
	public void tickClickMenuEditor(String ref)
	{
		if(ref=="EditorClose")
		{
			System.exit(0);
		}
	}
	
	public void tickClickMenuFile(String ref)
	{
		/*if(ref=="FileSomething")
		{
			
		}*/
	}
	
	public void tickClickToolbar(String ref)
	{
		if(ref=="MainClose")
		{
			mouseListenClick = false;
			toolbarCloseActive = true;
		}
		if(ref=="MainEditor")
		{
			if(toolbarMenuActive=="Editor"){toolbarMenuActive="";}
			else{toolbarMenuActive = "Editor";}
		}
		if(ref=="MainFile")
		{
			if(toolbarMenuActive=="File"){toolbarMenuActive="";}
			else{toolbarMenuActive = "File";}
		}
	}
	
	public void tickClose()
	{
		toolbarCloseTick+=1;
		if(toolbarCloseTick>=60){System.exit(0);}
	}

}