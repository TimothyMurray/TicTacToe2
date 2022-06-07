package uiobjects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.MouseOverArea;

import utilities.Txt;


public class Button
{

	private MouseOverArea moa;
	
	private Image normalImage;
	private Image mouseOverImage;
	private Image mouseDownImage;
	
	private int x;
	private int y;
	
	private int width;
	private int height;
	private String buttonText;
	
	
	
	public Button(GameContainer gc, String normalImageString, int x, int y, int width, int height, String buttonText)
	{
		try
		{
			//Create a mouseOverArea and give it a default image
			this.normalImage = new Image(normalImageString);
			normalImage = normalImage.getScaledCopy(width, height);
			this.moa = new MouseOverArea(gc, this.normalImage, x, y, width, height);
			
			
			//Remove the .png from the filename and replace it with Hover.png for the overImage filename
			String mouseOverString = normalImageString.substring(0, normalImageString.length()-7);
			mouseOverString = mouseOverString + "Hover.png";
			
			//Create the hover image and set the mouseOverImage to the hover filename
			mouseOverImage = new Image(mouseOverString);
			mouseOverImage = mouseOverImage.getScaledCopy(width, height);
			moa.setMouseOverImage(mouseOverImage);
			
			//Remove the .png from the original filename and replace it with On.png for the overImage filename
			String mouseDownString = normalImageString.substring(0, normalImageString.length()-7);
			mouseDownString = mouseDownString + "On.png";
			
			//Create the hover image and set the mouseOverImage to the hover filename
			mouseDownImage = new Image(mouseDownString);
			mouseDownImage = mouseDownImage.getScaledCopy(width, height);
			moa.setMouseDownImage(mouseDownImage);
			
			this.buttonText = buttonText;
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}
		
		catch (SlickException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	
	/**Method to return the MouseOverArea with the same dimensions as the button object
	 * @return the moa
	 */
	public MouseOverArea getMoa()
	{
		return moa;
	}



	
	/**Method to set the MouseOverArea for this Button.
	 * @param moa the moa to set
	 */
	public void setMoa(MouseOverArea moa)
	{
		this.moa = moa;
	}



	
	/**Method to return the value of normalImage
	 * @return the normalImage
	 */
	public Image getNormalImage()
	{
		return normalImage;
	}



	
	/**Method to return the value of normalImage
	 * @param normalImage the normalImage to set
	 */
	public void setNormalImage(Image normalImage)
	{
		this.normalImage = normalImage;
	}



	
	/**Method to return the value of mouseOverImage
	 * @return the mouseOverImage
	 */
	public Image getMouseOverImage()
	{
		return mouseOverImage;
	}



	
	/**Method to return the value of mouseOverImage
	 * @param mouseOverImage the mouseOverImage to set
	 */
	public void setMouseOverImage(Image mouseOverImage)
	{
		this.mouseOverImage = mouseOverImage;
	}



	
	/**Method to return the value of mouseDownImage
	 * @return the mouseDownImage
	 */
	public Image getMouseDownImage()
	{
		return mouseDownImage;
	}



	
	/**Method to return the value of mouseDownImage
	 * @param mouseDownImage the mouseDownImage to set
	 */
	public void setMouseDownImage(Image mouseDownImage)
	{
		this.mouseDownImage = mouseDownImage;
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



	
	/**Method to return the value of width
	 * @return the width
	 */
	public int getWidth()
	{
		return width;
	}



	
	/**Method to return the value of width
	 * @param width the width to set
	 */
	public void setWidth(int width)
	{
		this.width = width;
	}



	
	/**Method to return the value of height
	 * @return the height
	 */
	public int getHeight()
	{
		return height;
	}



	
	/**Method to return the value of height
	 * @param height the height to set
	 */
	public void setHeight(int height)
	{
		this.height = height;
	}
	
	/**Method to get the text displayed on this Button
	 * @return
	 */
	public String getButtonText()
	{
		return buttonText;
	}
	
	/**Method to set the text displayed on this Button
	 * @param text
	 */
	public void setButtonText(String text)
	{
		this.buttonText = text;
	}
	
	/**Method to render this Button (minus its text) to the screen.
	 * To print the text as well, use renderWithtext().
	 * @param gc
	 * @param g
	 */
	public void render (GameContainer gc, Graphics g)
	{
		this.getMoa().render(gc, g);
	}


	/**Method to render this Button, complete with its text, the screen.
	 * @param gc
	 * @param g
	 * @param f
	 */
	public void renderWithText (GameContainer gc, Graphics g, TrueTypeFont f)
	{
		this.getMoa().render(gc, g);
		
		float x = Txt.centerTextHorizontal(this, f);
		float y = Txt.centerTextVertical(this, f);
		
		g.drawString(this.getButtonText(), x, y);
	}
}
