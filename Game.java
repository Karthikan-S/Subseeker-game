import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.SwingUtilities;
import java.util.Random;

public class Game extends JPanel implements ActionListener
{
	//Example game entity object.
	Random rand = new Random();
	int maxX = 350;
	int maxY = 200;
	Entity submarine;
	Entity player;
	Entity bomb;
	//Enemy[] enemies;
	//NPC[] npcs;
	//Wall[] walls;

	//Initializes the game.
	public void init()
	{

		submarine = new Entity();
		submarine.init();
		//Initializes the player.
		player = new Entity();
		player.init(50,50);

		bomb = new Entity();
		bomb.init(rand.nextInt(maxX),rand.nextInt(maxY));

		//enemies = new Enemy[10];
		//npcs = new NPC[2];
		//walls = new Wall[15];

		//enemies[0] = new Enemy();
		//enemies[0].init();
		//npcs[0] = new NPC();
		//npcs[0].init();
		//walls[0] = new Wall();
		//walls[0].init();
		//etc etc
	}

	//Updates all game objects.
    public void step(ActionEvent e)
	{
		//Updates the player's movement.
		player.step();
		//enemies[0].step();
		//npcs[0].step();
		//walls[0].step();
		//etc etc
		if (player.collisionDetection(bomb))
		{
			System.out.println("GAME OVER");
			System.exit(0);
		}
		else if (player.collisionDetection(submarine))
		{
			System.out.println("YOU WON");
			System.exit(0);
		}
	}

	//Draws all game objects.
	public void draw(Graphics2D g2d)
	{
		//Sets background colour to black.
		setBackground(Color.blue);

		submarine.draw(g2d,this);

		g2d.setColor(Color.green);

		//Draws the player.
		player.draw(g2d, this);
		//enemies[0].draw(g2d, this);
		g2d.setColor(Color.red);
		bomb.draw(g2d,this);
		//npcs[0].draw(g2d, this);
		//walls[0].draw(g2d, this);
		//etc etc
	}

	//Automatically activates when a keyboard key is pressed.
	public void keyPress(int key)
	{
		//Sends key-press input to player.
		player.keyPress(key);

		//Prints key pressed to console log.
		//System.out.println("Key Pressed: " + key);
	}

	//Automatically activates when a keyboard key is released.
	public void keyRelease(int key)
	{
		//Sends key-release input to player.
		player.keyRelease(key);

		//Prints key released to console log.
		//System.out.println("Key Released: " + key);
	}

	//Activates when mouse button is pressed and then released.
	public void mouseClick(int x, int y, int button)
	{
		//Prints mouse coordinates to console log.
		//System.out.println("Mouse button " + button + " clicked: (" + x + ", " + y + ")");
	}

	//Activates when mouse button is pressed.
	public void mousePress(int x, int y, int button)
	{
		//Prints mouse coordinates to console log.
		//System.out.println("Mouse button " + button + " pressed: (" + x + ", " + y + ")");
	}

	//Activates when mouse button is released.
	public void mouseRelease(int x, int y, int button)
	{
		//Prints mouse coordinates to console log.
		//System.out.println("Mouse button " + button + " released: (" + x + ", " + y + ")");
	}

	//Activates when mouse cursor enters window.
	public void mouseEnter(int x, int y)
	{
		//Prints mouse coordinates to console log.
		//System.out.println("Mouse Entered: (" + x + ", " + y + ")");
	}

	//Activates when mouse cursor exits window.
	public void mouseExit(int x, int y)
	{
		//Prints mouse coordinates to console log.
		//System.out.println("Mouse Exited: (" + x + ", " + y + ")");
	}

	//Activates when mouse moves within the window.
	public void mouseMovement(int x, int y)
	{
		//Prints mouse coordinates to console log.
		//System.out.println("Mouse Moved: (" + x + ", " + y + ")");
	}  

