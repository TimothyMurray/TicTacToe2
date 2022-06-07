/**
 * Created on Sep 15, 2010
 *
 * Project: demo06-ThreadedChatServerExample 
 */
package serverside;

import java.util.*;
import java.net.*;
import java.io.*;

import utilities.*;

/**
 * @author dwatson
 * @version 1.0
 */
public class ClientHandler extends Thread implements Observer
{
	//Attributes
	private ObjectOutputStream			oos1,oos2;
	private Socket						cs1,cs2;
	private InputListener				lis1,lis2;
	
	private String[] names = new String[2];
	
	private Move move = new Move();
	
	//Constructors
	/**
	 * @param cs1
	 * @param cs2
	 */
	public ClientHandler(Socket cs1, Socket cs2)
	{
		this.cs1 = cs1;
		this.cs2 = cs2;
		
		try
		{
			oos1 = new ObjectOutputStream(cs1.getOutputStream()); //For writing to the 1st client
			oos2 = new ObjectOutputStream(cs2.getOutputStream()); //For writing to the 2nd client
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		lis1 = new InputListener(1,cs1,this); //Listens for input from client 1
		lis2 = new InputListener(2,cs2,this); //Listens for input from client 2
		
		Thread t1 = new Thread(lis1);
		t1.start();
		Thread t2 = new Thread(lis2);
		t2.start();
		
		try
		{
			Message m = new Message("SERVER","Welcome! You'll be player 'X'. Game starting now!",new Date());
			oos1.writeObject(m);
			oos1.writeObject(move);
			
			m = new Message("SERVER","Welcome! You'll be player 'O'. Game starting now!",new Date());
			oos2.writeObject(m);
			oos2.writeObject(move);
			
			while(cs1.isConnected() && cs2.isConnected());
			
			cs1.close();
			cs2.close();
			oos1.close();
			oos2.close();
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}
	
	
	
	private void ifMessage(Object obj)
	{
		if (obj instanceof Message)
		{
			Message m = (Message)obj;
			
			try
			{				
				oos1.writeObject(m);
				oos2.writeObject(m);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	private void ifName(InputListener l, Object obj)
	{
		if (obj instanceof PlayerNames)
		{
			PlayerNames n = (PlayerNames)obj;
			
			names[l.getListenerNumber()-1] = n.getName(l.getListenerNumber()-1);
			
			try
			{
				oos1.writeObject(names);
				oos2.writeObject(names);
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object obj)
	{
		InputListener listener = (InputListener)o;
		
		//Forward the message to all clients
		ifMessage(obj);
		
		//Grab the player's name and store it on the server side so we can use it later
		ifName(listener, obj);
		
	}
}
