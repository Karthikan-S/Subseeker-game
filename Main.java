import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main extends JFrame
{
	public Main()
	{
		//Main's constructor calls the function that initializes the game.
		initUI();
	}

	private void initUI()
	{
		//Puts the Game to the center of the JFrame container.
		add(new Game());

		//Sets the size of the window.
		setSize(400, 300);

		//Sets whether the frame can be resized.
		setResizable(false);

		//Sets the title of the window.
		setTitle("Subseeker");

		//This will close the application when the user clicks on the close button.
		//It is not the default behaviour.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Passing null to the setLocationRelativeTo() method centers the window on the screen.
		setLocationRelativeTo(null);
	}	
	
	public static void main(String[] args)
	{
		//This is the entry point of the game. Main's constructor is called here.
		EventQueue.invokeLater(() -> {JFrame ex = new Main(); ex.setVisible(true);});
	}
}