# KinectforOculus

README for Kinect Server Download Process

In the zip folder you'll find a few files. The run.jar file and the 2 .dll files. Ultimately the run.jar file
is what you will run in order to start the server and the application, but first a few things need to happen before
the program runs fluidly.

1. Download the Kinect Runtime Environment 2.0 from Microsoft.

	Link here: https://www.microsoft.com/en-us/download/details.aspx?id=44559

2. Unzip the folder

3. Start the 'run.jar' file.
	
	A window should open that states "Server Started" which means the program is running properly.

4. Now you can access the data from the kinect server if you access the port 1234 via a software like PuTTY with
the IP address of 127.0.0.1(your own computer)

	If you connect to the server properly the window should state We Got a Client: #___

