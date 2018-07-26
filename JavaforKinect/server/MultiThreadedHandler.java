package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class MultiThreadedHandler  {
	

	static int numOfClients = 0;
	static int numOfLines = 1;
	
	public static void main(String[] args) {
		
		Frame frame = new Frame("Server");
		Frame.l.setText("Server Started");
		try {

			ServerSocket serverSocket = new ServerSocket(1234);
			while(true) {
			Socket clientsocket = serverSocket.accept();
			if (clientsocket.isConnected())
			{
				numOfClients++;
				String cur = frame.l.getText();
				if (numOfLines >= 15) {
					numOfLines = 0; frame.l.setText("");
				}
				frame.l.setText(cur + "\nWe Got a client: #" + numOfClients);
			
				numOfLines++;
				JointsThreaded thread = new JointsThreaded(clientsocket);
				new Thread(thread).start();
			}
			
			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		System.out.println("End");
		
	}



}
