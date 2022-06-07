package uiobjects;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;

import utilities.FontMaster;
import utilities.ScreenPosition;


public class TextArea
{

	private final int numTextFields = 29;
	
	private ArrayList<TextField>textFields = new ArrayList<TextField>();
	FontMaster fm = new FontMaster();
	ScreenPosition s = new ScreenPosition();
	
	TrueTypeFont textAreaFont1;
	
	public TextArea(GameContainer gc, Graphics g)
	{
		
		
		textAreaFont1 = fm.createFont("Arial", Font.PLAIN, 14);
		
		//Build a textArea out of a bunch of text fields
		for (int i = 0; i < numTextFields; i++)
		{

			if (i != numTextFields-1)
			{
				TextField tf = new TextField(gc, textAreaFont1, s.screenX(1200, gc.getWidth()), s.screenY(102, gc.getHeight()) + (s.screenY(26*i, gc.getHeight())), s.screenX(620, gc.getWidth()), s.screenY(27, gc.getHeight()));
				tf.setAcceptingInput(false);
				textFields.add(tf);
			}
			
			//The last textfield is for player text entry. Place it separately.
			else if (i == numTextFields-1)
			{
				TextField tf = new TextField(gc, textAreaFont1, s.screenX(1200, gc.getWidth()), s.screenY(102, gc.getHeight()) + (s.screenY(26*(i+1), gc.getHeight())), s.screenX(620, gc.getWidth()), s.screenY(27, gc.getHeight()));
				textFields.add(tf);
			}
		}
	}
	
	public void draw(GameContainer gc, Graphics g)
	{
		for (int i = 0; i < numTextFields; i++)
		{
			textFields.get(i).setBackgroundColor(Color.white);
			textFields.get(i).setTextColor(Color.black);
			
			g.setColor(Color.white);
			textFields.get(i).render(gc, g);
		}
	}

	
	/**Method to return the value of textFields
	 * @return the textFields
	 */
	public ArrayList<TextField> getTextFields()
	{
		return textFields;
	}

	
	/**Method to return the value of textFields
	 * @param textFields the textFields to set
	 */
	public void setTextFields(ArrayList<TextField> textFields)
	{
		this.textFields = textFields;
	}

	
	/**Method to return the value of textAreaFont1
	 * @return the textAreaFont1
	 */
	public TrueTypeFont getTextAreaFont1()
	{
		return textAreaFont1;
	}

	
	/**Method to return the value of textAreaFont1
	 * @param textAreaFont1 the textAreaFont1 to set
	 */
	public void setTextAreaFont1(TrueTypeFont textAreaFont1)
	{
		this.textAreaFont1 = textAreaFont1;
	}

	
	/**Method to return the value of numTextFields
	 * @return the numTextFields
	 */
	public int getNumTextFields()
	{
		return numTextFields;
	}

}
