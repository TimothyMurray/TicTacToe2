/**
 * Created on Sep 13, 2010
 *
 * Project: demo06-ThreadedChatServerExample
 */
package serverside;

import java.net.*;
import java.io.*;
import java.util.*;

/**
 * @author dwatson
 * @version 1.0
 */
public class Server
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		ArrayList<Socket> socketList = new ArrayList<Socket>(2);
		
		try
		{
			ServerSocket ss = new ServerSocket(5555);
			System.out.println("Server up and running!");
			
			while(true)
			{
				Socket cs = ss.accept(); //Wait for a connection from a client.Accept() returns the client Socket object
				
				socketList.add(cs); //Add that connection to the arraylist
				
				//Once we have 2 players...
				if(socketList.size() == 2)
				{
					ClientHandler ch = new ClientHandler( socketList.get(0),socketList.get(1) );
					ch.start();
					socketList.clear();
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
