package server;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame {

	JFrame f;
	JButton start;
	JButton stop; 
	static JLabel status;
	public Frame()
	{
		f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Server");
		f.setSize(250, 100);
		f.setResizable(false);
		f.setLayout(new FlowLayout());
		f.setVisible(true);
		
		status = new JLabel("Status: ");
		start = new JButton("Start Server");
		start.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Start");
				jointPositionsServer.startServer();
			}
			
		});
		stop = new JButton("Stop Server");
		stop.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Stop");
				jointPositionsServer.stopServer();
			}
			
		});
		//status.setLocation(f.WIDTH/2, f.HEIGHT-10);
		f.add(start);
		f.add(stop);
		f.add(status);
		
	}
	public static void setStatus(String str)
	{
		status.setText(str);
	}
}
	
	