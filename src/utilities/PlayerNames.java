package utilities;

import java.io.Serializable;
import java.util.ArrayList;


public class PlayerNames implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7040740943003821308L;
	
	private String[] names = new String[2];
	
	
	public PlayerNames()
	{

	}

	public void setName(int i, String name)
	{
		names[i] = name;
	}
	
	public String getName(int i)
	{
		return names[i];
	}

}
