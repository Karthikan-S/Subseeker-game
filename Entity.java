import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.util.Random;

public class Entity
{
	/////////////
	//VARIABLES//
	/////////////

	private Image image;
	private Image user;
	private Image enemy;
	private int positionX;
	private int positionY;
	private int directionX;
	private int directionY;
	private int width;
	private int height;
	
	/////////////
	//FUNCTIONS//
	/////////////

	//An empty constructor.
	public Entity()
	{
	}

	//Initializes/resets the entity.
	public void init()
	{
		//Start entity's position at location (50, 50).
		this.positionX = 350;
		this.positionY = 100;

		//Call entity's method that loads its image.
		this.loadImage();
	}

	public void init(int startX, int startY)
	{
		this.positionX = startX;
		this.positionY = startY;

		this.height = 20;
		this.width = 20;

		if(positionX == 50 && positionY ==50)
		{
		this.loadImage1();
		}

		else 
		{
		this.loadImage2();
		}

	}

	//Loads the entity's image into the game, and sets its length and width.
	private void loadImage()
	{
		//Uses the ImageIcon class to load the image file into the program.
		ImageIcon file = new ImageIcon("src/resources/submarine.png");
		//ImageIcon shark = new ImageIcon("src/resources/shark.png");

		//Assigns the image data to its image variable reference.
		this.image = file.getImage();
		//this.enemy = shark.getImage();
		
		//Sets the entity's width and height to be that of the image just loaded in.
		this.width = this.image.getWidth(null);
		this.height = this.image.getHeight(null);

		//this.width = this.enemy.getWidth(null);
		//this.height = this.enemy.getHeight(null);

		//Resizes the image to 50 width and 67 height.
		this.width = 50;
		this.height = 67;
		this.image = this.image.getScaledInstance(this.width, this.height, Image.SCALE_DEFAULT);

		//this.width = 50;
		//this.height = 67;
		//this.enemy = this.enemy.getScaledInstance(this.width, this.height, Image.SCALE_DEFAULT);
	}

	private void loadImage1()
	{
		ImageIcon scuba = new ImageIcon("src/resources/scuba_diver.png");
		this.user = scuba.getImage();
		this.width = this.user.getWidth(null);
		this.height = this.user.getHeight(null);
		this.width = 50;
		this.height = 67;
		this.user = this.user.getScaledInstance(this.width, this.height, Image.SCALE_DEFAULT);

	}

	private void loadImage2()
	{
		ImageIcon shark = new ImageIcon("src/resources/shark.png");
		this.enemy = shark.getImage();
		this.width = this.enemy.getWidth(null);
		this.height = this.enemy.getHeight(null);
		this.width = 50;
		this.height = 67;
		this.enemy = this.enemy.getScaledInstance(this.width, this.height, Image.SCALE_DEFAULT);

	}

	//Changes the position coordinates of the sprite.
	//The positionX and positionY values are later used in the Game's paintComponent() method to draw the image of the sprite.
	public void step()
	{
		//Changes the entity's position, based on the user inputted direction.
		this.positionX += this.directionX;
		this.positionY += this.directionY;
	}

	public void draw(Graphics2D g2d, Game game)
	{
		//Draws the entity's image in the program window, at its x and y location.
		g2d.drawImage(this.image, this.positionX, this.positionY, game);
		g2d.drawImage(this.user, this.positionX, this.positionY, game);
		g2d.drawImage(this.enemy, this.positionX, this.positionY, game);
		g2d.drawRect(this.positionX, this.positionY, this.height, this.width);
	}

	//Collision detection between player and bomb
	public boolean collisionDetection(Entity other)
	{
		//player Dimensions
		int playerLeft = this.positionX;
		int playerRight = this.positionX + this.width;
		int playerTop = this.positionY;
		int playerBottom = this.positionY + this.height;

		//shark Dimensions
		int bombLeft = other.positionX;
		int bombRight = other.positionX + other.width;
		int bombTop = other.positionY;
		int bombBottom = other.positionY + other.height;

		//Check if player is in-between bomb on the x-axis.
		boolean collideX = playerLeft < bombRight && playerRight > bombLeft;

		//Check if player is in-between bomb on the y-axis.
		boolean collideY = playerTop < bombBottom && playerBottom > bombTop;

		//If it collide on both axes, then return true. Otherwise, return false.
		if (collideX && collideY)
			return true;
		else
			return false;
	}


	//When we press a keyboard arrow key, we set its respective direction variable to a value of 2.
	//This will make the spacecraft move that direction.
	public void keyPress(int key)
	{
		//All if-statements are seperate, so that multiple keys can be pressed at the same time.
		if (key == KeyEvent.VK_LEFT)
		{
			this.directionX = -2;
		}
		if (key == KeyEvent.VK_RIGHT)
		{
			this.directionX = 2;
		}
		if (key == KeyEvent.VK_UP)
		{
			this.directionY = -2;
		}
		if (key == KeyEvent.VK_DOWN)
		{
			this.directionY = 2;
		}
	}

	//When we release a keyboard arrow key, we set its respective direction variable to zero.
	//This will stop the spacecraft from moving that direction.
	public void keyRelease(int key)
	{
		//All if-statements are seperate, so that multiple keys can be pressed at the same time.
		if (key == KeyEvent.VK_LEFT)
		{
			this.directionX = 0;
		}
		if (key == KeyEvent.VK_RIGHT)
		{
			this.directionX = 0;
		}
		if (key == KeyEvent.VK_UP)
		{
			this.directionY = 0;
		}
		if (key == KeyEvent.VK_DOWN)
		{
			this.directionY = 0;
		}
	}

	//Getter functions.
	public int getPositionX()
	{
		return this.positionX;
	}
	public int getPositionY()
	{
		return this.positionY;
	}
	public int getDirectionX()
	{
		return this.directionX;
	}
	public int getDirectionY()
	{
		return this.directionY;
	}
	public int getWidth()
	{
		return this.width;
	}
	public int getHeight()
	{
		return this.height;
	}
	public Image getImage()
	{
		return this.image;
	}
}