	//Activates when mouse is moved while button is pressed within the window.
	public void mouseDragging(int x, int y, int button)
	{
		//Prints mouse coordinates to console log.
		//System.out.println("Mouse button " + button + " dragged: (" + x + ", " + y + ")");
	}

	//Activates when mouse wheel has moved.
	public void mouseWheel(int direction)
	{
		//Prints mouse wheel amount to console log.
		//System.out.println("Mouse wheel direction: " + direction);
	}

	//////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////ENGINE////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////

	//Program animation speed interval.
	private final int DELAY = 15;

	//Updates program based on speed delay interval.
	private Timer timer;

	//Constructs/initializes the game.
	public Game()
	{
		//Sets up keyboard input, using nested private GameKeyboardAdapter class.
		addKeyListener(new GameKeyboardAdapter());
		addMouseListener(new GameMouseListener());
		addMouseMotionListener(new GameMouseMotionListener());
		addMouseWheelListener(new GameMouseWheelListener());

		//Initializes the game.
		init();

		//Allows keyboard input to work.
		setFocusable(true);

		//For every DELAY amount in milliseconds, the timer will call the actionPerformed() method,
		//which updates the movement of objects in the game. Think of it as the program's frame-rate.
		this.timer = new Timer(DELAY, this);
		this.timer.start();
	}

	//All game graphics are drawn inside the paintComponent() method.
	@Override
	public void paintComponent(Graphics g)
	{
		//Draws JPanel window
		super.paintComponent(g);

		//The Graphics2D class extends the Graphics class.
		//It provides more sophisticated control over geometry,
		//coordinate transformations, colour management, and text layout.
		Graphics2D g2d = (Graphics2D) g;

		//Draws all game components.
		draw(g2d);

		//Toolkit.getDefaultToolkit().sync() synchronises the painting on systems that buffer graphics events.
		//Without this line, the animation might not be smooth on Linux.
		Toolkit.getDefaultToolkit().sync();
	}

	//Updates the movement of objects in the game. This method is repeatedly called by the Timer object.
	//NOTE:	In order to use the actionPerformed() method, the ActionListener interface must be implemented
	//		in the class' signature header, above.
	@Override
    public void actionPerformed(ActionEvent e)
	{
		//Updates all game objects.
		step(e);

		//Causes the paintComponent() drawing method to be called.
		//This way we can regularly redraw the Game, thus making the animation.
		repaint();
	}

	//This inner private class detects keyboard inputs by the user.
	private class GameKeyboardAdapter extends KeyAdapter
	{
		//Detects whenever a key (represented by variable "e") is released.
		@Override
		public void keyReleased(KeyEvent e)
		{
			//Gets the key value of the key just released.
			int key = e.getKeyCode();

			//Send key to game function.
			keyRelease(key);
		}

		//Detects whenever a key (represented by variable "e") is pressed down.
		@Override
		public void keyPressed(KeyEvent e)
		{
			//Gets the key value of the key just pressed.
			int key = e.getKeyCode();

			//Send key to game function.
			keyPress(key);
		}
	}

	//This inner private class detects mouse inputs and location by the user.
	private class GameMouseListener implements MouseListener
	{
		//Activates when mouse button is pressed and then released.
		@Override
		public void mouseClicked(MouseEvent e)
		{
			//Gets coordinates of mouse, and assigns it to mouse coordinate variables.
			int mouseX = e.getX();
			int mouseY = e.getY();

			//Figures out which button on mouse was pressed.
			//0 means left, 1 means middle, 2 means right.
			int mouseButton = -1;
			if (SwingUtilities.isLeftMouseButton(e) == true)
				mouseButton = 0;
			else if (SwingUtilities.isMiddleMouseButton(e) == true)
				mouseButton = 1;
			else if (SwingUtilities.isRightMouseButton(e) == true)
				mouseButton = 2;

			//Sends mouse coordinates to game function.
			mouseClick(mouseX,mouseY,mouseButton);
		}

