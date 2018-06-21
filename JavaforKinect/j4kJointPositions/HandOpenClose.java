package j4kJointPositions;

import edu.ufl.digitalworlds.j4k.*;
import java.math.*;



public class HandOpenClose extends J4KSDK{
	
	boolean handOpen;
	int i = 0;
	double wristToHandTipRightY;
	double wristToHandTipLeft;
	int counter = 0;
	
	 
	
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
    	
    	double LeftDistance = Math.sqrt(Math.pow((HandtipLeftX - HandLeftX),2) + Math.pow((HandtipLeftY - HandLeftY),2) + Math.pow(HandtipLeftZ - HandLeftZ, 2));
     	double RightDistance = Math.sqrt(Math.pow((HandtipRightX - HandRightX),2) + Math.pow((HandtipRightY - HandRightY),2) + Math.pow(HandtipRightZ - HandRightZ, 2));
     	
    	
     	System.out.println(RightDistance);
    	
    	/*if(LeftDistance < 0.065) {
    		System.out.println("L-Closed");
    	}
    	else {
    		System.out.println("L-Open");
    	}
    	
    	if(RightDistance < 0.065) {
    		System.out.println("R-Closed");
    	}
    	else {
    		System.out.println("R-Open");
    	}
    	*/
   
    	
	}

	@Override
	public void onColorFrameEvent(byte[] color_frame) {
		
		
	
	}

	@Override
	public void onDepthFrameEvent(short[] depth_frame, byte[] body_index, float[] xyz, float[] uv) {
	}
	

	
	
	public static void main(String[] args) {
		
		HandOpenClose kinect=new HandOpenClose();
		
		kinect.start(J4KSDK.SKELETON);
		
		try {
			Thread.sleep(50000);
		}
		catch (InterruptedException e) {
		}
		
		kinect.stop();		
		
		
	}

}
