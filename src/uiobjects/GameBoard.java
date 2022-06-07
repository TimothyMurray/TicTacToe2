package uiobjects;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import utilities.ScreenPosition;


public class GameBoard
{

	public static final int X = 1;
	public static final int O = 2;
	public static final int EMPTY = 0;
	
	private Image borderImage;
	private int x;
	private int y;
	
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private ArrayList<Image> xImages = new ArrayList<Image>();
	private ArrayList<Image> oImages = new ArrayList<Image>();
	
	private int[] moves = new int[9];
	private Image xImage;
	private Image oImage;
	
	private ScreenPosition s = new ScreenPosition();
	
	public GameBoard(GameContainer gc, Graphics g)
	{
		try
		{
			borderImage = new Image("images/board1.png");
			
			for (int i = 0; i < 9; i++)
			{
				xImage = new Image("images/x.png");
				xImage = xImage.getScaledCopy(s.screenX(220, gc.getWidth()), s.screenY(220, gc.getHeight()));
				xImages.add(xImage);
								
				oImage = new Image("images/o.png");
				oImage = oImage.getScaledCopy(s.screenX(220, gc.getWidth()), s.screenY(220, gc.getHeight()));
				oImages.add(oImage);
				
				
			/*	//TESTING============
				moves[0] = O;
				moves[2] = O;
				moves[4] = O;
				moves[6] = O;
				moves[8] = O;
				moves[1] = X;
				moves[3] = X;
				moves[5] = X;
				moves[7] = X;
				//==================
			*/
				
			}
			
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
		
		//Resize the image to be the same proportions no matter the user's screen resolution
		borderImage = borderImage.getScaledCopy(s.screenX(1044, gc.getWidth()), s.screenY(770, gc.getHeight()));
				
		//Place the buttons into the board:
		for (int o = 0; o < 3; o++)
		{
			
			for (int i = 0; i < 3; i++)
			{
				
				Button b = new Button(gc, "buttons/yellowButtonOff.png", s.screenX( 100 + (346*i), gc.getWidth()), s.screenY( 98 + (254*o), gc.getHeight() ), s.screenX(336, gc.getWidth()), s.screenY(246, gc.getHeight()), "");
				buttons.add(b);
				
			}
		}
	}
	
	public void draw (GameContainer gc, Graphics g, Color xColor, Color oColor)
	{
		
		
		//Draw the board itself
		borderImage.draw(s.screenX(100, gc.getWidth()), s.screenY(90, gc.getHeight()));
		
		//Position the buttons and X/O graphics inside the game board
		for (int o = 0; o < 3; o++)
		{
			for (int i = 0; i < 3; i++)
			{
			//	buttons.get((o*3) + i).render(gc, g); //UNCOMMENT TO DRAW BUTTONS
				
				//Draw the X and O graphics based on the moves[] array data.
				if (moves[(o*3) + i] == X)
				{
					xImages.get(i).draw(s.screenX( 160 + (346*i), gc.getWidth()), s.screenY( 110 + (254*o), gc.getHeight() ), xColor);
				}
				
				else if (moves[(o*3) + i] == O)
				{
					oImages.get(i).draw(s.screenX( 160 + (346*i), gc.getWidth()), s.screenY( 110 + (254*o), gc.getHeight() ), oColor);
				}
			}
		}

	}

	
	/**Method to return the value of borderImage
	 * @return the borderImage
	 */
	public Image getBorderImage()
	{
		return borderImage;
	}

	
	/**Method to return the value of borderImage
	 * @param borderImage the borderImage to set
	 */
	public void setBorderImage(Image borderImage)
	{
		this.borderImage = borderImage;
	}

	
	/**Method to return the value of x
	 * @return the x
	 */
	public int getX()
	{
		return x;
	}

	
	/**Method to return the value of x
	 * @param x the x to set
	 */
	public void setX(int x)
	{
		this.x = x;
	}

	
	/**Method to return the value of y
	 * @return the y
	 */
	public int getY()
	{
		return y;
	}

	
	/**Method to return the value of y
	 * @param y the y to set
	 */
	public void setY(int y)
	{
		this.y = y;
	}

	
	/**Method to return the value of buttons
	 * @return the buttons
	 */
	public ArrayList<Button> getButtons()
	{
		return buttons;
	}

	
	/**Method to return the value of buttons
	 * @param buttons the buttons to set
	 */
	public void setButtons(ArrayList<Button> buttons)
	{
		this.buttons = buttons;
	}

	
	/**Method to return the value of xImages
	 * @return the xImages
	 */
	public ArrayList<Image> getxImages()
	{
		return xImages;
	}

	
	/**Method to return the value of xImages
	 * @param xImages the xImages to set
	 */
	public void setxImages(ArrayList<Image> xImages)
	{
		this.xImages = xImages;
	}

	
	/**Method to return the value of oImages
	 * @return the oImages
	 */
	public ArrayList<Image> getoImages()
	{
		return oImages;
	}

	
	/**Method to return the value of oImages
	 * @param oImages the oImages to set
	 */
	public void setoImages(ArrayList<Image> oImages)
	{
		this.oImages = oImages;
	}

	
	/**Method to return the value of moves
	 * @return the moves
	 */
	public int[] getMoves()
	{
		return moves;
	}
	
	/**Method to return the value of a move at a specific location in the grid
	 * @param i
	 * @return
	 */
	public int getMove(int i)
	{
		return moves[i];
	}

	
	/**Method to return the value of moves
	 * @param moves the moves to set
	 */
	public void setMoves(int[] moves)
	{
		this.moves = moves;
	}
	
	public void setMove(int index, int move)
	{
		this.moves[index] = move;
	}

	
	/**Method to return the value of xImage
	 * @return the xImage
	 */
	public Image getxImage()
	{
		return xImage;
	}

	
	/**Method to return the value of xImage
	 * @param xImage the xImage to set
	 */
	public void setxImage(Image xImage)
	{
		this.xImage = xImage;
	}

	
	/**Method to return the value of oImage
	 * @return the oImage
	 */
	public Image getoImage()
	{
		return oImage;
	}

	
	/**Method to return the value of oImage
	 * @param oImage the oImage to set
	 */
	public void setoImage(Image oImage)
	{
		this.oImage = oImage;
	}

	
	/**Method to return the value of O
	 * @return the o
	 */
	public int getO()
	{
		return O;
	}

	
	/**Method to return the value of EMPTY
	 * @return the EMPTY
	 */
	public int getEMPTY()
	{
		return EMPTY;
	}

	
	
	
}
