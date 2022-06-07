package clientside;

import java.awt.Font;
import java.awt.HeadlessException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import main.Game;
import management.GameManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import uiobjects.Background;
import uiobjects.ButtonPanel;
import uiobjects.GameBoard;
import uiobjects.TextArea;
import utilities.FontMaster;
import utilities.InputListener;
import utilities.Message;
import utilities.PlayerNames;


/**
 * @author Tim Murray 396368
 *
 */
public class MainGame extends BasicGameState implements Observer
{
		
	//CONSTANTS===========================================================
	
	private final float COLOR_CHANGE_SPEED = 0.005f;
	
	//====================================================================
	
	
	
	
	//NETWORK CONTROL=====================================================
	
	private Socket				socket;
	private ObjectOutputStream 	oos;
	private InputListener		listener;
	
	private ArrayList<String>playerNames = new ArrayList<String>();
	
	//====================================================================
	
	
	
	
	//PROGRAM FLOW CONTROL================================================
	
	private boolean firstRun = true;
	
	//====================================================================
	
	
	
	//==GAME OBJECTS======================================================
	
	private Background bg;
	private GameBoard board;
	private TextArea textArea;
	private ButtonPanel bp;
	private GameManager gm;
	
	//====================================================================
	
	
	
	//FONTS AND FONT BUILDERS=============================================

	private FontMaster fm = new FontMaster();
	
	private TrueTypeFont buttonFont1;
	
	//====================================================================
	
	
	
	
	//COLOURS AND COLOUR EFFECT HANDLING==================================
	
	private Color xColor = new Color(1.0f, 0.25f, 0.25f);
	private Color oColor = new Color(0.50f, 0.75f, 1.0f);
	private boolean colorIncreasing = true;
	
	//====================================================================
	
	
	
	/**Constructor for a client MainGame. This  contains the methods that run the actual game loop.
	 * @param id
	 */
	public MainGame(int id)
	{
		
	}

	
	
	
	
	
	
	//THESE METHODS ARE IMPLEMENTED BY BasicGameState! These function as the actual game loop itself.
	//=========================================================================================================
	//=========================================================================================================
	
	
	//INIT - runs in a separate thread and can cause problems. I use loadInitialData() instead.
	//=========================================================================================================
	
	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException
	{
		
	}
	
	
	

	
	
	
	//RENDER - for drawing to the screen
	//=========================================================================================================
	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException
	{
		if (firstRun == true)
			loadInitialData(gc, g);
				
		bg.draw(gc, g);
		board.draw(gc, g, xColor, oColor);
		textArea.draw(gc, g);
		
		g.setFont(buttonFont1);
		g.setColor(Color.black);
		bp.render(gc, g, buttonFont1);
		
		if (playerNames.get(0) != null)
			g.drawString(playerNames.get(0), 10, 1000);
		
		if (playerNames.get(1) != null)
			g.drawString(playerNames.get(1), 10, 1040);
	}

	
	
	//UPDATE - for handling anything that changes continually, such as animations.
	//=========================================================================================================	
	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException
	{
//		textArea.getTextFields().get(textArea.getNumTextFields()-1).setFocus(true);
		
		handleColorEffects();
	}

	
	/**Method that calls all colour effect operational methods.
	 * 
	 */
	private void handleColorEffects()
	{
		
		rotateBackgroundColours();

		xColor = getXColor();
		oColor = getOColor();	
	}
	
	
	
	//getID - for getting the state ID of this part of the game
	//=========================================================================================================
	@Override
	public int getID()
	{	
		return Game.mainGame;
	}
	
	
	//=========================================================================================================
	//=========================================================================================================
	
	
	
	
	