		//Activates when mouse button is pressed.
		@Override
		public void mousePressed(MouseEvent e)
		{
			//Gets coordinates of mouse, and assigns it to mouse coordinate variables.
			int mouseX = e.getX();
			int mouseY = e.getY();

			//Figures out which button on mouse was pressed.
			//0 means left, 1 means middle, 2 means right.
			int mouseButton = -1;
			if (SwingUtilities.isLeftMouseButton(e) == true)
				mouseButton = 0;
			else if (SwingUtilities.isMiddleMouseButton(e) == true)
				mouseButton = 1;
			else if (SwingUtilities.isRightMouseButton(e) == true)
				mouseButton = 2;

			//Sends mouse coordinates to game function.
			mousePress(mouseX,mouseY,mouseButton);
		}

		//Activates when mouse button is released.
		@Override
		public void mouseReleased(MouseEvent e)
		{
			//Gets coordinates of mouse, and assigns it to mouse coordinate variables.
			int mouseX = e.getX();
			int mouseY = e.getY();

			//Figures out which button on mouse was pressed.
			//0 means left, 1 means middle, 2 means right.
			int mouseButton = -1;
			if (SwingUtilities.isLeftMouseButton(e) == true)
				mouseButton = 0;
			else if (SwingUtilities.isMiddleMouseButton(e) == true)
				mouseButton = 1;
			else if (SwingUtilities.isRightMouseButton(e) == true)
				mouseButton = 2;

			//Sends mouse coordinates to game function.
			mouseRelease(mouseX,mouseY,mouseButton);
		}

		//Activates when mouse cursor enters window.
		@Override
		public void mouseEntered(MouseEvent e)
		{
			//Gets coordinates of mouse, and assigns it to mouse coordinate variables.
			int mouseX = e.getX();
			int mouseY = e.getY();

			//Sends mouse coordinates to game function.
			mouseEnter(mouseX,mouseY);
		}

		//Activates when mouse cursor exits window.
		@Override
		public void mouseExited(MouseEvent e)
		{
			//Gets coordinates of mouse, and assigns it to mouse coordinate variables.
			int mouseX = e.getX();
			int mouseY = e.getY();

			//Sends mouse coordinates to game function.
			mouseExit(mouseX,mouseY);
		}
	}

	//This inner private class detects mouse movement and location by the user.
	private class GameMouseMotionListener extends Frame implements MouseMotionListener
	{
		//Activates when mouse moves within the window.
		@Override
		public void mouseMoved(MouseEvent e)
		{
			//Gets coordinates of mouse, and assigns it to mouse coordinate variables.
			int mouseX = e.getX();
			int mouseY = e.getY();

			//Sends mouse coordinates to game function.
			mouseMovement(mouseX,mouseY);
		}  

		//Activates when mouse is moved while button is pressed within the window.
		@Override
		public void mouseDragged(MouseEvent e)
		{
			//Gets coordinates of mouse, and assigns it to mouse coordinate variables.
			int mouseX = e.getX();
			int mouseY = e.getY();

			//Figures out which button on mouse was pressed.
			//0 means left, 1 means middle, 2 means right.
			int mouseButton = -1;
			if (SwingUtilities.isLeftMouseButton(e) == true)
				mouseButton = 0;
			else if (SwingUtilities.isMiddleMouseButton(e) == true)
				mouseButton = 1;
			else if (SwingUtilities.isRightMouseButton(e) == true)
				mouseButton = 2;

			//Sends mouse coordinates to game function.
			mouseDragging(mouseX,mouseY,mouseButton);
		}
	}

	//This inner private class detects mouse wheel movement by the user.
	private class GameMouseWheelListener extends JPanel implements MouseWheelListener
	{
		//Activates when mouse wheel has moved.
		@Override
		public void mouseWheelMoved(MouseWheelEvent e)
		{
			//Sends mouse wheel status to game function.
			//-1 means downwards, 1 means upwards.
			mouseWheel(e.getWheelRotation());
		}
	}
}