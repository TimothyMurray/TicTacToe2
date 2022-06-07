package clientside;

import java.awt.Font;

import main.Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;

import uiobjects.Button;
import utilities.FontMaster;
import utilities.ScreenPosition;


public class TitleScreen extends BasicGameState
{
	
	public static Player player = new Player("");
	
	//PROGRAM FLOW CONTROL====================================
	
	private boolean firstRun = true;
	private boolean connectToGame = false;
	//========================================================
	
	
	
	//IMAGES==================================================
	
	private Image transparent;
	
	private Image bg1;
	private int bg1X = 0;
	private int bg2X;
	private Image bg2;
	
	private int animationTimer = 0;
	private int animationSpeed = 2;
	
	private int tickPosition = 0;
	private int tackPosition = 0;
	private int toePosition = 0;
	
	//TICK TACK TOE
	private Image tick1;
	private Image tack1;
	private Image toe1;
	
	//TIC-TAC-TOE
	private Image tic2;
	private Image tac2;
	private Image toe2;
	//========================================================
	
	
	
	//UI Objects==============================================
	
	private TextField nameEntry;
	private TextField ipEntry;
	private TextField portEntry;
	
	private Button connectButton;
	
	private ScreenPosition s = new ScreenPosition();
	
	//========================================================

	
	
	
	//FONTS===================================================
	
	private FontMaster fm = new FontMaster();
	
	private TrueTypeFont buttonFont1;
	private TrueTypeFont textFieldFont1;
	private TrueTypeFont labelText1;
	
	//========================================================
	//====================================================================================================
	
	
	
	/**Constructor for a client MainGame. This  contains the methods that run the actual game loop.
	 * @param id
	 */
	public TitleScreen(int id)
	{		
	}
	
	public TitleScreen()
	{
		
	}

	
	
	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException
	{
		// TODO Auto-generated method stub
		
	}

	private void renderBackground(GameContainer gc, Graphics g)
	{
		bg1.draw(bg1X, 0);
		bg2.draw(bg2X, 0);
	}
	