	/**This method loads the background and other graphics when the game is first started up.
	 * @param gc
	 * @param g
	 */
	private void loadInitialData(GameContainer gc, Graphics g)
	{
		bg = new Background(gc, g);	//Load the background images which change colour behind other game elements.
		board = new GameBoard(gc, g); //Load and initialize the game grid and associated variables
		textArea = new TextArea(gc, g); //Build a text area out of textfields.
		bp = new ButtonPanel(gc, g); //A virtual panel which holds all of the UI buttons in one object
		gm = new GameManager(); //An object that contains operational variables and methods for handling gameplay.
		
		//FONTS=====================
		buttonFont1 = fm.createFont("Arial", Font.BOLD, 14); //Font used for rendering text onto buttons
		
		createConnection();
		
		playerNames.add("X");
		playerNames.add("O");
		
		firstRun = false; //Used to prevent re-initializing after the game has loaded up	
	}
	
	
	private void createConnection()
	{
		
		try
		{
			
			socket = new Socket("localhost",5555);
			
			//Sends messages from the client to the server
			oos = new ObjectOutputStream(socket.getOutputStream());
			
			//start an input listener thread
			listener = new InputListener(socket,MainGame.this); //Listens for input from the server
			Thread t1 = new Thread(listener);
			t1.start();			
		}
		
		
		catch (HeadlessException e1)
		{
			e1.printStackTrace();
		}
		catch (UnknownHostException e1)
		{
			e1.printStackTrace();
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}
	
	private void animateNewMove()
	{
		
	}
	
	/**Generate the color for the X images for the next rendered frame of gameplay
	 * @return the color of the X images, used when rendering the GameBoard
	 */
	private Color getXColor()
	{
		Color colorX = xColor;
		
		//Once we've reached the brightest shade we want, start going down
		if ( (colorX.r > 0.99f) && (colorIncreasing == true) )
			colorIncreasing = false;
		
		
		
		//Once we've reached the darkest shade we want, start going up!
		else if ( (colorX.r <= 0.50f) && (colorIncreasing == false) ) 
		{
			colorIncreasing = true;
		}
	
		//If we want to make the colour brighter...
		if (colorIncreasing == true)
		{
			//Increase the RGB values of the colour by whatever we want, at the rate of COLOR_CHANGE_SPEED (higher = faster)
			colorX.r = colorX.r + COLOR_CHANGE_SPEED;
			colorX.g = colorX.g + (COLOR_CHANGE_SPEED)/2;
			colorX.b = colorX.b + (COLOR_CHANGE_SPEED)/2;
		}
		
		//If we want to make the colour darker...
		else if (colorIncreasing == false)
		{
			//DECREASE the RGB values of the colour by whatever we want, at the rate of COLOR_CHANGE_SPEED.
			//the DECREASE RATE should be the same as the INCREASE RATE for maximum stability.
			colorX.r = xColor.r - COLOR_CHANGE_SPEED;
			colorX.g = colorX.g - (COLOR_CHANGE_SPEED)/2;
			colorX.b = colorX.b - (COLOR_CHANGE_SPEED)/2;
		}

		//Now that we've generated our new colour, we can give it to the render method to use!
		return colorX;
	}
	
	
	
	/**Generate the color for the O images for the next rendered frame of gameplay
	 * @return the color of the O images, used when rendering the GameBoard
	 */
	private Color getOColor()
	{
		Color colorO = oColor;
		
		//Once we've reached the top, start going down
		if ( (colorO.b > 0.99f) && (colorIncreasing == true) )
			colorIncreasing = false;
		
		//Once we've reached the bottom, start going up!
		else if ( (colorO.b <= 0.50f) && (colorIncreasing == false) ) 
		{
			colorIncreasing = true;
		}
	
		if (colorIncreasing == true)
		{
			colorO.b = colorO.b + COLOR_CHANGE_SPEED;
			colorO.g = colorO.g + (COLOR_CHANGE_SPEED)/2;
			colorO.r = colorO.r + (COLOR_CHANGE_SPEED)/2;
		}
		
		else if (colorIncreasing == false)
		{
			colorO.b = colorO.b - COLOR_CHANGE_SPEED;
			colorO.g = colorO.g - (COLOR_CHANGE_SPEED)/2;
			colorO.r = colorO.r - (COLOR_CHANGE_SPEED)/2;
		}

		return colorO;
	}
	
	
	/**Change the various backgrounds' alpha (transparency) values each frame (loop which runs ~60 times per second).
	 * This change simulates the effect of a rotating colour in the background.
	 */
	private void rotateBackgroundColours()
	{
		
		//Store the current and next active images that the background will be blending together.
		int i = bg.getActiveImage();
		Image currImg = bg.getBgList().get(i);
		Image nextImg = null;
		
		
		
		//Store the previous image so we can fade it out later
		Image prevImg = null;
		
		
		//Determine the next image. Jump to the beginning of the list of images once we're at the end.
		if (i < bg.getBgList().size()-1)
		{
			nextImg = bg.getBgList().get(i+1);
		}
		
		else if (i >= bg.getBgList().size()-1)
		{
			nextImg = bg.getBgList().get(0);
		}
		
		
		
		
		//Determine the previous image to work with. Jump to the beginning of the list of images once we're at the end.
		if (i > 0)
		{
			prevImg = bg.getBgList().get(i-1);
		}
		
		else if (i <= 0)
		{
			prevImg = bg.getBgList().get(bg.getBgList().size()-1);
		}
	

		
		
		
		//Fade out the previous image, reseting it for the next cycle.
		if (prevImg.getAlpha() > 0.0f)
		{
			prevImg.setAlpha(currImg.getAlpha() - (0.4f));
		}
		
		
		
		
		//Decrease the current image's alpha. Increase the next image's alpha
		currImg.setAlpha(currImg.getAlpha() - (0.5f / (float)bg.getImageDuration()));
		nextImg.setAlpha(nextImg.getAlpha() + (1.0f / (float)bg.getImageDuration()));
		
		
		
		
		//Once the next image's alpha reaches 1.0 (maximum), switch that image to the current image and continue the cycle.
		if (nextImg.getAlpha() >= 0.99f)
		{
			prevImg.setAlpha(0.0f); //Make sure the previous image is completely faded out to make sure the cycle continues normally.
			
			
			//Set the old "next image" as the new "current image", now that the old 'next image' has reached full alpha.
			if (i < bg.getBgList().size()-1)
			{
				
				bg.setActiveImage(i+1);
			}
			
			else if (i >= bg.getBgList().size()-1)
			{
				bg.setActiveImage(0);
			}	
		}
	
	}
	
	private void turnOver()
	{
		gm.checkIfGameOver(board.getMoves());
	}
	
	public void mouseClicked (int button, int x, int y, int clickCount)
	{
		//Loop through the board and place the correct object there if it's that player's turn
		if (gm.getTurn() == TitleScreen.player.getGamePiece())
		{
			
			for (int i = 0; i < 9; i++)
			{
				//If the mouse is over a certain button...
				if (board.getButtons().get(i).getMoa().isMouseOver())
				{
					board.setMove(i, TitleScreen.player.getGamePiece());
				}
				
				turnOver();
				
			} //end FOR		
			
		} //end IF (it's this player's turn...)
		
		//SEND BUTTON...
		if (bp.getSendButton().getMoa().isMouseOver())
		{
			
			//If the text area is not empty, submit it
			if (!textArea.getTextFields().get(28).getText().equals(""))
			{
				Message m = new Message(TitleScreen.player.getName(),textArea.getTextFields().get(28).getText(),new Date());

				try
				{
					oos.writeObject(m);
					textArea.getTextFields().get(28).setText("");
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}
			}
			
		}
		
	}//end mouseClicked()





	private void ifMessage(Object o)
	{
		if (o instanceof Message)
		{
			Message m = (Message) o;
			
			for (int i = 0; i <= 26; i++)
			{			
				textArea.getTextFields().get(i).setText( textArea.getTextFields().get(i+1).getText() );
			}
			
			String msg = m.getUser() + ": " + m.getMsg();
			textArea.getTextFields().get(27).setText(msg);
		}
	}
	
	
	private void ifPlayerName(Object o)
	{
		if (o instanceof PlayerNames)
		{
			PlayerNames names = (PlayerNames) o;
		
			for (int i = 0; i <= 1; i++)
			{
				if (names.getName(i) != null)
					playerNames.add(names.getName(i));
			}
	
		}
	}

	@Override
	public void update(Observable arg0, Object o)
	{
		ifMessage(o);
		ifPlayerName(o);
		
		
	}
	
} //end class
