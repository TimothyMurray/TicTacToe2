package uiobjects;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.io.File;


public class Background
{

	private ArrayList<Image> bgList = new ArrayList<Image>(); //Holds all the images that will be cycled through.
	
	private int imageDuration = 180; //This is the number of frames each cycle lasts. Game runs at 60 FPS.
	
	private int activeImage = 0; //The currently-active image
	
	//Loop through all the BG files in the folder and create a bunch of images from them
	public Background(GameContainer gc, Graphics g)
	{
		
		//The file name and file ID of the first background image
		int filenum = 1;
		String filename = "bg/" + Integer.toString(filenum) + ".png";
		File f = new File(filename);
		
		
		//While there is another background image to load, load it and store it in the arraylist
		while (f.exists())
		{			
			try
			{
				//Create an Image object by loading a PNG image using the generated filename
				Image i = new Image(filename);
				
				//Resize the image to fit the game's window perfectly
				i = i.getScaledCopy(gc.getWidth(), gc.getHeight());
				
				//If it's not the first image, make it fully transparent.
				if (filenum != 1)					
					i.setAlpha(0);
				
				//Add this final processed image to the arraylist
				bgList.add(i);			
			}
			catch (SlickException e)
			{
				e.printStackTrace();
			}
			
			//Increment the filenumber, which is used to build the filename string
			filenum++;
			
			//Generate a new filename based on the filenum
			filename = "bg/" + Integer.toString(filenum) + ".png";
			
			//Create a new file using this generated filename
			f = new File(filename);
		}
	
	}
	
	
	/**Method to return the value of bgList
	 * @return the bgList
	 */
	public ArrayList<Image> getBgList()
	{
		return bgList;
	}
	
	/**Method to return the value of bgList
	 * @param bgList the bgList to set
	 */
	public void setBgList(ArrayList<Image> bgList)
	{
		this.bgList = bgList;
	}
	
	public Image getImage(int index)
	{
		return bgList.get(index);
	}

		
	/**Method to return the value of imageDuration
	 * @return the imageDuration
	 */
	public int getImageDuration()
	{
		return imageDuration;
	}

	
	/**Method to return the value of imageDuration
	 * @param imageDuration the imageDuration to set
	 */
	public void setImageDuration(int imageDuration)
	{
		this.imageDuration = imageDuration;
	}


	
	/**Method to return the value of activeImage
	 * @return the activeImage
	 */
	public int getActiveImage()
	{
		return activeImage;
	}


	
	/**Method to return the value of activeImage
	 * @param activeImage the activeImage to set
	 */
	public void setActiveImage(int activeImage)
	{
		this.activeImage = activeImage;
	}


	/**Method to loop through all of the background images and draw them to the screen
	 * @param gc
	 * @param g
	 */
	public void draw(GameContainer gc, Graphics g)
	{
		for (Image i : bgList)
		{
			i.draw(0,0);
		}
	}
}
