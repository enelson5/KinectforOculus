package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import edu.ufl.digitalworlds.j4k.J4KSDK;
import edu.ufl.digitalworlds.j4k.Skeleton;
import java.io.FileWriter;
import java.io.IOException;
 

public class JointsThreaded extends J4KSDK implements Runnable{
	
	
	Socket clientSocket;
	int counter = 0;
	int i = 0;
	static String dampenedJointPosition;
	static int c = 0;
	static int a = 0;
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
	
	
	public JointsThreaded(Socket _clientSocket)
	{
		clientSocket = _clientSocket;
	}

	
	public void run()
	{
		
		JointsThreaded kinect = this;
		
		try {
			kinect.start(J4KSDK.SKELETON);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			while(!clientSocket.isClosed()) {

				if (MultiThreadedHandler.numOfLines >= 15)
				{
					
					MultiThreadedHandler.numOfLines = 0;
					Frame.l.setText("");
				}
				if (dampenedJointsX[2] != 0)
				{
					if(a == 0) {
						String cur = Frame.l.getText();
						Frame.l.setText(cur + "\nKinect Data has been recieved");
						MultiThreadedHandler.numOfLines++;
						a++;
					}
				}
					
				out.println("{");
				out.println("\"Left Wrist\": \"[" + dampenedJointsX[2] + "," + dampenedJointsY[2] + "," + dampenedJointsZ[2] + "]\"");
				out.println("\"Left Elbow\": \"[" + dampenedJointsX[1] + "," + dampenedJointsY[1]+ "," + dampenedJointsZ[1] + "]\"");
				out.println("\"Left Shoulder\": \"[" + dampenedJointsX[0] + "," + dampenedJointsY[0] + "," + dampenedJointsZ[0] + "]\"");
				out.println("\"Right Wrist\": \"[" + dampenedJointsX[5] + "," + dampenedJointsY[5] + "," + dampenedJointsZ[5] + "]\"");
				out.println("\"Right Elbow\": \"[" + dampenedJointsX[4] + "," + dampenedJointsY[4] + "," + dampenedJointsZ[4] + "]\"");
				out.println("\"Right Shoulder\": \"[" + dampenedJointsX[3] + "," + dampenedJointsY[3] + "," + dampenedJointsZ[3] + "]\"");
				out.println("}");

				
			try {
				Thread.sleep(32);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		kinect.stop();
		
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
		 
	dampenedJointsX[c] = (float) ((jointsX[c] + jointPositionX)/2);
	dampenedJointsY[c] = (float) ((jointsY[c] + jointPositionY)/2);
	dampenedJointsZ[c] = (float) ((jointsZ[c] + jointPositionZ)/2);


	}



}
