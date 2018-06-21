package j4kJointPositions;

import edu.ufl.digitalworlds.j4k.*;


public class JointPositions extends J4KSDK{
	
		int counter = 0;
		boolean handOpen;
		int i = 1;
		
	
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
	        
				
	        
		
	}

	@Override
	public void onColorFrameEvent(byte[] color_frame) {
	}

	@Override
	public void onDepthFrameEvent(short[] depth_frame, byte[] body_index, float[] xyz, float[] uv) {
	}
	
	public String dampeningEffect(float oldX, float newX) {
		
		double dampeningEffect = (0.8 * oldX) + (0.2 * newX);
		return "hi";
	}
	
	
	
	public static void main(String[] args) {
		
		JointPositions kinect=new JointPositions();
		
		
		kinect.start(J4KSDK.SKELETON);
		
		try {
			Thread.sleep(5000);
		}
		catch (InterruptedException e) {
		}
		
		kinect.stop();		
		
		
	}

}
