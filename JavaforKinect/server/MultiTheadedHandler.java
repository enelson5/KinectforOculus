package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class MultiTheadedHandler  {

	static int numOfClients = 0;
	public static void main(String[] args) {

		System.out.println("Start");
		
		try {

			ServerSocket serverSocket = new ServerSocket(1234);
			while(true) {
			Socket clientsocket = serverSocket.accept();
			if (clientsocket.isConnected())
			{
				numOfClients++;
				System.out.println("We got a client: #" + numOfClients);
				JointsThreaded thread = new JointsThreaded(clientsocket);
				new Thread(thread).start();
			}
			
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		System.out.println("End");
		
	}



}
