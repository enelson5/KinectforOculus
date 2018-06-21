package server;

import edu.ufl.digitalworlds.j4k.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.*;
import java.net.ServerSocket;
import java.net.Socket;



public class HandOpenCloseServer extends J4KSDK{
	
	boolean handOpen;
	int i = 0;
	double wristToHandTipRightY;
	double wristToHandTipLeft;
	int counter = 0;
	static boolean RightHandOpen;
	static boolean LeftHandOpen;
	static double LeftDistance;
	static double RightDistance;
	
	
	 
public static void main(String[] args) {
	
	System.out.println("Start");
	

			HandOpenCloseServer kinect=new HandOpenCloseServer();

			kinect.start(J4KSDK.SKELETON);
			
			try {
				ServerSocket serversocket = new ServerSocket(1345);
				
				Socket clientsocket = serversocket.accept();
				System.out.println("We got a client.");
				
				BufferedReader in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
				
				PrintWriter out = new PrintWriter(clientsocket.getOutputStream(), true);
				while(!clientsocket.isClosed()) {
					
					out.println("{");
					
					if(RightHandOpen == true) {
						out.println("\"Right Hand\": \"Open\"");
					}
					else {
						out.println("\"Right Hand\": \"Closed\"");
					}
					if(LeftHandOpen == true){
						out.println("\"Left Hand\": \"Open\"");
					}
					else {
						out.println("\"Left Hand\": \"Closed\"");
					}
					
					out.println("}");
					
					out.println(RightDistance);
					
					
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			kinect.stop();
			
			System.out.println("End");
		
		
	}
	
	
	
	@Override
	public void onSkeletonFrameEvent(boolean[] skeleton_tracked, float[] positions, float[] orientations, byte[] joint_status) {
		 
		Skeleton mySkeleton[]=new Skeleton[getSkeletonCountLimit()]; 
		    counter ++;
		    
		    if(counter == 1) {
			    
			    for(int j = 0; j < getMaxNumberOfSkeletons(); j++) {
			    	
			    	mySkeleton[j]=Skeleton.getSkeleton(j, skeleton_tracked, positions, orientations, joint_status, this); 
			    	
			    	if(mySkeleton[j].get3DJointZ(1) > 0.00001) {
			    		i = j;
			    	}
			    }
		    
		    }	  
		
		
	 	mySkeleton[i]=Skeleton.getSkeleton(i, skeleton_tracked, positions, orientations, joint_status, this);
	 	
	 	float ThumbLeftX = mySkeleton[i].get3DJointX(22);
	 	float ThumbLeftY = mySkeleton[i].get3DJointY(22);
	 	float HandtipLeftX = mySkeleton[i].get3DJointX(21);
	 	float HandtipLeftY = mySkeleton[i].get3DJointY(21);
	 	float HandtipLeftZ = mySkeleton[i].get3DJointZ(21);
	 	float HandLeftX = mySkeleton[i].get3DJointX(7);
	 	float HandLeftY = mySkeleton[i].get3DJointY(7);
	 	float HandLeftZ = mySkeleton[i].get3DJointZ(7);
	 	float HandRightX = mySkeleton[i].get3DJointX(11);
	 	float HandRightY = mySkeleton[i].get3DJointY(11);
	 	float HandRightZ = mySkeleton[i].get3DJointZ(11);
	 	float HandtipRightX = mySkeleton[i].get3DJointX(23);
	 	float HandtipRightY = mySkeleton[i].get3DJointY(23);
	 	float HandtipRightZ = mySkeleton[i].get3DJointZ(23);
	 	
	 	System.out.println("Hand Right:" + HandRightX);
	 	
	 	LeftDistance = Math.sqrt(Math.pow((HandtipLeftX - HandLeftX),2) + Math.pow((HandtipLeftY - HandLeftY),2) + Math.pow(HandtipLeftZ - HandLeftZ, 2));
	 	RightDistance = Math.sqrt(Math.pow((HandtipRightX - HandRightX),2) + Math.pow((HandtipRightY - HandRightY),2) + Math.pow(HandtipRightZ - HandRightZ, 2));
	 	
	 	
	
	 	
	 	if(LeftDistance < 0.067) {
	 		LeftHandOpen = false;
	 	}
	 	else {
	 		LeftHandOpen = true;
	 	}
	 	
	 	if(RightDistance < 0.067) {
	 		RightHandOpen = false;
	 	}
	 	else {
	 		RightHandOpen = true;
	 	}
    	
	}
	

	@Override
	public void onColorFrameEvent(byte[] color_frame) {
		
		
	
	}

	@Override
	public void onDepthFrameEvent(short[] depth_frame, byte[] body_index, float[] xyz, float[] uv) {
	}
	


}
