
import java.io.*;
import java.net.*;

public class Server
{
	public static void main(String[] args) throws IOException 
	{	   
		ServerSocket serversocket = new ServerSocket(2222);
		// Created server socket at port number 2222
		System.out.println("Server is running");
		Socket socket = serversocket.accept();
		// Accepting client request
		System.out.println("Client Connected");
		String filepath = ("D:\\Santa Clara University\\COEN233\\Programming Assignment\\Client_Server\\Server.txt");
		try
		{       
			//Read the file and write on the socket
			//Create a file if it doesn't exists
			FileOutputStream fileoutputstream = new FileOutputStream(filepath);
			BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String h=bufferedreader.readLine();
			int f = Integer.parseInt(h)/2;
			String g=bufferedreader.readLine();
			int count=Integer.parseInt(g);
         
			//It gets total bytes present in a file
			String bytesinfile=bufferedreader.readLine();
			int temp=Integer.parseInt(bytesinfile);
			byte[] serverarray = new byte[temp];
			System.out.println("File will be divided into "+temp/5+ " parts");
         
			for(int i=0;i<f;i++)
			{  
				String finaloutput = bufferedreader.readLine();
				serverarray=finaloutput.getBytes();
         
				System.out.println("10 bytes of data will be divided into two 5 bytes files");
				System.out.println("First 5 bytes of data is sending ");
				for(int q=0;q<5;q++)
				{
					fileoutputstream.write(serverarray[q]);
				}
				System.out.println("Second 5 bytes of data is sending");
				for(int q=5;q<10;q++)
				{ 
					fileoutputstream.write(serverarray[q]);
				} 
           
				if(i==(f))
				{        		 
					System.out.println("Inside f-1 loop");
					String finaloutput2 = bufferedreader.readLine();
					serverarray=finaloutput2.getBytes();
					System.out.println("Sending the remaining bytes of data ");
					if(count<5)
					{
						for(int q=0;q<count;q++)
							fileoutputstream.write(serverarray[q]);
					}
					else
						for(int q=5;q<count;q++)
						{
							for(int t=0;t<5;t++)
								fileoutputstream.write(serverarray[t]);	
							fileoutputstream.write(serverarray[q]);
						}
				}
			}  
         
			System.out.println("Closing the server connection ");
         
      
			fileoutputstream.close();
		}
	   
		catch(Exception ex)
		{}   
		serversocket.close();
	}
}
