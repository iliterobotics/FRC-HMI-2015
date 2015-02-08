package org.ilite.telemetry.data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ServerConnector implements Runnable {
	private Socket serverSocket;

	private boolean isRunning;
	
	private static ServerConnector serverConnector;
	
	public static ServerConnector getInstance()
	{
		if(serverConnector!=null)
		{
			serverConnector = new ServerConnector();
		}
			
		return serverConnector;
	}
	
	protected ServerConnector() 
	{
		isRunning = false;
	}
	
	public boolean startConnector()
	{
		if(!isRunning)
		{
			(new Thread(this)).start();
			isRunning = true;
		}
		
		return isRunning;
	}
	
	public boolean stopConnector()
	{
		if(isRunning)
			isRunning = false;
		return isRunning;
	}

	@Override
	public void run() 
	{
		ObjectInputStream clientInputStream = null;
		try {
			serverSocket = new Socket("team-1885.local", 4444);

			clientInputStream = new ObjectInputStream(
					serverSocket.getInputStream());

			Object msg;
			while (isRunning) 
			{
				msg = clientInputStream.readObject();

				System.out.println(msg);
				// invoke data listeners
			}
		}
		catch (IOException e) {		e.printStackTrace();	}
		catch (ClassNotFoundException e) {		e.printStackTrace();	}
		
		try {
			serverSocket.close();
			clientInputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		isRunning = false;
	}
}