	private void renderCenterPanel(GameContainer gc, Graphics g)
	{
		transparent.draw(s.screenX(700, gc.getWidth()), s.screenY(300, gc.getHeight()));
		
		g.setColor(Color.orange);
		g.setFont(labelText1);
		g.drawString("Player Name:", s.screenX(744, gc.getWidth()), s.screenY(398, gc.getHeight()));
		g.drawString("Server IP:", s.screenX(782, gc.getWidth()), s.screenY(478, gc.getHeight()));
		g.drawString("Server Port:", s.screenX(754, gc.getWidth()), s.screenY(558, gc.getHeight()));
		
		
		g.setColor(Color.white);
		nameEntry.render(gc, g);
		ipEntry.render(gc, g);
		portEntry.render(gc, g);
		
		g.setColor(Color.black);
		g.setFont(buttonFont1);
		connectButton.renderWithText(gc, g, buttonFont1);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException
	{
		if (firstRun == true)
			loadTitleScreen(gc, g);
		
		
		
		//Draw elements to the screen
		renderBackground(gc, g);	
		renderCenterPanel(gc, g);
		
		//Draw the Tick, Tack and Toe images based on their animation position
		tick1.draw(s.screenX(400, gc.getWidth()), tickPosition);
		tack1.draw(s.screenX(800, gc.getWidth()), tackPosition);
		toe1.draw(s.screenX(1300, gc.getWidth()), toePosition);
		
		//Draw the words "Tic Tac Toe" that fade in over time
		tic2.draw(s.screenX(400, gc.getWidth()), tickPosition);
		tac2.draw(s.screenX(800, gc.getWidth()), tackPosition);
		toe2.draw(s.screenX(1300, gc.getWidth()), toePosition);
		
		
		
		if (connectToGame == true)
		{			
			player.setName(nameEntry.getText());		
			game.enterState(Game.mainGame, null, new FadeInTransition());
		}
		
	}

	private void updateBackgroundPosition(GameContainer gc)
	{
		bg1X = bg1X + 1;		
		bg2X = bg2X + 1;

		
		if (bg1X == gc.getWidth())
		{
			bg1X = 0 - gc.getWidth();
		}
		
		if (bg2X == gc.getWidth())
		{
			bg2X = 0 - gc.getWidth();
		}
	}
	
	
	
	private void moveInitialImages(GameContainer gc)
	{
		if (tickPosition < 0)
			tickPosition = tickPosition + animationSpeed;
		
		else if (tickPosition == 0 && tackPosition < 0)
			tackPosition = tackPosition + animationSpeed;
		
		else if (tackPosition == 0 && toePosition < 0)
			toePosition = toePosition + animationSpeed;
		
		else
			fadeInTitleWords();
	}
	
	private void fadeInTitleWords()
	{
		if ( tic2.getAlpha() <= 1.0f )
		{
			tick1.setAlpha( tick1.getAlpha() - 0.01f);
			tic2.setAlpha( tic2.getAlpha() + 0.01f);
			
			tack1.setAlpha( tack1.getAlpha() - 0.01f);
			tac2.setAlpha( tac2.getAlpha() + 0.01f);
			
			toe1.setAlpha( toe1.getAlpha() - 0.01f);
			toe2.setAlpha( toe2.getAlpha() + 0.01f);
		}
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException
	{
		updateBackgroundPosition(gc);
		moveInitialImages(gc);
		
		
		
		animationTimer++;
	}

	@Override
	public int getID()
	{
		// TODO Auto-generated method stub
		return Game.titleScreen;
	}

	
	
	private void loadTitleScreen(GameContainer gc, Graphics g)
	{
		
		bg2X = 0 - gc.getWidth();//Set the position of the 2nd image that will scroll onto the screen
		
		try
		{
			transparent = new Image("images/transparent.png");
			transparent = transparent.getScaledCopy(s.screenX(600, gc.getWidth()), s.screenY(480, gc.getHeight()));
			transparent.setAlpha(0.95f);
		}
		catch (SlickException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Load the background images
		try
		{
			bg1 = new Image("images/titlebg.png");
			bg1 = bg1.getScaledCopy(gc.getWidth(), gc.getHeight());
			
			bg2 = new Image("images/titlebg.png");
			bg2 = bg2.getScaledCopy(gc.getWidth(), gc.getHeight());
			
			tick1 = new Image("images/tick.jpg");
			tick1 = tick1.getScaledCopy(s.screenX(300, gc.getWidth()), s.screenY(240, gc.getHeight()));
			tickPosition = 0 - tick1.getHeight();
			
			tack1 = new Image("images/tack.png");
			tack1 = tack1.getScaledCopy(s.screenX(300, gc.getWidth()), s.screenY(240, gc.getHeight()));
			tackPosition = 0 - tack1.getHeight();
			
			toe1 = new Image("images/toe.jpg");
			toe1 = toe1.getScaledCopy(s.screenX(300, gc.getWidth()), s.screenY(240, gc.getHeight()));
			toePosition = 0 - toe1.getHeight();
			
			tic2 = new Image("images/tic.png");
			tic2 = tic2.getScaledCopy(s.screenX(300, gc.getWidth()), s.screenY(240, gc.getHeight()));
			tic2.setAlpha(0.0f);
			
			tac2 = new Image("images/tac.png");
			tac2 = tac2.getScaledCopy(s.screenX(300, gc.getWidth()), s.screenY(240, gc.getHeight()));
			tac2.setAlpha(0.0f);
			
			toe2 = new Image("images/toe.png");
			toe2 = toe2.getScaledCopy(s.screenX(300, gc.getWidth()), s.screenY(240, gc.getHeight()));
			toe2.setAlpha(0.0f);
		}

		catch (SlickException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Create the fonts used on this screen
		buttonFont1 = fm.createFont("Arial", Font.BOLD, 14);
		textFieldFont1 = fm.createFont("Arial", Font.PLAIN, 14);
		labelText1 = fm.createFont("Arial", Font.BOLD, 16);
		
		
		//Name entry textbox
		nameEntry = new TextField(gc, textFieldFont1, s.screenX(920, gc.getWidth()), s.screenY(400, gc.getHeight()), 
								s.screenX(300, gc.getWidth()), s.screenY(26, gc.getHeight()));
		nameEntry.setBackgroundColor(Color.white);
		nameEntry.setTextColor(Color.black);
		
		
		//IP Entry textbox
		ipEntry = new TextField(gc, textFieldFont1, s.screenX(920, gc.getWidth()), s.screenY(480, gc.getHeight()), 
								s.screenX(240, gc.getWidth()), s.screenY(26, gc.getHeight()));
		
		ipEntry.setBackgroundColor(Color.white);
		ipEntry.setTextColor(Color.black);
		

		//Port Entry textbox
		portEntry = new TextField(gc, textFieldFont1, s.screenX(920, gc.getWidth()), s.screenY(560, gc.getHeight()), 
								s.screenX(160, gc.getWidth()), s.screenY(26, gc.getHeight()));
		
		portEntry.setBackgroundColor(Color.white);
		portEntry.setTextColor(Color.black);
		
		
		//Connect button
		connectButton = new Button(gc, "buttons/yellowButtonOff.png",s.screenX(900, gc.getWidth()), s.screenY(640, gc.getHeight()), 
								s.screenX(200, gc.getWidth()), s.screenY(80, gc.getHeight()), "CONNECT!" );
		
		
		firstRun = false; //Make sure this initialization code only runs once
	}
	
	
	
	public void mouseClicked (int button, int x, int y, int clickCount)
	{
		
		if (connectButton.getMoa().isMouseOver())
		{
			connectToGame = true;					
		} //end IF
		
	}//end mouseClicked()
	
	public Player getPlayer()
	{
		return player;
	}
	
}
