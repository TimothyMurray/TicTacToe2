/**
 * Created on Sep 15, 2010
 *
 * Project: demo06-ThreadedChatServerExample 
 */
package utilities;

import java.util.*;
import java.io.*;
import java.net.*;

/**
 * @author dwatson
 * @version 1.0
 */
public class InputListener extends Observable implements Runnable
{
	//Attributes
	private int 				listenerNumber;
	private Socket				socket;
	private ObjectInputStream	ois;
	
	// Constructors
	/**
	 * @param socket the socket being monitored
	 * @param obs class to be notified when something changes
	 */
	public InputListener(Socket socket,Observer obs)
	{
		listenerNumber = 0;
		this.socket = socket;
		this.addObserver(obs);
	}
	/**
	 * @param listenerNumber number assigned to this listener
	 * @param socket the socket being monitored
	 * @param obs class to be notified when something changes
	 */
	public InputListener(int listenerNumber, Socket socket, Observer obs)
	{
		this.listenerNumber = listenerNumber;
		this.socket = socket;
		this.addObserver(obs);
	}
	//Getter and Setter Methods
	/**
	 * @return the listenerNumber
	 */
	public int getListenerNumber()
	{
		return listenerNumber;
	}
	/**
	 * @param listenerNumber the listenerNumber to set
	 */
	public void setListenerNumber(int listenerNumber)
	{
		this.listenerNumber = listenerNumber;
	}
	
	//Operational Methods
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		try
		{
			ois = new ObjectInputStream(socket.getInputStream());
			
			while(true)
			{
				System.out.println("input listener number "+listenerNumber);
				Object o = ois.readObject(); //Hang here until the client reads an object from the server
				setChanged();
				notifyObservers(o);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
