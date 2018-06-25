package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import edu.ufl.digitalworlds.j4k.J4KSDK;
import edu.ufl.digitalworlds.j4k.Skeleton;
import j4kJointPositions.HandOpenClose;
import j4kJointPositions.JointPositions;
import java.io.FileWriter;
import java.io.IOException;
 

public class jointPositionsServer extends J4KSDK{
	
	private static String fileName = "C:/Users/brian/Documents/json.txt";
	
	int counter = 0;
	int i = 0;
	static String dampenedJointPosition;
	static int c = 0;
	static float prevLeftWristX;
	static float prevLeftWristY;
	static float prevLeftWristZ;
	static float prevLeftElbowX;
	static float prevLeftElbowY;
	static float prevLeftElbowZ;
	static float prevLeftShoulderX;
	static float prevLeftShoulderY;
	static float prevLeftShoulderZ;
	static float prevRightWristX;
	static float prevRightWristY;
	static float prevRightWristZ;
	static float prevRightElbowX;
	static float prevRightElbowY;
	static float prevRightElbowZ;
	static float prevRightShoulderX;
	static float prevRightShoulderY;
	static float prevRightShoulderZ;
	static float dampenedLeftWristX;
	static float dampenedLeftWristY;
	static float dampenedLeftWristZ;
	static float dampenedLeftElbowX;
	static float dampenedLeftElbowY;
	static float dampenedLeftElbowZ;
	static float dampenedLeftShoulderX;
	static float dampenedLeftShoulderY;
	static float dampenedLeftShoulderZ;
	static float dampenedRightWristX;
	static float dampenedRightWristY;
	static float dampenedRightWristZ;
	static float dampenedRightElbowX;
	static float dampenedRightElbowY;
	static float dampenedRightElbowZ;
	static float dampenedRightShoulderX;
	static float dampenedRightShoulderY;
	static float dampenedRightShoulderZ;
	static float testJoint;
	static float[] jointsX = {prevLeftShoulderX, prevLeftElbowX, prevLeftWristX, prevRightShoulderX, prevRightElbowX, prevRightWristX};
	static float[] jointsY = {prevLeftShoulderY, prevLeftElbowY, prevLeftWristY, prevRightShoulderY, prevRightElbowY, prevRightWristY};
	static float[] jointsZ = {prevLeftShoulderZ, prevLeftElbowZ, prevLeftWristZ, prevRightShoulderZ, prevRightElbowZ, prevRightWristZ};
	static float[] dampenedJointsX = {dampenedLeftShoulderX, dampenedLeftElbowX, dampenedLeftWristX, dampenedRightShoulderX, dampenedRightElbowX, dampenedRightWristX};
	static float[] dampenedJointsY = {dampenedLeftShoulderY, dampenedLeftElbowY, dampenedLeftWristY, dampenedRightShoulderY, dampenedRightElbowY, dampenedRightWristY};
	static float[] dampenedJointsZ = {dampenedLeftShoulderZ, dampenedLeftElbowZ, dampenedLeftWristZ, dampenedRightShoulderZ, dampenedRightElbowZ, dampenedRightWristZ};
	
	
	
	public static void main(String[] args) {
		
		System.out.println("Start");
	
		
	
		jointPositionsServer kinect=new jointPositionsServer();
	
		kinect.start(J4KSDK.SKELETON);
		
		
		try {
			ServerSocket serversocket = new ServerSocket(1345);
			
			Socket clientsocket = serversocket.accept();
			System.out.println("We got a client.");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
			
			PrintWriter out = new PrintWriter(clientsocket.getOutputStream(), true);
			while(!clientsocket.isClosed()) {
				out.println("{");
				out.println("\"Left Wrist\": \"[" + dampenedJointsX[2] + "," + dampenedJointsY[2] + "," + dampenedJointsZ[2] + "]\"");
				out.println("\"Left Elbow\": \"[" + dampenedJointsX[1] + "," + dampenedJointsY[1]+ "," + dampenedJointsZ[1] + "]\"");
				out.println("\"Left Shoulder\": \"[" + dampenedJointsX[0] + "," + dampenedJointsY[0] + "," + dampenedJointsZ[0] + "]\"");
				out.println("\"Right Wrist\": \"[" + dampenedJointsX[5] + "," + dampenedJointsY[5] + "," + dampenedJointsZ[5] + "]\"");
				out.println("\"Right Elbow\": \"[" + dampenedJointsX[4] + "," + dampenedJointsY[4] + "," + dampenedJointsZ[4] + "]\"");
				out.println("\"Right Shoulder\": \"[" + dampenedJointsX[3] + "," + dampenedJointsY[3] + "," + dampenedJointsZ[3] + "]\"");
				out.println("}");

				
			try {
				Thread.sleep(26);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			}
			
		} catch (IOException e) {
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
			    	
			    	if(mySkeleton[j].get3DJointZ(1) > 0.00001 ) {
			    		i = j;
			    	}
			    }
		    }
		    
	 	mySkeleton[i]=Skeleton.getSkeleton(i, skeleton_tracked, positions, orientations, joint_status, this);  
	 	
	 	for(int a = 4, b = 0; a <= 10 && b < 6; a++, b++) {
	 		if(a == 7) 
	 			a = 8;
	 		
	 	dampenedPosition(mySkeleton[i].get3DJointX(a), mySkeleton[i].get3DJointY(a), mySkeleton[i].get3DJointZ(a));
	 	

	 		jointsX[b] = mySkeleton[i].get3DJointX(a);
	 		jointsY[b] = mySkeleton[i].get3DJointY(a);
	 		jointsZ[b] = mySkeleton[i].get3DJointZ(a);

	 	}
	 	
		
	}

	@Override
	public void onColorFrameEvent(byte[] arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDepthFrameEvent(short[] arg0, byte[] arg1, float[] arg2, float[] arg3) {
		// TODO Auto-generated method stub
		
	}

	public void dampenedPosition(float jointPositionX, float jointPositionY, float jointPositionZ) {
		c ++;
		if(c == 6)
			c = 0;
		 
	dampenedJointsX[c] = (float) ((0.8*jointsX[c]) + (0.2*jointPositionX));
	dampenedJointsY[c] = (float) ((0.8*jointsY[c]) + (0.2*jointPositionY));
	dampenedJointsZ[c] = (float) ((0.8*jointsZ[c]) + (0.2*jointPositionZ));


	}
	

}
