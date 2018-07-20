package server;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;


public class Frame extends JFrame
{
	
	JFrame frame;
	public static JTextArea l;
	
	public Frame(String title)
	{
		frame = new JFrame(title);
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setResizable(false);
		frame.setLayout(new FlowLayout());
		frame.setVisible(true);
		
		l = new JTextArea("");
		l.setEditable(false);
		frame.add(l);
		
		
		
	}
	

}
