package tge;

import tge.graphics.Fonts;
import tge.input.Mouse;
import tge.state.State;
import tge.state.StateIntro;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Editor extends JFrame implements Runnable
{
	// States
	public State editorState;
	public static State editorStateNew;
	public static boolean editorStateChange = false;

	// Graphics
	private BufferStrategy bs;
	private Graphics g;
	public Canvas canvas;
	
	// Mouse
	public static Mouse mouse;
	
	// Threads
	private Thread thread;
	private boolean running = false;
 
	public Editor()
	{
	}
	
	public void init()
	{
		initEditor();
		initState();
	}
	
	public void initEditor()
	{
		mouse = new Mouse();
		addMouseListener (mouse);
		setTitle("Tile Game Editor");
		setSize(1366, 768);
		setUndecorated(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Create the canvas
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(1366, 768));
		canvas.setMaximumSize(new Dimension(1366, 768));
		canvas.setMinimumSize(new Dimension(1366, 768));
		canvas.addMouseListener (mouse);
		canvas.addMouseMotionListener (mouse);
		
		// Add the canvas to the frame
		add(canvas);
		//pack();

		// Prepare fonts
		Fonts.init();
	}
	
	public void initState()
	{
		// Load States
		/*stateBattle = new StateBattle();
		stateDebug = new StateDebug();
		stateTitle = new StateTitle();*/
		
		// Initial State
		State.setState(new StateIntro());
		//State.setState(new StateTitle());
		//State.setState(new StateBattle());
	}
	
	public void render()
	{
		// Buffer strategy
		bs = canvas.getBufferStrategy();
		if(bs == null)
		{
			canvas.createBufferStrategy(3);
			return;
		}
		
		// Graphics start
		g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1366, 768);
		
		// Temp
		/*g.setColor(Color.BLUE);
		g.fillOval(mouse.mouseCoordsX,mouse.mouseCoordsY,15,15);
		if (mouse.mouseOnScreen)
		{
			//g.setColor(Color.BLUE);
			//g.drawString("Coords: ("+ x + " , "+y+ ")", 150, 150);
		}*/
		
		// Graphics draw
		if(State.getState() != null)
		{
			State.getState().render(g);
		}

		// Graphics done
		bs.show();
		g.dispose();
	}
	
	public void run()
	{
		// Render speed
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		// Create window
		init();
		
		// Main game loop
		while(running)
		{
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if(delta >= 1)
			{
				tick();
				render();
				ticks++;
				delta--;
			}
			if(timer >= 1000000000)
			{
				//System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		// End game
		stop();
	}
	
	public static void setStateChange(State newState)
	{
		Editor.editorStateChange = true;
		Editor.editorStateNew = newState;
	}
	
	public synchronized void start()
	{
		if(running==false)
		{
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public synchronized void stop()
	{
		if(running==true)
		{
			try
			{
				thread.join();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void tick()
	{
		if(editorStateChange==true){tickStateChange();}
		State.getState().tick();
	}

	public void tickStateChange()
	{
		State.setState(editorStateNew);
		editorStateChange = false;
	}
	
}